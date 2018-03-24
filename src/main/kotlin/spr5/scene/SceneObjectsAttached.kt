package spr5.scene


class SceneObjectsAttached(var center: Coordinate) : SceneObject {


    override fun getVertices(): Array<Float> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getColors(): Array<Float> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getIndices(): Array<Short> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    //eventuell position in der liste von SceneObjects merken.


    private var attachedObjects: List<SceneObject> = listOf();
    private var coordinateNew = Coordinate(0.0f, 0.0f, 0.0f);

    fun getAllAttachedObjects(): List<SceneObject> {
        return attachedObjects;
    }

}

fun addToAttachedObjects(listOfObjectsAttached: List<SceneObject>, objectToAttach: SceneObject): List<SceneObject> {
    var resultList : List <SceneObject> = listOf()
    resultList = listOfObjectsAttached
    resultList += objectToAttach
    return resultList
}

fun removeObjectFromAttachedObjects(listOfObjectsAttached: List<SceneObject>, objectToRemove: SceneObject): List<SceneObject> {
    var resultList : List <SceneObject> = listOf()
    resultList = listOfObjectsAttached
    resultList -= objectToRemove
    return resultList
}

fun moveAttachedObjects(attachedObjects: List<SceneObject>, oldCoordinate: Coordinate, newCoordinate: Coordinate) {
    for (attachedObject in attachedObjects) {

    }
}


fun createSceneObjectsAttached(center: Coordinate) :SceneObject {
    return SceneObjectsAttached(center)
}