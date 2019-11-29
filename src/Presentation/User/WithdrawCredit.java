package Presentation.User;

import Presentation.MainUser;

public class WithdrawCredit implements MainUser {
    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("-------------- Withdraw Credit -----------------\n")
               .append("-- Insert the amount to withdraw --\n");

        System.out.println(builder);
    }
}
