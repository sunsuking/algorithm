import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        int pow = (int) Math.pow(2, num);
        int col = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());
        int count = 0;
        while (pow != 1) {
            if (col == 0 && row == 1) {
                count += 1;
                break;
            } else if (col == 1 && row == 0) {
                count += 2;
                break;
            } else if (col == 1 && row == 1) {
                count += 3;
                break;
            }

            if (col < pow / 2 && row >= pow / 2) {
                count += (pow / 2) * (pow / 2);
                row -= pow / 2;
            } else if (row < pow / 2 && col >= pow / 2) {
                count += (pow / 2) * (pow / 2) * 2;
                col -= pow / 2;
            } else if (col >= pow / 2 && row >= pow / 2) {
                count += (pow / 2) * (pow / 2) * 3;
                col -= pow / 2;
                row -= pow / 2;
            }
            pow /= 2;
        }
        System.out.println(count);
    }
}