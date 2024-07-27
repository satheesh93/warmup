package multiThreading.synchronizedKeyword

class Worker {
    private var count = 0

    @Synchronized
    @Throws(InterruptedException::class)
    fun increment(threadName: String) {
        count++
        println("Thread in Progress: $threadName and count is: $count")
    }

    fun doWork() {
        val thread1 = Thread {
            for (i in 0..9) {
                try {
                    increment(Thread.currentThread().name)
                } catch (ex: InterruptedException) {
                    println(ex.message)
                }
            }
        }
        thread1.start()
        val thread2 = Thread {
            for (i in 0..9) {
                try {
                    increment(Thread.currentThread().name)
                } catch (ex: InterruptedException) {
                    println(ex.message)
                }
            }
        }
        thread2.start()

        try {
            thread1.join()
            thread2.join()
        } catch (ignored: InterruptedException) {
        }
        println("Count is: $count")
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val worker = Worker()
            worker.doWork()
        }
    }
}