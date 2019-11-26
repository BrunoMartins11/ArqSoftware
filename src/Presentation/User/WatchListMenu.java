package Presentation.User;

import BusinessModel.Assets.Asset;
import Presentation.MainUser;

import java.util.List;

public class WatchListMenu implements MainUser {
    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Menu WatchList\n");

        System.out.println(builder);
    }

    public void drawWatchListMenu(List<Asset> assets)
    {
        StringBuilder builder = new StringBuilder();

        builder.append("WatchList\n")
                .append("NR. | Item | Value \n");


        for(Asset a : assets)
        {
            builder.append(insertItem(a));
        }

        builder.append("------------------------------------------------\n")
                .append("1. Abrir Posição\n")
                .append("2. Remover Item da WatchList\n")
                .append("3. Regressar\n");

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

    public void drawRemoveItemMenu()
    {
        System.out.println("Insira o ID do item a remover da WatchList:\n");
    }

    public void addToWatchListMenu()
    {
        System.out.println("Insira o ID do item a adicionar à WatchList: \n (-1 para cancelar)\n");
    }
}
