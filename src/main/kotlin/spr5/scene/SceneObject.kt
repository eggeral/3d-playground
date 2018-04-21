package spr5.scene

import spr5.matrix.Vec3
import spr5.util.Ray
import spr5.util.Triangle

interface SceneObject
{
    fun getVertices(): Array<Float>
    fun getColors(): Array<Float>
    fun setColors(color: Rgba)
    fun setColors(colors:Array<Float>)

    fun isHit(): Boolean
    fun setHit(hit: Boolean)
    fun getIndices(): Array<Short>

    fun getMesh(): Array<Triangle>;
    fun getNormals(): Array<Vec3>;

    fun intersect(ray: Ray): Triangle?;
}
