import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    public String operator;
    public int left;
    public int right;

    public boolean isOperator() {
        return Objects.equals(operator, "+") || Objects.equals(operator, "-") || Objects.equals(operator, "/") || Objects.equals(operator, "*");
    }

    public Node(String operator, int left, int right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        for (int T = 1; T <= 10; T++) {
            Map<Integer, Node> map = new HashMap<>();
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int key = Integer.parseInt(st.nextToken());
                String value = st.nextToken();
                int left = -1;
                int right = -1;
                if (st.hasMoreTokens()) {
                    left = Integer.parseInt(st.nextToken());
                }
                if (st.hasMoreTokens()) {
                    right = Integer.parseInt(st.nextToken());
                }
                map.put(key, new Node(value, left, right));
            }

            Node head = map.get(1);
            Queue<Node> queue = new LinkedList<>();
            queue.offer(head);
            boolean flag = true;
            while(!queue.isEmpty()) {
                Node pop = queue.poll();
                if (pop.isOperator()) {
                    Node leftNode = map.getOrDefault(pop.left, null);
                    Node rightNode = map.getOrDefault(pop.right, null);
                    if (leftNode == null || rightNode == null) {
                        flag = false;
                        break;
                    } else if (leftNode.isOperator()) {
                        if (leftNode.left == -1 || leftNode.right == -1) {
                            flag = false;
                            break;
                        }
                    } else if (rightNode.isOperator()) {
                        if (rightNode.left == -1 || rightNode.right == -1) {
                            flag = false;
                            break;
                        }
                    }
                    queue.offer(leftNode);
                    queue.offer(rightNode);
                } else {
                    Node leftNode = map.getOrDefault(pop.left, null);
                    Node rightNode = map.getOrDefault(pop.right, null);
                    if (leftNode != null || rightNode != null) {
                        flag = false;
                        break;
                    }
                }
            }
            builder.append("#").append(T).append(" ").append(flag ? 1 : 0).append("\n");
        }
        System.out.println(builder);
    }
}