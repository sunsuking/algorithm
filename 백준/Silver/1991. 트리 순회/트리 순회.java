import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static class N1991Node {
        char c;
        N1991Node left;
        N1991Node right;

        public N1991Node(char c, N1991Node left, N1991Node right) {
            this.c = c;
            this.left = left;
            this.right = right;
        }
    }

    private static StringBuilder builder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        Map<Character, N1991Node> map = new HashMap<>();
        N1991Node head = new N1991Node('A', null, null);
        map.put('A', head);
        StringTokenizer st;
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            char parent = st.nextToken().charAt(0);
            N1991Node parentNode = map.get(parent);
            char left = st.nextToken().charAt(0);
            if (left != '.') {
                parentNode.left = map.computeIfAbsent(left, l -> new N1991Node(l, null, null));

            }
            char right = st.nextToken().charAt(0);
            if (right != '.') {
                parentNode.right = map.computeIfAbsent(right, r -> new N1991Node(r, null, null));
            }
        }
        builder = new StringBuilder();
        preOrder(head);
        builder.append("\n");
        inOrder(head);
        builder.append("\n");
        postOrder(head);
        System.out.println(builder);
    }

    private static void preOrder(N1991Node node) {
        if (node == null) return;
        builder.append(node.c);
        preOrder(node.left);
        preOrder(node.right);
    }

    private static void inOrder(N1991Node node) {
        if (node == null) return;
        inOrder(node.left);
        builder.append(node.c);
        inOrder(node.right);
    }

    private static void postOrder(N1991Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        builder.append(node.c);
    }
}