package task3

class Shadows(val name: String) {
    var isAlive = true

    fun murder(shadows: Shadows, place: Place) {
        if (!shadows.isAlive) {
            error("${shadows.name} уже мертвы")
        }
        println("$name убивали ${shadows.name}")
        place.changeSounds(Sounds.звуки_убийств)
        shadows.isAlive = false
    }
}