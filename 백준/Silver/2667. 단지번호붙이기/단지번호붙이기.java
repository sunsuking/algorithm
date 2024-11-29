import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        num = Integer.parseInt(br.readLine());

        int[][] squares = new int[num][num];
        List<int[]> positions = new ArrayList<>();
        for (int x = 0; x < num; x++) {
            char[] chars = br.readLine().toCharArray();
            for (int y = 0; y < num; y++) {
                squares[x][y] = chars[y] - '0';
                positions.add(new int[]{x, y});
            }
        }

        List<Integer> counts = new ArrayList<>();

        int n = 0;
        boolean[][] visited = new boolean[num][num];
        for (int[] position : positions) {
            if (visited[position[0]][position[1]] || squares[position[0]][position[1]] == 0) continue;
            n++;
            Queue<int[]> queue = new LinkedList<>();
            queue.add(position);

            int count = 1;
            visited[position[0]][position[1]] = true;
            while (!queue.isEmpty()) {
                int[] pop = queue.poll();
                for (int[] direction : directions) {
                    int newX = pop[0] + direction[0];
                    int newY = pop[1] + direction[1];

                    if (isIn(newX, newY) && !visited[newX][newY] && squares[newX][newY] == 1) {
                        queue.add(new int[]{newX, newY});
                        visited[newX][newY] = true;
                        count++;
                    }
                }
            }

            counts.add(count);
        }
        Collections.sort(counts);

        StringBuilder builder = new StringBuilder();
        builder.append(n).append("\n");
        for (int c : counts) builder.append(c).append("\n");

        System.out.println(builder);
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && x < num && y >= 0 && y < num;
    }
}