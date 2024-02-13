import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int[] array;
    private static int[][] available;
    private static char[][] squares;
    private static List<int[]> students = new ArrayList<>();
    private static boolean[][] visited;
    private static int x, num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        num = Integer.parseInt(br.readLine());
        squares = new char[num][num];
        available = new int[num * num][2];
        array = new int[3];

        StringTokenizer st;
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < num; j++) {
                char c = st.nextToken().charAt(0);
                if (c == 'X') {
                    available[x++] = new int[]{i, j};
                } else if (c == 'S') {
                    students.add(new int[]{i, j});
                }
                squares[i][j] = c;
            }
        }

        combination(0, 0);
        System.out.println("NO");
    }

    private static void combination(int index, int start) {
        if (index == 3) {
            for (int i = 0; i < 3; i++) {
                squares[available[array[i]][0]][available[array[i]][1]] = 'O';
            }
            boolean flag = false;
            for (int[] student : students) {
                visited = new boolean[num][num];
                flag = dfs(student[0] - 1, student[1], 1) && dfs(student[0] + 1, student[1], 2) && dfs(student[0], student[1] - 1, 3) && dfs(student[0], student[1] + 1, 4);
                if (!flag) {
                    for (int i = 0; i < 3; i++) {
                        squares[available[array[i]][0]][available[array[i]][1]] = 'X';
                    }
                    return;
                }
            }
            if (flag) {
//                for (char[] c : squares) System.out.println(Arrays.toString(c));
                System.out.println("YES");
                System.exit(0);
            }

            for (int i = 0; i < 3; i++) {
                squares[available[array[i]][0]][available[array[i]][1]] = 'X';
            }
            return;
        }

        for (int i = start; i < x; i++) {
            array[index] = i;
            combination(index + 1, i + 1);
        }
    }

    private static boolean dfs(int x, int y, int direction) {
        if (x < 0 || y < 0 || x >= num || y >= num || visited[x][y]) return true;
        visited[x][y] = true;
        if (squares[x][y] == 'T') return false;
        else if (squares[x][y] == 'O') return true;
        if (direction == 1) {
            return dfs(x - 1, y, 1);
        } else if (direction == 2) {
            return dfs(x + 1, y, 2);
        } else if (direction == 3) {
            return dfs(x, y - 1, 3);
        } else {
            return dfs(x, y + 1, 4);
        }
    }
}