package webgl

import org.khronos.webgl.WebGLRenderingContext
import kotlin.browser.window
import kotlin.js.Math



fun WebGLRenderingContext.fitDrawingBufferIntoCanvas() {
    val realToCSSPixels = window.devicePixelRatio // For Retina Displays etc where CSS pixel != device pixel

    // Lookup the size the browser is displaying the canvas in CSS pixels
    // and compute a size needed to make our drawing buffer match it in
    // device pixels.
    val displayWidth = Math.floor(this.canvas.clientWidth * realToCSSPixels)
    val displayHeight = Math.floor(this.canvas.clientHeight * realToCSSPixels)

    // Check if the canvas is not the same size.
    if (this.canvas.width != displayWidth || this.canvas.height != displayHeight) {

        // Make the canvas the same size (or double the size
        this.canvas.width = displayWidth
        this.canvas.height = displayHeight

        this.viewport(0, 0, displayWidth, displayHeight)

    }
}