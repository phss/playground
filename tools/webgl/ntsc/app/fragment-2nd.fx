precision mediump float;

uniform sampler2D u_image;
varying vec2 v_texCoord;

#define NTSC_CRT_GAMMA 2.5
#define NTSC_MONITOR_GAMMA 2.0

float luma_filter1 = -0.000012020;
float luma_filter2 = -0.000022146;
float luma_filter3 = -0.000013155;
float luma_filter4 = -0.000012020;
float luma_filter5 = -0.000049979;
float luma_filter6 = -0.000113940;
float luma_filter7 = -0.000122150;
float luma_filter8 = -0.000005612;
float luma_filter9 = 0.000170516;
float luma_filter10 = 0.000237199;
float luma_filter11 = 0.000169640;
float luma_filter12 = 0.000285688;
float luma_filter13 = 0.000984574;
float luma_filter14 = 0.002018683;
float luma_filter15 = 0.002002275;
float luma_filter16 = -0.000909882;
float luma_filter17 = -0.007049081;
float luma_filter18 = -0.013222860;
float luma_filter19 = -0.012606931;
float luma_filter20 = 0.002460860;
float luma_filter21 = 0.035868225;
float luma_filter22 = 0.084016453;
float luma_filter23 = 0.135563500;
float luma_filter24 = 0.175261268;
float luma_filter25 = 0.190176552;

float chroma_filter1 = -0.000118847;
float chroma_filter2 = -0.000271306;
float chroma_filter3 = -0.000502642;
float chroma_filter4 = -0.000930833;
float chroma_filter5 = -0.001451013;
float chroma_filter6 = -0.002064744;
float chroma_filter7 = -0.002700432;
float chroma_filter8 = -0.003241276;
float chroma_filter9 = -0.003524948;
float chroma_filter10 = -0.003350284;
float chroma_filter11 = -0.002491729;
float chroma_filter12 = -0.000721149;
float chroma_filter13 = 0.002164659;
float chroma_filter14 = 0.006313635;
float chroma_filter15 = 0.011789103;
float chroma_filter16 = 0.018545660;
float chroma_filter17 = 0.026414396;
float chroma_filter18 = 0.035100710;
float chroma_filter19 = 0.044196567;
float chroma_filter20 = 0.053207202;
float chroma_filter21 = 0.061590275;
float chroma_filter22 = 0.068803602;
float chroma_filter23 = 0.074356193;
float chroma_filter24 = 0.077856564;
float chroma_filter25 = 0.079052396;


const mat3 yiq2rgb_mat = mat3(
   1.0, 0.956, 0.6210,
   1.0, -0.2720, -0.6474,
   1.0, -1.1060, 1.7046);

vec3 yiq2rgb(vec3 yiq)
{
   return yiq * yiq2rgb_mat;
}


// To be defined somewhere else?
const int FrameCount = 1;
const float scaleX = 1.0 * 3.0;
const vec2 TextureSize = vec2(256.0 * scaleX, 240.0);
// const vec2 TextureSize = vec2(512.0 * scaleX, 512.0);
const vec2 InputSize = vec2(256.0 * scaleX, 240.0);
// const vec2 InputSize = vec2(512.0 * scaleX, 512.0);
const vec2 OutputSize = vec2(256.0 * scaleX, 240.0);
// const vec2 OutputSize = vec2(512.0 * scaleX, 512.0);

#define fetch_offset(offset, one_x) texture2D(u_image, v_texCoord + vec2((offset) * (one_x), 0.0)).xyz

void main() {
   float one_x = 1.0 / TextureSize.x;
	vec3 signal = vec3(0.0);

   #define do_thing(offset, luma_filter, chroma_filter) signal += (fetch_offset(offset - 24., one_x) + fetch_offset(24. - offset, one_x)) * vec3(luma_filter, chroma_filter, chroma_filter);
	// unrolling the loopz
   do_thing(0., luma_filter1, chroma_filter1)
   do_thing(1., luma_filter2, chroma_filter2)
   do_thing(2., luma_filter3, chroma_filter3)
   do_thing(3., luma_filter4, chroma_filter4)
   do_thing(4., luma_filter5, chroma_filter5)
   do_thing(5., luma_filter6, chroma_filter6)
   do_thing(6., luma_filter7, chroma_filter7)
   do_thing(7., luma_filter8, chroma_filter8)
   do_thing(8., luma_filter9, chroma_filter9)
   do_thing(9., luma_filter10, chroma_filter10)
   do_thing(10., luma_filter11, chroma_filter11)
   do_thing(11., luma_filter12, chroma_filter12)
   do_thing(12., luma_filter13, chroma_filter13)
   do_thing(13., luma_filter14, chroma_filter14)
   do_thing(14., luma_filter15, chroma_filter15)
   do_thing(15., luma_filter16, chroma_filter16)
   do_thing(16., luma_filter17, chroma_filter17)
   do_thing(17., luma_filter18, chroma_filter18)
   do_thing(18., luma_filter19, chroma_filter19)
   do_thing(19., luma_filter20, chroma_filter20)
   do_thing(20., luma_filter21, chroma_filter21)
   do_thing(21., luma_filter22, chroma_filter22)
   do_thing(22., luma_filter23, chroma_filter23)
   do_thing(23., luma_filter24, chroma_filter24)

	signal += texture2D(u_image, v_texCoord).xyz * vec3(luma_filter25, chroma_filter25, chroma_filter25);

	vec3 rgb = yiq2rgb(signal);
	gl_FragColor = vec4(pow(rgb, vec3(NTSC_CRT_GAMMA / NTSC_MONITOR_GAMMA)), 1.0);
}