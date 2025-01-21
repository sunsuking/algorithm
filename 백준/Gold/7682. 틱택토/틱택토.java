import java.util.*;
import java.io.*;

public class Main {

    static char[][] map;
    static int XrowBingo, XcolBingo; // X로 행, 열 빙고 갯수
    static int OrowBingo, OcolBingo; // O로 행, 열 빙고 갯수
    static int OCrossBingo, XCrossBingo; // X, O로 대각선 빙고 갯수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        while (true) {
            String str = br.readLine();

            if (str.equals("end")) break; // 입력 종료

            XrowBingo = 0;
            XcolBingo = 0;
            OrowBingo = 0;
            OcolBingo = 0;
            OCrossBingo = 0;
            XCrossBingo = 0;

            map = new char[3][3];
            int idx = 0;
            int xCnt = 0; // X 갯수
            int oCnt = 0; // O 갯수
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    map[i][j] = str.charAt(idx);
                    idx++;

                    if (map[i][j] == 'X') {
                        xCnt++;
                    } else if (map[i][j] == 'O') {
                        oCnt++;
                    }
                }
            }

            // X, O 갯수 검사
            if (Math.abs(xCnt - oCnt) > 1 || oCnt > xCnt) {
                builder.append("invalid\n");
                continue;
            }

            // 가로, 세로 검사
            for (int i = 0; i < 3; i++) {
                int rowX = 0;
                int rowO = 0;
                int colX = 0;
                int colO = 0;

                for (int j = 0; j < 3; j++) {
                    if (map[i][j] == 'X') rowX++;
                    else if (map[i][j] == 'O') rowO++;

                    if (map[j][i] == 'X') colX++;
                    else if (map[j][i] == 'O') colO++;
                }

                if (rowO == 3) OrowBingo++;
                if (rowX == 3) XrowBingo++;
                if (colX == 3) XcolBingo++;
                if (colO == 3) OcolBingo++;
            }

            // 대각선 검사
            int crossX1 = 0, crossO1 = 0;
            int crossX2 = 0, crossO2 = 0;
            for (int i = 0; i < 3; i++) {
                if (map[i][i] == 'X') crossX1++;
                else if (map[i][i] == 'O') crossO1++;

                if (map[i][2 - i] == 'X') crossX2++;
                else if (map[i][2 - i] == 'O') crossO2++;
            }
            if (crossO1 == 3 || crossO2 == 3) OCrossBingo++;
            if (crossX1 == 3 || crossX2 == 3) XCrossBingo++;

            // 같은 라인 빙고가 여러개인 경우 (cross제외)
            if (XrowBingo > 1 || XcolBingo > 1 || OrowBingo > 1 || OcolBingo > 1) {
                builder.append("invalid\n");
                continue;
            }

            // X와 O가 같으면 O가 이겨야한다
            if (xCnt == oCnt) {
                if ((XrowBingo > 0 || XcolBingo > 0 || XCrossBingo > 0)) {
                    builder.append("invalid\n");
                    continue;
                }
            }
            // X가 더 크면 X가 이겨야 한다
            else if (xCnt > oCnt) {
                if (OrowBingo > 0 || OcolBingo > 0 || OCrossBingo > 0) {
                    builder.append("invalid\n");
                    continue;
                }
            }

            // X와 O의 합이 9인 경우
            if (xCnt + oCnt == 9) {
                builder.append("valid\n");
                continue;
            }

            // 빙고가 하나도 없는 경우
            if (XrowBingo + XcolBingo + OrowBingo + OcolBingo + OCrossBingo + XCrossBingo == 0) {
                builder.append("invalid\n");
                continue;
            }

            builder.append("valid\n");
        }

        System.out.print(builder);
    }
}