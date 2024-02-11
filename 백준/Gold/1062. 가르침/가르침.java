import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, K, max;
    private static int[] array;
    private static List<Character> characters;
    private static List<Set<Character>> charList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if (K < 5) {
            System.out.println(0);
            System.exit(0);
        }
        max = Integer.MIN_VALUE;
        charList = new ArrayList<>();
        HashSet<Character> sets = new HashSet<>();
        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            Set<Character> set = new HashSet<>();
            for (char c : chars) {
                if (c != 'a' && c != 'n' && c != 't' & c != 'i' && c != 'c') set.add(c);
            }
            charList.add(set);
            sets.addAll(set);
        }
        characters = new ArrayList<>(sets);
        array = new int[characters.size()];
        if (characters.isEmpty() || K - 5 >= characters.size()) {
            max = N;
        } else {
            combination(0, 0);
        }
        System.out.println(max);
    }

    private static void combination(int index, int start) {
        if (index == K - 5) {
            List<Character> chars = new ArrayList<>();
            for (int i = 0; i < index; i++) {
                chars.add(characters.get(array[i]));
            }
            int count = 0;
            for (int i = 0; i < charList.size(); i++) {
                if (contains(charList.get(i), chars)) count++;
            }
            max = Math.max(max, count);
            return;
        }

        for (int i = start; i < characters.size(); i++) {
            array[index] = i;
            combination(index + 1, i + 1);
        }
    }

    private static boolean contains(Set<Character> set, List<Character> chars) {
        for (char c : set) {
            if (!chars.contains(c)) return false;
        }
        return true;
    }
}