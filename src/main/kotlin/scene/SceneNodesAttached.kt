package scene

import glmatrix.Mat4

class SceneNodesAttached : SceneNode {

    override var model: Mat4 = Mat4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
    override var rotationSpeedX: Double = 0.0
    override var rotationSpeedY: Double = 0.0
    override var rotationSpeedZ: Double = 0.0
    override var rotationAngleX: Double = 0.0
    override var rotationAngleY: Double = 0.0
    override var rotationAngleZ: Double = 0.0
    override var speedX: Double = 0.0
    override var speedY: Double = 0.0
    override var speedZ: Double = 0.0
    override var center: Coordinate = Coordinate()
    val children = mutableListOf<SceneNode>()

    fun addChild(child: SceneNode) {
        child.model = Mat4().translate(arrayOf(0.0, 0.0, 0.0))
        children.add(child)
    }


    override var absoluteCoordinate: Coordinate = Coordinate(0.0f, 0.0f, 0.0f)
        set(value) {

            val oldValue = field
            field = value

            val xDiff = oldValue.x - value.x
            val yDiff = oldValue.y - value.y
            val zDiff = oldValue.z - value.z

            for (child in children) {

                val newPosition = Coordinate(
                        child.absoluteCoordinate.x - xDiff,
                        child.absoluteCoordinate.y - yDiff,
                        child.absoluteCoordinate.z - zDiff
                )
                child.absoluteCoordinate = newPosition
            }
        }

    override fun isHit(): Boolean {
        return children.any { c -> c.isHit() }
    }

    override fun setHit(hit: Boolean) {
        children.forEach { c -> c.setHit(hit) }
    }
}
