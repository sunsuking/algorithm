import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Person implements Comparable<Person>{
    int country;
    int num;
    int score;

    public Person(int country, int num, int score) {
        this.country = country;
        this.num = num;
        this.score = score;
    }
    @Override
    public int compareTo(Person other) {
        return Integer.compare(other.score, this.score);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        Queue<Person> queue = new PriorityQueue<>();
        StringTokenizer st;
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            queue.offer(new Person(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Person p1 = queue.poll();
        Person p2 = queue.poll();
        Person p3 = queue.poll();
        if (p1.country == p2.country) {
            while (p3.country == p2.country) {
                p3 = queue.poll();
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append(p1.country).append(" ").append(p1.num).append("\n");
        builder.append(p2.country).append(" ").append(p2.num).append("\n");
        builder.append(p3.country).append(" ").append(p3.num).append("\n");
        System.out.print(builder);
    }
}