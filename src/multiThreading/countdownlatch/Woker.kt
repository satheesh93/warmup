package multiThreading.countdownlatch

import java.util.concurrent.CountDownLatch

class Worker(private val latch: CountDownLatch) : Runnable {
    override fun run() {
        try {
            Thread.sleep(1000)
            println(Thread.currentThread().name + " finished work.")
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } finally {
            latch.countDown()
        }
    }

    companion object{
        @JvmStatic
        fun main(args:Array<String>){
            val latch = CountDownLatch(3)
            for (i in 0..2) {
                Thread(Worker(latch)).start()
            }
            try {
                latch.await()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            println("All workers have finished.")
        }
    }
}