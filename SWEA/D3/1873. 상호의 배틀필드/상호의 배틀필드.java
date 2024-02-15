import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class Solution {
    private static char[][] squares;
    private static char direction;
    private static int positionX, positionY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            squares = new char[col][row];
            positionX = 0;
            positionY = 0;
            direction = '0';
            for (int i = 0; i < col; i++) {
                String str = br.readLine();
                for (int j = 0; j < row; j++) {
                    squares[i][j] = str.charAt(j);
                    if (str.charAt(j) == '<' || str.charAt(j) == '>' || str.charAt(j) == '^' || str.charAt(j) == 'v') {
                        positionX = i;
                        positionY = j;
                        direction = str.charAt(j);
                    }
                }
            }

            int num = Integer.parseInt(br.readLine());
            char[] chars = br.readLine().toCharArray();

            for (int i = 0; i < num; i++) {
                action(chars[i]);
            }

            builder.append("#").append(testCase).append(" ");
            for(char[] ch : squares) {
                for (char c: ch) {
                    builder.append(c);
                }
                builder.append("\n");
            }
        }
        System.out.println(builder);
    }

    private static void action(char action) {
        switch (action) {
            case 'U':
                direction = '^';
                if (positionX - 1 >= 0 && squares[positionX - 1][positionY] == '.') squares[positionX--][positionY] = '.';
                squares[positionX][positionY] = direction;
                return;
            case 'D':
                direction = 'v';
                if (positionX + 1 < squares.length && squares[positionX + 1][positionY] == '.') squares[positionX++][positionY] = '.';
                squares[positionX][positionY] = direction;
                return;
            case 'L':
                direction = '<';
                if (positionY - 1 >= 0 && squares[positionX][positionY - 1] == '.') squares[positionX][positionY--] = '.';
                squares[positionX][positionY] = direction;
                return;
            case 'R':
                direction = '>';
                if (positionY + 1 < squares[0].length && squares[positionX][positionY + 1] == '.') squares[positionX][positionY++] = '.';
                squares[positionX][positionY] = direction;
                return;
            case 'S':
                switch (direction) {
                    case '<':
                        for (int i = positionY; i >= 0; i--) {
                            if (squares[positionX][i] == '#') return;
                            if (squares[positionX][i] == '*') {
                                squares[positionX][i] = '.';
                                return;
                            }
                        }
                        break;
                    case '>':
                        for (int i = positionY; i < squares[0].length; i++) {
                            if (squares[positionX][i] == '#') return;
                            if (squares[positionX][i] == '*') {
                                squares[positionX][i] = '.';
                                return;
                            }
                        }
                        break;
                    case '^':
                        for (int i = positionX; i >= 0; i--) {
                            if (squares[i][positionY] == '#') return;
                            if (squares[i][positionY] == '*') {
                                squares[i][positionY] = '.';
                                return;
                            }
                        }
                        break;
                    case 'v':
                        for (int i = positionX; i < squares.length; i++) {
                            if (squares[i][positionY] == '#') return;
                            if (squares[i][positionY] == '*') {
                                squares[i][positionY] = '.';
                                return;
                            }
                        }
                        break;
                }
                return;
        }
    }
}