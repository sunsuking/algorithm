import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Solution {
    private static int N, max;
    private static int[][] squares;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        for (int testCase = 1; testCase <= T; testCase++) {
            N = Integer.parseInt(br.readLine());
            squares = new int[N][N];
            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    squares[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            Map<Integer, Integer> map = new HashMap<>();
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    max = Integer.MIN_VALUE;
                    dfs(x, y, 1);
                    int prevMax = map.getOrDefault(max, -1);
                    if (prevMax == -1) {
                        map.put(max, squares[x][y]);
                    } else {
                        if (prevMax > squares[x][y]) {
                            map.put(max, squares[x][y]);
                        }
                    }
                }
            }
            int key = map.keySet().stream().mapToInt(m -> m).max().orElse(-1);
            builder.append("#").append(testCase).append(" ").append(map.get(key)).append(" ").append(key).append("\n");
        }
        System.out.println(builder);
    }
    
    private static void dfs(int x, int y, int count) {
        max = Math.max(max, count);

        if(y + 1 < N && squares[x][y] + 1 == squares[x][y + 1]) {
            dfs(x, y + 1, count + 1);
        }
        if (x + 1 < N && squares[x][y] + 1 == squares[x + 1][y]) {
            dfs(x + 1, y, count + 1);
        }
        if (x - 1 >= 0 && squares[x][y] + 1 == squares[x - 1][y]) {
            dfs(x - 1, y, count + 1);
        }
        if (y - 1 >= 0 && squares[x][y] + 1 == squares[x][y - 1]) {
            dfs(x, y - 1, count + 1);
        }
    }
}