import java.util.*;

class Solution {
    private static int[] parent;
    public int solution(int n, int[][] computers) {
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (computers[i][j] == 1) {
                    merge(i + 1, j + 1);
                }
            }
        }
        
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            set.add(find(i));
        }
        
        return set.size();
    }
    
    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    
    private static boolean merge(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);
        if (parentX == parentY) return false;
        parent[parentY] = parentX;
        return true;
    }
}