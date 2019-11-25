package Presentation.User;

import Presentation.MainUser;

public class WatchListMenu implements MainUser {
    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Menu WatchList\n");

        System.out.println(builder);
    }

    public void drawRemoveItemMenu()
    {
        System.out.println("Insira o ID do item a remover da WatchList:\n");
    }
}
