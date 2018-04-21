package scene

interface SceneRenderer {
    fun add(sceneNode: SceneNode)
    fun remove(sceneNode: SceneNode)

    fun rotateModel(rotateRad: Double);
    fun rotateModel(rotateXRad: Double, rotateYRad: Double, rotateZRad: Double);
}
