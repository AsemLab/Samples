package com.asemlab.samples.unittesting

data class Car(
    var speed: Int,
    var direction: Directions
) {
    fun drive(newDirection: Directions): String? {
        direction = newDirection

        return "Car drives toward $direction"
    }

    fun printStatus() {

        println("Car drives toward $direction at $speed")
    }

    suspend fun getCarDetails(): String {
        return "Car(Direction=$direction, Speed=$speed)"
    }

    suspend fun printOnlineStatus() {

        println("Car drives toward $direction at $speed")
    }


}

