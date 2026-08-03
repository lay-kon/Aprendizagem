package com.tyler.dev;

import com.tyler.dev.bank.Agence;
import com.tyler.dev.bank.Bank;
import com.tyler.dev.user.BankUsers;
import com.tyler.dev.user.ModifyUser;
import com.tyler.dev.user.VerifyData;

import javax.swing.JOptionPane;
import java.util.Scanner;

//Responsavel por Apresentar centralizar todo que o usuário deve ver
public class Apresentation {

    static Scanner scanner = new Scanner(System.in);

    //Por agora trabalharemos com apenas 1 agência e um Admin
    // Caso se mude isso só sera necessário implementar o cadastro de agências e de Admins (toda lógica já esta feito)
    static Agence agence = new Agence("Abdoulayé Eduardo","abdoulayeduardo.k@gmail.com","(+244)","001", "976873",
                                    "adm123secretpin","Kiluange","agenciakiluange@gmail.com","(+244)","0012");

    static BankUsers bankUsers = new BankUsers();
    static Bank bank = new Bank();

    //O ponto de execução | cama 2
    public static void master(){
        String option;

        firstMenu: do {
            System.out.println("\nSEJA BEM-VINDO (A) DE VOLTA!\n");
            System.out.println("1 - Fazer Login");
            System.out.println("2 - Criar Conta");
            System.out.println("0 - Fechar Aplicação");
            System.out.print("\nEscolha uma das opções à cima: ");
            option = scanner.next();

            switch (option) {
                case "1":
                    login();
                    break;
                case "2":
                    createAcount();
                    break;
                case "0":
                    break firstMenu;
                default:
                    System.out.println("\n\n\t[ERROR]\nComando Inválido");
                    break;
            }
        }while (true);
        //while (option.equals("1") || option.equals("2")); -> Aqui se o user digitar qualquer coisa que não seje 1 ou 2, o programa fecha
    }

    //Login Simples do Usuário
    public static void login() {
        int cont=1;
        boolean isPinAdm;
        System.out.println("\n\n       TELA DE LOGIN\n");

        while (cont<=3) {
            System.out.print("Nº da Conta: ");
            String numberCont = scanner.next();
            System.out.print("PIN: ");
            String pin = scanner.next();

            isPinAdm = VerifyData.verifyAdmPin(pin, agence); //Esse PIN secreto do Admin vai levar o Adm à sua respectiva tela de login

            if (!isPinAdm) {
                if (!VerifyData.verifyLogin(numberCont, pin, bankUsers)) {
                    System.out.println("\nCredênciais Inválidas."); //login false
                    System.out.println("Números de Tentativas " + cont + " de 3.\n");
                } else {
                    System.out.println("\n\nLogin Realizado Com Sucesso.\n"); //login true

                    //ID que será usado em todo canto programa começando com o loged que recebe o id
                    loged(bankUsers.getIndexPin(pin)); //bankUsers.getIndexPin(pin) -> retorna o id do usuário
                    break;
                }
            }else {
                //Tela de login do Admin
                admLogin();
                break;
            }
            cont++;
        }
    }

    private static void admLogin() {
        int cont=1;

        System.out.println("\n\nTELA DE LOGIN DO ADMINISTRADOR\n"); //login true

        while (cont<=3) {
            System.out.print("Nº de Agente: ");
            String numberAgent = scanner.next();
            System.out.print("PIN de Acesso: ");
            String pin = scanner.next();
            System.out.print("Nº da Agência: ");
            String numberAgence = scanner.next();

            if (!VerifyData.verifyAdmLogin(numberAgent, numberAgence, pin, agence)) {
                System.out.println("\nCredênciais Inválidas."); //login false
                System.out.println("Números de Tentativas " + cont + " de 3.\n");
            } else {
                System.out.println("\n\nLogin Realizado Com Sucesso.\n"); //login true

                //Depois do login, caso for bem sussedido
                AdmLoged(agence.getIndexPin(pin));
                break;
            }

            cont++;
        }
    }

    private static void AdmLoged(int id) {
        System.out.println("\n\nSEJA BEM-VINDO (A) DE VOLTA\n");
        System.out.println("ADM: "+agence.getNameAgent(id));
        System.out.println("\t"+agence.getEmailAgent(id));
        secondMenu: do {

            System.out.println("\n1 - Ver Perfil");
            System.out.println("2 - Listar Usuários");
            System.out.println("0 - Logout");
            System.out.print("\nEscolha uma das opções à cima: ");
            String option = scanner.next();

            switch (option) {
                case "1":
                    agence.inforCount(id);
                    break;
                case "2":
                    agence.admListUser(bankUsers, bank, agence.getPinAgent(id));
                    break;
                case "0":
                    System.out.println("Logout....");
                    break secondMenu;
                default:
                    System.out.println("\n\n\t[ERROR]\nComando Inválido");
                    break;
            }
        }while (true);
    }

