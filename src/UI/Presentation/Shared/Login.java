package Presentation.Shared;

import java.util.Scanner;

public class Login implements Shared
{
    @Override
    public void drawMenu()
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
