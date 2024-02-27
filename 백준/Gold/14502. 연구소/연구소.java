import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int row, col, max;
    private static int[][] squares;
    private static int[] array;
    private static List<int[]> virus, available;
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        max = Integer.MIN_VALUE;
        virus = new ArrayList<>();
        available = new ArrayList<>();
        squares = new int[row][col];
        array = new int[3];
        for (int x = 0; x < row; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < col; y++) {
                squares[x][y] = Integer.parseInt(st.nextToken());
                if (squares[x][y] == 0) {
                    available.add(new int[]{x, y});
                } else if (squares[x][y] == 2) {
                    virus.add(new int[]{x, y});
                }
            }
        }

        combination(0, 0);

        System.out.println(max);
    }

    private static void combination(int index, int start) {
        if (index == 3) {
            int[][] newSquares = copy(squares);
            for (int i = 0; i < index; i++) {
                int[] position = available.get(array[i]);
                newSquares[position[0]][position[1]] = 1;
            }

//            if (newSquares[0][1] == 1 && newSquares[1][0] == 1 && newSquares[3][5] == 1 ) {
//                for (int i = 0; i < row; i++) System.out.println(Arrays.toString(newSquares[i]));
//                System.out.println("==============");
//            }

            Queue<int[]> queue = new LinkedList<>();
            for (int[] v : virus) queue.offer(v);

            boolean[][] visited = new boolean[row][col];
            while (!queue.isEmpty()) {
                int[] pop = queue.poll();

                for (int[] ints : direction) {
                    int newX = pop[0] + ints[0];
                    int newY = pop[1] + ints[1];

                    if (isIn(newX, newY) && !visited[newX][newY] && newSquares[newX][newY] == 0) {
//                        if (newSquares[0][1] == 1 && newSquares[1][0] == 1 && newSquares[3][5] == 1 ) {
//                            System.out.println(pop[0] + " : " + pop[1] + " : " + newX + " : " + newY);
//                        }
                        newSquares[newX][newY] = 2;
                        visited[newX][newY] = true;
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }

//            if (newSquares[0][1] == 1 && newSquares[1][0] == 1 && newSquares[3][5] == 1 ) {
//                for (int i = 0; i < row; i++) System.out.println(Arrays.toString(newSquares[i]));
//                System.out.println("==============");
//            }

            int count = 0;
            for (int x = 0; x < row; x++) {
                for (int y = 0; y < col; y++) {
                    if (newSquares[x][y] == 0) count++;
                }
            }
            max = Math.max(max, count);
            return;
        }

        for (int i = start; i < available.size(); i++) {
            array[index] = i;
            combination(index + 1, i + 1);
        }
    }

    private static int[][] copy(int[][] squares) {
        int[][] newSquares = new int[row][col];
        for (int i = 0; i < row; i++) newSquares[i] = squares[i].clone();
        return newSquares;
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < row && y < col;
    }
}