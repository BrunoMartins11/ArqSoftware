package Presentation.User;

import Presentation.MainUser;

public class InsertCredit implements MainUser
{
    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Menu Inserir Crétido\n")
               .append("Insira o montante a adicionar:\n");

        System.out.println(builder);
    }

}
