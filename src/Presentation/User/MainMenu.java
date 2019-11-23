package Presentation.User;

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
               .append("2. Ver WatchList\n")
               .append("3. Ver Portfólio\n")
               .append("4. Inserir Crédito\n")
               .append("5. Retirar Crédito\n")
               .append("6. Reportar Bug\n")
               .append("7. Sair\n");

        System.out.println(builder);
    }

    public void printNoCredit()
    {
        System.out.println("Não tem crédito suficiente para a operação\n");
    }

    public void printSuccessfulOperation()
    {
        System.out.println("Operação Efectuada com sucesso\n");
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
}
