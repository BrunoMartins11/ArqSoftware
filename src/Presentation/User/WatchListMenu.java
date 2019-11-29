package Presentation.User;

import BusinessModel.Assets.Asset;
import Presentation.MainUser;

import java.util.List;

public class WatchListMenu implements MainUser {
    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("---------------- WatchList Menu ----------------\n");

        System.out.println(builder);
    }

    public void drawWatchListMenu(List<Asset> assets)
    {
        StringBuilder builder = new StringBuilder();

        builder.append("NR. | Item | Value \n");


        for(Asset a : assets)
        {
            builder.append(insertItem(a));
        }

        builder.append("------------------------------------------------\n")
                .append("1. Open Position\n")
                .append("2. Remove item from WatchList\n")
                .append("3. Return\n")
                .append("------------------------------------------------\n");

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
        System.out.println("Insert the Asset ID to remove (-1 to cancel):\n");
    }

    public void addToWatchListMenu()
    {
        System.out.println("Insert the Asset ID to add: \n (-1 to cancel)\n");
    }
}
