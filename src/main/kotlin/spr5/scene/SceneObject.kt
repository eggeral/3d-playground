package spr5.scene

import spr5.util.Raycaster

interface SceneObject
{
    fun getVertices(): Array<Float>
    fun getColors(): Array<Float>
    fun getIndices(): Array<Short>

//    fun raycast(raycaster: Raycaster): Array<Float>
}