import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;

class N1227Position {
    int x;
    int y;

    public N1227Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    private static final int[][] direction = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        for (int _i = 0; _i < 10; _i++) {
            int num = Integer.parseInt(br.readLine());
            char[][] squares = new char[100][];
            for (int x = 0; x < 100; x++) {
                squares[x] = br.readLine().toCharArray();
            }
            squares[1][1] = '1';
            Queue<N1227Position> queue = new LinkedList<>();
            queue.offer(new N1227Position(1, 1));
            boolean isFin = false;

            main:
            while (!queue.isEmpty()) {
                N1227Position position = queue.poll();
                for (int d = 0; d < direction.length; d++) {
                    int newX = position.x + direction[d][0];
                    int newY = position.y + direction[d][1];
                    if (isIn(newX, newY)) {
                        if (squares[newX][newY] == '0') {
                            squares[newX][newY] = '1';
                            queue.offer(new N1227Position(newX, newY));
                        } else if (squares[newX][newY] == '3') {
                            isFin = true;
                            break main;
                        }
                    }
                }
            }
            builder.append("#").append(num).append(" ").append(isFin ? 1 : 0).append("\n");
        }
        System.out.print(builder);
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < 100 && y < 100;
    }
}