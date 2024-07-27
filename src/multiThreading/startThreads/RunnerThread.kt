package multiThreading.startThreads

class Runner : Thread() {
    override fun run() {
        for (i in 0..4) {
            println("Hello: " + i + " Thread: " + currentThread().name)
            try {
                sleep(100)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
}