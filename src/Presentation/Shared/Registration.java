package Presentation.Shared;

import Presentation.MainUser;

public class Registration implements MainUser {

    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();

        builder.append("Menu Registar\n").append("Insira o Email\n");
        System.out.println(builder);
    }
}
