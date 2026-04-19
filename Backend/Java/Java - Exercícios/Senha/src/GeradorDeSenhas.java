import java.util.Random;
import java.util.Scanner;

public class GeradorDeSenhas{
    public static void main(String[] args){

        Scanner read = new Scanner(System.in);
        Random generate = new Random();

        char [] letra = {
                        'A','B','C','D','E','F','G'
                        ,'H','I','J','K','L','M','N'
                        ,'O','P','Q','R','S','T','U'
                        ,'V','W','X','Y','Z'
                        };


        System.out.print("Por favor informe o número de caracteres que a senha de ter: ");
        short numberKey = read.nextShort();
        

        char [] keys = new char [numberKey];
        
        String password = " ";

        for(int i = 0; i<numberKey; i++){
            keys[i] = letra[generate.nextInt(numberKey)];
        }
        
        for(int i = 0; i<numberKey; i++){
            password = ""+Character.toString(keys[i]);
        }

        System.out.println("A sua senha é: "+password);
    }
}