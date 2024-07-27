package multiThreading.threadpools

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class Processor(private val id: Int) : Runnable {
    override fun run() {
        println("Starting: $id")
        try {
            Thread.sleep(5000)
        } catch (ignored: InterruptedException) {
        }
        println("Completed: $id")
    }

    companion object{
        @JvmStatic
        fun main(args:Array<String>){
            val executor = Executors.newFixedThreadPool(2) //2 Threads
            for (i in 0..10) {
                executor.submit(Processor(i))
            }
            executor.shutdown()
            println("All tasks submitted.")
            try {
                executor.awaitTermination(1, TimeUnit.DAYS)
            } catch (ignored: InterruptedException) {
            }
            println("All tasks completed.")
        }
    }
}