    //Home Page | Apóis o login
    private static void loged(int id) {

        double value = 0;

        strMenu: do {

            bank.inforCount(id, bankUsers, agence,false);

            System.out.println("\n1 - Informações da Conta");
            System.out.println("2 - Transferir");
            System.out.println("3 - Depositar");
            System.out.println("4 - Sacar");
            System.out.println("5 - Consultar");
            System.out.println("6 - Editar Conta");
            System.out.println("0 - Logout");
            System.out.print("\nEscolha uma das opções à cima: ");
            String option = scanner.next();

            switch (option) {
                case "1":
                    bank.inforCount(id, bankUsers, agence, true);
                    break;
                case "2":
                    bank.transferMoney(id, bankUsers);
                    break;
                case "3":
                    try {
                        value = getValue(option);
                    }catch (Exception exception){
                        erroException();
                    }
                    bank.depositMoney(id, value);
                    break;
                case "4":
                    try {
                        value = getValue(option);
                    }catch (Exception exception){
                        erroException();
                    }
                    bank.sackMoney(id, value);
                    break;
                case "5":
                    System.out.println(bank.saldActual(id));
                    break;
                case "6":
                    //Editar dados do user
                    ModifyUser.editAcount(id, bankUsers, bank);
                    break;
                case "0":
                    System.out.println("Logout....");
                    break strMenu;
                default:
                    System.out.println("\n\n\t[ERROR]\nComando Inválido");
                    break;
            }
        }while (true);
    }

    //Valor do Deposito ou do Sack
    private static double getValue(String option) {
        return switch (option) {
            case "3" -> {
                System.out.print("Informe O Valor Do Depósito: ");
                yield scanner.nextDouble();
            }
            case "4" -> {
                System.out.print("Informe O Valor Do Sack: ");
                yield scanner.nextDouble();
            }
            default -> 0;
        };
    }

    //Usado para pegar os valores e usar para criar conta
    public static void createAcount(){

        String newFisrtName, newLastName, newIban, newEmail, newPin, newPhone;

        System.out.println("\n\nTELA DE CADASTRO\n");
        System.out.println("Para a criação da conta deves fornecer os seguintes dados:");
        System.out.println("1 - O Seu Primeiro Nome");
        System.out.println("2 - O Seu Último Nome");
        System.out.println("3 - O Seu E-mail Pessoal");
        System.out.println("4 - O Seu Número De Telefone");
        System.out.println("5 - Um PIN de 4 dígitos");
        System.out.println("\nDigite 1 para continuar");
        String dec = scanner.next();

        if (dec.equals("1")){
            boolean loop;
            do {
                System.out.println("\n\nPor Favor Preencha Correctamente Os Dados.\n");

                System.out.print("Primeiro Nome: ");
                newFisrtName= scanner.next();

                System.out.print("Último Nome: ");
                newLastName= scanner.next();

                System.out.print("Digite O Seu Email: ");
                newEmail= scanner.next();

                System.out.print("Digite O Seu Número De Telefone: ");
                newPhone = scanner.next();

                System.out.print("Digite O PIN (Ex.: 1234): ");
                newPin = scanner.next();

                boolean isCorrectName1 = VerifyData.verifyName(newFisrtName.toLowerCase());
                boolean isCorrectName2 = VerifyData.verifyName(newLastName.toLowerCase());
                boolean isCorrectEmail = VerifyData.verifyEmail(newEmail.toLowerCase());
                boolean isCorrectTel = VerifyData.verifyPhone(newPhone);
                boolean isCorrectPin = VerifyData.verifyPin(newPin);

                loop = VerifyData.breakLoop(isCorrectName1, isCorrectName2, isCorrectEmail, isCorrectTel, isCorrectPin);

            }while (loop);

            newIban = "AO60"+ newPhone;
            bankUsers.createUser(newFisrtName, newLastName, newEmail, newPhone, newIban, newPin, bank);

        }else {
            System.out.println("OPERAÇÃO CANCELADA");
        }
    }

    public static void erroException(){
        String info = ("\nDado Inválido.\nColoca Os Dados Certos Para Não Se Deparar Com Algo Assim De Novo.\n\nOperção Cancelada.");

        JOptionPane.showMessageDialog(null, info, "ERRRO", JOptionPane.INFORMATION_MESSAGE);

        System.out.println("\n\n\n\n");
    }
}
