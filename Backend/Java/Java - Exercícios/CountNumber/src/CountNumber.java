import java.util.Scanner;

public class CountNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int stop, start,leap;

        System.out.print("\nVamos começar a contar em ? ");
        start= scanner.nextInt();

        System.out.print("Vamos parar a contagem em ? ");
        stop= scanner.nextInt();

        System.out.println("\nVamos contar de quanto em quanto?");
        System.out.print("Digite o número de saltos: ");
        leap= scanner.nextInt();

        System.out.println("\n\n");
        for (int i = start; i <= stop; i=i+leap) {
            System.out.print(i+" ");
        }
        System.out.println("\n\n");
    }
}
