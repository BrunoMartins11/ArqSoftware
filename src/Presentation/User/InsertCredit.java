package Presentation.User;

import Presentation.MainUser;

public class InsertCredit implements MainUser
{
    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("-------------- Menu Insert Credit --------------\n")
               .append("-- Insert the amount to add --\n");

        System.out.println(builder);
    }

}
