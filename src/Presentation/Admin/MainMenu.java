package Presentation.Admin;

import Presentation.MainUser;

public class MainMenu implements MainUser
{

    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Admin Menu\n")
                .append("1. See Markets\n")
                .append("2. List Bugs\n")
                .append("3. Leave\n");

        System.out.println(builder);
    }
}
