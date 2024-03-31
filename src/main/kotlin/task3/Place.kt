package task3

class Place(val name: String) {
    var currentLighting = Lighting.свет
    var currentSounds = Sounds.тишина

    fun plungeInto(lighting: Lighting) {
        if (currentLighting == lighting) {
            error("место уже находится в ${lighting.name}")
        }
        currentLighting = lighting
        println("$name погрузилось во ${lighting.name}")
    }

    fun changeSounds(sounds: Sounds) {
        if (currentSounds == sounds) {
            error("место уже наполненно ${sounds.name}")
        }
        currentSounds = sounds
        println("$name наполнилось ${sounds.name}")
    }
}