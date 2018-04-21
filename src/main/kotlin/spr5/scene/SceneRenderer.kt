package spr5.scene

import spr5.matrix.Mat4
import spr5.util.Ray

interface SceneRenderer {
    fun add(sceneObject: SceneObject);
    fun remove(sceneObject: SceneObject);

    fun rotateModel(rotateRad: Double);
    fun rotateModel(rotateXRad: Double, rotateYRad: Double, rotateZRad: Double);
}