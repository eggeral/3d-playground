package spr5.scene;

import spr5.util.assert;

class Rgba(val red: Float = 0.0f,
           val green: Float = 0.0f,
           val blue: Float = 0.0f,
           val alpha: Float = 0.0f) {
    init {
        assert(red in 0.0f..1.0f, "Out of bounds");
        assert(green in 0.0f..1.0f, "Out of bounds");
        assert(blue in 0.0f..1.0f, "Out of bounds");
        assert(alpha in 0.0f..1.0f, "Out of bounds");
    }
}