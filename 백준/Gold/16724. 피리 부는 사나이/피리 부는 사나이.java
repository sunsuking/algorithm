import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] squares = new char[N][M];
        boolean[][] visited = new boolean[N][M];
        for (int x = 0; x < N; x++) {
            squares[x] = br.readLine().toCharArray();
        }

        Set<String> set = new HashSet<>();

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (visited[x][y]) continue;

                Queue<int[]> queue = new LinkedList<>();
                Set<String> finished = new HashSet<>();
                queue.offer(new int[]{x, y});

                while (!queue.isEmpty()) {
                    int[] pop = queue.poll();

                    char direction = squares[pop[0]][pop[1]];
                    visited[pop[0]][pop[1]] = true;
                    finished.add(pop[0] + ":" + pop[1]);

                    int[] next;

                    if (direction == 'L') {
                        next = new int[]{pop[0], pop[1] - 1};
                    } else if (direction == 'R') {
                        next = new int[]{pop[0], pop[1] + 1};
                    } else if (direction == 'U') {
                        next = new int[]{pop[0] - 1, pop[1]};
                    } else {
                        next = new int[]{pop[0] + 1, pop[1]};
                    }

                    if (visited[next[0]][next[1]]) {
                        if (finished.contains(next[0] + ":" + next[1])) {
                            set.add(pop[0] + ":" + pop[1]);
                         }
                        break;
                    }

                    queue.offer(next);
                }
//                System.out.println("--------");
            }
        }
        System.out.println(set.size());
    }
}
