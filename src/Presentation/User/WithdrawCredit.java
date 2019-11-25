package Presentation.User;

import Presentation.MainUser;

public class WithdrawCredit implements MainUser {
    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Menu Retirar Crédito\n")
               .append("Insira o montante a retirar:\n");

        System.out.println(builder);
    }
}
