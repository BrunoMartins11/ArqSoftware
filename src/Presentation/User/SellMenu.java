package Presentation.User;

import Presentation.MainUser;

public class SellMenu implements MainUser {
    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Menu fechar Posição\n")
               .append("Indique o ID do CFD a fechar\n")
               .append("(-1 para cancelar)\n");

        System.out.println(builder.toString());
    }
}
