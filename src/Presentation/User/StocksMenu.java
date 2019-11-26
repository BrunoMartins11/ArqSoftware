package Presentation.User;

import BusinessModel.Assets.Asset;
import Presentation.MainUser;

import java.util.Collection;
import java.util.List;

public class StocksMenu implements MainUser
{
    @Override
    public void drawMainMenu()
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

    public void drawSecondMenu(Collection<Asset> assets, String type)
    {
        StringBuilder builder = new StringBuilder();

        builder.append("Ver Mercados - ")
                .append(type)
                .append("\n")
                .append("NR. | Item | Value \n");


        for(Asset a : assets)
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
                .append(o.getCompany())
                .append(" | ")
                .append(o.getValue())
                .append("\n");

        return builder.toString();
    }

    public void printAuthenticationFault() {
        System.out.println("Utilizador não autenticado\n");
    }
}
