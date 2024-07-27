package multiThreading.reentrantlocks

import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock


class Runner {

    private var count = 0
    private val lock: Lock = ReentrantLock()
    private val cond: Condition = lock.newCondition()

    private fun increment() {
        for (i in 0..9999) {
            count++
        }
    }

    @Throws(InterruptedException::class)
    fun firstThread() {
        lock.lock()
        println("Waiting ....")
        cond.await()
        println("Woken up!")
        try {
            increment()
        } finally {
            lock.unlock()
        }
    }

    @Throws(InterruptedException::class)
    fun secondThread() {
        Thread.sleep(1000)
        lock.lock()
        println("Press the return key!")
        readln()
        println("Got return key!")
        cond.signal()
        try {
            increment()
        } finally {
            //should be written to unlock Thread whenever signal() is called
            lock.unlock()
        }
    }

    fun finished() {
        println("Count is: $count")
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val runner = Runner()
            val t1 = Thread {
                try {
                    runner.firstThread()
                } catch (ignored: InterruptedException) {
                }
            }

            val t2 = Thread {
                try {
                    runner.secondThread()
                } catch (ignored: InterruptedException) {
                }
            }

            t1.start()
            t2.start()
            t1.join()
            t2.join()
            runner.finished()
        }
    }
}