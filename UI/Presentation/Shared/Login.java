package Presentation.Shared;

import UI.Presentation.MainUser;

import java.util.Scanner;

public class Login implements MainUser
{
    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Menu Login\n")
               .append("\n")
               .append("Insira o seu Email");

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
