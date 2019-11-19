package Presentation.User;

import java.util.Scanner;

public class WithdrawCredit implements Investor {
    @Override
    public void drawMenu()
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
    public String stringInput()
    {
        Scanner scan = new Scanner(System.in);
        return scan.next();
    }
}
