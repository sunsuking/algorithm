import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int[][] direction = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    private static final int[][] squareCheck = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
    private static boolean[][] squares;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        squares = new boolean[101][101];
        int num = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            squares[x][y] = true;
            squares[x + direction[d][0]][y + direction[d][1]] = true;
            createDragon(x, y, new int[]{d, rotate(d)}, g);
//            break;
        }

//        for (boolean[] v : squares) System.out.println(Arrays.toString(v));

        int count = 0;
        for (int x = 0; x < 100; x++) {
            main:
            for (int y = 0; y < 100; y++) {
                for (int[] ints : squareCheck) {
                    int newX = x + ints[0];
                    int newY = y + ints[1];
                    if (!squares[newX][newY]) continue main;
                }
                count++;
            }
        }
        System.out.println(count);
    }

    private static int rotate(int d) {
        int newD = d + 1;
        if (newD > 3) {
            newD = 0;
        }
        return newD;
    }

    private static void createDragon(int x, int y, int[] move, int g) {
        if (g == 0) return;

        int newX = x;
        int newY = y;
        for (int d : move) {
            newX = newX + direction[d][0];
            newY = newY + direction[d][1];
            squares[newX][newY] = true;
        }

        int[] newMove = Arrays.copyOf(move, move.length * 2);
        for (int i = move.length; i < newMove.length; i++) {
            newMove[i] = rotate(move[newMove.length - 1 - i]);
        }
        createDragon(x, y, newMove, g - 1);
    }
}