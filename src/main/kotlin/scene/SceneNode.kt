package scene

import glmatrix.Mat4

interface SceneNode {
    var absoluteCoordinate: Coordinate
    var model: Mat4
    var rotationSpeedX: Double
    var rotationSpeedY: Double
    var rotationSpeedZ: Double
    var rotationAngleX: Double
    var rotationAngleY: Double
    var rotationAngleZ: Double
    var center: Coordinate
    var speedX: Double
    var speedY: Double
    var speedZ: Double

    fun isHit(): Boolean
    fun setHit(hit: Boolean)
}
