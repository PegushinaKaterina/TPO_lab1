package task3

class NaturalPhenomenon(val name: String) {
    fun curlAround(people: People, place: Place): Boolean {
        if (place.currentLighting == Lighting.мрак) {
            println("$name вился вокруг ${people.name}")
            return true
        }
        return false
    }
}