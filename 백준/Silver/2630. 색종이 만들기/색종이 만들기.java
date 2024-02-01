
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2630 색종이 만들기
 */
public class Main {
    private static int[][] squares;
    private static int blue = 0;
    private static int white = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        squares = new int[num][num];
        for (int i = 0; i < num; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < num; j++) {
                squares[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cut(0, 0, num);
        System.out.println(white);
        System.out.println(blue);
    }

    private static void cut(int x, int y, int size) {
        if (size == 1) {
            if (squares[x][y] == 1) {
                blue++;
            } else {
                white++;
            }
            return;
        }
        int color = squares[x][y];
        boolean isSame = true;
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (squares[i][j] != color) {
                    isSame = false;
                    break;
                }
            }
        }
        if (isSame) {
            if (color == 1) {
                blue++;
            } else {
                white++;
            }
            return;
        }
        cut(x, y, size / 2);
        cut(x + size / 2, y, size / 2);
        cut(x, y + size / 2, size / 2);
        cut(x + size / 2, y + size / 2, size / 2);
    }
}
