package scene

interface SceneRenderer {
    fun add(sceneObject: SceneObject)
    fun remove(sceneObject: SceneObject)
    fun rotateModel(rotateRad: Double)
    fun rotateModel(rotateXRad: Double, rotateYRad: Double, rotateZRad: Double)
}