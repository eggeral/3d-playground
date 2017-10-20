package matrix

import org.khronos.webgl.Float32Array
import org.khronos.webgl.get
import org.khronos.webgl.set
import kotlin.js.Math

class mat2d : glMatrix() {
    companion object {
        /**
         * 2x3 Matrix
         * @module mat2d
         *
         * @description
         * A mat2d contains six elements defined as:
         * <pre>
         * [a, c, tx,
         *  b, d, ty]
         * </pre>
         * This is a short form for the 3x3 matrix:
         * <pre>
         * [a, c, tx,
         *  b, d, ty,
         *  0, 0, 1]
         * </pre>
         * The last row is ignored so the array is shorter and operations are faster.
         */
        /**
         * Creates a new identity mat2d
         *
         * @returns {mat2d} a new 2x3 matrix
         */
        fun create(): Float32Array {
            val out = Float32Array(6)
            out[0] = 1.0f
            out[1] = 0.0f
            out[2] = 0.0f
            out[3] = 1.0f
            out[4] = 0.0f
            out[5] = 0.0f
            return out
        }

        /**
         * Creates matrixToClone new mat2d initialized with values from an existing matrix
         *
         * @param {mat2d} matrixToClone matrix to clone
         * @returns {mat2d} matrixToClone new 2x3 matrix
         */
        fun clone(matrixToClone: Float32Array): Float32Array {
            if (matrixToClone.length != 6) {
                throw IllegalArgumentException("matrixToClone must be of size 6")
            }
            val out = Float32Array(6)
            out[0] = matrixToClone[0]
            out[1] = matrixToClone[1]
            out[2] = matrixToClone[2]
            out[3] = matrixToClone[3]
            out[4] = matrixToClone[4]
            out[5] = matrixToClone[5]
            return out
        }

        /**
         * Copy the values from one mat2d to another
         *
         * @param {mat2d} inOut the receiving matrix
         * @param {mat2d} toCopy the toCopy matrix
         * @returns {mat2d} inOut
         */
        fun copy(inOut: Float32Array, toCopy: Float32Array): Float32Array {
            inOut[0] = toCopy[0]
            inOut[1] = toCopy[1]
            inOut[2] = toCopy[2]
            inOut[3] = toCopy[3]
            inOut[4] = toCopy[4]
            inOut[5] = toCopy[5]
            return inOut
        }

        /**
         * Set a mat2d to the identity matrix
         *
         * @param {mat2d} inOut the receiving matrix
         * @returns {mat2d} inOut
         */
        fun identity(inOut: Float32Array): Float32Array {
            inOut[0] = 1.0f
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = 1.0f
            inOut[4] = 0.0f
            inOut[5] = 0.0f
            return inOut
        }

        /**
         * Create a new mat2d with the given values
         *
         * @param {Number} a Component A (index 0)
         * @param {Number} b Component B (index 1)
         * @param {Number} c Component C (index 2)
         * @param {Number} d Component D (index 3)
         * @param {Number} tx Component TX (index 4)
         * @param {Number} ty Component TY (index 5)
         * @returns {mat2d} A new mat2d
         */
        fun fromValues(a: Float, b: Float, c: Float, d: Float, tx: Float, ty: Float): Float32Array {
            val out = Float32Array(6)
            out[0] = a
            out[1] = b
            out[2] = c
            out[3] = d
            out[4] = tx
            out[5] = ty
            return out
        }

        fun fromValues(a: Double, b: Double, c: Double, d: Double, tx: Double, ty: Double): Float32Array {
            val out = Float32Array(6)
            out[0] = a.toFloat()
            out[1] = b.toFloat()
            out[2] = c.toFloat()
            out[3] = d.toFloat()
            out[4] = tx.toFloat()
            out[5] = ty.toFloat()
            return out
        }

        /**
         * Set the components of a mat2d to the given values
         *
         * @param {mat2d} inOut the receiving matrix
         * @param {Number} a Component A (index 0)
         * @param {Number} b Component B (index 1)
         * @param {Number} c Component C (index 2)
         * @param {Number} d Component D (index 3)
         * @param {Number} tx Component TX (index 4)
         * @param {Number} ty Component TY (index 5)
         * @returns {mat2d} inOut
         */
        fun set(inOut: Float32Array, a: Float, b: Float, c: Float, d: Float, tx: Float, ty: Float): Float32Array {
            inOut[0] = a
            inOut[1] = b
            inOut[2] = c
            inOut[3] = d
            inOut[4] = tx
            inOut[5] = ty
            return inOut
        }

        fun set(inOut: Float32Array, a: Double, b: Double, c: Double, d: Double, tx: Double, ty: Double): Float32Array {
            inOut[0] = a.toFloat()
            inOut[1] = b.toFloat()
            inOut[2] = c.toFloat()
            inOut[3] = d.toFloat()
            inOut[4] = tx.toFloat()
            inOut[5] = ty.toFloat()
            return inOut
        }

        /**
         * Inverts source mat2d
         *
         * @param {mat2d} inOut the receiving matrix
         * @param {mat2d} source the source matrix
         * @returns {mat2d} inOut
         */
        fun invert(inOut: Float32Array, source: Float32Array): Float32Array {
            val aa = source[0]
            val ab = source[1]
            val ac = source[2]
            val ad = source[3]
            val atx = source[4]
            val aty = source[5]
            var det = aa * ad - ab * ac
            if (det < 0) {
                return Float32Array(0)
            }
            det = (1.0 / det).toFloat()
            inOut[0] = ad * det
            inOut[1] = -ab * det
            inOut[2] = -ac * det
            inOut[3] = aa * det
            inOut[4] = (ac * aty - ad * atx) * det
            inOut[5] = (ab * atx - aa * aty) * det
            return inOut
        }

        /**
         * Calculates the determinant of source mat2d
         *
         * @param {mat2d} source the source matrix
         * @returns {Number} determinant of source
         */
        fun determinant(source: Float32Array): Double {
            return (source[0] * source[3] - source[1] * source[2]).toDouble()
        }

        /**
         * Multiplies two mat2d's
         *
         * @param {mat2d} inOut the receiving matrix
         * @param {mat2d} multiplier the first operand
         * @param {mat2d} multiplicand the second operand
         * @returns {mat2d} inOut
         */
        fun multiply(inOut: Float32Array, multiplier: Float32Array, multiplicand: Float32Array): Float32Array {
            val a0 = multiplier[0]
            val a1 = multiplier[1]
            val a2 = multiplier[2]
            val a3 = multiplier[3]
            val a4 = multiplier[4]
            val a5 = multiplier[5]
            val b0 = multiplicand[0]
            val b1 = multiplicand[1]
            val b2 = multiplicand[2]
            val b3 = multiplicand[3]
            val b4 = multiplicand[4]
            val b5 = multiplicand[5]
            inOut[0] = a0 * b0 + a2 * b1
            inOut[1] = a1 * b0 + a3 * b1
            inOut[2] = a0 * b2 + a2 * b3
            inOut[3] = a1 * b2 + a3 * b3
            inOut[4] = a0 * b4 + a2 * b5 + a4
            inOut[5] = a1 * b4 + a3 * b5 + a5
            return inOut
        }

        /**
         * Rotates matrixToRotate mat2d by the given angle
         *
         * @param {mat2d} inOut the receiving matrix
         * @param {mat2d} matrixToRotate the matrix to rotate
         * @param {Number} angleInRad the angle to rotate the matrix by
         * @returns {mat2d} inOut
         */
        fun rotate(inOut: Float32Array, matrixToRotate: Float32Array, angleInRad: Double): Float32Array {
            val a0 = matrixToRotate[0]
            val a1 = matrixToRotate[1]
            val a2 = matrixToRotate[2]
            val a3 = matrixToRotate[3]
            val a4 = matrixToRotate[4]
            val a5 = matrixToRotate[5]
            val s = (Math.sin(angleInRad)).toFloat()
            val c = (Math.cos(angleInRad)).toFloat()
            inOut[0] = a0 * c + a2 * s
            inOut[1] = a1 * c + a3 * s
            inOut[2] = a0 * -s + a2 * c
            inOut[3] = a1 * -s + a3 * c
            inOut[4] = a4
            inOut[5] = a5
            return inOut
        }

        /**
         * Scales the mat2d by the dimensions in the given vec2
         *
         * @param {mat2d} inOut the receiving matrix
         * @param {mat2d} matrixToRotate the matrix to rotate
         * @param {vec2} vec2ToScaleBy the vec2 to scale the matrix by
         * @returns {mat2d} inOut
         **/
        fun scale(inOut: Float32Array, matrixToRotate: Float32Array, vec2ToScaleBy: Array<Double>): Float32Array {
            val a0 = matrixToRotate[0]
            val a1 = matrixToRotate[1]
            val a2 = matrixToRotate[2]
            val a3 = matrixToRotate[3]
            val a4 = matrixToRotate[4]
            val a5 = matrixToRotate[5]
            val v0 = vec2ToScaleBy[0].toFloat()
            val v1 = vec2ToScaleBy[1].toFloat()
            inOut[0] = a0 * v0
            inOut[1] = a1 * v0
            inOut[2] = a2 * v1
            inOut[3] = a3 * v1
            inOut[4] = a4
            inOut[5] = a5
            return inOut
        }

        /**
         * Translates the mat2d by the dimensions in the given vec2
         *
         * @param {mat2d} inOut the receiving matrix
         * @param {mat2d} matrixToTranslate the matrix to translate
         * @param {vec2} vec2ToTranslateBy the vec2 to translate the matrix by
         * @returns {mat2d} inOut
         **/
        fun translate(inOut: Float32Array, matrixToTranslate: Float32Array, vec2ToTranslateBy: Array<Double>): Float32Array {
            val a0 = matrixToTranslate[0]
            val a1 = matrixToTranslate[1]
            val a2 = matrixToTranslate[2]
            val a3 = matrixToTranslate[3]
            val a4 = matrixToTranslate[4]
            val a5 = matrixToTranslate[5]
            val v0 = vec2ToTranslateBy[0].toFloat()
            val v1 = vec2ToTranslateBy[1].toFloat()
            inOut[0] = a0
            inOut[1] = a1
            inOut[2] = a2
            inOut[3] = a3
            inOut[4] = a0 * v0 + a2 * v1 + a4
            inOut[5] = a1 * v0 + a3 * v1 + a5
            return inOut
        }

        /**
         * Creates a matrix from a given angle
         * This is equivalent to (but much faster than):
         *
         *     mat2d.identity(dest);
         *     mat2d.rotate(dest, dest, angleToRotateByInRad);
         *
         * @param {mat2d} inOut mat2d receiving operation result
         * @param {Number} angleToRotateByInRad the angle to rotate the matrix by
         * @returns {mat2d} inOut
         */
        fun fromRotation(inOut: Float32Array, angleToRotateByInRad: Double): Float32Array {
            val s = (Math.sin(angleToRotateByInRad)).toFloat()
            val c = (Math.cos(angleToRotateByInRad)).toFloat()
            inOut[0] = c
            inOut[1] = s
            inOut[2] = -s
            inOut[3] = c
            inOut[4] = 0.0f
            inOut[5] = 0.0f
            return inOut
        }

        /**
         * Creates a matrix from a vector scaling
         * This is equivalent to (but much faster than):
         *
         *     mat2d.identity(dest);
         *     mat2d.scale(dest, dest, vec);
         *
         * @param {mat2d} inOut mat2d receiving operation result
         * @param {vec2} scalingVec2 Scaling vector
         * @returns {mat2d} inOut
         */
        fun fromScaling(inOut: Float32Array, scalingVec2: Array<Double>): Float32Array {
            inOut[0] = scalingVec2[0].toFloat()
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = scalingVec2[1].toFloat()
            inOut[4] = 0.0f
            inOut[5] = 0.0f
            return inOut
        }

        /**
         * Creates a matrix from a vector translation
         * This is equivalent to (but much faster than):
         *
         *     mat2d.identity(dest);
         *     mat2d.translate(dest, dest, vec);
         *
         * @param {mat2d} inOut mat2d receiving operation result
         * @param {vec2} translationVec2 Translation vector
         * @returns {mat2d} inOut
         */
        fun fromTranslation(inOut: Float32Array, translationVec2: Array<Double>): Float32Array {
            inOut[0] = 1.0f
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = 1.0f
            inOut[4] = translationVec2[0].toFloat()
            inOut[5] = translationVec2[1].toFloat()
            return inOut
        }

        /**
         * Returns matrix string representation of matrix mat2d
         *
         * @param {mat2d} matrix matrix to represent as matrix string
         * @returns {String} string representation of the matrix
         */
        fun toString(matrix: Float32Array): String {
            return "mat2d(${matrix[0]}, ${matrix[1]}, ${matrix[2]}, ${matrix[3]})"
        }

        /**
         * Returns Frobenius norm of matrix mat2d
         *
         * @param {mat2d} matrix the matrix to calculate Frobenius norm of
         * @returns {Number} Frobenius norm
         */
        fun frob(matrix: Float32Array): Double {
            return (Math.sqrt(Math.pow(matrix[0].toDouble(), 2.0) + Math.pow(matrix[1].toDouble(), 2.0)
                    + Math.pow(matrix[2].toDouble(), 2.0) + Math.pow(matrix[3].toDouble(), 2.0)
                    + Math.pow(matrix[4].toDouble(), 2.0) + Math.pow(matrix[5].toDouble(), 2.0) + 1))
        }

        /**
         * Adds two mat2d's
         *
         * @param {mat2d} inOut the receiving matrix
         * @param {mat2d} firstSummand the first operand
         * @param {mat2d} secondSummand the second operand
         * @returns {mat2d} inOut
         */
        fun add(inOut: Float32Array, firstSummand: Float32Array, secondSummand: Float32Array): Float32Array {
            inOut[0] = firstSummand[0] + secondSummand[0]
            inOut[1] = firstSummand[1] + secondSummand[1]
            inOut[2] = firstSummand[2] + secondSummand[2]
            inOut[3] = firstSummand[3] + secondSummand[3]
            inOut[4] = firstSummand[4] + secondSummand[4]
            inOut[5] = firstSummand[5] + secondSummand[5]
            return inOut
        }

        /**
         * Subtracts matrix subtrahend from matrix minuend
         *
         * @param {mat2d} inOut the receiving matrix
         * @param {mat2d} minuend the first operand
         * @param {mat2d} subtrahend the second operand
         * @returns {mat2d} inOut
         */
        fun subtract(inOut: Float32Array, minuend: Float32Array, subtrahend: Float32Array): Float32Array {
            inOut[0] = minuend[0] - subtrahend[0]
            inOut[1] = minuend[1] - subtrahend[1]
            inOut[2] = minuend[2] - subtrahend[2]
            inOut[3] = minuend[3] - subtrahend[3]
            inOut[4] = minuend[4] - subtrahend[4]
            inOut[5] = minuend[5] - subtrahend[5]
            return inOut
        }

        /**
         * Multiply each element of the matrix by matrixToScale scalar.
         *
         * @param {mat2d} inOut the receiving matrix
         * @param {mat2d} matrixToScale the matrix to scale
         * @param {Number} amountToScaleBy amount to scale the matrix's elements by
         * @returns {mat2d} inOut
         */
        fun multiplyScalar(inOut: Float32Array, matrixToScale: Float32Array, amountToScaleBy: Double): Float32Array {
            val amountToScaleTheMatrix = amountToScaleBy.toFloat()
            inOut[0] = matrixToScale[0] * amountToScaleTheMatrix
            inOut[1] = matrixToScale[1] * amountToScaleTheMatrix
            inOut[2] = matrixToScale[2] * amountToScaleTheMatrix
            inOut[3] = matrixToScale[3] * amountToScaleTheMatrix
            inOut[4] = matrixToScale[4] * amountToScaleTheMatrix
            inOut[5] = matrixToScale[5] * amountToScaleTheMatrix
            return inOut
        }

        /**
         * Adds two mat2d's after multiplying each element of the second operand by firstSummand scalar value.
         *
         * @param {mat2d} inOut the receiving vector
         * @param {mat2d} firstSummand the first operand
         * @param {mat2d} secondSummand the second operand
         * @param {Number} amountToScale the amount to amountToScale secondSummand's elements by before adding
         * @returns {mat2d} inOut
         */
        fun multiplyScalarAndAdd(inOut: Float32Array, firstSummand: Float32Array, secondSummand: Float32Array, amountToScale: Double): Float32Array {
            val amountToScaleTheMatrix = amountToScale.toFloat()
            inOut[0] = firstSummand[0] + (secondSummand[0] * amountToScaleTheMatrix)
            inOut[1] = firstSummand[1] + (secondSummand[1] * amountToScaleTheMatrix)
            inOut[2] = firstSummand[2] + (secondSummand[2] * amountToScaleTheMatrix)
            inOut[3] = firstSummand[3] + (secondSummand[3] * amountToScaleTheMatrix)
            inOut[4] = firstSummand[4] + (secondSummand[4] * amountToScaleTheMatrix)
            inOut[5] = firstSummand[5] + (secondSummand[5] * amountToScaleTheMatrix)
            return inOut
        }

        /**
         * Returns whether or not the matrices have exactly the same elements in the same position (when compared with ===)
         *
         * @param {mat2d} firstMatrix The first matrix.
         * @param {mat2d} secondMatrix The second matrix.
         * @returns {Boolean} True if the matrices are equal, false otherwise.
         */
        fun exactEquals(firstMatrix: Float32Array, secondMatrix: Float32Array): Boolean {
            return firstMatrix[0] == secondMatrix[0] && firstMatrix[1] == secondMatrix[1]
                    && firstMatrix[2] == secondMatrix[2] && firstMatrix[3] == secondMatrix[3]
                    && firstMatrix[4] == secondMatrix[4] && firstMatrix[5] == secondMatrix[5]
        }

        /**
         * Returns whether or not the matrices have approximately the same elements in the same position.
         *
         * @param {mat2d} firstMatrix The first matrix.
         * @param {mat2d} secondMatrix The second matrix.
         * @returns {Boolean} True if the matrices are equal, false otherwise.
         */
        fun equals(firstMatrix: Float32Array, secondMatrix: Float32Array): Boolean {
            val a0 = firstMatrix[0].toDouble()
            val a1 = firstMatrix[1].toDouble()
            val a2 = firstMatrix[2].toDouble()
            val a3 = firstMatrix[3].toDouble()
            val a4 = firstMatrix[4].toDouble()
            val a5 = firstMatrix[5].toDouble()
            val b0 = secondMatrix[0].toDouble()
            val b1 = secondMatrix[1].toDouble()
            val b2 = secondMatrix[2].toDouble()
            val b3 = secondMatrix[3].toDouble()
            val b4 = secondMatrix[4].toDouble()
            val b5 = secondMatrix[5].toDouble()
            return (Math.abs(a0 - b0) <= EPSILON * Math.max(1.0, Math.abs(a0), Math.abs(b0)) &&
                    Math.abs(a1 - b1) <= EPSILON * Math.max(1.0, Math.abs(a1), Math.abs(b1)) &&
                    Math.abs(a2 - b2) <= EPSILON * Math.max(1.0, Math.abs(a2), Math.abs(b2)) &&
                    Math.abs(a3 - b3) <= EPSILON * Math.max(1.0, Math.abs(a3), Math.abs(b3)) &&
                    Math.abs(a4 - b4) <= EPSILON * Math.max(1.0, Math.abs(a4), Math.abs(b4)) &&
                    Math.abs(a5 - b5) <= EPSILON * Math.max(1.0, Math.abs(a5), Math.abs(b5)))
        }
    }
}