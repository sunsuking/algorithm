import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        char[] chars = br.readLine().toCharArray();
        char[] toBe = br.readLine().toCharArray();


        int count0 = 0;
        char[] temp = chars.clone();
        for (int i = 1; i < num; i++) {
            if (temp[i - 1] != toBe[i - 1]) {
                if (i == num - 1) {
                    temp[i - 1] = temp[i - 1] == '0' ? '1' : '0';
                    temp[i] = temp[i] == '0' ? '1' : '0';
                    count0++;
                } else {
                    temp[i - 1] = temp[i - 1] == '0' ? '1' : '0';
                    temp[i] = temp[i] == '0' ? '1' : '0';
                    temp[i + 1] = temp[i + 1] == '0' ? '1' : '0';
                    count0++;
                }
            }
        }

        for (int i = 0; i < num; i++) {
            if (temp[i] != toBe[i]) {
                count0 = Integer.MAX_VALUE;
                break;
            }
        }

        int count1 = 1;
        temp = chars.clone();
        temp[0] = temp[0] == '0' ? '1' : '0';
        temp[1] = temp[1] == '0' ? '1' : '0';
        for (int i = 1; i < num; i++) {
            if (temp[i - 1] != toBe[i - 1]) {
                if (i == num - 1) {
                    temp[i - 1] = temp[i - 1] == '0' ? '1' : '0';
                    temp[i] = temp[i] == '0' ? '1' : '0';
                    count1++;
                } else {
                    temp[i - 1] = temp[i - 1] == '0' ? '1' : '0';
                    temp[i] = temp[i] == '0' ? '1' : '0';
                    temp[i + 1] = temp[i + 1] == '0' ? '1' : '0';
                    count1++;
                }
            }
        }


        for (int i = 0; i < num; i++) {
            if (temp[i] != toBe[i]) {
                count1 = Integer.MAX_VALUE;
                break;
            }
        }
        System.out.println(Math.min(count0, count1) == Integer.MAX_VALUE ? "-1" : Math.min(count0, count1));
    }

}