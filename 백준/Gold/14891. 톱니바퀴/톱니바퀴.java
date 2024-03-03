import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        StringTokenizer st;
        N4013Node[] nodes = new N4013Node[4];
        for (int i = 0; i < 4; i++) {
            Deque<Boolean> queue = new LinkedList<>();
            char[] chars = br.readLine().toCharArray();
            for (int x = 0; x < 8; x++) {
                queue.offer(chars[x] == '0');
            }
            nodes[i] = new N4013Node(queue);
        }
        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1;
            boolean isLeft = Integer.parseInt(st.nextToken()) == -1;

            Queue<Integer> turnRight = new LinkedList<>();
            Queue<Integer> turnLeft = new LinkedList<>();

            if (isLeft) {
                turnLeft.offer(num);
            } else {
                turnRight.offer(num);
            }

            boolean turn = isLeft;
            for (int s = num; s < 3; s++) {
                if (nodes[s].rightNode() == nodes[s + 1].leftNode()) break;
                turn = !turn;
                if (turn) {
                    turnLeft.offer(s + 1);
                } else {
                    turnRight.offer(s + 1);
                }
            }

            turn = isLeft;
            for (int e = num; e >= 1; e--) {
                turn = !turn;
                if (nodes[e].leftNode() == nodes[e - 1].rightNode()) break;
                if (turn) {
                    turnLeft.offer(e - 1);
                } else {
                    turnRight.offer(e - 1);
                }
            }

            while (!turnLeft.isEmpty()) nodes[turnLeft.poll()].rotateLeft();
            while (!turnRight.isEmpty()) nodes[turnRight.poll()].rotateRight();
        }
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            sum += nodes[i].queue.getFirst() ? 0 : Math.pow(2, i);
        }
        System.out.println(sum);
    }

    static class N4013Node {
        Deque<Boolean> queue;

        public N4013Node(Deque<Boolean> queue) {
            this.queue = queue;
        }

        void rotateRight() {
            Deque<Boolean> temp = new ArrayDeque<>();
            for (int i = 0; i < 7; i++) {
                temp.offer(queue.pop());
            }
            for (int i = 0; i < 7; i++) {
                queue.offer(temp.pop());
            }
        }

        void rotateLeft() {
            boolean pop = queue.pop();
            queue.offer(pop);
        }

        boolean rightNode() {
            int index = 0;
            for (boolean isN : queue) {
                if (index == 2) {
                    return isN;
                }
                index++;
            }
            return false;
        }

        boolean leftNode() {
            int index = 0;
            for (boolean isN : queue) {
                if (index == 6) {
                    return isN;
                }
                index++;
            }
            return false;
        }
    }
}