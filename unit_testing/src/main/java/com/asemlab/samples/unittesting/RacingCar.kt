package com.asemlab.samples.unittesting

object RacingCar {

    var direction: Directions = Directions.NORTH

    fun drive(newDirection: Directions): String {
        direction = newDirection

        return "Car drives toward $direction"
    }


}