import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int sum = 0;
        long sum2 = 0;
        for (int i = 1; i <= num; i++) {
            sum += i;
            sum2 += (int) Math.pow(i, 3);
        }
        System.out.println(sum);
        System.out.println((int) Math.pow(sum, 2));
        System.out.println(sum2);
    }
}