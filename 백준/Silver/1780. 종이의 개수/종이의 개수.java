
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1780 종이의 개수
 * 
 */
public class Main {
    private static int minus = 0;
    private static int zero = 0;
    private static int plus = 0;
    private static int[][] squares;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        squares = new int[num][num];
        StringTokenizer st;
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < num; j++) {
                squares[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cut(0, 0, num);
        System.out.println(minus);
        System.out.println(zero);
        System.out.println(plus);
    }

    private static void cut(int x, int y, int num) {
        int color = squares[x][y];
        if (num == 1) {
            if (color == 1) plus++;
            else if (color == 0) zero++;
            else minus++;
            return;
        }
        boolean isSame = true;
        search:
        for (int i = x; i < x + num; i++) {
            for (int j = y; j < y + num; j++) {
                if (color != squares[i][j]) {
                    isSame = false;
                    break search;
                }
            }
        }


        if (isSame) {
            if (color == 1) plus++;
            else if (color == 0) zero++;
            else minus++;
            return;
        }
        cut(x, y, num / 3);
        cut(x + num / 3, y, num / 3);
        cut(x + (num / 3 * 2), y, num / 3);
        cut(x, y + num / 3, num / 3);
        cut(x + num / 3, y + num / 3, num / 3);
        cut(x + (num / 3 * 2), y + num / 3, num / 3);
        cut(x, y + (num / 3 * 2), num / 3);
        cut(x + num / 3, y + (num / 3 * 2), num / 3);
        cut(x + (num / 3 * 2), y + (num / 3 * 2), num / 3);
    }
}
