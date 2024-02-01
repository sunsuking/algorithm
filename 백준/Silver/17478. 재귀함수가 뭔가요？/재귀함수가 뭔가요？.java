
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        int num = Integer.parseInt(br.readLine());
        builder.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
        ask(builder,0, num);
        System.out.println(builder);
    }

    private static void ask(StringBuilder builder, int count, int num) {
        builder.append(repeat(count)).append("\"재귀함수가 뭔가요?\"\n");
        if (num == count) {
            builder.append(repeat(count)).append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
        } else {
            builder.append(repeat(count)).append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
            builder.append(repeat(count)).append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
            builder.append(repeat(count)).append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
        }
        if (num > count) {
            ask(builder, count + 1, num);
        }
        builder.append(repeat(count)).append("라고 답변하였지.\n");
    }

    private static String repeat(int count) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count * 4; i++) {
            builder.append("_");
        }
        return builder.toString();
    }
}
