package spr5.scene

interface SceneObjectMoveable : SceneObject {
    fun setCenter(newCenter : Coordinate)
    fun getCenter() : Coordinate
}