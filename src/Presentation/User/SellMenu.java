package Presentation.User;

import Presentation.MainUser;

public class SellMenu implements MainUser {
    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();

        builder.append("-------------- Close Position Menu ---------------\n")
               .append("-- Insert CFD ID to close --\n")
               .append("(-1 to cancel)\n");

        System.out.println(builder.toString());
    }
}
