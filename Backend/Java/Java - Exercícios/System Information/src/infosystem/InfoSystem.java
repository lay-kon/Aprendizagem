package infosystem;


/*
    1º Detetar a data actual do sistema
    2º Detetar o idioma do sistema
    3º Detetar a resolução do da tela do sistema
    4º Detetar o idioma do teclado do sistema
    5º Detetar se o CapsLock esta ativado
*/

import java.time.LocalTime;
import java.time.LocalDate;

public class InfoSystem {

    public static void main(String[] args){
        dateNow();
        timeNow();
    }

    //Função Responsavel Por Imprimir Tempo Actual
    private static void timeNow() {

        LocalTime time = LocalTime.now();

        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
    }

    //Função Responsavel Por Imprimir A Data Actual
    public static void dateNow(){

        LocalDate date = LocalDate.now();

        int yaer = date.getYear();
        String month = date.getMonth().toString();
        int dayMonth = date.getDayOfMonth();
        String dayWeek = date.getDayOfWeek().toString();
        int dayPastYear = date.getDayOfYear();
        int dayPastMonth = date.getDayOfMonth();
        int numberMonthNow = date.getMonthValue();
        int quantDayMonth = date.lengthOfMonth();
        int quantDayYear = date.lengthOfYear();
    }
    
}
