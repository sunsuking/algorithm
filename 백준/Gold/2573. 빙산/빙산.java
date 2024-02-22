import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private static int col, row;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<int[]> ices = new ArrayList<>();
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());

        int[][] squares = new int[col][row];

        for (int x = 0; x < col; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < row; y++) {
                squares[x][y] = Integer.parseInt(st.nextToken());
                if (squares[x][y] > 0) {
                    ices.add(new int[]{x, y});
                }
            }
        }


        int day = 0;
        while (true) {
            List<int[]> newIces = new ArrayList<>();
            int[] minusCount = new int[ices.size()];
            for (int i = 0; i < ices.size(); i++) {
                int[] position = ices.get(i);
                for (int[] ints : direction) {
                    int newX = position[0] + ints[0];
                    int newY = position[1] + ints[1];
                    if (isIn(newX, newY) && squares[newX][newY] == 0) {
                        minusCount[i]++;
                    }
                }
            }
            for (int i = 0; i < ices.size(); i++) {
                int[] position = ices.get(i);
                if (minusCount[i] > 0) {
                    squares[position[0]][position[1]] = Math.max(squares[position[0]][position[1]] - minusCount[i], 0);
                    if (squares[position[0]][position[1]] > 0) {
                        newIces.add(position);
                    }
                } else {
                    newIces.add(position);
                }
            }

            ices = newIces;
//            for (int i = 0; i < ices.size(); i++) System.out.println(Arrays.toString(ices.get(i)));
            day++;
            boolean[][] visited = new boolean[col][row];

            int count = 0;
            for (int[] position : ices) {
                if (visited[position[0]][position[1]]) continue;
                count++;
                Queue<int[]> queue = new LinkedList<>();
                queue.offer(position);
                while (!queue.isEmpty()) {
                    int[] pop = queue.poll();

                    for (int[] ints : direction) {
                        int newX = pop[0] + ints[0];
                        int newY = pop[1] + ints[1];
                        if (isIn(newX, newY) && squares[newX][newY] > 0 && !visited[newX][newY]) {
                            visited[newX][newY] = true;
                            queue.offer(new int[]{newX, newY});
                        }
                    }
                }
            }

//            for(int i = 0; i < col; i++) System.out.println(Arrays.toString(squares[i]));
//            System.out.println("========================");

            if (count == 0) {
                break;
            } else if (count > 1) {
                System.out.println(day);
                System.exit(0);
            }
        }
        System.out.println(0);
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < col && y < row;
    }
}