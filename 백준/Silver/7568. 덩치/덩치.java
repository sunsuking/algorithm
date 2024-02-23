import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class N7568Dunk implements Comparable<N7568Dunk> {
    int index;
    int weight;
    int height;

    public N7568Dunk(int index, int weight, int height) {
        this.index = index;
        this.weight = weight;
        this.height = height;
    }

    @Override
    public int compareTo(N7568Dunk ohter) {
        return Integer.compare(ohter.weight, this.weight) + Integer.compare(ohter.height, this.height);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        int num = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[] ranks = new int[num];
        List<N7568Dunk> array = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            array.add(new N7568Dunk(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        for (int i = 0; i < num; i++) {
            N7568Dunk me = array.get(i);
            int count = 0;
            for (int x = 0; x < num; x++) {
                N7568Dunk other = array.get(x);
                if (other.weight > me.weight && other.height > me.height) count++;
            }
            ranks[me.index] = count + 1;
        }

        for (int r : ranks) builder.append(r).append(" ");
        System.out.print(builder);
    }
}