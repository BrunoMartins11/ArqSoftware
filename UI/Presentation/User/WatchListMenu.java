package Presentation.User;

import java.util.Scanner;

public class WatchListMenu implements Investor {
    @Override
    public void drawMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Menu WatchList\n");

        System.out.println(builder);
    }

    @Override
    public int intInput()
    {
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

    @Override
    public String stringInput()
    {
        Scanner scan = new Scanner(System.in);
        return scan.next();
    }
}
