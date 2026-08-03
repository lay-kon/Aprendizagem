import java.util.Scanner;

public class CalculateBasic {

    static int num1, num2;
    static Scanner read = new Scanner(System.in);
    public static void main(String []args){


        System.out.println("O que queres fazer? Somar? ");
        String argment = read.nextLine();


        if (argment.toLowerCase().equals("somar")){
            input();
            sum(num1,num2);
        }else if(argment.toLowerCase().equals("subitrair")){
            input();
            manus(num1,num2);
        } else if (argment.toLowerCase().equals("dividir")) {
            input();
            division(num1,num2);
        } else if (argment.toLowerCase().equals("multiplicar")) {
            input();
            mul(num1,num2);
        }else {
            System.out.println("Argumento Inválido");
        }
    }

    private static void input() {
        System.out.print("Digite o primeiro número: ");
        num1 = read.nextInt();
        System.out.print("Digite o segundo número: ");
        num2 = read.nextInt();
    }

    static void sum(int number1, int number2){
        System.out.println(number1+" + "+number2+" = "+(number1+number2));
    }

    static void manus(int number1, int number2){
        System.out.println(number1+" - "+number2+" = "+(number1-number2));
    }

    static void mul(int number1, int number2){
        System.out.println(number1+" x "+number2+" = "+(number1*number2));
    }

    static void division(int number1, int number2){
        if (number2==0) {
            System.out.println("Operação Impossivel, O Denominador Deve Ser Diferente de Zero (0)");
        }else {
            System.out.println(number1+" / "+number2+" = "+(number1/number2));
        }
    }
}
