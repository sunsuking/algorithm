import java.util.*;


class Solution {
    private final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        boolean[][] visited = new boolean[n][m];
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0, 1});
        
        while (!queue.isEmpty()) {
            int[] pop = queue.poll();
            // System.out.println("x: " + pop[0] + ", y: " + pop[1] + ", c: " + pop[2]);
            
            if (pop[0] == n - 1 && pop[1] == m - 1) {
                return pop[2];
            }
            
            for (int[] direction : directions) {
                int newX = direction[0] + pop[0];
                int newY = direction[1] + pop[1];
                
                if (isIn(newX, newY, n, m) && !visited[newX][newY] && maps[newX][newY] == 1) {
                    visited[newX][newY] = true;
                    queue.offer(new int[] {newX, newY, pop[2] + 1});
                }
            }
        }
        
        return -1;
    }
    
    private boolean isIn(int x, int y, int n, int m) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}