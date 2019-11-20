package Presentation.Admin;

import Presentation.MainUser;

import java.util.Scanner;

public class MainMenu implements MainUser
{

    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Menu Principal\n")
                .append("1. Ver Mercados\n")
                .append("2. Listar Bugs\n")
                .append("3. Sair\n");

        System.out.println(builder);
    }

    @Override
    public int intInput() {
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

    @Override
    public String stringInput() {
        Scanner scan = new Scanner(System.in);
        return scan.next();
    }
}
