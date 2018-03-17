// Ported from Three.js r91
// https://github.com/mrdoob/three.js/blob/r91/src/core/Raycaster.js

package spr5.util

import spr5.matrix.Mat4
import spr5.matrix.Vec2
import spr5.matrix.Vec3
import spr5.scene.SceneObject

class Raycaster(val origin: Vec3, direction: Vec3, var near: Float = 0.0f, var far: Float = 0.0f) {
    val ray: Ray = Ray(origin, direction);
    // direction is assumed to be normalized (for accurate distance calculations)

    /**
     * Updates the ray with a new origin and direction. Works with perspective cameras only.
     *
     * @param {Vec2} 2D coordinates of the mouse, in normalized device coordinates (NDC)---X and Y components should
     *               be between -1 and 1.
     * @param {Mat4} Camera's model matrix
     * @param {Mat4} Camera's projection matrix
     */
    fun setFromCamera(coords: Vec2, modelMatrix: Mat4, projectionMatrix: Mat4) {
        this.ray.origin.setFromMatrixPosition(modelMatrix);

        this.ray.direction
                .set(coords.x, coords.y, 0.5)
                .unproject(modelMatrix, projectionMatrix)
                .subtract(this.ray.origin)
                .normalize();
    }

    /**
     * Checks all intersection between the ray and the objects with or without the descendants. Intersections are
     * returned sorted by distance, closest first. Intersections are of the same form as those returned
     * by .intersectObject.
     */
    fun intersectObjects(objects: List<SceneObject>) {
//        objects.forEach { v -> v.raycast(this) }
    }

    override fun toString(): String {
        return "Raycaster [ray: ${ray.toString()}, near: ${near}, far: ${far}]";
    }
}