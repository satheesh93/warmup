package multiThreading.waitAndNotify

import java.util.*


class ProducerConsumer {

    private val list = LinkedList<Int>()
    private val LIMIT = 10
    private val lock = Any()

    @Throws(InterruptedException::class)
    fun produce() {
        var value = 0
        while (true) {
            synchronized(lock) {
                //whenever the thread is notified starts again from the loop
                while (list.size == LIMIT) {
                    (lock as Object).wait() // wait() is also true
                }
                list.add(value)

                println("Producer added: " + value + " queue size is " + list.size)
                value++
                (lock as Object).notify()
            }
        }
    }

    @Throws(InterruptedException::class)
    fun consume() {
        val random: Random = Random()
        while (true) {
            synchronized(lock) {
                while (list.size == 0) {
                    (lock as Object).wait()
                }
                val value = list.removeFirst()
                print("Removed value by consumer is: $value")
                println(" Now list size is: " + list.size)
                (lock as Object).notify()

            }
            Thread.sleep(random.nextLong(1000))
        }
    }
    companion object{
        @JvmStatic
        fun main(args:Array<String>){
            val processor = ProducerConsumer()
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