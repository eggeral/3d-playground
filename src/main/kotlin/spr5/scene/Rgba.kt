package spr5.scene;

import spr5.util.assert;

class Rgba(val red: Float = 0.0f,
           val green: Float = 0.0f,
           val blue: Float = 0.0f,
           val alpha: Float = 1.0f) {

    companion object {
        val Red: Rgba = Rgba(1.0f, 0.0f, 0.0f);
        val Green: Rgba = Rgba(0.0f, 1.0f, 0.0f);
        val Blue: Rgba = Rgba(0.0f, 0.0f, 1.0f);
        val White: Rgba = Rgba(1.0f, 1.0f, 1.0f);
        val Black: Rgba = Rgba(0.0f, 0.0f, 0.0f);
    }

    init {
        assert(red in 0.0f..1.0f, "Out of bounds");
        assert(green in 0.0f..1.0f, "Out of bounds");
        assert(blue in 0.0f..1.0f, "Out of bounds");
        assert(alpha in 0.0f..1.0f, "Out of bounds");
    }
}