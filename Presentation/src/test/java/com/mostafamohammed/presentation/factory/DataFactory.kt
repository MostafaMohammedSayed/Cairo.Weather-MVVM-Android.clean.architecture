package com.mostafamohammed.presentation.factory

import java.util.*
import java.util.concurrent.ThreadLocalRandom

object DataFactory {
    fun randomString(): String{
        return UUID.randomUUID().toString()
    }
    fun randomDouble(): Double{
        return ThreadLocalRandom.current().nextDouble(0.0,1000.0+1)
    }
}