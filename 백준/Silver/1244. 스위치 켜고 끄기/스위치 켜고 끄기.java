import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1244번 스위치 켜고 끄기
 * <p>
 * 만약, 남자가 스위치를 수정할 경우 단순히 N의 배수 만큼만 바꿔주면 되기에 간단함.
 * 만약, 여자가 스위치를 수정한다면 현재를 기준으로 배열을 새롭게 만들어서,
 * 생성한 배열에 수정해야할 요소를 넣어주고 해당 배열을 조건에 맞게 채워주는 식으로 문제 해결
 * <p>
 * 먼저, 옆으로 이동할 갯수를 선언해주고, 이동한 거리가 오버 플로우거나, 양 옆이 서로 다르면 반복 탈출
 * 그렇지 않다면 수정할 대상을 포함한 새로운 배열을 생성하고, 이동할 갯수를 늘려주고 다시 반복문 시작
 * </p>
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // 스위치의 개수 입력 받고, 스위치 저장할 배열을 선언하여 값을 대입해줌.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int[] switchs = new int[num];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < num; i++) {
            switchs[i] = Integer.parseInt(st.nextToken());
        }
        // 스위치를 수정해야하는 케이스 입력 받아 반복문 실행
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            // 여자 인지 남자인지 입력받고, 수정하는 스위치를 입력받아
            boolean isMan = Integer.parseInt(st.nextToken()) == 1;
            int n = Integer.parseInt(st.nextToken());
            // 만약에 남자라면 반복문을 돌려 배수인 것들을 반전시켜줌.
            if (isMan) {
                for (int x = 0; x < switchs.length; x++) {
                    if ((x + 1) % n == 0) {
                        switchs[x] = Math.abs(switchs[x] - 1);
                    }
                }
            }
            // 여자라면 새로운 배열을 생성하여 그 배열에 수정할 값들을 채워 넣고, 그 배열에 담긴 값만 수정하게 처리함.
            else {
                // 3번 스위치라면 실제 배열에는 2 인덱스에 있기에 1을 빼서 저장
                int[] change = new int[]{--n};
                // 최초 이동 거리를 저장해둠.
                int count = 1;
                // 만약, 숫자가 범위를 벗어나거나 양 옆이 동일한 결과라면 반복문 돌리기
                while (n - count >= 0 && n + count < switchs.length && switchs[n - count] == switchs[n + count]) {
                    // 양 옆이 동일하다면 새로운 배열을 생성하여 수정할 배열을 업데이트 시켜줌.
                    int[] newChange = Arrays.copyOf(change, change.length + 2);
                    newChange[change.length] = n - count;
                    newChange[change.length + 1] = n + count++;
                    change = newChange;
                }
                // 수정할 배열들을 업데이트
                for (int j : change) {
                    switchs[j] = Math.abs(switchs[j] - 1);
                }
            }
        }

        // 모두 수정된 스위치들을 출력하는데 이때, 20의 배수라면 개행 추가해주기
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            builder.append(switchs[i]).append(" ");
            if ((i + 1) % 20 == 0) {
                builder.append("\n");
            }
        }
        System.out.println(builder);
    }
}
