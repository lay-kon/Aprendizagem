package com.tyler.dev.bank;

import com.tyler.dev.user.BankUsers;
import com.tyler.dev.user.ModifyUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Agence {

    private final List<String> nameAgent = new ArrayList<>();
    private final List<String> emailAgent = new ArrayList<>();
    private final List<String> phoneAgent = new ArrayList<>();
    private final List<String> numberAgent = new ArrayList<>();
    private final List<String> pinAgent = new ArrayList<>();

    private final List<String> nameAgence = new ArrayList<>();
    private final List<String> emailAgence = new ArrayList<>();
    private final List<String> phoneAgence = new ArrayList<>();
    private final List<String> numberAgence = new ArrayList<>();

    private final List<String> secretPin = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

    public Agence( String nameAgent, String emailAgent, String phoneAgent, String numberAgent, String pinAgent, String secretPin,
                   String nameAgence, String emailAgence, String phoneAgence, String numberAgence )
    { //Porenquanto tudo será passado dessa forma

        this.nameAgent.addLast(nameAgent);
        this.emailAgent.addLast(emailAgent);
        this.phoneAgent.addLast(phoneAgent);
        this.numberAgent.addLast(numberAgent);
        this.pinAgent.addLast(pinAgent);
        this.secretPin.addLast(secretPin);

        this.nameAgence.addLast(nameAgence);
        this.emailAgence.addLast(emailAgence);
        this.phoneAgence.addLast(phoneAgence);
        this.numberAgence.addLast(numberAgence);
    }

    public void inforCount(int id) {
        System.out.println("\n\nINFORMAÇÕES DO PERFIL ADM\n");

        System.out.println("RESPONSAVEL DO PERFIL\n");
        System.out.println("Conta Nº "+numberAgent.get(id));
        System.out.println("Titular da Conta: "+nameAgent.get(id));
        System.out.println("PIN Secreto: "+secretPin.get(id));
        System.out.println("PIN de Acesso: "+pinAgent.get(id));
        System.out.println("Contactos: ");
        System.out.println("  - Email: "+emailAgent.get(id));
        System.out.println("  - Telefone: "+phoneAgent.get(id));

        System.out.println("\nAGÊNCIA\n");
        System.out.println("Agência: "+nameAgence.get(id));
        System.out.println("Agência Nº "+numberAgence.get(id));
        System.out.println("Contactos: ");
        System.out.println("  - Email: "+emailAgence.get(id));
        System.out.println("  - Telefone: "+phoneAgence.get(id));
        System.out.println("\n");
    }

    public void admListUser(BankUsers bankUsers, Bank bank, String pinAgent) {

        int id;

        menu: do {
            bankUsers.readUser();

            System.out.println("\n\n1 - Pesquisar Por Nº de Conta");
            System.out.println("2 - Eliminar Usuário");
            System.out.println("0 - Sair");
            System.out.print("\nEscolha uma opção: ");
            String op = scanner.next();

            switch (op){
                case "1":
                    if (bankUsers.getNumUsers()>0) {
                        System.out.print("\nDigite o ID: ");
                        id = scanner.nextInt();

                        bankUsers.readUser(id);
                    }else{
                        System.out.println("\n\nSem Usuários no sistema.\n\n");
                    }
                    break;
                case "2":
                    if (bankUsers.getNumUsers()>0) {
                        ModifyUser.deletUser(bankUsers, bank, pinAgent);
                    }else {
                        System.out.println("\n\nSem Usuários no sistema.\n\n");
                    }
                    break;
                case "0":
                    System.out.println("\n\nVoltando ao menu principal.....\n\n");
                    break menu;
            }
        }while (true);

    }

    //Getters

    //Retorna o index do pin digitado pelo adm
    public int getIndexPin(String pin) {
        int id;

        if (this.pinAgent.contains(pin)){
            id=this.pinAgent.indexOf(pin);
        }else {
            id=-1;
        }

        return id;
    }

    //Retorna o número de agente de um adm
    public int getNumberAgent(int index) {
        String num = numberAgent.get(index);

        return Integer.parseInt(num);
    }

    //Retorna o número da agência que um adm pertence
    public int getNumberAgence(int index) {
        String num = numberAgence.get(index);

        return Integer.parseInt(num);
    }

    public String getNameAgent(int index) {
        return nameAgent.get(index);
    }

    public String getEmailAgent(int index) {
        return emailAgent.get(index);
    }

    public String getPinAgent(int index) {
        return pinAgent.get(index);
    }

    public String getNameAgence(int index) {
        return nameAgence.get(index);
    }

    public String getEmailAgence(int index) {
        return emailAgence.get(index);
    }

    public String getPhoneAgence(int index) {
        return phoneAgence.get(index);
    }

    public String getSecretPin(int index) {
        return secretPin.get(index);
    }
}
