import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String string = br.readLine();
        String original = br.readLine();

        int[] pi = new int[original.length()];
        int index = 0;
        for (int i = 1; i < original.length(); i++) {
            while (index > 0 && original.charAt(i) != original.charAt(index)) {
                index = pi[index - 1];
            }

            if (original.charAt(index) == original.charAt(i)) {
                index += 1;
                pi[i] = index;
            }
        }

        int count = 0;
        StringBuilder builder = new StringBuilder();
        index = 0;
        for (int i = 0; i < string.length(); i++) {
            while (index > 0 && string.charAt(i) != original.charAt(index)) index = pi[index - 1];

            if (string.charAt(i) == original.charAt(index)) {
                index++;

                if (index == original.length()) {
                    count++;
                    index = pi[index - 1];
                    builder.append(i - (original.length() - 2)).append(" ");
                }
            }
        }
        System.out.println(count);
        System.out.print(builder);
    }
}