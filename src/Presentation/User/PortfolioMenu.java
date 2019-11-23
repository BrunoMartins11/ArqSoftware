package Presentation.User;

import BusinessModel.Assets.Asset;
import BusinessModel.Trading.CFD;
import Presentation.MainUser;

import java.util.List;
import java.util.Scanner;

public class PortfolioMenu implements MainUser {
    @Override
    public void drawMainMenu()
    {
        System.out.println("Menu Ver Portfólio\n");
    }

    public void drawSecondMenu(List<CFD> cfds)
    {
        StringBuilder builder = new StringBuilder();

        builder.append("NR. | AssetID | Date | Position | Buy Value | Quantity | TP Value | SL Value\n");


        for(CFD a : cfds)
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

    private String insertItem(CFD o)
    {
        StringBuilder builder = new StringBuilder();

        builder.append(o.getId())
                .append(" | ")
                .append(o.getAssetID())
                .append(" | ")
                .append(o.getDate().toLocalTime().toString())
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
