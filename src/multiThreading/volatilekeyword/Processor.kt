package multiThreading.volatilekeyword

import kotlin.concurrent.Volatile

class Processor : Thread() {

    @Volatile
    private var running = true

    override fun run() {
        while (running) {
            println("Running")

            try {
                sleep(50)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    fun shutdown() {
        running = false
    }
}