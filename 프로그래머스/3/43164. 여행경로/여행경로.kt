import java.util.ArrayDeque
import java.util.PriorityQueue

class Solution {
    fun solution(tickets: Array<Array<String>>): Array<String> {
        val answer = mutableListOf<String>()
        val map = mutableMapOf<String, PriorityQueue<String>>()

        for (ticket in tickets) {
            map.getOrPut(ticket[0]) { PriorityQueue() }.add(ticket[1])
        }

        val stack = ArrayDeque<String>()
        stack.push("ICN")

        while (!stack.isEmpty()) {
            while (map.containsKey(stack.first) && !map[stack.first]!!.isEmpty()) stack.push(map[stack.first]!!.poll())
            answer.add(0, stack.pop())
        }

        return answer.toTypedArray()
    }
}