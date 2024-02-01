
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        String str;
        int width = Integer.MIN_VALUE;
        int height = Integer.MIN_VALUE;
        int[][] squares = new int[count][];
        for (int i = 0; i < count; i++) {
            str = br.readLine();
            StringTokenizer st = new StringTokenizer(str);
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            squares[i] = new int[]{x, y};
            width = Math.max(x, width);
            height = Math.max(y, height);
        }

        int[][] newSquares = new int[width + 10][height + 10];

        for (int i = 0; i < count; i++) {
            for (int j = squares[i][0]; j < squares[i][0] + 10; j++) {
                for (int z = squares[i][1]; z < squares[i][1] + 10; z++) {
                    newSquares[j][z] = 1;
                }
            }
        }
        int sum = 0;
        for (int x = 0; x < width + 10; x++) {
            for (int y = 0; y < height + 10; y++) {
                if (newSquares[x][y] == 1) {
                    sum++;
                }
            }
        }
        System.out.println(sum);
    }
}
