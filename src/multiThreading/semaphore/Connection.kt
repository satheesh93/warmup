package multiThreading.semaphore

import java.util.concurrent.Executors
import java.util.concurrent.Semaphore
import java.util.concurrent.TimeUnit


class Connection {
    companion object {

        private val instance: Connection = Connection()

        fun getInstance(): Connection {
            return instance
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val executor = Executors.newCachedThreadPool()

            for (i in 0..199) {
                executor.submit { getInstance().connect() }
            }

            executor.shutdown()
            executor.awaitTermination(1, TimeUnit.DAYS)
        }
    }

    private val sem = Semaphore(10, true)


    fun connect() {
        try {
            sem.acquire()

            println("${Thread.currentThread().name} : Current connections (max 10 allowed):  ${sem.availablePermits()}")

            println("${Thread.currentThread().name} : WORKING...")
            Thread.sleep(2000)

            println("${Thread.currentThread().name} : Connection released. Permits Left = ${sem.availablePermits()}")
        } catch (ignored: InterruptedException) {
        } finally {
            sem.release()
        }
    }
}