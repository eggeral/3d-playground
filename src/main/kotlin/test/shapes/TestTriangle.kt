package test.shapes

import spr5.scene.Coordinate
import spr5.scene.SceneTriangle
import spr5.scene.Rgba
import test.TestClass
import spr5.util.*


class TestTriangle() : TestClass() {
    private var v1: Array<Double> = emptyArray();
    private var triangleColor : Rgba = setColor();
    private var testTriangle : SceneTriangle = setUp();


    @Before
    fun setUp() : SceneTriangle {
        val point1 = Coordinate(50.0f, 50.0f, 50.0f)
        val point2 = Coordinate (100.0f, 100.0f, 100.0f)
        val point3 = Coordinate (-50.0f, -50.0f, -50.0f)
        val vertices = arrayOf(
                point1, point2, point3)
        return SceneTriangle(vertices, this.triangleColor);
    }

    @After
    fun tearDown() {
    }
    fun setColor() : Rgba {
        return Rgba(1.0f, 0.5f, 0.7f, 0.5f);
    }

    public fun TestObjectCreation(){
        assertNotNull(setUp())
    }


    public fun TestTriangleColor(){
        val wrongTestColor = Rgba(1.0f, 0.5f, 0.7f, 1.0f);
        val wrongObjectColor = Rgba(1.0f, 0.5f, 0.7f, 0.5f);
        assertNotEquals(wrongTestColor, testTriangle.color)
        assertNotEquals(wrongObjectColor, testTriangle.color)
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
        assertNotEquals(vert, testTriangle.vertices)
    }

    public fun TestGetVertices(){
        val p1 = Coordinate(50.0f, 50.0f, 50.0f)
        val p2 = Coordinate (100.0f, 100.0f, 100.0f)
        val p3 = Coordinate (-50.0f, -50.0f, -50.0f)
        val vert = arrayOf(
                p1, p2, p3)

        // I would expect the getVertices returning Array<Coordinate> same as constructor param, and not Array<Float>
        assertNotEquals(vert.size, testTriangle.getVertices().size)
    }

    public fun TestGetColor(){
        val triCol = Rgba(1.0f, 0.5f, 0.7f, 0.5f);

        assertNotEquals(triCol, testTriangle.getColors())
        assertEquals(triCol.red, testTriangle.getColors()[0])
        assertEquals(triCol.green, testTriangle.getColors()[1])
        assertEquals(triCol.blue, testTriangle.getColors()[2])
        assertEquals(triCol.alpha, testTriangle.getColors()[3])
        assertNotEquals( 0.9f, testTriangle.getColors()[0])
    }
}



