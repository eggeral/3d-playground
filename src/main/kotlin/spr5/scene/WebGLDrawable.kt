package spr5.scene;

enum class Direction {
    Horizontal,
    Vertical,
    Sideways
};

interface WebGLDrawable : SceneObject {
    var color: Rgba
}