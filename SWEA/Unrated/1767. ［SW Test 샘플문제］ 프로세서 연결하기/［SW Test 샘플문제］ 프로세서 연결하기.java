import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int N, maxConnect, minLine;
    private static int[][] squares;
    private static List<int[]> cores;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int testCase = 1; testCase <= T; testCase++) {
            N = Integer.parseInt(br.readLine());
            squares = new int[N][N];
            cores = new ArrayList<>();

            for (int x = 0; x < N; x++) {
                st = new StringTokenizer(br.readLine());
                for (int y = 0; y < N; y++) {
                    squares[x][y] = Integer.parseInt(st.nextToken());
                    if (squares[x][y] == 1) {
                        if (x == 0 || y == 0 || x == N - 1 || y == N - 1) maxConnect++;
                        else cores.add(new int[]{x, y});
                    }
                }
            }
            dfs(0, maxConnect, 0);
            builder.append("#").append(testCase).append(" ").append(minLine).append("\n");
        }
        System.out.print(builder);
    }

    private static void dfs(int index, int connect, int size) {
        if (index == cores.size()) {
            if (maxConnect < connect) {
                maxConnect = connect;
                minLine = size;
            } else if (maxConnect == connect) {
                minLine = Math.min(size, minLine);
            }
            return;
        }

        int[] position = cores.get(index);
        for (int[] ints : direction) {
            boolean isConnect = true;
            int count = 0;
            int newX = position[0] + ints[0];
            int newY = position[1] + ints[1];

            while (isIn(newX, newY)) {
                if (squares[newX][newY] != 0) {
                    isConnect = false;
                    break;
                }
                count++;
                squares[newX][newY] = index + 10;
                newX += ints[0];
                newY += ints[1];
            }

            if (isConnect) {
                dfs(index + 1, connect + 1, size + count);
            }

            newX = position[0] + ints[0];
            newY = position[1] + ints[1];
            while (isIn(newX, newY)) {
                if (squares[newX][newY] == index + 10) squares[newX][newY] = 0;
                newX += ints[0];
                newY += ints[1];
            }
        }
        dfs(index + 1, connect, size);

    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}