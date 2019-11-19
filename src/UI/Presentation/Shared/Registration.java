package Presentation.Shared;

import java.util.Scanner;

public class Registration implements Shared {

    @Override
    public void drawMenu()
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
