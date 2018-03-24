package spr5

import spr5.scene.*

class SceneObjectsAttached : SceneObject
{
    override fun getVertices(): Array<Float> {return arrayOf(1.0f)}

    override fun getColors(): Array<Float> {return arrayOf(1.0f)}
    override fun getIndices(): Array<Short> {return arrayOf(1)}

    private var attachedObjects: List<SceneObjectMoveable> = listOf();
    private var baseCoordinate = Coordinate(0.0f,0.0f,0.0f);

    fun getAllAttachedObjects() : List<SceneObject> {
        return attachedObjects
    }

    fun moveAttachedObjects(attachedObjects: List<SceneObjectMoveable>, newCoordinate: Coordinate) {
        var delta = calculateDeltaCoordinate(baseCoordinate, newCoordinate)
        for (attachedObject in attachedObjects) {
           attachedObject.setCenter(newCoordinate);
        }
    }

    fun calculateDeltaCoordinate(oldPosition : Coordinate, newPosition : Coordinate) : Coordinate {
        return Coordinate(oldPosition.x - newPosition.x, oldPosition.y - newPosition.y, oldPosition.z - newPosition.z)
    }

    fun moveSingleObject(obj : SceneObjectMoveable, delta : Coordinate) : SceneObject {
        obj.setCenter(obj.getCenter() + delta);
        return obj
    }
}