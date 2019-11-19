package Presentation.User;

import Presentation.MainUser;

import java.util.Scanner;

public class SellMenu implements MainUser {
    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Menu fechar Posição\n")
               .append("Indique a ação a fechar\n");

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
