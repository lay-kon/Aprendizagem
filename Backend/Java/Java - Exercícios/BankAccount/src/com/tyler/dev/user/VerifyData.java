package com.tyler.dev.user;

import com.tyler.dev.bank.Agence;

public class VerifyData {

    //Garantindo que o nome do user não poder conter número e ném caracteres especiais
    public static boolean verifyName(String name){

        boolean isCorrect=true;

        String [] numbers = {"0","1","2","3","4","5","6","7","8","9"};
        String [] caracters = {"¬","¹","@","£","§", "½", "^","{","{","[","]","}","¸","~","'","`","_",":",
                ";",",",".","-","~","º","ª","*","»","«","'","?","=",")","(","/","&","%","$","#","!","|","+"};

        for (String numberTest : numbers){
            if (name.contains(numberTest)){
                isCorrect=false;
                break;
            }
        }

        for (String charTest: caracters){
            if (name.contains(charTest)){
                isCorrect=false;
                break;
            }
        }

        return isCorrect;
    }

    //Verificação do PIN
    public static boolean verifyPin(String pin){
        return pin.matches("\\d{4}");
    }

    //Verificação do PINs
    public static boolean verifyPhone(String tel){
        return tel.matches("\\d{9}");
    }

    //Verificação de Email
    public static boolean verifyEmail(String email){

        boolean isCorrect=true;

        String [] caracters = {"¬","¹","£","§", "½", "^","{","{","[","]","}","¸","~","'","`",":",
                ";",",","~","º","ª","*","»","«","'","?","=",")","(","/","&","%","$","#","!","|",
                "á","é","í","ó","ú","à","è","ì","ò","ù","ã","õ","ç"," ","ẽ","ĩ","ũ"};

        for (String charTest: caracters){
            if (email.contains(charTest)){
                isCorrect=false;
                break;
            }
        }

        if (isCorrect){
            if (!email.contains("@")){
                isCorrect=false;
            }if (!email.contains(".com")) {
                isCorrect=false;
            }
        }

        return isCorrect;
    }

    //Para quebrar o loop
    public static boolean breakLoop(boolean bol1, boolean bol2, boolean bol3, boolean bol4, boolean bol5){

        boolean loop=false;

        if (!bol1 || !bol2 || !bol3 || !bol4 || !bol5){
            loop=true;
        }

        //se deu positivo então loop = true, se não loop = false
        return loop;
    }

    //Para verificar o Login
    public static boolean verifyLogin(String numberCont, String pin, BankUsers bankUsers){
        boolean isLogin=false;

        boolean isCorrectPin = verifyPin(pin);
        boolean isCorrectNumCont = numberCont.matches("\\d+");

        if (!isCorrectPin || !isCorrectNumCont) {
            isLogin = false;
        }else{

            //O pin será sempre number, converter não dará problema
            int index = bankUsers.getIndexPin(pin);

            int numCont = bankUsers.getNumberCont(index);

            //O numberCont será sempre number e diferente de Zero, converter não dará problema
            if (numCont!=0) {
                if (Integer.parseInt(numberCont) == numCont) {
                    isLogin = true;
                }
            }
        }

        return isLogin;
    }

    //Verificar credencias do Admin | O número da agência e o número de agente com o pin deve estar quites
    public static boolean verifyAdmLogin(String numberAgent, String numberAgence, String pin, Agence agence) {

        boolean isAdm =false;

        boolean isCorrectPin = pin.matches("\\d{6}");
        boolean isCorrectNumAgent = numberAgent.matches("\\d+");
        boolean isCorrectNumAgence = numberAgence.matches("\\d+");

        if (!isCorrectPin || !isCorrectNumAgent || !isCorrectNumAgence) {
            isAdm = false;
        }else{

            int index = agence.getIndexPin(pin); //Retornar o index do pin digitado

            int numAgent = agence.getNumberAgent(index);
            int numAgence = agence.getNumberAgence(index);

            //O numberCont será sempre number e diferente de Zero, converter não dará problema
            if (numAgent !=0) {
                if (Integer.parseInt(numberAgent) == numAgent && Integer.parseInt(numberAgence) == numAgence) {
                    isAdm = true;
                }
            }
        }

        return isAdm;
    }

    //Verificar se o pin é do admin
    public static boolean verifyAdmPin(String pin, Agence agence) {
        return pin.equals(agence.getSecretPin(0));
    }

}