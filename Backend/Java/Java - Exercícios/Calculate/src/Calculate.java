public class Calculate{
    
    public static void main(String[] args){

        int num1 = Integer.parseInt(args[1]);
        int num2 = Integer.parseInt(args[2]);

        String argumento = args[0].toUpperCase();

        if(argumento.equals("SOMA")){

            sum(num1,num2);

        }else if(argumento.equals("SUBTRAIR")){

            manus(num1,num2);

        }else if(argumento.equals("MULTIPLICAR")){

            mult(num1,num2);

        }else if(argumento.equals("DIVIDIR")){

            division(num1,num2);
            
        }else{
            System.out.println("Nenhuma Argumento Foi Passado!");
        }
    }

    static void sum(int num1, int num2){

        System.out.println(num1+" + "+num2+" = "+(num1+num2));
    }

    static void manus(int num1, int num2){

        System.out.println(num1+" - "+num2+" = "+(num1-num2));
    }

    static void mult(int num1, int num2){
        
        System.out.println(num1+" x "+num2+" = "+(num1*num2));
    }

    static void division(int num1, int num2){
        if(num2==0){
            System.out.println("Operação Impossivel, O Denominador Deve Ser Diferente de Zero (0)");
        }else{
            System.out.println(num1+" / "+num2+" = "+(num1/num2));
        }
    }
}