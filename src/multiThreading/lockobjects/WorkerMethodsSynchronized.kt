package multiThreading.lockobjects

class WorkerMethodsSynchronized {

    private val list1: MutableList<Int> = ArrayList()
    private val list2: MutableList<Int> = ArrayList()

    @Synchronized
    fun stageOne() {
        try {
            //do your work here
            Thread.sleep(1)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        list1.add((0..100).random())
    }

    @Synchronized
    fun stageTwo() {
        try {
            //do your work here
            Thread.sleep(1)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        list2.add((0..100).random())
    }

    private fun process() {
        for (i in 0..999) {
            stageOne()
            stageTwo()
        }
    }

    fun main() {
        println("Starting ...")
        val start = System.currentTimeMillis()
        val t1 = Thread { process() }

        val t2 = Thread { process() }

        t1.start()
        t2.start()

        try {
            t1.join()
            t2.join()
        } catch (ignored: InterruptedException) {
        }

        val end = System.currentTimeMillis()

        println("Time taken: " + (end - start))
        println("List1: " + list1.size + "; List2: " + list2.size)
    }


    companion object{
        @JvmStatic
        fun main(args:Array<String>){
            println("Synchronized Methods: ")
            val worker = WorkerMethodsSynchronized()
            worker.main()
        }
    }
}