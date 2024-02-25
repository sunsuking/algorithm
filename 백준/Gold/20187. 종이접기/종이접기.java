import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// R -> 좌우 대칭
// U ->
// D ->
public class Main {
    private static char[] chars;
    private static int funk, K, startX, startY, size;
    private static int[][] squares;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        chars = new char[K * 2];
        size = (int) Math.pow(2, K);
        startX = size;
        startY = size;
        for (int i = (K * 2) - 1; i >= 0; i--) {
            char c = st.nextToken().charAt(0);
            chars[i] = c;
            if (c == 'L') {
                startY /= 2;
            } else if (c == 'U') {
                startX /= 2;
            }
        }
        startX = Math.max(startX - 1, 0);
        startY = Math.max(startY - 1, 0);
        funk = Integer.parseInt(br.readLine());
        squares = new int[size][size];
        squares[startX][startY] = funk;

        dfs(1, 1, 0);
        int n0 = 0;
        int n1 = 0;
        int n2 = 0;
        int n3 = 0;
        if (startX % 2 == 0) {
            if (startY % 2 == 0) {
                n0 = squares[startX][startY];
                n1 = squares[startX][startY + 1];
                n2 = squares[startX + 1][startY];
                n3 = squares[startX + 1][startY + 1];
            } else {
                n0 = squares[startX][startY - 1];
                n1 = squares[startX][startY];
                n2 = squares[startX + 1][startY - 1];
                n3 = squares[startX + 1][startY];
            }
        } else {
            if (startY % 2 == 0) {
                n0 = squares[startX - 1][startY];
                n1 = squares[startX - 1][startY + 1];
                n2 = squares[startX][startY];
                n3 = squares[startX][startY + 1];
            } else {
                n0 = squares[startX - 1][startY - 1];
                n1 = squares[startX - 1][startY];
                n2 = squares[startX][startY - 1];
                n3 = squares[startX][startY];
            }
        }
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (x % 2 == 0) {
                    if (y % 2 == 0) {
                        squares[x][y] = n0;
                    } else {
                        squares[x][y] = n1;
                    }
                } else {
                    if (y % 2 == 0) {
                        squares[x][y] = n2;
                    } else {
                        squares[x][y] = n3;
                    }
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                builder.append(squares[x][y]).append(" ");
            }
            builder.append("\n");
        }
        System.out.print(builder);
    }

    private static void dfs(int width, int height, int count) {
        if ((count == K * 2)) return;
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
            }
        }
        dfs(width, height, count + 1);
    }
}