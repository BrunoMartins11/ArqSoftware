package Presentation.Admin;

import BusinessModel.Assets.Asset;
import Presentation.MainUser;

import java.util.Collection;

public class Stocks implements MainUser {
    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();

        builder.append("---------------- Market Menu -------------------\n")
                .append("-- Pick Market Type --\n")
                .append("1. Commodities\n")
                .append("2. Coin\n")
                .append("3. Stock\n")
                .append("4. Return\n")
                .append("------------------------------------------------\n");

        System.out.println(builder);
    }

    public void drawSecondMenu(Collection<Asset> assets, String type)
    {
        StringBuilder builder = new StringBuilder();

        builder.append("-- Market - ")
                .append(type)
                .append(" --")
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
