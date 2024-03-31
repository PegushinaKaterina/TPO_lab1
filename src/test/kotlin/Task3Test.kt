import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import task3.Lighting
import task3.NaturalPhenomenon
import task3.People
import task3.Place
import task3.Shadows
import task3.Sounds
import kotlin.test.assertEquals

class Task3Test {


    @Test
    fun wantToPayTest() {
        val place = Place("текущее место")
        val people = People("люди", 1, place)
        assertTrue { people.wantToPay() }

        people.money = 0
        assertFalse { people.wantToPay() }
    }

    @Test
    fun changesPlace() {
        val place = Place("текущее место")
        val people = People("люди", 1, place)

        val newPlace = Place("новое место")

        people.changePlace(newPlace)

        assertEquals(newPlace, people.place)
    }

    @Test
    fun curlAroundTest() {
        val place = Place("текущее место")
        place.currentLighting = Lighting.свет
        val people = People("люди", 1, place)
        val naturalPhenomenon = NaturalPhenomenon("темный туман")

        assertFalse { naturalPhenomenon.curlAround(people, place) }

        place.plungeInto(Lighting.мрак)

        assertTrue { naturalPhenomenon.curlAround(people, place) }
    }

    @Test
    fun plungeIntoTest() {
        val place = Place("текущее место")
        place.currentLighting = Lighting.свет

        assertThrows<IllegalStateException> { place.plungeInto(Lighting.свет) }
    }

    @Test
    fun changeSoundsTest() {
        val place = Place("текущее место")
        place.currentSounds = Sounds.звуки_убийств

        assertThrows<IllegalStateException> { place.changeSounds(Sounds.звуки_убийств) }
    }

    @Test
    fun murderTest() {
        val place = Place("текущее место")

        val shadows2 = Shadows("призрачные существа")
        val shadows3 = Shadows("другие призрачные существа")

        assertDoesNotThrow { shadows2.murder(shadows3, place) }
        assertFalse { shadows3.isAlive }
        assertThrows<IllegalStateException> { shadows2.murder(shadows3, place) }
    }
}