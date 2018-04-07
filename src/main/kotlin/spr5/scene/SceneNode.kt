package spr5.scene

import spr5.matrix.Mat4

interface SceneNode {
    var position: Coordinate
    var model: Mat4
    var rotationSpeedX: Double
    var rotationSpeedY: Double
    var rotationSpeedZ: Double
}