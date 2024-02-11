import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        Integer[] candidates = new Integer[num - 1];
        int candidate = Integer.parseInt(br.readLine());
        for (int i = 0; i < num - 1; i++) {
            candidates[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(candidates, (x, y) -> y - x);

        int count = 0;
        main:
        while (candidate <= Arrays.stream(candidates).max(Integer::compareTo).orElse(0)) {
            Arrays.sort(candidates, (x, y) -> y - x);
            for (int i = 0; i < num - 1; i++) {
                if (candidate <= candidates[i]) {
                    candidate += 1;
                    candidates[i] -= 1;
                    count++;
                    continue main;
                }
            }


        }
        System.out.println(count);
    }
}