package Presentation.User;

import Presentation.MainUser;

public class MainMenu implements MainUser
{
    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("------------------- Main Menu ------------------\n")
               .append("1. See Markets\n")
               .append("2. See WatchList\n")
               .append("3. See Portfolio\n")
               .append("4. Insert Credit\n")
               .append("5. Withdraw Credit\n")
               .append("6. Report new Bug\n")
               .append("7. Leave\n")
               .append("------------------------------------------------\n");

        System.out.println(builder);
    }

    public void printNoCredit()
    {
        System.out.println("You do not have enough Credit\n");
    }

    public void printSuccessfulOperation()
    {
        System.out.println("Successful Operation\n");
    }
}
