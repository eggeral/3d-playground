package test.shapes

import spr5.scene.Coordinate
import spr5.scene.SceneTriangle
import spr5.scene.Rgba
import spr5.util.*
import test.annotations.*


class TestTriangle{
    private var triangleColor : Rgba = setColor()
    private var testTriangle : SceneTriangle = setUp()


    @Before
    fun setUp() : SceneTriangle {
        val point1 = Coordinate(50.0f, 50.0f, 50.0f)
        val point2 = Coordinate (100.0f, 100.0f, 100.0f)
        val point3 = Coordinate (-50.0f, -50.0f, -50.0f)
        val vertices = arrayOf(
                point1, point2, point3)
        return SceneTriangle(vertices, triangleColor)
    }

    @After
    fun tearDown() {
    }
    fun setColor() : Rgba {
        return Rgba(1.0f, 0.5f, 0.7f, 0.5f);
    }

    public fun TestObjectCreation(){
        assertNotNull(testTriangle)
    }


    public fun TestTriangleColor(){
        assertEquals(triangleColor, testTriangle.color)
    }

    @Test public fun TestConstructionPoints(){
        val p1 = Coordinate(50.0f, 50.0f, 50.0f)
        val p2 = Coordinate (100.0f, 100.0f, 100.0f)
        val p3 = Coordinate (-50.0f, -50.0f, -50.0f)
        val vert = arrayOf(
                p1, p2, p3)
        val vertices = testTriangle.vertices
        assertEquals(vertices, testTriangle.vertices)
        //not
        //assertNotEquals(vert, testTriangle.vertices)
    }

    public fun TestGetVertices(){
        val p1 = Coordinate(50.0f, 50.0f, 50.0f)
        val p2 = Coordinate (100.0f, 100.0f, 100.0f)
        val p3 = Coordinate (-50.0f, -50.0f, -50.0f)
        val vert = arrayOf(
                p1, p2, p3)

    }

    public fun TestGetColor(){
        val triCol = Rgba(1.0f, 0.5f, 0.7f, 0.5f)

        assertEquals(triCol.red, testTriangle.getColors()[0])
        assertEquals(triCol.green, testTriangle.getColors()[1])
        assertEquals(triCol.blue, testTriangle.getColors()[2])
        assertEquals(triCol.alpha, testTriangle.getColors()[3])
    }
}



