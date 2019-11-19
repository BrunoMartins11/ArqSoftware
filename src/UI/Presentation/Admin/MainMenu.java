package Presentation.Admin;

import java.util.Scanner;

public class MainMenu implements Admin
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
