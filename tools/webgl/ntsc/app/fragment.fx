#define THREE_PHASE
#define COMPOSITE

precision mediump float;

uniform sampler2D u_image;
varying vec2 v_texCoord;

#define PI 3.14159265

#if defined(TWO_PHASE)
	#define CHROMA_MOD_FREQ (4.0 * PI / 15.0)
#elif defined(THREE_PHASE)
	#define CHROMA_MOD_FREQ (PI / 3.0)
#endif

#if defined(COMPOSITE)
	#define SATURATION 1.0
	#define BRIGHTNESS 1.0
	#define ARTIFACTING 1.0
	#define FRINGING 1.0
#elif defined(SVIDEO)
	#define SATURATION 1.0
	#define BRIGHTNESS 1.0
	#define ARTIFACTING 0.0
	#define FRINGING 0.0
#endif

// To be defined somewhere else?
const int FrameCount = 1;
const vec2 TextureSize = vec2(512.0, 512.0);
const vec2 InputSize = vec2(512.0, 512.0);
const vec2 OutputSize = vec2(512.0, 512.0);

const mat3 yiq_mat = mat3(
      0.2989, 0.5870, 0.1140,
      0.5959, -0.2744, -0.3216,
      0.2115, -0.5229, 0.3114
);

const mat3 mix_mat = mat3(
	BRIGHTNESS, FRINGING, FRINGING,
	ARTIFACTING, 2.0 * SATURATION, 0.0,
	ARTIFACTING, 0.0, 2.0 * SATURATION
);

vec3 rgb2yiq(vec3 col) {
   return col * yiq_mat;
}

void main() {
   vec2 pix_no = v_texCoord * TextureSize * (OutputSize / InputSize); /// ???


   vec3 col = texture2D(u_image, v_texCoord).rgb;
   vec3 yiq = rgb2yiq(col);

	#if defined(TWO_PHASE)
		float chroma_phase = PI * (mod(pix_no.y, 2.0) + float(FrameCount));
	#elif defined(THREE_PHASE)
		float chroma_phase = 0.6667 * PI * (mod(pix_no.y, 3.0) + float(FrameCount));
	#endif

   float mod_phase = chroma_phase + pix_no.x * CHROMA_MOD_FREQ;

	float i_mod = cos(mod_phase);
	float q_mod = sin(mod_phase);

	yiq.yz *= vec2(i_mod, q_mod); // Modulate.
	yiq *= mix_mat; // Cross-talk.
	yiq.yz *= vec2(i_mod, q_mod); // Demodulate.

   gl_FragColor = vec4(yiq, 1.0);
}