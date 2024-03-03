import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        StringTokenizer st;
        for (int testCase = 1; testCase <= T; testCase++) {
            int K = Integer.parseInt(br.readLine());
            N4013Node[] nodes = new N4013Node[4];

            for (int i = 0; i < 4; i++) {
                Deque<Boolean> queue = new LinkedList<>();
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < 8; x++) {
                    queue.offer(st.nextToken().equals("0"));
                }
                nodes[i] = new N4013Node(queue);
            }
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
//                System.out.println("============");
//                System.out.println(Arrays.toString(nodes));
//                System.out.println(turnLeft);
//                System.out.println(turnRight);
                while (!turnLeft.isEmpty()) nodes[turnLeft.poll()].rotateLeft();
                while (!turnRight.isEmpty()) nodes[turnRight.poll()].rotateRight();
//                System.out.println(Arrays.toString(nodes));
//                System.out.println("============");
            }
            int sum = 0;
            for (int i = 0; i < 4; i++) {
                sum += nodes[i].queue.getFirst() ? 0 : Math.pow(2, i);
            }
            builder.append("#").append(testCase).append(" ").append(sum).append("\n");
        }
        System.out.print(builder);
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

        @Override
        public String toString() {
            return "N4013Node{" +
                    "queue=" + queue +
                    '}';
        }
    }
}
