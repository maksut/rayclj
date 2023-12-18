uniform vec2 u_resolution;

vec3 rect(in vec2 pos, in vec2 size, in vec2 st)
{
  vec2 bl = step(pos, st);
  vec2 tr = step(1.0-(pos+size), 1.0-st);

  return vec3(bl.x * bl.y * tr.x * tr.y);
}

vec3 rect(in float pos_x, in float pos_y, in float width, in float height, in vec2 st)
{
  float bottom = step(pos_y, st.y);
  float left = step(pos_x, st.x);
  float top = step(1.0-(pos_y+height), 1.0-st.y);
  float right = step(1.0-(pos_x+width), 1.0-st.x);

  return vec3(bottom * left * top * right);
}

vec3 rect_outline(in vec2 pos, in vec2 size, in float border, in vec2 st)
{
  vec3 out_rect = rect(pos, size, st);
  vec3 in_rect = rect(pos+border, size-(2.0*border), st);

  return out_rect - in_rect;
}

vec3 rect_outline(in float pos_x, in float pos_y, in float width, in float height, in float border, in vec2 st)
{
  vec3 out_rect = rect(pos_x, pos_y, width, height, st);
  vec3 in_rect = rect(pos_x+border, pos_y+border, width-(2.0*border), height-(2.0*border), st);

  return out_rect - in_rect;
}

void main(){
    vec2 st = gl_FragCoord.xy/u_resolution.xy;
    //vec3 color = rect(vec2(0.1, 0.1), vec2(0.8, 0.8), st);
    vec3 color = vec3(1., 0., 0.) * rect(0.1, 0.1, 0.8, 0.8, st);
    //vec3 color = rect_outline(vec2(0.1, 0.1), vec2(0.8, 0.8), 0.01, st);
    //vec3 color = rect_outline(0.1, 0.1, 0.8, 0.8, 0.01, st);

    gl_FragColor = vec4(color,1.0);
}
