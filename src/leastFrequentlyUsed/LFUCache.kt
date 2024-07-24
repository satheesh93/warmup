package leastFrequentlyUsed

import java.util.*

public class LFUCache<T>(private val size: Int) {

    private val minHeap = PriorityQueue { pair1: Pair<T>, pair2: Pair<T> ->
        pair1.frequency - pair2.frequency
    }
    private val hashMap = HashMap<T, Pair<T>>()

    public fun insertItem(value: T) {
        if (hashMap.containsKey(value)) {
            incrementFrequency(value)
        } else {
            if (minHeap.size == size) {
                removeLFU()
            }
            val pair = Pair(value, 1)
            minHeap.offer(pair)
            hashMap[value] = pair
            println("item ${pair.value} inserted.")
        }
    }

    private fun removeLFU() {
        val pair = minHeap.poll()
        hashMap.remove(pair.value)
        println("item ${pair.value} removed.")
    }

    private fun incrementFrequency(value: T) {
        val pair = hashMap[value]
        pair?.let {
            it.frequency += 1
        }
        minHeap.remove(pair)
        minHeap.offer(pair)
    }

    class Pair<T>(val value: T, var frequency: Int)

}