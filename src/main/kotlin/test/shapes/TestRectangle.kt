package test.shapes

import spr5.scene.Coordinate
import spr5.scene.SceneRectangle
import spr5.scene.Rgba
import spr5.scene.createSquare
import test.annotations.*
import spr5.util.*


class TestRectangle(){
    private var testRectangle : SceneRectangle = setUp()

    @Before
    fun setUp() : SceneRectangle {
        var color : Rgba = setColor(1.0f, 0.5f, 0.7f, 0.5f);
        val centerPoint = setCoordinate(50.0f, 50.0f, 50.0f)
        return SceneRectangle(centerPoint, 50.0f, 50.0f, color)
    }
    @After
    fun tearDown() {
        //testRectangle = null;
    }

    fun setColor( red: Float, green : Float, blue : Float, alpha : Float ) : Rgba {
        return Rgba(red, green, blue, alpha)
    }
    fun setCoordinate(point1 : Float, point2 : Float, point3: Float) : Coordinate {
        return Coordinate(point1, point2, point3)
    }

    public fun TestObjectCreation(){
        assertNotNull(testRectangle)
    }

    public fun TestColors(){
        val testColor = setColor(1.0f, 0.5f, 0.7f, 0.5f)
        val wrongObjectColor = Rgba(0.9f, 0.4f, 0.6f, 0.4f)
        assertEquals(testColor.red, testRectangle.getColors()[0])
        assertEquals(testColor.green, testRectangle.getColors()[1])
        assertEquals(testColor.blue, testRectangle.getColors()[2])
        assertEquals(testColor.alpha, testRectangle.getColors()[3])
    }

    public fun TestVertices(){
        assertEquals(12, testRectangle.getVertices().size)
        assertEquals(25, testRectangle.getVertices()[0])
        assertEquals(75, testRectangle.getVertices()[1])
        assertEquals(50, testRectangle.getVertices()[2])
        assertEquals(75, testRectangle.getVertices()[3])
        assertEquals(75, testRectangle.getVertices()[4])
        assertEquals(50, testRectangle.getVertices()[5])
        assertEquals(25, testRectangle.getVertices()[6])
        assertEquals(25, testRectangle.getVertices()[7])
        assertEquals(50, testRectangle.getVertices()[8])
        assertEquals(75, testRectangle.getVertices()[9])
        assertEquals(25, testRectangle.getVertices()[10])
        assertEquals(50, testRectangle.getVertices()[11])
    }

    public fun TestCreateSquare(){
        var centerPoint = setCoordinate(50.0f, 50.0f, 50.0f)
        var color : Rgba = setColor(1.0f, 0.5f, 0.7f, 0.5f)
        var square = createSquare(centerPoint, 50.0f, color)
        val testColor = setColor(1.0f, 0.5f, 0.7f, 0.5f)
        assertEquals(50, square.heigth);
        assertEquals(50, square.width);
        assertEquals(square.heigth, square.width);
        assertEquals(testColor.red, square.getColors()[0])
        assertEquals(25, square.getVertices()[0])
    }
}

