import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class N18111Build implements Comparable<N18111Build> {
    int height;
    int count;
    int time;

    public N18111Build(int height, int count, int time) {
        this.height = height;
        this.count = count;
        this.time = time;
    }

    @Override
    public int compareTo(N18111Build other) {
        int compare = Integer.compare(this.time, other.time);
        if (compare == 0) {
            return Integer.compare(other.height, this.height);
        }
        return compare;
    }

    @Override
    public String toString() {
        return "N18111Build{" +
                "height=" + height +
                ", count=" + count +
                ", time=" + time +
                '}';
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int col = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());
        int block = Integer.parseInt(st.nextToken());
        int[][] squares = new int[col][row];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int x = 0; x < col; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < row; y++) {
                squares[x][y] = Integer.parseInt(st.nextToken());
                min = Math.min(min, squares[x][y]);
                max = Math.max(max, squares[x][y]);
            }
        }

        min = Math.max(min - 1, 0);
        max += 1;
        List<N18111Build> available = new ArrayList<>();
        for (int x = min; x <= max; x++) {
            available.add(new N18111Build(x, block, 0));
        }

        for (int x = 0; x < col; x++) {
            for (int y = 0; y < row; y++) {
                for (N18111Build build : available) {
                    int diff = Math.abs(build.height - squares[x][y]);
                    // 목표 높이가 더 크다면 -> 쌓아 올려야한다.
                    if (build.height > squares[x][y]) {
                        build.count -= diff;
                        build.time += diff;
                    }
                    // 목표 높이가 더 작다면 -> 블럭을 부셔야한다.
                    else if (build.height < squares[x][y]) {
                        build.count += diff;
                        build.time += diff * 2;
                    }
                }
            }
        }
        Collections.sort(available);
        for (N18111Build build : available) {
            if (build.count >= 0) {
                System.out.println(build.time + " " + build.height);
                System.exit(0);
            }
        }
    }
}