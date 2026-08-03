import java.util.Scanner;

public class Fatorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number, factorial = 1;
        String cont="";

        System.out.print("\nInforme um número: ");
        number = scanner.nextInt();
        if (!(number<0)) {
            for (int i = number; i > 0; i--) {
                factorial *= i;
                if (i>1){
                    cont+=i+" x ";
                }else{
                    cont+=i;
                }

            }

            System.out.println("\n"+cont+" = "+factorial);
            System.out.print("\nO Fatórial De " + number + " é " + factorial+"\n\n");
        }else {
            System.out.println("\nPor acaso estás a pensar em calcular o fatórial de um número negativo?");
            System.out.println("Seguindo a formula de fatórial, podemos usar um gabarito (massete), aparentemente.");

            System.out.println("\nPressione a tecla 1 para continuar");
            String unutil = scanner.next();
            if (unutil.equals("1")){
                for (int i = number; i < 0; i++) {
                    factorial *= i;
                    if (i<-1){
                        cont+=i+" x ";
                    }else{
                        cont+=i;
                    }
                }

                System.out.println("\n"+cont+" = "+factorial);
                System.out.print("O Fatórial De " + number + " é " + factorial+"\n");
            }else {
                System.out.println("\nOperção Encerrada.\n\n");
            }

        }
    }
}