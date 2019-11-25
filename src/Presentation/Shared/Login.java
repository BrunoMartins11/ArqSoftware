package Presentation.Shared;

import Presentation.MainUser;

public class Login implements MainUser
{
    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Menu Login\n")
               .append("\n")
               .append("Insira o seu Email");

        System.out.println(builder);
    }
}
