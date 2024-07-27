package multiThreading.deadlock

internal class Account {
    var balance: Int = 10000
        private set

    fun deposit(amount: Int) {
        balance += amount
    }

    fun withdraw(amount: Int) {
        balance -= amount
    }

    companion object {
        fun transfer(acc1: Account, acc2: Account, amount: Int) {
            acc1.withdraw(amount)
            acc2.deposit(amount)
        }
    }
}