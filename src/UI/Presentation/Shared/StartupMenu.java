package Presentation.Shared;

import java.util.Scanner;

public class StartupMenu implements Shared
{
    @Override
    public void drawMenu()
    {
        int input;
        Scanner scan = new Scanner(System.in);
        StringBuilder builder = new StringBuilder();
        builder.append("Menu Iniciar\n")
                .append("1. Ver Mercados\n")
                .append("2. Efectuar Login\n")
                .append("3. Registar\n")
                .append("4. Sair\n");

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
