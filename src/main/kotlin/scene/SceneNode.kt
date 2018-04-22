package scene

interface SceneNode {
    var rotationSpeedX: Double
    var rotationSpeedY: Double
    var rotationSpeedZ: Double
    var rotationAngleX: Double
    var rotationAngleY: Double
    var rotationAngleZ: Double
    var speedX: Double
    var speedY: Double
    var speedZ: Double
    var isChildOf: SceneNode?
    var center: Coordinate
    fun getCenter(): Coordinate
    fun setCenter(c: Coordinate)
    fun copyProperties(sceneNode: SceneNode)

    fun isHit(): Boolean
    fun setHit(hit: Boolean)
}
