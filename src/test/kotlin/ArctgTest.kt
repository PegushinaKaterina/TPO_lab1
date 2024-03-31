import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.atan
import kotlin.math.roundToInt

class ArctgTest {
    private val arctg = Arctg()

    @ParameterizedTest
    @MethodSource("provideArguments")
    fun testArctg(
        x: Double,
        expected: Double,
    ) {
        val actual = arctg.calc(x, n)

        compareResult(expected, actual)
    }

    @Test
    fun testN0Ok() {
        val x = -0.5
        val expected = 0.0
        val actual = arctg.calc(x, 0)

        compareResult(expected, actual)
    }

    @Test
    fun testMoreThanPositive1Exception() {
        val x = 2.0

        assertThrows<IllegalArgumentException> { arctg.calc(x, n) }
    }

    @Test
    fun testLessThanNegative1Exception() {
        val x = -2.0
        assertThrows<IllegalArgumentException> { arctg.calc(x, n) }
    }

    @Test
    fun testNegativeNException() {
        val x = -0.5
        val actual = arctg.calc(x, n)


        assertThrows<IllegalArgumentException> { arctg.calc(x, -1) }
    }

    private fun compareResult(expected: Double, actual: Double) {
        val expectedRounded = (expected * errorMargin).roundToInt()
        val actualRounded = (actual * errorMargin).roundToInt()
        assertEquals(expectedRounded, actualRounded)
    }

    companion object {
        private const val errorMargin = 10000
        private const val n = 50001

        @JvmStatic
        private fun provideArguments(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    0.0,
                    atan(0.0),
                ),
                Arguments.of(
                    1.0,
                    atan(1.0),
                ),
                Arguments.of(
                    -1.0,
                    atan(-1.0),
                ),
                Arguments.of(
                    0.5,
                    atan(0.5),
                ),
                Arguments.of(
                    -0.5,
                    atan(-0.5),
                ),
            )
        }
    }
}