package spr5.scene

class SceneNodesAttached : SceneNode {

    val children = mutableListOf<SceneNode>()

    fun addChild(child: SceneNode) {
        children.add(child)
    }

}