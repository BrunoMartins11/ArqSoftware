package Presentation.Shared;

import Presentation.MainUser;

import java.util.Scanner;

public class Registration implements MainUser {

    @Override
    public void drawMainMenu()
    {
        Scanner scan = new Scanner(System.in);
        StringBuilder builder = new StringBuilder();

        builder.append("Menu Registar\n").append("Insira o Email\n");
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
