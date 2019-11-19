package Presentation.User;

import java.util.Scanner;

public class BugReport implements Investor {
    @Override
    public void drawMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Menu de Bugs\n")
               .append("1. Reportar novo Bug\n")
               .append("2. Regressar\n");

        System.out.println(builder);
    }

    public void drawSecondMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Descreva o Bug (Carregue enter para terminar)\n");
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
