package spr5.scene

class SceneNodesAttached : SceneNode {

    val children = mutableListOf<SceneNode>()

    fun addChild(child: SceneNode) {
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