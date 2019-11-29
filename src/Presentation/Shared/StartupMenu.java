package Presentation.Shared;

import Presentation.MainUser;

public class StartupMenu implements MainUser
{
    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("----------------- ESS TRADING ------------------\n")
                .append("1. See Markets\n")
                .append("2. Login\n")
                .append("3. Register\n")
                .append("4. Leave\n")
                .append("------------------------------------------------\n");

        System.out.println(builder);
    }
}
