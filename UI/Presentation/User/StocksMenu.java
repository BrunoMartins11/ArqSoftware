package Presentation.User;

import Testing.Asset;
import Testing.AssetList;

import java.util.Scanner;

public class StocksMenu implements Investor
{
    @Override
    public void drawMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Menu Ver Mercados\n")
                .append("-- Escolha o tipo de Mercado --\n")
                .append("1. Commodities\n")
                .append("2. Moeda\n")
                .append("3. Stock\n")
                .append("4. Voltar ao Menu Iniciar\n");
        System.out.println(builder);
    }

    public void drawWatchListMenu(AssetList assets)
    {
        StringBuilder builder = new StringBuilder();

        builder.append("WatchList\n")
                .append("NR. | Item | Value | State \n");


        for(Asset a : assets.getAssets())
        {
            builder.append(insertItem(a));
        }

        builder.append("------------------------------------------------\n")
                .append("1. Abrir Posição\n")
                .append("2. Remover Item da WatchList\n")
                .append("3. Regressar\n");

        System.out.println(builder);
    }

    public void drawSecondMenu(AssetList assets, String type)
    {
        StringBuilder builder = new StringBuilder();

        builder.append("Ver Mercados - ")
                .append(type)
                .append("\n")
                .append("NR. | Item | Value | State \n");


        for(Asset a : assets.getAssets())
        {
            builder.append(insertItem(a));
        }

        builder.append("------------------------------------------------\n")
                .append("1. Abrir Posição\n")
                .append("2. Adicionar Item à WatchList\n")
                .append("3. Regressar\n");

        System.out.println(builder);
    }

    private String insertItem(Asset o)
    {
        StringBuilder builder = new StringBuilder();

        builder.append(o.getId())
                .append(" | ")
                .append(o.getName())
                .append(" | ")
                .append(o.getValue())
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

    public void printAuthenticationFault() {
        System.out.println("Utilizador não autenticado\n");
    }
}
