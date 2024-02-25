import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// R -> 좌우 대칭
// U ->
// D ->
public class Main {
    private static char[] chars;
    private static int funk, K, startX, startY;
    private static int[][] squares;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        chars = new char[K * 2];
        startX = K * 2;
        startY = K * 2;
        for (int i = (K * 2) - 1; i >= 0; i--) {
            char c = st.nextToken().charAt(0);
            chars[i] = c;
            if (c == 'L') {
                startY /= 2;
            } else if (c == 'U') {
                startX /= 2;
            }
        }
        startX--;
        startY--;
        funk = Integer.parseInt(br.readLine());
        squares = new int[K * 2][K * 2];
        squares[startX][startY] = funk;
        dfs(1, 1, 0);
        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < K * 2; x++) {
            for (int y = 0; y < K * 2; y++) {
                builder.append(squares[x][y]).append(" ");
            }
            builder.append("\n");
        }
        System.out.print(builder);
    }

    private static void dfs(int width, int height, int count) {
        if (count == K * 2) return;
        char command = chars[count];
        if (command == 'L' || command == 'R') {
            width *= 2;
            if (width == 2) {
                if (funk % 2 == 0) {
                    if (command == 'L') {
                        squares[startX][startY + 1] = squares[startX][startY] == 0 ? 1 : 3;
                        if (startX % 2 == 0) {
                            squares[startX + 1][startY + 1] = squares[startX + 1][startY] == 0 ? 1 : 3;
                        } else {
                            squares[startX - 1][startY + 1] = squares[startX - 1][startY] == 0 ? 1 : 3;
                        }
                    } else {
                        squares[startX][startY - 1] = squares[startX][startY] == 0 ? 1 : 3;
                        if (startX % 2 == 0) {
                            squares[startX + 1][startY - 1] = squares[startX + 1][startY] == 0 ? 1 : 3;
                        } else {
                            squares[startX - 1][startY - 1] = squares[startX - 1][startY] == 0 ? 1 : 3;
                        }
                    }
                } else {
                    if (command == 'L') {
                        squares[startX][startY + 1] = squares[startX][startY] == 1 ? 0 : 2;
                        if (startX % 2 == 0) {
                            squares[startX + 1][startY + 1] = squares[startX + 1][startY] == 1 ? 0 : 2;
                        } else {
                            squares[startX - 1][startY + 1] = squares[startX - 1][startY] == 1 ? 0 : 2;
                        }
                    } else {
                        squares[startX][startY - 1] = squares[startX][startY] == 1 ? 0 : 2;
                        if (startX % 2 == 0) {
                            squares[startX + 1][startY - 1] = squares[startX + 1][startY] == 1 ? 0 : 2;
                        } else {
                            squares[startX - 1][startY - 1] = squares[startX - 1][startY] == 1 ? 0 : 2;
                        }
                    }
                }
            } else {
                if (command == 'L') {
                    int newStartX = startX - (startX % 2 == 0 ? 0 : 1);
                    int newStartY = startY - (startY % 2 == 0 ? 0 : 1);
                    for (int x = 0; x < height; x++) {
                        for (int y = 0; y < width / 2; y++) {
                            squares[newStartX + x][newStartY + y] = squares[newStartX + x][newStartY + y - (width * 2)];
                        }
                    }
                } else {
                    int newStartX = startX + (startX % 2 == 0 ? 2 : 1) - height;
                    int newStartY = startY + (startY % 2 == 0 ? 2 : 1) - width;
                    for (int x = 0; x < height; x++) {
                        for (int y = 0; y < width / 2; y++) {
                            squares[newStartX + x][newStartY + y] = squares[newStartX + x][newStartY + y + (width / 2)];
                        }
                    }
                }
            }
        } else {
            height *= 2;
            if (height == 2) {
                if (funk < 2) {
                    if (command == 'U') {
                        squares[startX + 1][startY] = squares[startX][startY] == 0 ? 2 : 3;
                        if (startY % 2 == 0) {
                            squares[startX + 1][startY + 1] = squares[startX][startY + 1] == 0 ? 2 : 3;
                        } else {
                            squares[startX + 1][startY - 1] = squares[startX][startY - 1] == 0 ? 2 : 3;
                        }
                    } else {
                        squares[startX - 1][startY] = squares[startX][startY] == 0 ? 2 : 3;
                        if (startY % 2 == 0) {
                            squares[startX - 1][startY + 1] = squares[startX][startY + 1] == 0 ? 2 : 3;
                        } else {
                            squares[startX - 1][startY - 1] = squares[startX][startY - 1] == 0 ? 2 : 3;
                        }
                    }
                } else {
                    if (command == 'U') {
                        squares[startX + 1][startY] = squares[startX][startY] == 2 ? 0 : 1;
                        if (startY % 2 == 0) {
                            squares[startX + 1][startY + 1] = squares[startX][startY + 1] == 2 ? 0 : 1;
                        } else {
                            squares[startX + 1][startY - 1] = squares[startX][startY - 1] == 2 ? 0 : 1;
                        }
                    } else {
                        squares[startX - 1][startY] = squares[startX][startY] == 2 ? 0 : 1;
                        if (startY % 2 == 0) {
                            squares[startX - 1][startY + 1] = squares[startX][startY + 1] == 2 ? 0 : 1;
                        } else {
                            squares[startX - 1][startY - 1] = squares[startX][startY - 1] == 2 ? 0 : 1;
                        }
                    }
                }
            } else {
                if (command == 'U') {
                    int newStartX = startX - (startX % 2 == 0 ? 0 : 1);
                    int newStartY = startY - (startY % 2 == 0 ? 0 : 1);
                    for (int x = 0; x < height / 2; x++) {
                        for (int y = 0; y < width; y++) {
                            squares[newStartX + x][newStartY + y] = squares[newStartX + x - (height / 2)][newStartY + y];
                        }
                    }
                } else {
                    int newStartX = startX + (startX % 2 == 0 ? 2 : 1) - height;
                    int newStartY = startY + (startY % 2 == 0 ? 2 : 1) - width;
                    for (int x = 0; x < height / 2; x++) {
                        for (int y = 0; y < width; y++) {
                            squares[newStartX + x][newStartY + y] = squares[newStartX + x + (height / 2)][newStartY + y];
                        }
                    }
                }
            }
        }
        dfs(width, height, count + 1);
    }
}