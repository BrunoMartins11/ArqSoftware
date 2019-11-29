package Presentation.User;

import Presentation.MainUser;

public class PositionManage implements MainUser
{

    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("------------- Manage Position Menu -------------\n")
               .append("-- Insert CFD ID --\n")
               .append("(-1 to cancel)\n");

        System.out.println(builder);
    }

    public void drawSLTPValues()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("-- Do you wish to reset TP/SL? [Y/N] --\n");
        System.out.println(builder);
    }

    public void tpValueDraw()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("-- Insert TP limit --\n")
                .append("(-1 to cancel)\n");
        System.out.println(builder);
    }

    public void slValueDraw()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("-- Insert SL limit --\n")
                .append("(-1 to cancel)\n");
        System.out.println(builder);
    }

}
