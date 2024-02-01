import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, H, K;
    private static int[][] main;
    private static int array[];
    private static int[][] available;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        array = new int[3];
        main = new int[H][N];

        if (N == 0 || M == 0) {
            System.out.println(0);
            System.exit(0);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            main[a - 1][b - 1] = 1;
            main[a - 1][b] = -1;
        }

        available = new int[N * H][];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (main[i][j] == 0 && main[i][j + 1] == 0) {
                    available[K++] = new int[] {i, j};
                }
            }
        }

        for (int i = 0; i <= 3; i++) {
            combination(0, 0, i);
        }
        System.out.println(-1);
    }

    private static void combination(int index, int start, int end) {
        if (index == end) {
            int i;
            for (i = 0; i < end; i++) {
                int[] add = available[array[i]];
                main[add[0]][add[1]] = 1;
                main[add[0]][add[1] + 1] = -1;
            }
            if (i == end && isOk(main)) {
                System.out.println(end);
                System.exit(0);
            }
            for (int x = 0; x < i; x++) {
                int[] add = available[array[x]];
                main[add[0]][add[1]] = 0;
                main[add[0]][add[1] + 1] = 0;
            }
            return;
        }

        for (int i = start; i < K; i++) {
            array[index] = i;
            combination(index + 1, i + 1, end);
        }
    }

    private static int[][] deepcopy(int[][] array) {
        int[][] newArray = new int[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i], 0, newArray[i], 0, array[0].length);
        }
        return newArray;
    }

    private static boolean isOk(int[][] map) {
        for (int i = 0; i < N; i++) {
            boolean[][] visited = new boolean[H][N];
            int x = 0;
            int y = i;
            while(x != H) {
                if (map[x][y] == 1 && !visited[x][y+1]) visited[x][y++] = true;
                else if (map[x][y] == -1 && !visited[x][y-1]) visited[x][y--] = true;
                else visited[x++][y] = true;
            }
            if (y != i) return false;
        }
        return true;
    }
}