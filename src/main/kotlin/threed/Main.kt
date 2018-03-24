package threed

//import example.projection.drawOrthographicVsPerspectiveCubes
import example.projection.drawOrthographicVsPerspectiveCubes
import webgl.createWebGLRenderingContext
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLDivElement
import spr5.matrix.Mat4
import threed.example.drawExample
import webgl.fitDrawingBufferIntoCanvas
import kotlin.browser.document


fun main(args: Array<String>) {
    drawExampleOneCanvas(args)
    //drawExampleTwoCanvas(args)
}

@JsName("drawExampleOneCanvas")
fun drawExampleOneCanvas(args: Array<String>) {

    val container = document.getElementById("container") as HTMLDivElement
    val canvas = document.createElement("canvas") as HTMLCanvasElement
    canvas.style.height = "92%"
    canvas.style.width= "97%"

    val webGlContext = createWebGLRenderingContext(canvas)

    container.appendChild(canvas)

    drawExample(webGlContext)
}


fun drawExampleTwoCanvas(args: Array<String>) {
    val container = document.getElementById("container") as HTMLDivElement
    val canvas1 = document.createElement("canvas") as HTMLCanvasElement
    canvas1.style.height = "100%"
    canvas1.style.width = "50%"
    val canvas2 = document.createElement("canvas") as HTMLCanvasElement
    canvas2.style.height = "100%"
    canvas2.style.width = "50%"

    val webGlContext1 = createWebGLRenderingContext(canvas1)
    val webGlContext2 = createWebGLRenderingContext(canvas2)

    container.appendChild(canvas1)
    container.appendChild(canvas2)

    webGlContext1.fitDrawingBufferIntoCanvas()

    drawOrthographicVsPerspectiveCubes(webGlContext1,
            Mat4.perspective(40.0.asRad,
                    (webGlContext1.canvas.width.toDouble() / webGlContext1.canvas.height.toDouble()),
                    1.0, 100.0, Mat4()).toFloat32Array()
    )
    webGlContext2.fitDrawingBufferIntoCanvas()

    drawOrthographicVsPerspectiveCubes(webGlContext2,
            Mat4.ortho(
                    -5.0, 5.0,
                    -5.0, 5.0,
                    0.1, 100.0, Mat4()).toFloat32Array()
    )
}



