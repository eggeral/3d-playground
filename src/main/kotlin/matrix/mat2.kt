package matrix

import org.khronos.webgl.Float32Array
import org.khronos.webgl.get
import org.khronos.webgl.set
import kotlin.js.Math

class mat2 : glMatrix() {
    companion object {
        /**
         * 2x2 Matrix
         * @module mat2
         */
        /**
         * Creates a new identity mat2
         *
         * @returns {mat2} a new 2x2 matrix
         */
        fun create(): Float32Array {
            val out = Float32Array(4)
            out[0] = 1.0f
            out[1] = 0.0f
            out[2] = 0.0f
            out[3] = 1.0f
            return out
        }

        /**
         * Creates a new mat2 initialized with values from an existing matrix
         *
         * @param {mat2} a matrix to clone
         * @returns {mat2} a new 2x2 matrix
         */
        fun clone(a: Float32Array): Float32Array {
            val out = Float32Array(4)
            out[0] = a[0]
            out[1] = a[1]
            out[2] = a[2]
            out[3] = a[3]
            return out
        }

        fun clone(a: Array<Float>): Array<Float> {
            return arrayOf(a[0], a[1], a[2], a[3])
        }

        /**
         * Copy the values from one mat2 to another
         *
         * @param {mat2} out the receiving matrix
         * @param {mat2} a the source matrix
         * @returns {mat2} out
         */
        fun copy(inOut: Float32Array, a: Float32Array): Float32Array {
            inOut[0] = a[0]
            inOut[1] = a[1]
            inOut[2] = a[2]
            inOut[3] = a[3]
            return inOut
        }

        /**
         * Set a mat2 to the identity matrix
         *
         * @param {mat2} out the receiving matrix
         * @returns {mat2} out
         */
        fun identity(inOut: Float32Array): Float32Array {
            inOut[0] = 1.0f
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = 1.0f
            return inOut
        }

        /**
         * Create a new mat2 with the given values
         *
         * @param {Number} m00 Component in column 0, row 0 position (index 0)
         * @param {Number} m01 Component in column 0, row 1 position (index 1)
         * @param {Number} m10 Component in column 1, row 0 position (index 2)
         * @param {Number} m11 Component in column 1, row 1 position (index 3)
         * @returns {mat2} out A new 2x2 matrix
         */
        fun fromValues(m00: Float, m01: Float, m10: Float, m11: Float): Float32Array {
            val out = Float32Array(4)
            out[0] = m00
            out[1] = m01
            out[2] = m10
            out[3] = m11
            return out
        }
        fun fromValues(m00: Double, m01: Double, m10: Double, m11: Double): Float32Array {
            val out = Float32Array(4)
            out[0] = m00.toFloat()
            out[1] = m01.toFloat()
            out[2] = m10.toFloat()
            out[3] = m11.toFloat()
            return out
        }

        /**
         * Set the components of a mat2 to the given values
         *
         * @param {mat2} out the receiving matrix
         * @param {Number} m00 Component in column 0, row 0 position (index 0)
         * @param {Number} m01 Component in column 0, row 1 position (index 1)
         * @param {Number} m10 Component in column 1, row 0 position (index 2)
         * @param {Number} m11 Component in column 1, row 1 position (index 3)
         * @returns {mat2} out
         */
        fun set(inOut: Float32Array, m00: Float, m01: Float, m10: Float, m11: Float): Float32Array {
            inOut[0] = m00
            inOut[1] = m01
            inOut[2] = m10
            inOut[3] = m11
            return inOut
        }

        fun set(inOut: Float32Array, m00: Double, m01: Double, m10: Double, m11: Double): Float32Array {
            inOut[0] = m00.toFloat()
            inOut[1] = m01.toFloat()
            inOut[2] = m10.toFloat()
            inOut[3] = m11.toFloat()
            return inOut
        }

        /**
         * Transpose the values of a mat2
         *
         * @param {mat2} out the receiving matrix
         * @param {mat2} a the source matrix
         * @returns {mat2} out
         */
        fun transpose(inOut: Float32Array, a: Float32Array): Float32Array {
            // If we are transposing ourselves we can skip a few steps but have to cache
            // some values
            if (inOut === a) {
                val a1 = a[1]
                inOut[1] = a[2]
                inOut[2] = a1
            } else {
                inOut[0] = a[0]
                inOut[1] = a[2]
                inOut[2] = a[1]
                inOut[3] = a[3]
            }
            return inOut
        }

        /**
         * Inverts a mat2
         *
         * @param {mat2} out the receiving matrix
         * @param {mat2} a the source matrix
         * @returns {mat2} out
         */
        fun invert(inOut: Float32Array, a: Float32Array): Float32Array {
            val a0 = a[0]
            val a1 = a[1]
            val a2 = a[2]
            val a3 = a[3]
            // Calculate the determinant
            var det = a0 * a3 - a2 * a1
            if (det < 0) {
                return Float32Array(0)
            }
            det = (1.0 / det).toFloat()
            inOut[0] = a3 * det
            inOut[1] = -a1 * det
            inOut[2] = -a2 * det
            inOut[3] = a0 * det
            return inOut
        }

        /**
         * Calculates the adjugate of a mat2
         *
         * @param {mat2} out the receiving matrix
         * @param {mat2} a the source matrix
         * @returns {mat2} out
         */
        fun adjoint(inOut: Float32Array, a: Float32Array): Float32Array {
            // Caching this value is nessecary if out == a
            val a0 = a[0]
            inOut[0] = a[3]
            inOut[1] = -a[1]
            inOut[2] = -a[2]
            inOut[3] = a0
            return inOut
        }

        /**
         * Calculates the determinant of a mat2
         *
         * @param {mat2} a the source matrix
         * @returns {Number} determinant of a
         */
        fun determinant(a: Float32Array): Float {
            return a[0] * a[3] - a[2] * a[1]
        }

        /**
         * Multiplies two mat2's
         *
         * @param {mat2} out the receiving matrix
         * @param {mat2} a the first operand
         * @param {mat2} b the second operand
         * @returns {mat2} out
         */
        fun multiply(inOut: Float32Array, a: Float32Array, b: Float32Array): Float32Array {
            val a0 = a[0]
            val a1 = a[1]
            val a2 = a[2]
            val a3 = a[3]
            val b0 = b[0]
            val b1 = b[1]
            val b2 = b[2]
            val b3 = b[3]
            inOut[0] = a0 * b0 + a2 * b1
            inOut[1] = a1 * b0 + a3 * b1
            inOut[2] = a0 * b2 + a2 * b3
            inOut[3] = a1 * b2 + a3 * b3
            return inOut
        }

        /**
         * Rotates a mat2 by the given angle
         *
         * @param {mat2} out the receiving matrix
         * @param {mat2} a the matrix to rotate
         * @param {Number} rad the angle to rotate the matrix by
         * @returns {mat2} out
         */
        fun rotate(inOut: Float32Array, a: Float32Array, rad: Float): Float32Array {
            val a0 = a[0]
            val a1 = a[1]
            val a2 = a[2]
            val a3 = a[3]
            val s = (Math.sin(rad.toDouble())).toFloat()
            val c = (Math.cos(rad.toDouble())).toFloat()
            inOut[0] = a0 * c + a2 * s
            inOut[1] = a1 * c + a3 * s
            inOut[2] = a0 * -s + a2 * c
            inOut[3] = a1 * -s + a3 * c
            return inOut
        }

        /**
         * Scales the mat2 by the dimensions in the given vec2
         *
         * @param {mat2} out the receiving matrix
         * @param {mat2} a the matrix to rotate
         * @param {vec2} v the vec2 to scale the matrix by
         * @returns {mat2} out
         **/
        fun scale(inOut: Float32Array, a: Float32Array, v: Float32Array): Float32Array {
            val a0 = a[0]
            val a1 = a[1]
            val a2 = a[2]
            val a3 = a[3]
            val v0 = v[0]
            val v1 = v[1]
            inOut[0] = a0 * v0
            inOut[1] = a1 * v0
            inOut[2] = a2 * v1
            inOut[3] = a3 * v1
            return inOut
        }

        /**
         * Creates a matrix from a given angle
         * This is equivalent to (but much faster than):
         *
         *     mat2.identity(dest);
         *     mat2.rotate(dest, dest, rad);
         *
         * @param {mat2} out mat2 receiving operation result
         * @param {Number} rad the angle to rotate the matrix by
         * @returns {mat2} out
         */
        fun fromRotation(inOut: Float32Array, rad: Float): Float32Array {
            val s = (Math.sin(rad.toDouble())).toFloat()
            val c = (Math.cos(rad.toDouble())).toFloat()
            inOut[0] = c
            inOut[1] = s
            inOut[2] = -s
            inOut[3] = c
            return inOut
        }

        fun fromRotation(inOut: Float32Array, rad: Double): Float32Array {
            val s = (Math.sin(rad)).toFloat()
            val c = (Math.cos(rad)).toFloat()
            inOut[0] = c
            inOut[1] = s
            inOut[2] = -s
            inOut[3] = c
            return inOut
        }

        /**
         * Creates a matrix from a vector scaling
         * This is equivalent to (but much faster than):
         *
         *     mat2.identity(dest);
         *     mat2.scale(dest, dest, vec);
         *
         * @param {mat2} out mat2 receiving operation result
         * @param {vec2} v Scaling vector
         * @returns {mat2} out
         */
        fun fromScaling(inOut: Float32Array, v: Float32Array): Float32Array {
            inOut[0] = v[0]
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = v[1]
            return inOut
        }

        /**
         * Returns a string representation of a mat2
         *
         * @param {mat2} a matrix to represent as a string
         * @returns {String} string representation of the matrix
         */
        fun str(a: Float32Array): String {
            return "mat2(${a[0]}, ${a[1]}, ${a[2]}, ${a[3]})"
        }

        /**
         * Returns Frobenius norm of a mat2
         *
         * @param {mat2} a the matrix to calculate Frobenius norm of
         * @returns {Number} Frobenius norm
         */
        fun frob(a: Float32Array): Double {
            return (Math.sqrt(Math.pow(a[0].toDouble(), 2.0) + Math.pow(a[1].toDouble(), 2.0) + Math.pow(a[2].toDouble(), 2.0) + Math.pow(a[3].toDouble(), 2.0)))
        }

        /**
         * Returns L, D and U matrices (Lower triangular, Diagonal and Upper triangular) by factorizing the input matrix
         * @param {mat2} L the lower triangular matrix
         * @param {mat2} D the diagonal matrix
         * @param {mat2} U the upper triangular matrix
         * @param {mat2} a the input matrix to factorize
         */
        fun LDU(L: Float32Array, D: Float32Array, U: Float32Array, a: Float32Array): Triple<Float32Array, Float32Array, Float32Array> {
            L[2] = a[2] / a[0]
            U[0] = a[0]
            U[1] = a[1]
            U[3] = a[3] - L[2] * U[1]
            return Triple(L, D, U)
        }

        /**
         * Adds two mat2's
         *
         * @param {mat2} out the receiving matrix
         * @param {mat2} a the first operand
         * @param {mat2} b the second operand
         * @returns {mat2} out
         */
        fun add(inOut: Float32Array, a: Float32Array, b: Float32Array): Float32Array {
            inOut[0] = a[0] + b[0]
            inOut[1] = a[1] + b[1]
            inOut[2] = a[2] + b[2]
            inOut[3] = a[3] + b[3]
            return inOut
        }

        /**
         * Subtracts matrix b from matrix a
         *
         * @param {mat2} out the receiving matrix
         * @param {mat2} a the first operand
         * @param {mat2} b the second operand
         * @returns {mat2} out
         */
        fun subtract(inOut: Float32Array, a: Float32Array, b: Float32Array): Float32Array {
            inOut[0] = a[0] - b[0]
            inOut[1] = a[1] - b[1]
            inOut[2] = a[2] - b[2]
            inOut[3] = a[3] - b[3]
            return inOut
        }

        /**
         * Returns whether or not the matrices have exactly the same elements in the same position (when compared with ===)
         *
         * @param {mat2} a The first matrix.
         * @param {mat2} b The second matrix.
         * @returns {Boolean} True if the matrices are equal, false otherwise.
         */
        fun exactEquals(a: Float32Array, b: Float32Array): Boolean {
            return a[0] == b[0] && a[1] == b[1] && a[2] == b[2] && a[3] == b[3]
        }

        /**
         * Returns whether or not the matrices have approximately the same elements in the same position.
         *
         * @param {mat2} a The first matrix.
         * @param {mat2} b The second matrix.
         * @returns {Boolean} True if the matrices are equal, false otherwise.
         */
        fun equals(a: Float32Array, b: Float32Array): Boolean {
            val a0 = a[0].toDouble()
            val a1 = a[1].toDouble()
            val a2 = a[2].toDouble()
            val a3 = a[3].toDouble()
            val b0 = b[0].toDouble()
            val b1 = b[1].toDouble()
            val b2 = b[2].toDouble()
            val b3 = b[3].toDouble()
            return (Math.abs(a0 - b0) <= glMatrix.EPSILON * Math.max(1.0, Math.abs(a0), Math.abs(b0)) &&
                    Math.abs(a1 - b1) <= glMatrix.EPSILON * Math.max(1.0, Math.abs(a1), Math.abs(b1)) &&
                    Math.abs(a2 - b2) <= glMatrix.EPSILON * Math.max(1.0, Math.abs(a2), Math.abs(b2)) &&
                    Math.abs(a3 - b3) <= glMatrix.EPSILON * Math.max(1.0, Math.abs(a3), Math.abs(b3)))
        }

        /**
         * Multiply each element of the matrix by a scalar.
         *
         * @param {mat2} out the receiving matrix
         * @param {mat2} a the matrix to scale
         * @param {Number} b amount to scale the matrix's elements by
         * @returns {mat2} out
         */
        fun multiplyScalar(inOut: Float32Array, a: Float32Array, b: Float): Float32Array {
            inOut[0] = a[0] * b
            inOut[1] = a[1] * b
            inOut[2] = a[2] * b
            inOut[3] = a[3] * b
            return inOut
        }

        /**
         * Adds two mat2's after multiplying each element of the second operand by a scalar value.
         *
         * @param {mat2} out the receiving vector
         * @param {mat2} a the first operand
         * @param {mat2} b the second operand
         * @param {Number} scale the amount to scale b's elements by before adding
         * @returns {mat2} out
         */
        fun multiplyScalarAndAdd(inOut: Float32Array, a: Float32Array, b: Float32Array, scale: Float): Float32Array {
            inOut[0] = a[0] + (b[0] * scale)
            inOut[1] = a[1] + (b[1] * scale)
            inOut[2] = a[2] + (b[2] * scale)
            inOut[3] = a[3] + (b[3] * scale)
            return inOut
        }

    }
}