import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int[] buttons = {300, 60, 10};
        int[] counts = new int[3];
        for (int i = 0; i < buttons.length; i++) {
            counts[i] = num / buttons[i];
            num -= buttons[i] * counts[i];
        }
        if (num == 0) {
            System.out.println(counts[0] + " " + counts[1] + " " + counts[2]);
        } else {
            System.out.println(-1);
        }

    }
}