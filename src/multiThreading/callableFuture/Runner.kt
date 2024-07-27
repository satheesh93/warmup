package multiThreading.callableFuture

import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors
import java.util.concurrent.TimeoutException

class Runner {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val executor = Executors.newCachedThreadPool()
            val future = executor.submit<Int>
            {
                val duration: Int = (0..4000).random()
                if (duration > 2000) {
                    throw TimeoutException("Sleeping for too long.")
                }

                println("Starting ...")
                try {
                    Thread.sleep(duration.toLong())
                } catch (ignored: InterruptedException) {
                }
                println("Finished.")
                duration
            }

            executor.shutdown()
            //        executor.awaitTermination(1, TimeUnit.DAYS);
            try {
                println("Result is: " + future.get())
            } catch (ignored: InterruptedException) {
            } catch (e: ExecutionException) {
                val ex = e.cause as TimeoutException?
                println(ex!!.message)
            }
        }
    }
}