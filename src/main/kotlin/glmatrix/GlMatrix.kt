package glmatrix

import org.khronos.webgl.Float32Array
import kotlin.js.Math

open class GlMatrix {
    companion object {
        /**
         * Common utilities
         * @module GlMatrix
         */
        // Configuration Constants
        var ARRAY_TYPE = Any()
        val EPSILON = 0.000001
        val RANDOM = Math.random()
        /**
         * Sets the type of array used when creating new vectors and matrices
         *
         * @param {Type} type Array type, such as Float32Array or Array
         */
        fun setMatrixArrayType(type: Float32Array) {
            ARRAY_TYPE = arrayOf(type)
        }

        val degree = Math.PI / 180
        /**
         * Convert Degree To Radian
         *
         * @param {Number} a Angle in Degrees
         */
        fun toRadian(a: Double): Double {
            return a * degree
        }

        fun toRadian(a: Float): Double {
            return a * degree
        }


        fun toRad(a: Double): Double {
            return a * (Math.PI / 180.0)
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

        /**
         * Tests whether or not the arguments have approximately the same value, within an absolute
         * or relative tolerance of GlMatrix.EPSILON (an absolute tolerance is used for values less
         * than or equal to 1.0, and a relative tolerance is used for larger values)
         *
         * @param {Number} a The first number to test.
         * @param {Number} b The second number to test.
         * @returns {Boolean} True if the numbers are approximately equal, false otherwise.
         */
        fun equals(a: Double, b: Double): Boolean {
            return Math.abs(a - b) <= EPSILON * Math.max(1.0, Math.abs(a), Math.abs(b))
        }

        fun equals(a: Float, b: Float): Boolean {
            val aDouble: Double = a.toDouble()
            val bDouble: Double = b.toDouble()
            return Math.abs(aDouble - bDouble) <= EPSILON * Math.max(1.0, Math.abs(aDouble), Math.abs(bDouble))
        }
    }
}
