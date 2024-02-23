import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int MAX_PLAYER = 9;
    private static int N, max;
    private static boolean[] visited;
    private static int[] array;
    private static int[][] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        nums = new int[N][MAX_PLAYER];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < MAX_PLAYER; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        max = 0;

        array = new int[MAX_PLAYER - 1];
        visited = new boolean[MAX_PLAYER - 1];

        permutation(0);

        System.out.println(max);
    }

    private static void permutation(int index) {
        if (index == MAX_PLAYER - 1) {
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < MAX_PLAYER - 1; i++) {
                if (i == 3) {
                    queue.offer(0);
                }
                queue.offer(array[i] + 1);
            }

            int score = 0;
            for (int innings = 0; innings < N; innings++) {
                int out = 0;
                int base = 0;
                while (out != 3) {
                    int player = queue.poll();
                    switch (nums[innings][player]) {
                        case 0:
                            out++;
                            break;
                        case 1:
                            base <<= 1;
                            base = base | 1;
                            break;
                        case 2:
                            base <<= 2;
                            base = base | 2;
                            break;
                        case 3:
                            base <<= 3;
                            base = base | 4;
                            break;
                        case 4:
                            base <<= 4;
                            base = base | 8;
                            break;
                    }
                    score += ((base & 64) / 64) + ((base & 32) / 32) + ((base & 16) / 16) + ((base & 8) / 8);
                    base &= 7;

                    queue.offer(player);
                }
            }
            max = Math.max(score, max);
            return;
        }

        for (int i = 0; i < MAX_PLAYER - 1; i++) {
            if (!visited[i]) {
                visited[i] = true;
                array[index] = i;
                permutation(index + 1);
                visited[i] = false;
            }
        }
    }
}