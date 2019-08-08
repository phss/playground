precision mediump float;

uniform sampler2D u_image; // s_p
varying vec2 v_texCoord; // texCoord


// Horizontal cubic filter.

// Some known filters use these values:

//    B = 0.0, C = 0.0  =>  Hermite cubic filter.
//    B = 1.0, C = 0.0  =>  Cubic B-Spline filter.
//    B = 0.0, C = 0.5  =>  Catmull-Rom Spline filter. This is the default used in this shader.
//    B = C = 1.0/3.0   =>  Mitchell-Netravali cubic filter.
//    B = 0.3782, C = 0.3109  =>  Robidoux filter.
//    B = 0.2620, C = 0.3690  =>  Robidoux Sharp filter.
//    B = 0.36, C = 0.28  =>  My best config for ringing elimination in pixel art (Hyllian).


// For more info, see: http://www.imagemagick.org/Usage/img_diagrams/cubic_survey.gif

// Change these params to configure the horizontal filter.
const float  B =  0.0; 
const float  C =  0.5;  

const  mat4 invX = mat4(                          (-B - 6.0*C)/6.0,   (12.0 - 9.0*B - 6.0*C)/6.0,  -(12.0 - 9.0*B - 6.0*C)/6.0,   (B + 6.0*C)/6.0,
                                              (3.0*B + 12.0*C)/6.0, (-18.0 + 12.0*B + 6.0*C)/6.0, (18.0 - 15.0*B - 12.0*C)/6.0,                -C,
                                              (-3.0*B - 6.0*C)/6.0,                          0.0,          (3.0*B + 6.0*C)/6.0,               0.0,
                                                             B/6.0,            (6.0 - 2.0*B)/6.0,                        B/6.0,               0.0);



// Consts to parametrise
const float PHOSPHOR = 0.0;
const float VSCANLINES = 0.0;
const float InputGamma = 2.4;
const float OutputGamma = 2.2;
const float SHARPNESS = 1.00;
const float COLOR_BOOST = 1.5;
const float RED_BOOST = 1.0;
const float GREEN_BOOST = 1.0;
const float BLUE_BOOST = 1.0;
const float SCANLINES_STRENGTH = 0.72;
const float BEAM_MIN_WIDTH = 0.86;
const float BEAM_MAX_WIDTH = 1.5;
const float CRT_ANTI_RINGING = 0.8;

// Guesses
// vec2 TextureSize = vec2(256.0, 256.0);
vec2 TextureSize = vec2(512.0, 512.0);
// vec2 TextureSize = vec2(768.0, 720.0);
vec2 InputSize = vec2(512.0, 512.0);
vec2 OutputSize = vec2(1024.0, 1024.0);

vec4 GAMMA_IN(vec4 color) {
   return pow(color, vec4(InputGamma, InputGamma, InputGamma, InputGamma));
}

vec4 GAMMA_OUT(vec4 color) {
   return pow(color, vec4(1.0 / OutputGamma, 1.0 / OutputGamma, 1.0 / OutputGamma, 1.0 / OutputGamma));
}

void main() {
   vec2 texture_size = vec2(SHARPNESS*TextureSize.x, TextureSize.y);

   vec4 color;
   vec2 dx = vec2(1.0/texture_size.x, 0.0);
   vec2 dy = vec2(0.0, 1.0/texture_size.y);

   vec2 pix_coord = v_texCoord*texture_size;
   // vec2 pix_coord = v_texCoord*texture_size+vec2(-0.5,0.5);

   vec2 tc = floor(pix_coord)/texture_size;
   // vec2 tc = (floor(pix_coord)+vec2(0.5,0.5))/texture_size;

   vec2 fp = fract(pix_coord);
   // vec2 fp = fract(v_texCoord * texture_size + vec2(-0.5, 0.5));

   vec4 c00 = GAMMA_IN(texture2D(u_image, tc     - dx - dy).xyzw);
   vec4 c01 = GAMMA_IN(texture2D(u_image, tc          - dy).xyzw);
   vec4 c02 = GAMMA_IN(texture2D(u_image, tc     + dx - dy).xyzw);
   vec4 c03 = GAMMA_IN(texture2D(u_image, tc + 2.0*dx - dy).xyzw);
   vec4 c10 = GAMMA_IN(texture2D(u_image, tc     - dx).xyzw);
   vec4 c11 = GAMMA_IN(texture2D(u_image, tc         ).xyzw);
   vec4 c12 = GAMMA_IN(texture2D(u_image, tc     + dx).xyzw);
   vec4 c13 = GAMMA_IN(texture2D(u_image, tc + 2.0*dx).xyzw);

   //  Get min/max samples
   vec4 min_sample = min(min(c01,c11), min(c02,c12));
   vec4 max_sample = max(max(c01,c11), max(c02,c12));

   mat4 color_matrix0 = mat4(c00, c01, c02, c03);
   mat4 color_matrix1 = mat4(c10, c11, c12, c13);

   vec4 lobes = vec4(fp.x*fp.x*fp.x, fp.x*fp.x, fp.x, 1.0);

   vec4 invX_Px  = invX * lobes;
   vec4 color0   = color_matrix0 * invX_Px;
   vec4 color1   = color_matrix1 * invX_Px;

   // Anti-ringing
   vec4 aux = color0;
   color0 = clamp(color0, min_sample, max_sample);
   color0 = mix(aux, color0, CRT_ANTI_RINGING);
   aux = color1;
   color1 = clamp(color1, min_sample, max_sample);
   color1 = mix(aux, color1, CRT_ANTI_RINGING);

   float pos0 = fp.y;
   float pos1 = 1.0 - fp.y;

   vec4 lum0 = mix(vec4(BEAM_MIN_WIDTH), vec4(BEAM_MAX_WIDTH), color0);
   vec4 lum1 = mix(vec4(BEAM_MIN_WIDTH), vec4(BEAM_MAX_WIDTH), color1);

   vec4 d0 = clamp(pos0/(lum0+0.0000001), 0.0, 1.0);
   vec4 d1 = clamp(pos1/(lum1+0.0000001), 0.0, 1.0);

   d0 = exp(-10.0*SCANLINES_STRENGTH*d0*d0);
   d1 = exp(-10.0*SCANLINES_STRENGTH*d1*d1);

   color = clamp(color0*d0+color1*d1, 0.0, 1.0); 

   color *= COLOR_BOOST*vec4(RED_BOOST, GREEN_BOOST, BLUE_BOOST, 1.0);

   float mod_factor = v_texCoord.x * OutputSize.x * TextureSize.x / InputSize.x;

   vec4 dotMaskWeights = mix(
                                vec4(1.0, 0.7, 1.0, 1.),
                                vec4(0.7, 1.0, 0.7, 1.),
                                floor(mod(mod_factor, 2.0))
                                 );

   color.rgba *= mix(vec4(1.0,1.0,1.0,1.0), dotMaskWeights, PHOSPHOR);

   color  = GAMMA_OUT(color);

   gl_FragColor = color;
}