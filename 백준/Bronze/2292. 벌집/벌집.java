import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(br.readLine());
        // 1 -> 6 -> 12 -> 18

        if (count == 1) {
            System.out.println(1);
            return;
        }

        int num = 0;
        while (count > (num * 6)) {
            if (num == 0) {
                count -= 1;
            } else {
                count -= (6 * num);
            }
            num++;
        }

        System.out.println(num + 1);
    }
}
