package Presentation.Shared;

import Presentation.MainUser;

public class Registration implements MainUser {

    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("------------- Registration Menu ----------------\n")
               .append("-- Insert your email --\n");

        System.out.println(builder);
    }
}
