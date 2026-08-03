import java.util.Scanner;

public class RandomNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        short random = (short) (0 + Math.random() * (5-0));

        System.out.println("\nEstou a pensar em um valor entre 0 e 5");
        System.out.println("Digite o valor do teu palpite\n");

        System.out.print("Valor: ");
        short palpite = scanner.nextShort();

        System.out.println("");
        if (palpite==random){
            System.out.println("Acertou Encheio!");
        }else {
            if (palpite>5 || palpite<0){
                System.out.println("Dica: Digitaste Um Valor Fora Do Intervalo.");
            }else {
                System.out.println("Errou! Eu pensei no valor "+random);
            }
        }
    }
}
