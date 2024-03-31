package task3

class People(val name: String, var money: Int, var place: Place) {
    fun changePlace(place: Place) {
        println("$name находились в ${place.name}")
        this.place = place
    }

    fun wantToPay(): Boolean {
        if (money > 0) {
            println("$name хотели за это заплатить")
            return true
        }
        return false
    }
}