#define PI 3.14159265359

uniform vec2 u_resolution;
uniform vec2 u_mouse;
uniform float u_time;

float rand(in vec2 _st) {
    return fract(sin(dot(_st.xy,
                         vec2(12.9898,78.233)))*
        43758.5453123);
}

float rand(in float i) {
    return rand(vec2(i));
}

float noise(in float x) {
    float i = floor(x);  // integer
    float f = fract(x);  // fraction
    float y = rand(i);

    // y = mix(rand(i), rand(i + 1.0), f);
    // y = mix(rand(i), rand(i + 1.0), smoothstep(0.,1.,f));
    // y = mix(rand(i), rand(i + 1.0), smoothstep(0.,1.,f));

    // float u = f * f * (3.0 - 2.0 * f ); // custom cubic curve
    // y = mix(rand(i), rand(i + 1.0), u); // using it in the interpolation

    return y;
}

float plot(vec2 st, float pct){
  return  smoothstep( pct-0.02, pct, st.y) -
          smoothstep( pct, pct+0.02, st.y);
}

void main() {
    vec2 st = gl_FragCoord.xy/u_resolution;
    st.x *= 10.0;
    // st.x += u_time;

    float y = noise(st.x);
    vec3 color = vec3(y);

    float pct = plot(st,y);
    color = (1.0-pct)*color+pct*vec3(0.0,1.0,0.0);

    gl_FragColor = vec4(color,1.0);
}
