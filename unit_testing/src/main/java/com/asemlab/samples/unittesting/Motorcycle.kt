package com.asemlab.samples.unittesting

class Motorcycle {

    lateinit var manufacturer: String
    lateinit var direction: Directions

    constructor(manufacturer: String) {

        this.manufacturer = manufacturer

        println("Constructor 1")

    }

    fun drive(newDirection: Directions): String? {
        direction = newDirection

        return "Motorcycle drives toward $direction"
    }

    fun printStatus(){

        println("Made by $manufacturer")
    }

    private fun accelerate() = "going faster"

    fun driveFaster() = accelerate()

}