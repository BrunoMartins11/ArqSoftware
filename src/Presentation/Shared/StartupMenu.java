package Presentation.Shared;

import Presentation.MainUser;

public class StartupMenu implements MainUser
{
    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Menu Iniciar\n")
                .append("1. Ver Mercados\n")
                .append("2. Efectuar Login\n")
                .append("3. Registar\n")
                .append("4. Sair\n");

        System.out.println(builder);
    }
}
