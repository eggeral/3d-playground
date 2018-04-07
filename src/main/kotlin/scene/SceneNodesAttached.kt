package scene

import glmatrix.Mat4

class SceneNodesAttached : SceneNode {

    override var model: Mat4 = Mat4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
    override var rotationSpeedX: Double = 0.0
    override var rotationSpeedY: Double = 0.0
    override var rotationSpeedZ: Double = 0.0
    val children = mutableListOf<SceneNode>()

    fun addChild(child: SceneNode) {
        child.model = Mat4().translate(arrayOf(-2.0, 1.0, 0.0))
        children.add(child)
    }


    override var position: Coordinate = Coordinate(0.0f, 0.0f, 0.0f)
        set(value) {

            val oldValue = field
            field = value

            val xDiff = oldValue.x - value.x
            val yDiff = oldValue.y - value.y
            val zDiff = oldValue.z - value.z

            for (child in children) {

                val newPosition = Coordinate(
                        child.position.x - xDiff,
                        child.position.y - yDiff,
                        child.position.z - zDiff
                )
                child.position = newPosition
            }
        }

}