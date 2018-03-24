package spr5.scene

import spr5.matrix.Vec3
import spr5.util.Ray
import spr5.util.Triangle

interface SceneObject
{
    fun getVertices(): Array<Float>
    fun getColors(): Array<Float>
    fun getIndices(): Array<Short>

    fun getMesh(): Array<Triangle>;
    fun getNormals(): Array<Vec3>;
}