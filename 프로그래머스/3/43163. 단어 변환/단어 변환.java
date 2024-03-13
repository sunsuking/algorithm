import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        Queue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(begin, 0));
        boolean[] visited = new boolean[words.length];
        while(!queue.isEmpty()) {
            Node pop = queue.poll();
            if (pop.str.equals(target)) {
                return pop.count;
            }
            
            for (int i = 0; i < words.length; i++) {
                if (visited[i]) continue;
                if (canChange(pop.str, words[i])) {
                    visited[i] = true;
                    queue.offer(new Node(words[i], pop.count + 1));
                }
            }
        }
        return 0;
    }
    
    static class Node implements Comparable<Node> {
        int count;
        String str;
        
        public Node(String str, int count) {
            this.count = count;
            this.str = str;
        }
        
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.count, other.count);
        }
    }
    
    private static boolean canChange(String start, String end) {
        int size = start.length();
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (start.charAt(i) == end.charAt(i)) count++;
        }
        
        if (count + 1 == size) return true;
        return false;
    }
}