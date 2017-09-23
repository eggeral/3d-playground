package webgl

import org.khronos.webgl.WebGLRenderingContext
import org.w3c.dom.HTMLCanvasElement

fun createWebGLRenderingContext(canvas: HTMLCanvasElement): WebGLRenderingContext {
    val webGlContext = canvas.getContext("webgl")

    if (webGlContext == null)
        console.error("WebGL not available in this browser!")
    else
        console.info("WebGlRenderingContext successfully created!")

    return webGlContext as WebGLRenderingContext
}