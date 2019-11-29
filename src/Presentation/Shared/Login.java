package Presentation.Shared;

import Presentation.MainUser;

public class Login implements MainUser
{
    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("------------------ Login Menu ------------------\n")
               .append("-- Insert your Email --");

        System.out.println(builder);
    }

    public void drawPasswordMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("-- Insert your Password --");

        System.out.println(builder);
    }
}
