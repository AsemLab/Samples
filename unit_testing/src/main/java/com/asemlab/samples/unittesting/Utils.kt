package com.asemlab.samples.unittesting

fun Car.isFast() = speed > 200

fun printAll(vararg objs: Any) {
    objs.forEach {
        println(it)
    }
}