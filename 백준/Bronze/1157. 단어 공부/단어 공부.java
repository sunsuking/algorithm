import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] chars = br.readLine().toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : chars) {
            char upperC = Character.toUpperCase(c);
            map.put(upperC, map.getOrDefault(upperC, 0) + 1);
        }
        List<Map.Entry<Character, Integer>> collect = map.entrySet().stream().sorted((o1, o2) -> {
            return o2.getValue() - o1.getValue();
        }).collect(Collectors.toList());
        if (collect.size() == 1) {
            System.out.println(collect.get(0).getKey());
        } else {
            if (Objects.equals(collect.get(0).getValue(), collect.get(1).getValue())) {
                System.out.println("?");
            } else {
                System.out.println(collect.get(0).getKey());
            }
        }
    }
}