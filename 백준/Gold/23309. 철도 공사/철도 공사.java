import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Link {
    int prev;
    int next;

    public Link(int prev, int next) {
        this.prev = prev;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Link{" +
                "prev=" + prev +
                ", next=" + next +
                '}';
    }
}

public class Main {
    private static int num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        num = Integer.parseInt(st.nextToken());
        int count = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Link[] map = new Link[1_000_000_1];
        int num1 = Integer.parseInt(st.nextToken());
        int num2 = Integer.parseInt(st.nextToken());
        int start = num1;
        int startNext = num2;
        for (int i = 2; i < num; i++) {
            int n = Integer.parseInt(st.nextToken());
            map[num2] = new Link(num1, n);
            num1 = num2;
            num2 = n;
        }
        map[start] = new Link(num2, startNext);
        map[num2] = new Link(num1, start);
//        System.out.println(Arrays.toString(map));

//        for (int i = 0; i < num; i++) {
//            map[nums[i]] = new Link(i, nums[prev(i)], nums[next(i)]);
//        }

        int num, now;
        Link link, newLink;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command) {
                case "BN":
                    num = Integer.parseInt(st.nextToken());
                    now = Integer.parseInt(st.nextToken());
                    link = map[num];
                    builder.append(link.next).append("\n");
                    newLink = new Link(num, link.next);
                    map[link.next].prev = now;
                    link.next = now;
                    map[now] = newLink;
                    break;
                case "BP":
                    num = Integer.parseInt(st.nextToken());
                    now = Integer.parseInt(st.nextToken());
                    link = map[num];
                    builder.append(link.prev).append("\n");
                    newLink = new Link(link.prev, num);
                    map[link.prev].next = now;
                    link.prev = now;
                    map[now] = newLink;
                    break;
                case "CP":
                    num = Integer.parseInt(st.nextToken());
                    link = map[num];
                    builder.append(link.prev).append("\n");
                    Link prev = map[link.prev];
                    map[link.prev] = null;
                    map[prev.prev].next = num;
                    link.prev = prev.prev;
                    break;
                case "CN":
                    num = Integer.parseInt(st.nextToken());
                    link = map[num];
                    builder.append(link.next).append("\n");
                    Link next = map[link.next];
                    map[link.next] = null;
                    map[next.next].prev = num;
                    link.next = next.next;
                    break;
            }
        }
        System.out.print(builder);
    }

    private static int prev(int n) {
        if (n - 1 < 0) {
            return num - 1;
        } else {
            return n - 1;
        }
    }

    private static int next(int n) {
        if (n + 1 >= num) {
            return 0;
        } else {
            return n + 1;
        }
    }
}