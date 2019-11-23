package Presentation.User;

import Presentation.MainUser;

import java.util.Scanner;

public class WithdrawCredit implements MainUser {
    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Menu Retirar Crédito\n")
               .append("Insira o montante a retirar:\n");

        System.out.println(builder);
    }

    @Override
    public int intInput()
    {
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

    @Override
    public double doubleInput() {
        Scanner scan = new Scanner(System.in);
        return scan.nextDouble();
    }

    @Override
    public String stringInput()
    {
        Scanner scan = new Scanner(System.in);
        return scan.next();
    }
}
