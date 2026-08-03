import javax.swing.JOptionPane;

public class WindowsInJava {
    public static void main(String[] args) {

        int number, sum=0,cont=0;
        int numberMax100=0, numberPair=0, numberOdd=0;
        double average=0;

        String message = mensagemMain();
        JOptionPane.showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);

        do {
            number = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite um número: ", "Entrada de Dados", JOptionPane.PLAIN_MESSAGE));

            sum += number;

            if (number!=0){
                cont++;

                if (number>100) {
                    numberMax100++;
                }if (number%2==0){
                    numberPair++;
                }else {
                    numberOdd++;
                }
            }
        }while (number!=0);

        if (sum!=0){
            average= (double) sum/cont;
        }

        String menssageFinal = menssageFinal(cont, numberPair, numberOdd, numberMax100,sum,average);

        JOptionPane.showMessageDialog(null, menssageFinal,"Resultado Final",JOptionPane.WARNING_MESSAGE);
    }

    private static String mensagemMain() {
        return h1("Informação <br><br>")+
                p("Seja Bem-Vindo (a) À Minha Aplicação!")+
                p("Na proxima tela poderás digitar quantos números quiser a vontade.")+
                p("Para parar de ser pedido o número, deves digitar o número "+em("zero (0)"))+
                p("<br>Clica em OK para continuar.");

    }

    static String menssageFinal(int cont, int numberPair, int numberOdd, int numberMax100, int sum, double average){
        String message =
                h1("Resultado <br><br>")+
               // hr()+
                p("Total de valores: " +cont+"<br>")+
                p("Total de Pares: " +numberPair+"<br>")+
                p("Total de Impares: " +numberOdd+"<br>")+
                p("Números Acima de 100: " +numberMax100+"<br>")+
                p("Soma dos Valores: " +sum+"<br>")+
                p("Média dos valores: "+(average)+"<br>");

        return message;
    }

    private static String h1(String text) {
        return "<html>" +
                " <h1 style=\"margin: -2px;text-align:center;color: red;\">"+text.toUpperCase()+"</h1>"+
                "<html>";
    }
    private static String p(String message){

        return "<html>" +
                " <p style=\"font-family: Arial;font-size: 10px;\">"+message+"</p>"+
                "<html>";
    }
    public static String em(String text){
        return "<html>" +
                " <em>"+text+"</em>"+
                "<html>";
    }
    private static String hr() {
        return "<html>" +
                "<hr  style=\"margin: 0px;\"> <br> <br>"+
                "<html>";
    }
}