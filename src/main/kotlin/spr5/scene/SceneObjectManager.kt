package spr5.scene

class SceneObjectManager {
    private var _objects: MutableList<SceneObject> = mutableListOf()

    val objects: List<SceneObject>
        get() {
            return objects.toList();
        }

    fun add(o: SceneObject) {
        _objects.add(o);
    }

    fun remove(o: SceneObject) {
        _objects.remove(o);
    }
}