import leastFrequentlyUsed.LFUCache
import multiThreading.lockobjects.Worker
import multiThreading.lockobjects.WorkerMethodsSynchronized
import multiThreading.startThreads.Runner
import multiThreading.startThreads.RunnerRunnable
import multiThreading.volatilekeyword.Processor


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
//    testLFU()
//    testStartThreads()
//    testStartThreadsInterface()
    testVolatile()
}

fun testLFU(){
    val lfu = LFUCache<Int>(4)
    lfu.insertItem(4)
    lfu.insertItem(3)
    lfu.insertItem(1)
    lfu.insertItem(2)
    lfu.insertItem(2)
    lfu.insertItem(1)
    lfu.insertItem(5)
    lfu.insertItem(3)

    println("$lfu")
}

fun testStartThreads(){
    val runner1 = Runner()
    runner1.start()

    val runner2 = Runner()
    runner2.start()
}

fun testStartThreadsInterface(){
    val thread1 = Thread(RunnerRunnable())
    val thread2 = Thread(RunnerRunnable())
    thread1.start()
    thread2.start()
}

fun testVolatile(){
    val pro = Processor()
    pro.start()

    println("Press Enter to stop the thread : ")
    readln()
    pro.shutdown()
}
