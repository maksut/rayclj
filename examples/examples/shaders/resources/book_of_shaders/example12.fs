uniform vec2 u_resolution;

void main(){
    vec2 st = gl_FragCoord.xy/u_resolution.xy;
    vec3 color = vec3(0.0);

    vec2 bl = step(0.1, st); // bottom-left
    vec2 tr = step(0.1, 1.0 - st); // top-right

    // vec2 bl = smoothstep(0.1, 0.2, st); // bottom-left
    // vec2 tr = smoothstep(0.1, 0.2, 1.0 - st); // top-right

    // vec2 bl = floor(0.9 + st); // bottom-left
    // vec2 tr = floor(1.9 - st); // top-right

    // The multiplication will be similar to the logical AND.
    color = vec3( bl.x * bl.y * tr.x * tr.y );

    gl_FragColor = vec4(color,1.0);
}
