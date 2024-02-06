import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());

        Set<String> set = new HashSet<>();
        for (int i = 0; i < num; i++) {
            set.add(br.readLine());
        }
        StringBuilder builder = new StringBuilder();
        set.stream().sorted((s1, s2) -> {
            int compare = Integer.compare(s1.length(), s2.length());
            if (compare == 0) {
                return s1.compareTo(s2);
            }
            return compare;
        }).forEach(s -> builder.append(s).append("\n"));

        System.out.println(builder);
    }
}