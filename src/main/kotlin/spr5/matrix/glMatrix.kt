package spr5.matrix

import org.khronos.webgl.Float32Array
import org.khronos.webgl.set
import kotlin.js.Math

open class glMatrix {
    companion object {
        /**
         * Common utilities
         * @module glMatrix
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

        /**
         * Tests whether or not the arguments have approximately the same value, within an absolute
         * or relative tolerance of glMatrix.EPSILON (an absolute tolerance is used for values less
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