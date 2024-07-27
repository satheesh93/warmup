package multiThreading.startThreads

class RunnerRunnable : Runnable {
    override fun run() {
        for (i in 0..4) {
            println("Hello: " + i + " Thread: " + Thread.currentThread().name)

            try {
                Thread.sleep(100)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
}