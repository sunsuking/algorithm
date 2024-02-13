import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Word {
    String word;
    int index;
    int n;

    public Word(String word, int index, int n) {
        this.word = word;
        this.index = index;
        this.n = n;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", index=" + index +
                ", n=" + n +
                '}';
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        Queue<Word> queue = new LinkedList<>();
        int[] nums = new int[num];
        StringTokenizer st;
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            int x = 0;
            while (st.hasMoreTokens()) {
                queue.offer(new Word(st.nextToken(), x++, i));
            }
        }

        List<String> words = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            words.add(st.nextToken());
        }

        int x = 0;
        int count = 0;

        while (!queue.isEmpty()) {
            Word pop = queue.poll();

            if (count > words.size() || x == words.size()) {
                System.out.println("Impossible");
                System.exit(0);
            }

            if (words.get(x).equals(pop.word)) {
                if (nums[pop.n] == pop.index) {
                    nums[pop.n]++;
                    x++;
                    count = 0;
                } else {
                    System.out.println("Impossible");
                    System.exit(0);
                }
            } else {
                queue.offer(pop);
                count++;
            }
        }

        if (x != words.size()) {
            System.out.println("Impossible");
        } else {
            System.out.println("Possible");
        }
    }
}