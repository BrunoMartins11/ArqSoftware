package Presentation.User;

import BusinessModel.Assets.Asset;
import BusinessModel.Trading.CFD;
import Presentation.MainUser;

import java.util.List;
import java.util.Map;

public class PortfolioMenu implements MainUser {
    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Menu Ver Portfólio\n")
               .append("CFD ID | AssetID - Asset Company | Date | Position | Buy Value / Quantity | TP Value | SL Value\n");

        System.out.println(builder.toString());
    }

    public void drawSecondMenu(List<CFD> cfdList, Map<Integer, Asset> assets)
    {
        StringBuilder builder = new StringBuilder();
        String company = "";
        if(cfdList.size() > 0)
        {
            for(CFD a : cfdList)
            {
                company = assets.get(a.getAssetID()).getCompany();
                builder.append(insertItem(a,company));
            }
        }
        System.out.println(builder.toString());
        drawOptionsMenu();
    }

    private void drawOptionsMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("------------------------------------------------\n")
                .append("1. Fechar Posição\n")
                .append("2. Gerir Posição (Redefinir valores TP/SL)\n")
                .append("3. Ver Total Investido\n")
                .append("4. Ver Saldo\n")
                .append("5. Regressar\n");

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
