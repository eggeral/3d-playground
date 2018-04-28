package scene

import glmatrix.Mat4

class SceneNodesAttached : SceneNode {
    override var rotationSpeedX: Double = 0.0
    override var rotationSpeedY: Double = 0.0
    override var rotationSpeedZ: Double = 0.0
    override var rotationAngleX: Double = 0.0
    override var rotationAngleY: Double = 0.0
    override var rotationAngleZ: Double = 0.0
    override var speedX: Double = 0.0
    override var speedY: Double = 0.0
    override var speedZ: Double = 0.0
    override var center: Coordinate = Coordinate(0.0f,0.0f,0.0f)
    var children: List<SceneNode> = listOf()
    override var isChildOf: SceneNode? = null

    fun addChild(child: SceneNode) {
        if (child is SceneObject)
            child.model = Mat4().translate(arrayOf(0.0, 0.0, 0.0))
        child.isChildOf = this
        child.copyProperties(this)
        children += child
    }

    fun removeChild(child: SceneNode, resetDefault: Boolean) {
        if (resetDefault) {
            child.speedX = 0.0
            child.speedY = 0.0
            child.speedZ = 0.0
            child.rotationSpeedX = 0.0
            child.rotationSpeedY = 0.0
            child.rotationSpeedZ = 0.0
        }
        child.isChildOf = null
        children -= child
    }

    override fun isHit(): Boolean {
        return children.any { c -> c.isHit() }
    }

    override fun setHit(hit: Boolean) {
        children.forEach { c -> c.setHit(hit) }
    }

    override fun getCenter(): Coordinate {
        return center
    }

    override fun setCenter(c: Coordinate) {
        center = c
    }

    override fun copyProperties(sceneNode: SceneNode) {
        speedX = sceneNode.speedX
        speedY = sceneNode.speedY
        speedZ = sceneNode.speedZ
        rotationSpeedX = sceneNode.rotationSpeedX
        rotationSpeedY = sceneNode.rotationSpeedY
        rotationSpeedZ = sceneNode.rotationSpeedZ
    }
}
