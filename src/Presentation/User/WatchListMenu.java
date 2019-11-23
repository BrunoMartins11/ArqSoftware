package Presentation.User;

import Presentation.MainUser;

import java.util.Scanner;

public class WatchListMenu implements MainUser {
    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Menu WatchList\n");

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

    public void drawRemoveItemMenu()
    {
        System.out.println("Insira o ID do item a remover da WatchList:\n");
    }
}
