package scene

import glmatrix.Mat4

interface SceneNode {
    var position: Coordinate
    var model: Mat4
    var rotationSpeedX: Double
    var rotationSpeedY: Double
    var rotationSpeedZ: Double

    fun isHit(): Boolean
    fun setHit(hit: Boolean)
}