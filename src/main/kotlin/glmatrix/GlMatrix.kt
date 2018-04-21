package glmatrix

import kotlin.js.Math

open class GlMatrix {
    companion object {
        /**
         * Common utilities
         * @module GlMatrix
         */
        // Configuration Constants
        val EPSILON = 0.000001
        val RANDOM = Math.random()
        private val degree = Math.PI / 180

        /**
         * Convert Degree To Radian
         *
         * @param {Double} angleInDegree Angle in Degrees
         */
        fun toRadian(angleInDegree: Double): Double {
            return angleInDegree * degree
        }

        /**
         * Convert Degree To Radian
         *
         * @param {Float} angleInDegree Angle in Degrees
         */
        fun toRadian(angleInDegree: Float): Double {
            return angleInDegree * degree
        }

        fun perspectiveProjectionMatrix(fieldOfViewInRadians: Float, aspectRatio: Float, near: Float, far: Float): Array<Float> {

            val f = (1.0 / Math.tan(fieldOfViewInRadians / 2.0)).toFloat()
            val rangeInv = 1 / (near - far)

            return arrayOf(
                    f / aspectRatio, 0.0f, 0.0f, 0.0f,
                    0.0f, f, 0.0f, 0.0f,
                    0.0f, 0.0f, (near + far) * rangeInv, -1.0f,
                    0.0f, 0.0f, near * far * rangeInv * 2, 0.0f
            )
        }

        fun orthographicProjectionMatrix(left: Float, right: Float, bottom: Float, top: Float, near: Float, far: Float): Array<Float> {
            return arrayOf(
                    2.0f / (right - left), 0.0f, 0.0f, 0.0f,
                    0.0f, 2.0f / (top - bottom), 0.0f, 0.0f,
                    0.0f, 0.0f, 2.0f / (near - far), 0.0f,
                    (left + right) / (left - right), (bottom + top) / (bottom - top), (near + far) / (near - far), 1.0f
            )
        }
    }
}
