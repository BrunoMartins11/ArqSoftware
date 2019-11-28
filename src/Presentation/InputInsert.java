package Presentation;

import java.util.Scanner;

public class InputInsert
{
    public int getIntInput()
    {
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

    public double getDoubleInput()
    {
        Scanner scan = new Scanner(System.in);
        return scan.nextDouble();
    }


    public String getStringInput()
    {
        Scanner scan = new Scanner(System.in);
        return scan.next();
    }

    public String getLineInput()
    {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }
}
