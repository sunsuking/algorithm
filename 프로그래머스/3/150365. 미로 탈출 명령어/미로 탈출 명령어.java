import java.util.*;

class Solution {
    private static int N, M, K, endX, endY;
    private static String answer;
    private static int[][] direction = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    private static char[] d = {'d', 'l', 'r', 'u'};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n; M = m; K = k; endX = r - 1; endY = c - 1; answer = "impossible";
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x - 1, y - 1, 0, ""));
        while(!queue.isEmpty()) {
            Node pop = queue.poll();
            
            if (pop.x == endX && pop.y == endY && k == pop.count) {
                return pop.ans;
            }
            
            for (int i = 0; i < 4; i++) {
                int newX = pop.x + direction[i][0];
                int newY = pop.y + direction[i][1];
                if (isIn(newX, newY) && canGo(newX, newY, K - pop.count)) {
                    queue.offer(new Node(newX, newY, pop.count + 1, pop.ans + d[i]));
                    break;
                }
            }
        }
        return "impossible";
    }
    
    static class Node {
        int x;
        int y;
        int count;
        String ans;
        
        public Node(int x, int y, int count, String ans) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.ans = ans;
        }
    }
    
    
    private static void dfs(int x, int y, int count, String ans) {
        if (count > K || !answer.equals("impossible")) return;
        if (x == endX && y == endY) {
            
            if (count == K) {
                answer = ans;    
                return;
            }
        }
        
        for (int i = 0; i < 4; i++) {
            int newX = x + direction[i][0];
            int newY = y + direction[i][1];
            if (isIn(newX, newY) && count < K  && answer.equals("impossible") && canGo(newX, newY, K - (count + 1))) {
                dfs(newX, newY, count + 1, ans + d[i]);
            }
        }
    }
    
    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
    
    private static boolean canGo(int nowX, int nowY, int remain) {
        return (Math.abs(nowX - endX) + Math.abs(nowY - endY)) <= remain;
    }
}