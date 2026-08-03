package com.tyler.dev.user;

import com.tyler.dev.bank.Bank;
import java.util.Scanner;

public class ModifyUser{
    static Scanner scanner = new Scanner(System.in);

    //Dados dos usuários serão alterados aqui
    public static void editAcount(int id, BankUsers bankUsers, Bank bank){

        String newFisrtName, newLastName, newIban, newEmail, newPin, newPhone;

        bank.inforCount(id,bankUsers);

        boolean loop;

        do {
            System.out.println("\n\nPor Favor Preencha Correctamente Os Dados.\n");

            System.out.print("Primeiro Nome: ");
            newFisrtName= scanner.nextLine();
            if (newFisrtName.isBlank()){
                newFisrtName= bankUsers.getFirstName(id);
            }

            System.out.print("Último Nome: ");
            newLastName= scanner.nextLine();
            if (newLastName.isBlank()){
                newLastName= bankUsers.getLastName(id);
            }

            System.out.print("Digite O Seu Email: ");
            newEmail= scanner.nextLine();
            if (newEmail.isBlank()){
                newEmail= bankUsers.getEmail(id);
            }

            System.out.print("Digite O Seu Número De Telefone: ");
            newPhone = scanner.nextLine();
            if (newPhone.isBlank()){
                newPhone = bankUsers.getPhone(id);
            }

            System.out.print("Digite O PIN (Ex.: 1234): ");
            newPin = scanner.nextLine();
            if (newPin.isBlank()){
                newPin= bankUsers.getPin(id);
            }

            boolean isCorrectName1 = VerifyData.verifyName(newFisrtName.toLowerCase());
            boolean isCorrectName2 = VerifyData.verifyName(newLastName.toLowerCase());
            boolean isCorrectEmail = VerifyData.verifyEmail(newEmail.toLowerCase());
            boolean isCorrectTel = VerifyData.verifyPhone(newPhone);
            boolean isCorrectPin = VerifyData.verifyPin(newPin);

            loop = VerifyData.breakLoop(isCorrectName1, isCorrectName2, isCorrectEmail, isCorrectTel, isCorrectPin);

        }while (loop);

        newIban = "AO60"+ newPhone;
        bankUsers.updateUser(id,newFisrtName, newLastName, newEmail, newPhone, newIban, newPin);
    }

    //Falta testar deleter
    public static void deletUser(BankUsers bankUsers, Bank bank, String pin){

        System.out.print("\nDigite o ID: ");
        String id = scanner.next();

        if (id.matches("\\d+")){
            bankUsers.deleteUser(Integer.parseInt(id), bank, pin);
        }

        System.out.println("ID Inválido");
    }
}
