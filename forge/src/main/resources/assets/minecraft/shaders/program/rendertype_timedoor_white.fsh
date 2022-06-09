#version 110
varying vec4 color;
varying vec2 texCoord;

float easeOut(float x) {
    float x2 = x * x;
    float x4 = x2 * x2;
    float x8 = x4 * x4;
    float x16 = x8 * x8;
    float x48 = x16 * x16 * x16;
    return clamp(x16 * x4 * x, 0.0, 1.0);
}

void main() {
    vec3 center = color.rgb * .03;
    vec3 edge = color.rgb * .3;
    float x = abs(texCoord.s - 0.5) * 2.0;
    float y = abs(texCoord.t - 0.5) * 2.0;
    vec3 xVec = mix(center, edge, easeOut(x));
    vec3 yVec = mix(center, edge, easeOut(y));
    gl_FragColor = vec4(xVec + yVec, easeOut(x) + easeOut(y) + .2);

    //fragColor = vec4(1);
}