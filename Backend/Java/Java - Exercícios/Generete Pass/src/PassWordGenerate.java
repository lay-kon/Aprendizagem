import java.util.Random;
import java.util.Scanner;

public class PassWordGenerate {

    public static void main(String[] args){

        Scanner read = new Scanner(System.in);
        Random generate = new Random();

        char [] letra = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

        System.out.print("\nPor favor informe o número de caracteres que a senha de ter: ");
        short numberKey = read.nextShort();

        char [] keys = new char [numberKey];

        String password;

        if (numberKey>6) {

            for (int i = 0; i < numberKey; i++) {
                keys[i] = letra[generate.nextInt(numberKey)];
            }

            System.out.print("\n\nA sua senha é: ");

            for (int i = 0; i < numberKey; i++) {
                password = Character.toString(keys[i]);
                System.out.print(password);
            }
            System.out.println("\n");
        } else{
            System.out.println("\n\nImpossivel ter uma uma senha segura com está quantidade de caracteres\n\n");
        }
    }
}
