package spr5.matrix

import org.khronos.webgl.Float32Array
import org.khronos.webgl.get
import org.khronos.webgl.set
import kotlin.js.Math

class mat3 : glMatrix() {
    companion object {
        /**
         * 3x3 Matrix
         * @module mat3
         */
        /**
         * Creates a new identity mat3
         *
         * @returns {mat3} a new 3x3 matrix
         */
        fun create(): Float32Array {
            val out = Float32Array(9)
            out[0] = 1.0f
            out[1] = 0.0f
            out[2] = 0.0f
            out[3] = 0.0f
            out[4] = 1.0f
            out[5] = 0.0f
            out[6] = 0.0f
            out[7] = 0.0f
            out[8] = 1.0f
            return out
        }

        /**
         * Copies the upper-left 3x3 values into the given mat3.
         *
         * @param {mat3} inOut the receiving 3x3 matrix
         * @param {mat4} source   the source 4x4 matrix
         * @returns {mat3} inOut
         */
        fun fromMat4(inOut: Float32Array, source: Float32Array): Float32Array {
            inOut[0] = source[0]
            inOut[1] = source[1]
            inOut[2] = source[2]
            inOut[3] = source[4]
            inOut[4] = source[5]
            inOut[5] = source[6]
            inOut[6] = source[8]
            inOut[7] = source[9]
            inOut[8] = source[10]
            return inOut
        }

        /**
         * Creates matrixToClone new mat3 initialized with values from an existing matrix
         *
         * @param {mat3} matrixToClone matrix to clone
         * @returns {mat3} matrixToClone new 3x3 matrix
         */
        fun clone(matrixToClone: Float32Array): Float32Array {
            val out = Float32Array(9)
            out[5] = matrixToClone[5]
            out[6] = matrixToClone[6]
            out[7] = matrixToClone[7]
            out[8] = matrixToClone[8]
            return out
        }

        /**
         * Copy the values from one mat3 to another
         *
         * @param {mat3} inOut the receiving matrix
         * @param {mat3} toCopy the source matrix
         * @returns {mat3} inOut
         */
        fun copy(inOut: Float32Array, toCopy: Float32Array): Float32Array {
            inOut[0] = toCopy[0]
            inOut[1] = toCopy[1]
            inOut[2] = toCopy[2]
            inOut[3] = toCopy[3]
            inOut[4] = toCopy[4]
            inOut[5] = toCopy[5]
            inOut[6] = toCopy[6]
            inOut[7] = toCopy[7]
            inOut[8] = toCopy[8]
            return inOut
        }

        /**
         * Create a new mat3 with the given values
         *
         * @param {Number} m00 Component in column 0, row 0 position (index 0)
         * @param {Number} m01 Component in column 0, row 1 position (index 1)
         * @param {Number} m02 Component in column 0, row 2 position (index 2)
         * @param {Number} m10 Component in column 1, row 0 position (index 3)
         * @param {Number} m11 Component in column 1, row 1 position (index 4)
         * @param {Number} m12 Component in column 1, row 2 position (index 5)
         * @param {Number} m20 Component in column 2, row 0 position (index 6)
         * @param {Number} m21 Component in column 2, row 1 position (index 7)
         * @param {Number} m22 Component in column 2, row 2 position (index 8)
         * @returns {mat3} A new mat3
         */
        fun fromValues(m00: Float, m01: Float, m02: Float, m10: Float, m11: Float, m12: Float, m20: Float, m21: Float, m22: Float): Float32Array {
            val out = Float32Array(9)
            out[0] = m00
            out[1] = m01
            out[2] = m02
            out[3] = m10
            out[4] = m11
            out[5] = m12
            out[6] = m20
            out[7] = m21
            out[8] = m22
            return out
        }

        fun fromValues(m00: Double, m01: Double, m02: Double, m10: Double, m11: Double, m12: Double, m20: Double, m21: Double, m22: Double): Float32Array {
            val out = Float32Array(9)
            out[0] = m00.toFloat()
            out[1] = m01.toFloat()
            out[2] = m02.toFloat()
            out[3] = m10.toFloat()
            out[4] = m11.toFloat()
            out[5] = m12.toFloat()
            out[6] = m20.toFloat()
            out[7] = m21.toFloat()
            out[8] = m22.toFloat()
            return out
        }

        /**
         * Set the components of a mat3 to the given values
         *
         * @param {mat3} inOut the receiving matrix
         * @param {Number} m00 Component in column 0, row 0 position (index 0)
         * @param {Number} m01 Component in column 0, row 1 position (index 1)
         * @param {Number} m02 Component in column 0, row 2 position (index 2)
         * @param {Number} m10 Component in column 1, row 0 position (index 3)
         * @param {Number} m11 Component in column 1, row 1 position (index 4)
         * @param {Number} m12 Component in column 1, row 2 position (index 5)
         * @param {Number} m20 Component in column 2, row 0 position (index 6)
         * @param {Number} m21 Component in column 2, row 1 position (index 7)
         * @param {Number} m22 Component in column 2, row 2 position (index 8)
         * @returns {mat3} inOut
         */
        fun set(inOut: Float32Array, m00: Float, m01: Float, m02: Float, m10: Float, m11: Float, m12: Float, m20: Float, m21: Float, m22: Float): Float32Array {
            inOut[0] = m00
            inOut[1] = m01
            inOut[2] = m02
            inOut[3] = m10
            inOut[4] = m11
            inOut[5] = m12
            inOut[6] = m20
            inOut[7] = m21
            inOut[8] = m22
            return inOut
        }

        fun set(inOut: Float32Array, m00: Double, m01: Double, m02: Double, m10: Double, m11: Double, m12: Double, m20: Double, m21: Double, m22: Double): Float32Array {
            inOut[0] = m00.toFloat()
            inOut[1] = m01.toFloat()
            inOut[2] = m02.toFloat()
            inOut[3] = m10.toFloat()
            inOut[4] = m11.toFloat()
            inOut[5] = m12.toFloat()
            inOut[6] = m20.toFloat()
            inOut[7] = m21.toFloat()
            inOut[8] = m22.toFloat()
            return inOut
        }

        /**
         * Set a mat3 to the identity matrix
         *
         * @param {mat3} inOut the receiving matrix
         * @returns {mat3} inOut
         */
        fun identity(inOut: Float32Array): Float32Array {
            inOut[0] = 1.0f
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = 0.0f
            inOut[4] = 1.0f
            inOut[5] = 0.0f
            inOut[6] = 0.0f
            inOut[7] = 0.0f
            inOut[8] = 1.0f
            return inOut
        }

        /**
         * Transpose the values of source mat3
         *
         * @param {mat3} inOut the receiving matrix
         * @param {mat3} source the source matrix
         * @returns {mat3} inOut
         */
        fun transpose(inOut: Float32Array, source: Float32Array): Float32Array {
            // If we are transposing ourselves we can skip source few steps but have to cache some values
            if (inOut === source) {
                val a01 = source[1]
                val a02 = source[2]
                val a12 = source[5]
                inOut[1] = source[3]
                inOut[2] = source[6]
                inOut[3] = a01
                inOut[5] = source[7]
                inOut[6] = a02
                inOut[7] = a12
            } else {
                inOut[0] = source[0]
                inOut[1] = source[3]
                inOut[2] = source[6]
                inOut[3] = source[1]
                inOut[4] = source[4]
                inOut[5] = source[7]
                inOut[6] = source[2]
                inOut[7] = source[5]
                inOut[8] = source[8]
            }
            return inOut
        }

        /**
         * Inverts source mat3
         *
         * @param {mat3} inOut the receiving matrix
         * @param {mat3} source the source matrix
         * @returns {mat3} inOut
         */
        fun invert(inOut: Float32Array, source: Float32Array): Float32Array {
            val a00 = source[0]
            val a01 = source[1]
            val a02 = source[2]
            val a10 = source[3]
            val a11 = source[4]
            val a12 = source[5]
            val a20 = source[6]
            val a21 = source[7]
            val a22 = source[8]
            val b01 = a22 * a11 - a12 * a21
            val b11 = -a22 * a10 + a12 * a20
            val b21 = a21 * a10 - a11 * a20
            // Calculate the determinant
            var det = a00 * b01 + a01 * b11 + a02 * b21
            if (det < 0) {
                return Float32Array(0)
            }
            det = (1.0 / det).toFloat()
            inOut[0] = b01 * det
            inOut[1] = (-a22 * a01 + a02 * a21) * det
            inOut[2] = (a12 * a01 - a02 * a11) * det
            inOut[3] = b11 * det
            inOut[4] = (a22 * a00 - a02 * a20) * det
            inOut[5] = (-a12 * a00 + a02 * a10) * det
            inOut[6] = b21 * det
            inOut[7] = (-a21 * a00 + a01 * a20) * det
            inOut[8] = (a11 * a00 - a01 * a10) * det
            return inOut
        }

        /**
         * Calculates the adjugate of source mat3
         *
         * @param {mat3} inOut the receiving matrix
         * @param {mat3} source the source matrix
         * @returns {mat3} inOut
         */
        fun adjoint(inOut: Float32Array, source: Float32Array): Float32Array {
            val a00 = source[0]
            val a01 = source[1]
            val a02 = source[2]
            val a10 = source[3]
            val a11 = source[4]
            val a12 = source[5]
            val a20 = source[6]
            val a21 = source[7]
            val a22 = source[8]
            inOut[0] = (a11 * a22 - a12 * a21)
            inOut[1] = (a02 * a21 - a01 * a22)
            inOut[2] = (a01 * a12 - a02 * a11)
            inOut[3] = (a12 * a20 - a10 * a22)
            inOut[4] = (a00 * a22 - a02 * a20)
            inOut[5] = (a02 * a10 - a00 * a12)
            inOut[6] = (a10 * a21 - a11 * a20)
            inOut[7] = (a01 * a20 - a00 * a21)
            inOut[8] = (a00 * a11 - a01 * a10)
            return inOut
        }

        /**
         * Calculates the determinant of source mat3
         *
         * @param {mat3} source the source matrix
         * @returns {Number} determinant of source
         */
        fun determinant(source: Float32Array): Double {
            val a00 = source[0]
            val a01 = source[1]
            val a02 = source[2]
            val a10 = source[3]
            val a11 = source[4]
            val a12 = source[5]
            val a20 = source[6]
            val a21 = source[7]
            val a22 = source[8]
            return (a00 * (a22 * a11 - a12 * a21) + a01 * (-a22 * a10 + a12 * a20) + a02 * (a21 * a10 - a11 * a20)).toDouble()
        }

        /**
         * Multiplies two mat3's
         *
         * @param {mat3} inOut the receiving matrix
         * @param {mat3} multiplier the first operand
         * @param {mat3} multiplicand the second operand
         * @returns {mat3} inOut
         */
        fun multiply(inOut: Float32Array, multiplier: Float32Array, multiplicand: Float32Array): Float32Array {
            val a00 = multiplier[0]
            val a01 = multiplier[1]
            val a02 = multiplier[2]
            val a10 = multiplier[3]
            val a11 = multiplier[4]
            val a12 = multiplier[5]
            val a20 = multiplier[6]
            val a21 = multiplier[7]
            val a22 = multiplier[8]
            val b00 = multiplicand[0]
            val b01 = multiplicand[1]
            val b02 = multiplicand[2]
            val b10 = multiplicand[3]
            val b11 = multiplicand[4]
            val b12 = multiplicand[5]
            val b20 = multiplicand[6]
            val b21 = multiplicand[7]
            val b22 = multiplicand[8]
            inOut[0] = b00 * a00 + b01 * a10 + b02 * a20
            inOut[1] = b00 * a01 + b01 * a11 + b02 * a21
            inOut[2] = b00 * a02 + b01 * a12 + b02 * a22
            inOut[3] = b10 * a00 + b11 * a10 + b12 * a20
            inOut[4] = b10 * a01 + b11 * a11 + b12 * a21
            inOut[5] = b10 * a02 + b11 * a12 + b12 * a22
            inOut[6] = b20 * a00 + b21 * a10 + b22 * a20
            inOut[7] = b20 * a01 + b21 * a11 + b22 * a21
            inOut[8] = b20 * a02 + b21 * a12 + b22 * a22
            return inOut
        }

        /**
         * Translate matrixToTranslate mat3 by the given vector
         *
         * @param {mat3} inOut the receiving matrix
         * @param {mat3} matrixToTranslate the matrix to translate
         * @param {vec2} vec2ToTranslateBy vector to translate by
         * @returns {mat3} inOut
         */
        fun translate(inOut: Float32Array, matrixToTranslate: Float32Array, vec2ToTranslateBy: Array<Double>): Float32Array {
            val x = vec2ToTranslateBy[0].toFloat()
            val y = vec2ToTranslateBy[1].toFloat()
            val a00 = matrixToTranslate[0]
            val a01 = matrixToTranslate[1]
            val a02 = matrixToTranslate[2]
            val a10 = matrixToTranslate[3]
            val a11 = matrixToTranslate[4]
            val a12 = matrixToTranslate[5]
            val a20 = matrixToTranslate[6]
            val a21 = matrixToTranslate[7]
            val a22 = matrixToTranslate[8]
            inOut[0] = a00
            inOut[1] = a01
            inOut[2] = a02
            inOut[3] = a10
            inOut[4] = a11
            inOut[5] = a12
            inOut[6] = x * a00 + y * a10 + a20
            inOut[7] = x * a01 + y * a11 + a21
            inOut[8] = x * a02 + y * a12 + a22
            return inOut
        }

        /**
         * Rotates matrixToRotate mat3 by the given angle
         *
         * @param {mat3} inOut the receiving matrix
         * @param {mat3} matrixToRotate the matrix to rotate
         * @param {Number} angleInRad the angle to rotate the matrix by
         * @returns {mat3} inOut
         */
        fun rotate(inOut: Float32Array, matrixToRotate: Float32Array, angleInRad: Double): Float32Array {
            val a00 = matrixToRotate[0]
            val a01 = matrixToRotate[1]
            val a02 = matrixToRotate[2]
            val a10 = matrixToRotate[3]
            val a11 = matrixToRotate[4]
            val a12 = matrixToRotate[5]
            val a20 = matrixToRotate[6]
            val a21 = matrixToRotate[7]
            val a22 = matrixToRotate[8]
            val s = (Math.sin(angleInRad)).toFloat()
            val c = (Math.cos(angleInRad)).toFloat()
            inOut[0] = c * a00 + s * a10
            inOut[1] = c * a01 + s * a11
            inOut[2] = c * a02 + s * a12
            inOut[3] = c * a10 - s * a00
            inOut[4] = c * a11 - s * a01
            inOut[5] = c * a12 - s * a02
            inOut[6] = a20
            inOut[7] = a21
            inOut[8] = a22
            return inOut
        }

        /**
         * Scales the mat3 by the dimensions in the given vec2
         *
         * @param {mat3} inOut the receiving matrix
         * @param {mat3} matrixToScale the matrix to scale
         * @param {vec2} vec2ToScaleBy the vec2 to scale the matrix by
         * @returns {mat3} inOut
         **/
        fun scale(inOut: Float32Array, matrixToScale: Float32Array, vec2ToScaleBy: Array<Double>): Float32Array {
            val x = vec2ToScaleBy[0].toFloat()
            val y = vec2ToScaleBy[1].toFloat()
            inOut[0] = x * matrixToScale[0]
            inOut[1] = x * matrixToScale[1]
            inOut[2] = x * matrixToScale[2]
            inOut[3] = y * matrixToScale[3]
            inOut[4] = y * matrixToScale[4]
            inOut[5] = y * matrixToScale[5]
            inOut[6] = matrixToScale[6]
            inOut[7] = matrixToScale[7]
            inOut[8] = matrixToScale[8]
            return inOut
        }

        /**
         * Creates a matrix from a vector translation
         * This is equivalent to (but much faster than):
         *
         *     mat3.identity(dest);
         *     mat3.translate(dest, dest, vec);
         *
         * @param {mat3} inOut mat3 receiving operation result
         * @param {vec2} translationVec2 Translation vector
         * @returns {mat3} inOut
         */
        fun fromTranslation(inOut: Float32Array, translationVec2: Array<Double>): Float32Array {
            inOut[0] = 1.0f
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = 0.0f
            inOut[4] = 1.0f
            inOut[5] = 0.0f
            inOut[6] = translationVec2[0].toFloat()
            inOut[7] = translationVec2[1].toFloat()
            inOut[8] = 1.0f
            return inOut
        }

        /**
         * Creates a matrix from a given angle
         * This is equivalent to (but much faster than):
         *
         *     mat3.identity(dest);
         *     mat3.rotate(dest, dest, angleToRotateByInRad);
         *
         * @param {mat3} inOut mat3 receiving operation result
         * @param {Number} angleToRotateByInRad the angle to rotate the matrix by
         * @returns {mat3} inOut
         */
        fun fromRotation(inOut: Float32Array, angleToRotateByInRad: Double): Float32Array {
            val s = (Math.sin(angleToRotateByInRad)).toFloat()
            val c = (Math.cos(angleToRotateByInRad)).toFloat()
            inOut[0] = c
            inOut[1] = s
            inOut[2] = 0.0f
            inOut[3] = -s
            inOut[4] = c
            inOut[5] = 0.0f
            inOut[6] = 0.0f
            inOut[7] = 0.0f
            inOut[8] = 1.0f
            return inOut
        }

        /**
         * Creates a matrix from a vector scaling
         * This is equivalent to (but much faster than):
         *
         *     mat3.identity(dest);
         *     mat3.scale(dest, dest, vec);
         *
         * @param {mat3} inOut mat3 receiving operation result
         * @param {vec2} scalingVec2 Scaling vector
         * @returns {mat3} inOut
         */
        fun fromScaling(inOut: Float32Array, scalingVec2: Array<Double>): Float32Array {
            inOut[0] = scalingVec2[0].toFloat()
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = 0.0f
            inOut[4] = scalingVec2[1].toFloat()
            inOut[5] = 0.0f
            inOut[6] = 0.0f
            inOut[7] = 0.0f
            inOut[8] = 1.0f
            return inOut
        }

        /**
         * Copies the values from mat2dToCreateMatrixFrom mat2d into mat2dToCreateMatrixFrom mat3
         *
         * @param {mat3} inOut the receiving matrix
         * @param {mat2d} mat2dToCreateMatrixFrom the matrix to copy
         * @returns {mat3} inOut
         **/
        fun fromMat2d(inOut: Float32Array, mat2dToCreateMatrixFrom: Float32Array): Float32Array {
            inOut[0] = mat2dToCreateMatrixFrom[0]
            inOut[1] = mat2dToCreateMatrixFrom[1]
            inOut[2] = 0.0f
            inOut[3] = mat2dToCreateMatrixFrom[2]
            inOut[4] = mat2dToCreateMatrixFrom[3]
            inOut[5] = 0.0f
            inOut[6] = mat2dToCreateMatrixFrom[4]
            inOut[7] = mat2dToCreateMatrixFrom[5]
            inOut[8] = 1.0f
            return inOut
        }

        /**
         * Calculates a 3x3 matrix from the given quaternion
         *
         * @param {mat3} inOut mat3 receiving operation result
         * @param {quat} quatToCreateMatrixFrom Quaternion to create matrix from
         *
         * @returns {mat3} inOut
         */
        fun fromQuat(inOut: Float32Array, quatToCreateMatrixFrom: Array<Double>): Float32Array {
            val x = quatToCreateMatrixFrom[0].toFloat()
            val y = quatToCreateMatrixFrom[1].toFloat()
            val z = quatToCreateMatrixFrom[2].toFloat()
            val w = quatToCreateMatrixFrom[3].toFloat()
            val x2 = x + x
            val y2 = y + y
            val z2 = z + z
            val xx = x * x2
            val yx = y * x2
            val yy = y * y2
            val zx = z * x2
            val zy = z * y2
            val zz = z * z2
            val wx = w * x2
            val wy = w * y2
            val wz = w * z2
            inOut[0] = 1 - yy - zz
            inOut[3] = yx - wz
            inOut[6] = zx + wy
            inOut[1] = yx + wz
            inOut[4] = 1 - xx - zz
            inOut[7] = zy - wx
            inOut[2] = zx - wy
            inOut[5] = zy + wx
            inOut[8] = 1 - xx - yy
            return inOut
        }

        /**
         * Calculates mat4ToDeriveNormalMatrix 3x3 normal matrix (transpose inverse) from the 4x4 matrix
         *
         * @param {mat3} inOut mat3 receiving operation result
         * @param {mat4} mat4ToDeriveNormalMatrix Mat4 to derive the normal matrix from
         *
         * @returns {mat3} inOut
         */
        fun normalFromMat4(inOut: Float32Array, mat4ToDeriveNormalMatrix: Float32Array): Float32Array {
            val a00 = mat4ToDeriveNormalMatrix[0]
            val a01 = mat4ToDeriveNormalMatrix[1]
            val a02 = mat4ToDeriveNormalMatrix[2]
            val a03 = mat4ToDeriveNormalMatrix[3]
            val a10 = mat4ToDeriveNormalMatrix[4]
            val a11 = mat4ToDeriveNormalMatrix[5]
            val a12 = mat4ToDeriveNormalMatrix[6]
            val a13 = mat4ToDeriveNormalMatrix[7]
            val a20 = mat4ToDeriveNormalMatrix[8]
            val a21 = mat4ToDeriveNormalMatrix[9]
            val a22 = mat4ToDeriveNormalMatrix[10]
            val a23 = mat4ToDeriveNormalMatrix[11]
            val a30 = mat4ToDeriveNormalMatrix[12]
            val a31 = mat4ToDeriveNormalMatrix[13]
            val a32 = mat4ToDeriveNormalMatrix[14]
            val a33 = mat4ToDeriveNormalMatrix[15]
            val b00 = a00 * a11 - a01 * a10
            val b01 = a00 * a12 - a02 * a10
            val b02 = a00 * a13 - a03 * a10
            val b03 = a01 * a12 - a02 * a11
            val b04 = a01 * a13 - a03 * a11
            val b05 = a02 * a13 - a03 * a12
            val b06 = a20 * a31 - a21 * a30
            val b07 = a20 * a32 - a22 * a30
            val b08 = a20 * a33 - a23 * a30
            val b09 = a21 * a32 - a22 * a31
            val b10 = a21 * a33 - a23 * a31
            val b11 = a22 * a33 - a23 * a32
            // Calculate the determinant
            var det = b00 * b11 - b01 * b10 + b02 * b09 + b03 * b08 - b04 * b07 + b05 * b06
            if (det < 0) {
                return Float32Array(0)
            }
            det = (1.0 / det).toFloat()
            inOut[0] = (a11 * b11 - a12 * b10 + a13 * b09) * det
            inOut[1] = (a12 * b08 - a10 * b11 - a13 * b07) * det
            inOut[2] = (a10 * b10 - a11 * b08 + a13 * b06) * det
            inOut[3] = (a02 * b10 - a01 * b11 - a03 * b09) * det
            inOut[4] = (a00 * b11 - a02 * b08 + a03 * b07) * det
            inOut[5] = (a01 * b08 - a00 * b10 - a03 * b06) * det
            inOut[6] = (a31 * b05 - a32 * b04 + a33 * b03) * det
            inOut[7] = (a32 * b02 - a30 * b05 - a33 * b01) * det
            inOut[8] = (a30 * b04 - a31 * b02 + a33 * b00) * det
            return inOut
        }

        /**
         * Generates a 2D projection matrix with the given bounds
         *
         * @param {mat3} inOut mat3 frustum matrix will be written into
         * @param {number} glContextWith Width of your gl context
         * @param {number} glContextHeight Height of gl context
         * @returns {mat3} inOut
         */
        fun projection(inOut: Float32Array, glContextWith: Double, glContextHeight: Double): Float32Array {
            inOut[0] = (2 / glContextWith).toFloat()
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = 0.0f
            inOut[4] = (-2 / glContextHeight).toFloat()
            inOut[5] = 0.0f
            inOut[6] = -1.0f
            inOut[7] = 1.0f
            inOut[8] = 1.0f
            return inOut
        }

        /**
         * Returns a string representation of a mat3
         *
         * @param {mat3} a matrix to represent as a string
         * @returns {String} string representation of the matrix
         */
        fun toString(matrix: Float32Array): String {
            return "mat2d(${matrix[0]}, ${matrix[1]}, ${matrix[2]}, " +
                    "${matrix[3]}, ${matrix[4]}, ${matrix[5]}," +
                    " ${matrix[6]}, ${matrix[7]}, ${matrix[8]})"
        }

        /**
         * Returns Frobenius norm of a mat3
         *
         * @param {mat3} a the matrix to calculate Frobenius norm of
         * @returns {Number} Frobenius norm
         */
        fun frob(matrix: Float32Array): Double {
            return (Math.sqrt(Math.pow(matrix[0].toDouble(), 2.0) + Math.pow(matrix[1].toDouble(), 2.0) + Math.pow(matrix[2].toDouble(), 2.0)
                    + Math.pow(matrix[3].toDouble(), 2.0) + Math.pow(matrix[4].toDouble(), 2.0) + Math.pow(matrix[5].toDouble(), 2.0)
                    + Math.pow(matrix[6].toDouble(), 2.0) + Math.pow(matrix[7].toDouble(), 2.0) + Math.pow(matrix[8].toDouble(), 2.0)))
        }

        /**
         * Adds two mat3's
         *
         * @param {mat3} inOut the receiving matrix
         * @param {mat3} firstSummand the first operand
         * @param {mat3} secondSummand the second operand
         * @returns {mat3} inOut
         */
        fun add(inOut: Float32Array, firstSummand: Float32Array, secondSummand: Float32Array): Float32Array {
            inOut[0] = firstSummand[0] + secondSummand[0]
            inOut[1] = firstSummand[1] + secondSummand[1]
            inOut[2] = firstSummand[2] + secondSummand[2]
            inOut[3] = firstSummand[3] + secondSummand[3]
            inOut[4] = firstSummand[4] + secondSummand[4]
            inOut[5] = firstSummand[5] + secondSummand[5]
            inOut[6] = firstSummand[6] + secondSummand[6]
            inOut[7] = firstSummand[7] + secondSummand[7]
            inOut[8] = firstSummand[8] + secondSummand[8]
            return inOut
        }

        /**
         * Subtracts matrix subtrahend from matrix minuend
         *
         * @param {mat3} inOut the receiving matrix
         * @param {mat3} minuend the first operand
         * @param {mat3} subtrahend the second operand
         * @returns {mat3} inOut
         */
        fun subtract(inOut: Float32Array, minuend: Float32Array, subtrahend: Float32Array): Float32Array {
            inOut[0] = minuend[0] - subtrahend[0]
            inOut[1] = minuend[1] - subtrahend[1]
            inOut[2] = minuend[2] - subtrahend[2]
            inOut[3] = minuend[3] - subtrahend[3]
            inOut[4] = minuend[4] - subtrahend[4]
            inOut[5] = minuend[5] - subtrahend[5]
            inOut[6] = minuend[6] - subtrahend[6]
            inOut[7] = minuend[7] - subtrahend[7]
            inOut[8] = minuend[8] - subtrahend[8]
            return inOut
        }

        /**
         * Multiply each element of the matrix by matrixToScale scalar.
         *
         * @param {mat3} inOut the receiving matrix
         * @param {mat3} matrixToScale the matrix to scale
         * @param {Number} amountToScaleBy amount to scale the matrix's elements by
         * @returns {mat3} inOut
         */
        fun multiplyScalar(inOut: Float32Array, matrixToScale: Float32Array, amountToScaleBy: Double): Float32Array {
            val amountToScaleTheMatrix = amountToScaleBy.toFloat()
            inOut[0] = matrixToScale[0] * amountToScaleTheMatrix
            inOut[1] = matrixToScale[1] * amountToScaleTheMatrix
            inOut[2] = matrixToScale[2] * amountToScaleTheMatrix
            inOut[3] = matrixToScale[3] * amountToScaleTheMatrix
            inOut[4] = matrixToScale[4] * amountToScaleTheMatrix
            inOut[5] = matrixToScale[5] * amountToScaleTheMatrix
            inOut[6] = matrixToScale[6] * amountToScaleTheMatrix
            inOut[7] = matrixToScale[7] * amountToScaleTheMatrix
            inOut[8] = matrixToScale[8] * amountToScaleTheMatrix
            return inOut
        }

        /**
         * Adds two mat3's after multiplying each element of the second operand by firstSummand scalar value.
         *
         * @param {mat3} inOut the receiving vector
         * @param {mat3} firstSummand the first operand
         * @param {mat3} secondSummand the second operand
         * @param {Number} amountToScale the amount to amountToScale secondSummand's elements by before adding
         * @returns {mat3} inOut
         */
        fun multiplyScalarAndAdd(inOut: Float32Array, firstSummand: Float32Array, secondSummand: Float32Array, amountToScale: Double): Float32Array {
            val amountToScaleTheMatrix = amountToScale.toFloat()
            inOut[0] = firstSummand[0] + (secondSummand[0] * amountToScaleTheMatrix)
            inOut[1] = firstSummand[1] + (secondSummand[1] * amountToScaleTheMatrix)
            inOut[2] = firstSummand[2] + (secondSummand[2] * amountToScaleTheMatrix)
            inOut[3] = firstSummand[3] + (secondSummand[3] * amountToScaleTheMatrix)
            inOut[4] = firstSummand[4] + (secondSummand[4] * amountToScaleTheMatrix)
            inOut[5] = firstSummand[5] + (secondSummand[5] * amountToScaleTheMatrix)
            inOut[6] = firstSummand[6] + (secondSummand[6] * amountToScaleTheMatrix)
            inOut[7] = firstSummand[7] + (secondSummand[7] * amountToScaleTheMatrix)
            inOut[8] = firstSummand[8] + (secondSummand[8] * amountToScaleTheMatrix)
            return inOut
        }

        /**
         * Returns whether or not the matrices have exactly the same elements in the same position (when compared with ===)
         *
         * @param {mat3} firstMatrix The first matrix.
         * @param {mat3} secondMatrix The second matrix.
         * @returns {Boolean} True if the matrices are equal, false otherwise.
         */
        fun exactEquals(firstMatrix: Float32Array, secondMatrix: Float32Array): Boolean {
            return firstMatrix[0] == secondMatrix[0] && firstMatrix[1] == secondMatrix[1] && firstMatrix[2] == secondMatrix[2] &&
                    firstMatrix[3] == secondMatrix[3] && firstMatrix[4] == secondMatrix[4] && firstMatrix[5] == secondMatrix[5] &&
                    firstMatrix[6] == secondMatrix[6] && firstMatrix[7] == secondMatrix[7] && firstMatrix[8] == secondMatrix[8]
        }

        /**
         * Returns whether or not the matrices have approximately the same elements in the same position.
         *
         * @param {mat3} firstMatrix The first matrix.
         * @param {mat3} secondMatrix The second matrix.
         * @returns {Boolean} True if the matrices are equal, false otherwise.
         */
        fun equals(firstMatrix: Float32Array, secondMatrix: Float32Array): Boolean {
            val a0 = firstMatrix[0].toDouble()
            val a1 = firstMatrix[1].toDouble()
            val a2 = firstMatrix[2].toDouble()
            val a3 = firstMatrix[3].toDouble()
            val a4 = firstMatrix[4].toDouble()
            val a5 = firstMatrix[5].toDouble()
            val a6 = firstMatrix[6].toDouble()
            val a7 = firstMatrix[7].toDouble()
            val a8 = firstMatrix[8].toDouble()
            val b0 = secondMatrix[0].toDouble()
            val b1 = secondMatrix[1].toDouble()
            val b2 = secondMatrix[2].toDouble()
            val b3 = secondMatrix[3].toDouble()
            val b4 = secondMatrix[4].toDouble()
            val b5 = secondMatrix[5].toDouble()
            val b6 = secondMatrix[6].toDouble()
            val b7 = secondMatrix[7].toDouble()
            val b8 = secondMatrix[8].toDouble()
            return (Math.abs(a0 - b0) <= EPSILON * Math.max(1.0, Math.abs(a0), Math.abs(b0)) &&
                    Math.abs(a1 - b1) <= EPSILON * Math.max(1.0, Math.abs(a1), Math.abs(b1)) &&
                    Math.abs(a2 - b2) <= EPSILON * Math.max(1.0, Math.abs(a2), Math.abs(b2)) &&
                    Math.abs(a3 - b3) <= EPSILON * Math.max(1.0, Math.abs(a3), Math.abs(b3)) &&
                    Math.abs(a4 - b4) <= EPSILON * Math.max(1.0, Math.abs(a4), Math.abs(b4)) &&
                    Math.abs(a5 - b5) <= EPSILON * Math.max(1.0, Math.abs(a5), Math.abs(b5)) &&
                    Math.abs(a6 - b6) <= EPSILON * Math.max(1.0, Math.abs(a6), Math.abs(b6)) &&
                    Math.abs(a7 - b7) <= EPSILON * Math.max(1.0, Math.abs(a7), Math.abs(b7)) &&
                    Math.abs(a8 - b8) <= EPSILON * Math.max(1.0, Math.abs(a8), Math.abs(b8)))
        }
    }
}