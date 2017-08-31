package threed

import org.khronos.webgl.WebGLRenderingContext
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLDivElement
import kotlin.browser.document
import kotlin.browser.window


fun main(args: Array<String>) {

    val container = document.getElementById("container") as HTMLDivElement
    val canvas = document.createElement("canvas") as HTMLCanvasElement
    canvas.style.height = "100%"

    val webGlContext = canvas.getContext("webgl")


    if (webGlContext == null) {

        val text = document.createTextNode("WebGl not available in this browser!")
        container.appendChild(text)

    } else {

        container.appendChild(canvas)
        webGlContext as WebGLRenderingContext

        fun render() {

            webGlContext.viewport(0, 0, canvas.width, canvas.height)
            drawTriangle(webGlContext)
            window.requestAnimationFrame { render() }

        }
        render()

    }

}


