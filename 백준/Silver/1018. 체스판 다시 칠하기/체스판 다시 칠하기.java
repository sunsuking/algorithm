import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        char[][] squares = new char[x][y];
        for (int i = 0; i < x; i++) {
            squares[i] = br.readLine().toCharArray();
        }
        int min = Integer.MAX_VALUE;

        for (int i = 0; i <= x - 8; i++) {
            for (int j = 0; j <= y - 8; j++) {
                // 왼쪽 위 화이트
                int count = 0;
                for (int a = 0; a < 8; a++) {
                    for (int b = 0; b < 8; b++) {
                        int newX = a + i;
                        int newY = b + j;
                        if (newX % 2 == 0) {
                            if (newY % 2 == 0) {
                                if (squares[newX][newY] != 'W') count++;
                            } else {
                                if (squares[newX][newY] != 'B') count++;
                            }
                        } else {
                            if (newY % 2 == 0) {
                                if (squares[newX][newY] != 'B') count++;
                            } else {
                                if (squares[newX][newY] != 'W') count++;
                            }
                        }

                    }
                }
                min = Math.min(min, count);

                // 왼쪽 위 블랙
                count = 0;
                for (int a = 0; a < 8; a++) {
                    for (int b = 0; b < 8; b++) {
                        int newX = a + i;
                        int newY = b + j;
                        if (newX % 2 == 0) {
                            if (newY % 2 == 0) {
                                if (squares[newX][newY] != 'B') count++;
                            } else {
                                if (squares[newX][newY] != 'W') count++;
                            }
                        } else {
                            if (newY % 2 == 0) {
                                if (squares[newX][newY] != 'W') count++;
                            } else {
                                if (squares[newX][newY] != 'B') count++;
                            }
                        }

                    }
                }
                min = Math.min(min, count);
            }
        }
        System.out.println(min);
    }
}