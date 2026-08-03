import java.util.Scanner;

public class Fibonacci {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int first = 0;
        int second = 1;
        System.out.print("\nDiz a sequencia de Fibonacci que deseja: ");
        int seq = scanner.nextInt();
        System.out.print("\nSequência Fibonacci de [" + seq + "]: ");

        while(true) {
            int fibonaci = first + second;
            if (fibonaci > seq - 1) {
                System.out.print(fibonaci);
                System.out.println("\n\n");
                return;
            }

            System.out.print(fibonaci + " - ");
            first = second;
            second = fibonaci;
        }
    }
}