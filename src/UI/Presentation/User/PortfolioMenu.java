package Presentation.User;

import Testing.Asset;
import Testing.AssetList;

import java.util.Scanner;

public class PortfolioMenu implements Investor {
    @Override
    public void drawMenu()
    {
        System.out.println("Menu Ver Portfólio\n");
    }

    public void drawSecondMenu(AssetList assets)
    {
        StringBuilder builder = new StringBuilder();

        builder.append("NR. | Item | Type | Position | Buy Value/Current/Gain | State \n");


        for(Asset a : assets.getAssets())
        {
            builder.append(insertItem(a));
        }

        builder.append("------------------------------------------------\n")
                .append("1. Fechar Posição\n")
                .append("2. Gerir Posição (Redefinir valores TP/SL\n")
                .append("3. Ver Total Investido\n")
                .append("4. Ver Saldo\n")
                .append("5. Regressar\n");

        System.out.println(builder);

    }

    private String insertItem(Asset o)
    {
        StringBuilder builder = new StringBuilder();

        builder.append(o.getId())
                .append(" | ")
                .append(o.getName())
                .append(" | ")
                .append(o.getType())
                .append(" | ")
                .append(o.getPosition())
                .append(" | ")
                .append(o.getBuyValue()).append("/").append(o.getCurrentValue()).append("/").append(o.getGain())
                .append(" | ")
                .append(o.getState())
                .append("\n");

        return builder.toString();
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
