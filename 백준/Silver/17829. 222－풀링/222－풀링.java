
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[][] squares;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        StringTokenizer st;
        squares = new int[num][num];
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < num; j++) {
                squares[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(pooling(0, 0, num));
    }

    private static int pooling(int x, int y, int num) {
        int count = 0;
        int[] nums = new int[4];
        if (num == 2) {
            for (int i = x; i < x + 2; i++) {
                for (int j = y; j < y + 2; j++) {
                    nums[count++] = squares[i][j];
                }
            }
        } else {
            nums[count++] = pooling(x, y, num / 2);
            nums[count++] = pooling(x + num / 2, y, num / 2);
            nums[count++] = pooling(x, y + num / 2, num / 2);
            nums[count++] = pooling(x + num / 2, y + num / 2, num / 2);
        }
        Arrays.sort(nums);
        return nums[2];
    }
}
