package Presentation.User;

import Presentation.MainUser;

public class SellMenu implements MainUser {
    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Menu fechar Posição\n")
               .append("Indique a ação a fechar\n");

        System.out.println(builder);
    }
}
