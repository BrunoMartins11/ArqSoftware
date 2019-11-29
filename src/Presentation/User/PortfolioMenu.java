package Presentation.User;

import BusinessModel.Assets.Asset;
import BusinessModel.Trading.CFD;
import Presentation.MainUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PortfolioMenu implements MainUser {

    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("--------------- Portfolio Menu -----------------\n")
               .append("CFD ID | AssetID - Asset Company | Date | Position | Buy Value / Quantity | TP Value | SL Value\n");

        System.out.println(builder.toString());
    }

    public void drawSecondMenu(List<CFD> cfdList, Map<Integer, Asset> assets)
    {
        StringBuilder builder = new StringBuilder();
        String company = "";

        if(cfdList != null)
        {
            List<CFD> list = new ArrayList<>();
            list = cfdList;
            Map<Integer,Asset> map = new HashMap<>();
            map = assets;

            if(list.size() > 0 && map.size() > 0)
            {
                for(CFD a : list)
                {
                    if( a != null)
                    {
                        company = map.get(a.getAssetID()).getCompany();
                        builder.append(insertItem(a,company));
                    }
                }
            }
            System.out.println(builder.toString());
        }
        drawOptionsMenu();
    }

    public void drawOptionsMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("------------------------------------------------\n")
                .append("1. Close Position\n")
                .append("2. Manage Position (Reset TP/SL values)\n")
                .append("3. See Total Invested\n")
                .append("4. Credit Amount\n")
                .append("5. Return\n")
                .append("------------------------------------------------\n");

        System.out.println(builder.toString());
    }

    public void drawOptionsTwoMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("------------------------------------------------\n")
                .append("1. See Total Invested\n")
                .append("2. Credit Amount\n")
                .append("3. Return\n")
                .append("------------------------------------------------\n");

        System.out.println(builder.toString());
    }

    private String insertItem(CFD o, String assetCompany)
    {
        StringBuilder builder = new StringBuilder();

        builder.append(o.getId())
                .append(" | ")
                .append(o.getAssetID())
                .append(" - ")
                .append(assetCompany)
                .append(" | ")
                .append(o.getDate().toString())
                .append(" | ")
                .append(o.getPosition().toString())
                .append(" | ")
                .append(o.getPriceAcquisition())
                .append(" / ")
                .append(o.getQuantity())
                .append(" | ")
                .append(o.getTP())
                .append(" | ")
                .append(o.getSL())
                .append("\n");

        return builder.toString();
    }

}
