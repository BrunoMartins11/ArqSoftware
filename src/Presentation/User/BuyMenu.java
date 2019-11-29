package Presentation.User;

import Presentation.MainUser;

public class BuyMenu implements MainUser
{

    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("------------ Open Position Menu ----------------\n")
               .append("-- Insert Asset ID --\n")
               .append("(-1 to cancel)\n");

        System.out.println(builder);
    }

    public void drawPositionType()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("-- Select position type --\n")
               .append("1. Long/Buy\n")
               .append("2. Short/Sell\n")
               .append("3. Cancel\n")
               .append("------------------------------------------------\n");
        System.out.println(builder);
    }

    public void drawSLTPValues()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("------------ Open Position Menu ----------------\n")
               .append("-- Do you wish to define TP/SL? [Y/N] --\n");
        System.out.println(builder);
    }

    public void tpValueDraw()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("-- Insert TP limit (-1 to ignore) --\n");
        System.out.println(builder);
    }

    public void slValueDraw()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("-- Insert SL limit (-1 to ignore) --\n");
        System.out.println(builder);
    }

    public void printSuccessfulOperation()
    {
        System.out.println("Successful Operation\n");
    }

    public void printNoCredit()
    {
        System.out.println("You do not have enough credit!!\n");
    }
}
