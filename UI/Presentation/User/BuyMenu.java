package Presentation.User;

import java.util.Scanner;

public class BuyMenu implements Investor
{

    @Override
    public void drawMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Menu Abrir Posição\n")
               .append("Indique o Número do Item\n");

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

    public void drawPositionType()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Selecione o tipo de Posição\n")
               .append("1. Compra\n")
               .append("2. Venda\n")
               .append("3. Cancelar\n");
        System.out.println(builder);
    }

    public void drawSLTPValues()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Menu Abrir Posição\n")
               .append("Deseja definir valores TP/SL? [Y/N]\n");
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

    public void printSuccessfulOperation()
    {
        System.out.println("Operação Efectuada com sucesso\n");
    }

    public void printNoCredit()
    {
        System.out.println("Não tem crédito suficiente para a operação\n");
    }
}
