package com.tyler.dev.user;

import com.tyler.dev.bank.Bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankUsers {

    Scanner scanner = new Scanner(System.in);

    private final List<String> firstName = new ArrayList<>();
    private final List<String> lastName = new ArrayList<>();
    private final List<String> email = new ArrayList<>();
    private final List<String> phone = new ArrayList<>();
    private final List<String> pin = new ArrayList<>();
    private final List<String> iban = new ArrayList<>();

    //Responsavel Por armazenar o número de usuário no Sistema e retorna-lo
    private int numUsers;

    //Preencher a Lista
    public void createUser(String firstName, String lastName, String email, String phone, String iban, String pin, Bank bank){

        if (this.email.contains(email)){
            System.out.println("\nImpossivel Reutilizar Um E-mail, E-mail Inválido.");
        } else if (this.phone.contains(phone)) {
            System.out.println("\nImpossivel Reutilizar Um Número De Telefone, Número De Telefone Inválido");
        }else {
            this.firstName.addLast(firstName.toUpperCase());
            this.lastName.addLast(lastName.toUpperCase());
            this.email.addLast(email.toLowerCase());
            this.phone.addLast(phone);
            this.iban.addLast(iban);
            this.pin.addLast(pin);
            bank.initSaldoUser();

            System.out.println("\nConta Criada Com Sucesso.\n\n");
        }
    }


    //O numberUsers será passado como parametro retornado pelo getNumberUsers
    //Listar Usuários
    public void readUser(){
        if (getNumUsers()<=0) {
            System.out.println("\n\nSem Usuários no sistema.\n\n");
        }else {
            System.out.println("LISTA DE USUÁRIOS");
            System.out.println("  Conta Nº  |                  Nome              |            E-mail          |       Telefone       |      IBAN     ");
            for(int i = 0; i<getNumUsers(); i++){

                System.out.println((i+1)+" |  "+firstName.get(i)+" "+lastName.get(i)+"    |      "+email.get(i)+"     | "+phone.get(i)+"   |   "+iban.get(i));
            }
            System.out.println("------------------------------------------------------------------------------------\n\n");
        }
    }

    //Listar Apenas um Usuário | o ID aqui, será o que usuário vai ver na tela -1, por conta do index
    public void readUser(int id){
        if (!(id<=0) && !(id>getNumUsers())){

            id--;

            System.out.println("Resultado da Pesquisa: [usuário "+(id+1)+"]");
            System.out.println("  Conta Nº  |                  Nome              |            E-mail          |       Telefone       |      IBAN     ");
            System.out.println((id+1)+" |  "+firstName.get(id)+" "+lastName.get(id)+"    |      "+email.get(id)+"     | "+phone.get(id)+"   |   "+iban.get(id));
            System.out.println("------------------------------------------------------------------------------------\n\n");
        }else {
            System.out.println("Resultado da Pesquisa: [usuário "+id+"]");
            System.out.println("Usuário Não Encontrado!");
        }

    }


    //Atualizar Dados Do Usuário
    public void updateUser(int id, String firstName, String lastName, String email, String phone, String iban, String pin){
        boolean isOK = true;

        if (!(id<0)){
            if (!getEmail(id).equals(email)){
                if (this.email.contains(email)) {
                    System.out.println("\nImpossivel Reutilizar Um E-mail, E-mail Inválido.\n");
                    isOK=false;
                }
            }if (!getPhone(id).equals(phone)) {
                if (this.phone.contains(phone)) {
                    System.out.println("\nImpossivel Reutilizar Um Número De Telefone, Número De Telefone Inválido.\n");
                    isOK=false;
                }
            }

            //Se tudo estiver OK
            if (isOK){
                this.firstName.set(id,firstName.toUpperCase());
                this.lastName.set(id,lastName.toUpperCase());
                this.email.set(id,email);
                this.phone.set(id,phone);
                this.iban.set(id,iban);
                this.pin.set(id,pin);

                System.out.println("\nUsuário Atualizado Com Sucesso.\n\n");
                }
        }else {
            System.out.println("Imnpossivel Realizar Mundaças, Usuário Inexistente");
        }
    }


    //Deletar Usuário da Sistema | Foi Testado Parcealmente
    protected void deleteUser(int id,Bank bank, String pinAgent){

        if (!(id<=0) && !(id>getNumUsers())) {

            System.out.print("Para Confirmar a Exclusão Digite O Seu PIN De Acesso: ");
            String pinConfirm = scanner.next();

            System.out.println("Quero eliminar o usuário"+id);
            System.out.println("Digita exatamente o texto situado em cima");
            String confirm=scanner.next();

            if (pinConfirm.equals(pinAgent) && confirm.equals("Quero eliminar o usuário"+id)){
                id--; //aqui sera digitada pelo adm e ele vera 1, enquanto aqi no back é 0

                firstName.remove(id);
                lastName.remove(id);
                email.remove(id);
                iban.remove(id);
                phone.remove(id);
                pin.remove(id);
                bank.deletSaldoUser(id);

                System.out.println("Usuário Removido Do Sistema Com Êxito.");
            }else {
                System.out.println("\nOperação Cancelada.\n");
            }
        }else {
            System.out.println("Imnpossivel Deletar, Usuário Inexistente.");
        }
    }


    //Getters

    //Retorna o Número de usuários que a no banco
    public int getNumUsers() {
        numUsers = pin.size();
        return numUsers;
    }

    //Para verificação do login, e Pegar o PIN do usuário
    public int getIndexPin(String pin){

        int id;

        if (this.pin.contains(pin)){
            id=this.pin.indexOf(pin);
        }else {
            id=-1;
        }

        return id;
    }

    //Para verificação da transferência, e Pegar o IBAN do usuário
    public int getIndexIban(String str){
        int id=-1;

        if (iban.contains(str)){
            id=iban.indexOf(str);
        }

        return id;
    }

    //Retorna o Número da conta de um usuário
    public int getNumberCont(int id){
        return (id+1);
    }

    //Retorna o Primeiro Nome da conta de um usuário
    public String getFirstName(int id) {
        return firstName.get(id);
    }

    //Retorna o Segundo Nome da conta de um usuário
    public String getLastName(int id) {
        return lastName.get(id);
    }

    //Retorna o IBAN da conta de um usuário
    public String getIban(int id) {
        return iban.get(id);
    }

    //Retorna o PIN da conta de um usuário
    public String getPin(int id) {
        return pin.get(id);
    }

    //Retorna o E-mail da conta de um usuário
    public String getEmail(int id) {
        return email.get(id);
    }

    //Retorna o Número de Telefone da conta de um usuário
    public String getPhone(int id) {
        return phone.get(id);
    }
}