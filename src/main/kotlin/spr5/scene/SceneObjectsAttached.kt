package spr5.scene

class SceneObjectsAttached : SceneObject
{
    //eventuell position in der liste von SceneObjects merken.
    fun getVertices(): Array<Float>
    fun getColors(): Array<Float>
    fun getIndices(): Array<Short>

    private var attachedObjects: List<SceneObject> = listOf();
    private var coordinateNew = Coordinate(0.0f,0.0f,0.0f);

    fun getAllAttachedObjects() : List<SceneObject> {
        return attachedObjects;
    }

    fun moveAttachedObjects(attachedObjects: List<SceneObject>, oldCoordinate: Coordinate, newCoordinate: Coordinate) {
        for (attachedObject in attachedObjects) {
            var ao = attachedObject.getVertices();


        }
    }
}