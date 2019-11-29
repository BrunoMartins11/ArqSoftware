package Presentation.User;

import Presentation.MainUser;

public class BugReport implements MainUser {

    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("------------------ Bug Menu --------------------\n")
               .append("1. Report new Bug\n")
               .append("2. Return\n")
               .append("------------------------------------------------\n");

        System.out.println(builder);
    }

    public void drawSecondMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("-- Describe the bug (Press enter to finish) --\n");

        System.out.println(builder);
    }
}
