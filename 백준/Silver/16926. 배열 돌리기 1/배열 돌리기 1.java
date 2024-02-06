import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int col, row;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] squares = new int[col][row];
        int[][] newSquares = new int[col][row];
        for (int x = 0; x < col; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < row; y++) {
                squares[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < Math.min(col / 2, row / 2); i++) {
            int c = ((col - i * 2) * 2) + ((row - i * 2) * 2) - 4;
            int x = i;
            int y = i;
            for (int j = 0; j < c; j++) {
                int[] newP = nextPosition(x, y, i, R);
                newSquares[newP[0]][newP[1]] = squares[x][y];
                int[] next = nextPosition(x, y, i, 1);
                x = next[0];
                y = next[1];
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < col; x++) {
            for (int y = 0; y < row; y++) {
                builder.append(newSquares[x][y]).append(" ");
            }
            builder.append("\n");
        }
        System.out.println(builder);
    }

    private static int[] nextPosition(int x, int y, int depth, int R) {
        int count = 0;
        int newX = x;
        int newY = y;
        while(R != count) {
            if (newY - 1 >= depth && newX == depth) {
                newY--;
            } else if (newX + 1 < col - depth && newY == depth) {
                newX++;
            } else if (newY + 1 < row - depth && newX == col - depth - 1) {
                newY++;
            } else {
                newX--;
            }
            count++;
        }
        return new int[]{newX, newY};
    }
}