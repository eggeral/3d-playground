package scene

data class Coordinate(val x: Float = 0.0f, val y: Float = 0.0f, val z: Float = 0.0f)

operator fun Coordinate.plus(rhs: Coordinate): Coordinate {
    return Coordinate(this.x + rhs.x, this.y + rhs.y, this.z + rhs.z)
}

operator fun Coordinate.plus(rhs: Float): Coordinate {
    return Coordinate(x + rhs, y + rhs, z + rhs)
}