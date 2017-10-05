package threed

import webgl.createWebGLRenderingContext
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLDivElement
import threed.example.drawExample
import webgl.fitDrawingBufferIntoCanvas
import kotlin.browser.document


fun main(args: Array<String>) {

    val container = document.getElementById("container") as HTMLDivElement
    val canvas = document.createElement("canvas") as HTMLCanvasElement
    canvas.style.height = "100%"

    val webGlContext = createWebGLRenderingContext(canvas)

    container.appendChild(canvas)

    webGlContext.fitDrawingBufferIntoCanvas()

    drawExample(webGlContext)


}


