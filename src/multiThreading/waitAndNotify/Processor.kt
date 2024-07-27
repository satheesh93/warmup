package multiThreading.waitAndNotify

class Processor {

    @Throws(InterruptedException::class)
    fun produce() {
        synchronized(this) {
            println("Producer thread running ....")
            (this as Object).wait()
            println("Resumed.")
        }
    }

    @Throws(InterruptedException::class)
    fun consume() {
        Thread.sleep(2000)
        synchronized(this) {
            println("Waiting for return key.")
            readln()
            println("Return key pressed.")
            (this as Object).notify()
            Thread.sleep(5000)
            println("Consumption done.")
        }
    }

    companion object{
        @JvmStatic
        fun main(args:Array<String>){
            val processor = Processor()
            val t1 = Thread {
                try {
                    processor.produce()
                } catch (_: InterruptedException) {
                }
            }

            val t2 = Thread {
                try {
                    processor.consume()
                } catch (_: InterruptedException) {
                }
            }

            t1.start()
            t2.start()
            t1.join()
            t2.join()
        }
    }
}