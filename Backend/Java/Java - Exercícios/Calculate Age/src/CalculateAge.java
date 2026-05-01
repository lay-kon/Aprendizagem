import java.time.LocalDate;
import java.util.Scanner;

//Foltou calcular de forma exata

public class CalculateAge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalDate date = LocalDate.now();

        System.out.print("\n\nPor favor digite o seu nome: ");
        String name = scanner.nextLine();

        System.out.print("Informe por favor o ano em que nasceste: ");
        int yearBorn=scanner.nextInt();

        int year = date.getYear();
        int age = year - yearBorn;

        System.out.println("\n\nOlá Senhor (a) "+name+", sou a informar que esta agora com "+age+" anos de idade.\n\n");
    }
}