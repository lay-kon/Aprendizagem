package com.tyler.dev.bank;

import com.tyler.dev.Apresentation;
import com.tyler.dev.user.BankUsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {
    Scanner scanner = new Scanner(System.in);

    private final List<Double> saldoUser = new ArrayList<>();
    private double saldo;

    //Deposito de dinheiro
    public void depositMoney(int id, double depo){

        saldo = saldoUser.get(id);

        if (depo<=0){
            System.out.println("Impossivel Realizar O Depósito, Montante inválido.");
            System.out.println(saldActual(id));
        }else {
            saldo=saldo+depo;
            saldoUser.set(id,saldo);

            System.out.println("Deposito realizado com sucesso!");
            System.out.println(saldActual(id));
        }
    }

    //Sack de dinheiro
    public void sackMoney(int id, double sack){

        saldo = saldoUser.get(id);

        if (sack>saldo){
            System.out.println("Impossivel Realizar O Sack, Saldo Inferior.");
            System.out.println(saldActual(id));
        }else if (sack<=0){
            System.out.println("Impossivel Realizar O Sack, Montante Inválido.");
            System.out.println(saldActual(id));
        }else {
            saldo=saldo-sack;
            saldoUser.set(id,saldo);

            System.out.println("Sack realizado com sucesso!");
            System.out.println(saldActual(id));
        }
    }

    //Transferência de dinheiro
    public void transferMoney(int id, BankUsers bankUsers, String iban, double transValue){

        int ibanIndex =bankUsers.getIndexIban(iban);
        saldo = saldoUser.get(id);

        if (ibanIndex<0 || id==ibanIndex) {
            System.out.println("Impossivel Realizar A Transferência, Referência Incorreta.");
            System.out.println(saldActual(id));
        }else if (transValue>saldo){
            System.out.println("Impossivel Realizar A Transferência, Saldo Inferior.");
            System.out.println(saldActual(id));
        } else if (transValue<=0) {
            System.out.println("Impossivel Realizar A Transferência, Montante Inválido.");
            System.out.println(saldActual(id));
        } else {

            saldo = saldo - transValue;
            saldoUser.set(id,saldo);

            //Mandar o dinheiro na conta do outro usuário
            double sald = saldoUser.get(ibanIndex);
            saldoUser.set(ibanIndex,transValue+sald);

            System.out.println("Transferência Realizada com sucesso.");
            System.out.println(bankUsers.getFirstName(ibanIndex)+" "+bankUsers.getLastName(ibanIndex)+" recebeu agora "+transValue+"Kz");
            System.out.println(saldActual(id));
        }
    }
    public void transferMoney(int id, BankUsers bankUsers){
        double transValue=0;
        String referIban="";

        System.out.println("\n\nTRANSFERÊNCIA DE DINHEIRO.\n");
        scanner.nextLine(); //Para Eliminar o Buffer

        try {
            System.out.print("Informe o IBAN de Referência: ");
            referIban = scanner.next();
            System.out.print("Informe o Montante a Transerir: ");
            transValue = scanner.nextDouble();
        }catch (Exception exception){
            Apresentation.erroException();
        }
        transferMoney(id, bankUsers, referIban, transValue);
    }


    //Quando o User estiver logado | Informações da conta (O ecensial e o completo)
    public void inforCount(int id, BankUsers bankUsers, Agence agence,boolean edit){

        //dados do usuário
        String fullName = bankUsers.getFirstName(id) +" "+ bankUsers.getLastName(id);
        String emailUser = bankUsers.getEmail(id);
        String iban = bankUsers.getIban(id);
        String phoneUser = bankUsers.getPhone(id);
        String pin = bankUsers.getPin(id);
        String numberAcount = Integer.toString(id+1); //Vamos fazer um get


        //Proximos dados além do user | Terá uma classe Agencia
        String nameAgence = agence.getNameAgence(0);
        int numberAgence = agence.getNumberAgence(0);
        String emailAgence = agence.getEmailAgence(0);
        String phoneAgence = agence.getPhoneAgence(0);


        if (edit){
            System.out.println("\n\nINFORMAÇÕES DA CONTA\n");

            System.out.println("RESPONSAVEL DA CONTA\n");
            System.out.println("Conta Nº "+numberAcount);
            System.out.println("Titular da Conta: "+fullName);
            System.out.println("IBAN: "+iban);
            System.out.println("PIN de Acesso: "+pin);
            System.out.println("Contactos: ");
            System.out.println("  - Email: "+emailUser);
            System.out.println("  - Telefone: "+phoneUser);

            System.out.println("\nAGÊNCIA\n");
            System.out.println("Agência: "+nameAgence);
            System.out.println("Agência Nº "+numberAgence);
            System.out.println("Contactos: ");
            System.out.println("  - Email: "+emailAgence);
            System.out.println("  - Telefone: "+phoneAgence);
        }else {
            System.out.println("\n\nConta Nº "+numberAcount);
            System.out.println("Titular da Conta: "+fullName);
            System.out.println("IBAN: "+iban);
            System.out.println(saldActual(id));
        }
    }

    //Informações do usuário, ecensial que ele pode editar
    public void inforCount(int id, BankUsers bankUsers){

        String fullName = bankUsers.getFirstName(id) +" "+ bankUsers.getLastName(id);
        String emailUser = bankUsers.getEmail(id);
        String iban = bankUsers.getIban(id);
        String phoneUser = bankUsers.getPhone(id);
        String pin = bankUsers.getPin(id);
        String numberAcount = Integer.toString(id+1); //Vamos fazer um get


        System.out.println("\n\nDADOS DA CONTA\n");

        System.out.println("Conta Nº "+numberAcount);
        System.out.println("Titular da Conta: "+fullName);
        System.out.println("Email: "+emailUser);
        System.out.println("IBAN: "+iban);
        System.out.println("Telefone: "+phoneUser);
        System.out.println("PIN de Acesso: "+pin);

    }


    //Getters e Setters

    //Consulta de dinheiro
    public String saldActual(int id){
        return "Saldo Actual: "+this.saldoUser.get(id)+"Kz.";
    }

    //Inicializar A conta com saldo de 0
    public void initSaldoUser() {
        this.saldoUser.addLast(0.00);
    }

    //Falta testar o delete
    public void deletSaldoUser(int id) {
        saldoUser.remove(id);
    }

}
