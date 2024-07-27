package multiThreading.producerConsumer

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import kotlin.system.exitProcess


class ProducerConsumer {

    companion object{
        @JvmStatic
        fun main(args:Array<String>){
            val queue: BlockingQueue<Int> = ArrayBlockingQueue(10)

            @Throws(InterruptedException::class)
            fun producer() {
                while (true) { //loop indefinitely
                    queue.put((0..100).random()) //if queue is full (10) waits
                }
            }

            @Throws(InterruptedException::class)
            fun consumer() {
                while (true) {
                    Thread.sleep(100)
                    if ((0..100).random() == 0) {
                        val value = queue.take()
                        println("Taken value: " + value + "; Queue size is: " + queue.size)
                    }
                }
            }


            val t1 = Thread {
                try {
                    producer()
                } catch (ignored: InterruptedException) {
                }
            }

            val t2 = Thread {
                try {
                    consumer()
                } catch (ignored: InterruptedException) {
                }
            }
            t1.start()
            t2.start()

            Thread.sleep(30000)
            exitProcess(0)
        }
    }
}
