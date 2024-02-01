import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int col;
    private static int row;
    private static int[][] squares;
    private static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        squares = new int[col][row];
        visited = new boolean[col][row];
        int count = 0;
        for (int i = 0; i < col; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < row; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 0) {
                    visited[i][j] = true;
                }
                squares[i][j] = num;
            }
        }

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (squares[i][j] == 1 && !visited[i][j]) {
                    count++;
                    hyunsumac(i, j);
                }
            }
        }
        System.out.println(count);
    }

    private static void hyunsumac(int x, int y) {
        if (x < 0 || y < 0 || x >= col || y >= row) return;
        if (!visited[x][y] && squares[x][y] == 1) {
            visited[x][y] = true;
            hyunsumac(x - 1, y - 1);
            hyunsumac(x, y - 1);
            hyunsumac(x + 1, y - 1);
            hyunsumac(x - 1, y);
            hyunsumac(x + 1, y);
            hyunsumac(x - 1, y + 1);
            hyunsumac(x, y + 1);
            hyunsumac(x + 1, y + 1);
        }
    }
}