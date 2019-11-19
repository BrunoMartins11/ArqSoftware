package Presentation.User;

import Presentation.MainUser;

import java.util.Scanner;

public class PositionManage implements MainUser
{

    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Menu Gerir Posição\n")
               .append("Insira o número da posição\n")
               .append("Insira -1 para regressar\n");

        System.out.println(builder);
    }

    public void drawSLTPValues()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Deseja redefinir valores TP/SL? [Y/N]\n");
        System.out.println(builder);
    }

    public void tpValueDraw()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Insira limite TP (-1 se não desejar definir)\n");
        System.out.println(builder);
    }

    public void slValueDraw()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Insira limite SL (-1 se não desejar definir)\n");
        System.out.println(builder);
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
