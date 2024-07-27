package multiThreading.deadlock

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

class Runner {

    private val acc1 = Account()
    private val acc2 = Account()
    private val lock1: Lock = ReentrantLock()
    private val lock2: Lock = ReentrantLock()

    @Throws(InterruptedException::class)
    private fun acquireLocks(firstLock: Lock, secondLock: Lock) {
        while (true) {
            // Acquire locks
            var gotFirstLock = false
            var gotSecondLock = false
            try {
                gotFirstLock = firstLock.tryLock()
                gotSecondLock = secondLock.tryLock()
            } finally {
                if (gotFirstLock && gotSecondLock) return
                else if (gotFirstLock) firstLock.unlock()
                else if (gotSecondLock) secondLock.unlock()
            }
            // Locks not acquired
            Thread.sleep(1)
        }
    }

    //firstThread runs
    @Throws(InterruptedException::class)
    fun firstThread() {
        for (i in 0..9999) {
            acquireLocks(lock1, lock2)
            try {
                Account.transfer(acc1, acc2, (0..100).random())
            } finally {
                lock1.unlock()
                lock2.unlock()
            }
        }
    }

    //SecondThread runs
    @Throws(InterruptedException::class)
    fun secondThread() {
        for (i in 0..9999) {
            acquireLocks(lock2, lock1)
            try {
                Account.transfer(acc2, acc1, (0..100).random())
            } finally {
                lock1.unlock()
                lock2.unlock()
            }
        }
    }

    //When both threads finish execution, finished runs
    fun finished() {
        println("Account 1 balance: " + acc1.balance)
        println("Account 2 balance: " + acc2.balance)
        println("Total balance: " + (acc1.balance + acc2.balance))
    }

    companion object{
        @JvmStatic
        fun main(args:Array<String>){
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