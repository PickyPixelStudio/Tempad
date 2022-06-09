#version 110


attribute vec3 Position;
attribute vec4 Color;
attribute vec2 UV0, UV2;

varying vec4 color;
varying vec2 texCoord, lightmap;

uniform mat4 ModelViewMat, ProjMat;

void main() {
    color = Color;
    texCoord = UV0;
    lightmap = UV2;
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1);
}