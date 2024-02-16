import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[] array;
    private static int[][] avaiable;
    private static char[][] squares;
    private static int col, row, x, min;
    private static final int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;
        squares = new char[col][row];
        avaiable = new int[col * row][2];
        array = new int[2];
        for (int i = 0; i < col; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < row; j++) {
                squares[i][j] = chars[j];
                if (chars[j] == '0') {
                    avaiable[x++] = new int[]{i, j};
                }
            }
        }
        combination(0, 0);
        System.out.println(min);
    }

    private static void combination(int index, int start) {
        if (index == 2) {
            min = Math.min(min, zinya(avaiable[array[0]], avaiable[array[1]]));
//            System.exit(0);
            return;
        }

        for (int i = start; i < x; i++) {
            array[index] = i;
            combination(index + 1, i + 1);
        }
    }

    private static int zinya(int[] position1, int[] position2) {
        char[][] newV = new char[col][row];
        for (int i = 0; i < col; i++) newV[i] = squares[i].clone();
        int count = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(position1);
        queue.offer(position2);
        while (!queue.isEmpty()) {
            count++;
            if (count > min) return Integer.MAX_VALUE;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] pop = queue.poll();

                for (int x = 0; x < 4; x++) {
                    int newX = pop[0] + direction[x][0];
                    int newY = pop[1] + direction[x][1];
                    if (isIn(newX, newY) && newV[newX][newY] != 'X') {
//                        System.out.println(newX + " : " + newY);
                        newV[newX][newY] = 'X';
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
            if (isAllTrue(newV)) return count;
        }
        return Integer.MAX_VALUE;
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && x < col && y >= 0 && y < row;
    }

    private static boolean isAllTrue(char[][] v) {
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (v[i][j] == '1') return false;
            }
        }
        return true;
    }
}