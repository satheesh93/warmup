import leastFrequentlyUsed.LFUCache

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    testLFU()
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