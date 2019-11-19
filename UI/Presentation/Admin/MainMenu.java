package UI.Presentation.Admin;

import UI.Presentation.MainUser;

import java.util.Scanner;

public class MainMenu implements MainUser
{

    @Override
    public void drawMainMenu() {

    }

    @Override
    public int intInput() {
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

    @Override
    public String stringInput() {
        Scanner scan = new Scanner(System.in);
        return scan.next();
    }
}
