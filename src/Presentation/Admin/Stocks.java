package Presentation.Admin;

import BusinessModel.Assets.Asset;
import Presentation.MainUser;

import java.util.List;
import java.util.Scanner;

public class Stocks implements MainUser {
    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        Scanner scan = new Scanner(System.in);
        int input;

        builder.append("Menu Ver Mercados\n")
                .append("-- Escolha o tipo de Mercado --\n")
                .append("1. Commodities\n")
                .append("2. Moeda\n")
                .append("3. Stock\n")
                .append("4. Voltar ao Menu Iniciar\n");
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

    public void drawSecondMenu(List<Asset> assets, String type)
    {
        StringBuilder builder = new StringBuilder();
        int i = 0;

        builder.append("Ver Mercados - ")
                .append(type)
                .append("\n")
                .append("NR. | Item | Value \n");


        for(Asset a : assets)
        {
            builder.append(insertItem(a));
        }

        builder.append("------------------------------------------------\n");

        System.out.println(builder);
    }

    private String insertItem(Asset o)
    {
        StringBuilder builder = new StringBuilder();

        builder.append(o.getId())
                .append(" | ")
                .append(o.getCompany())
                .append(" | ")
                .append(o.getValue())
                .append("\n");

        return builder.toString();
    }
}
