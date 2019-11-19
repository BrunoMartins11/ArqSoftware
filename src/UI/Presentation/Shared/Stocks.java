



import java.util.Scanner;

public class Stocks implements Presentation.Shared.Shared {
    @Override
    public void drawMenu()
    {
        StringBuilder builder = new StringBuilder();
        Scanner scan = new Scanner(System.in);
        int input;

        builder.append("Menu Ver Mercados\n")
               .append("-- Escolha o tipo de Mercado --\n")
               .append("1. Commodities\n")
               .append("2. Moeda\n")
               .append("3. Stock\n")
               .append("4. Voltar ao Menu Iniciar\n");
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

    public void drawSecondMenu(AssetList assets, String type)
    {
        StringBuilder builder = new StringBuilder();
        int i = 0;

        builder.append("Ver Mercados - ")
               .append(type)
               .append("\n")
               .append("NR. | Item | Value | State \n");


        for(Asset a : assets.getAssets())
        {
            builder.append(insertItem(a));
        }

        builder.append("------------------------------------------------\n")
               .append("1. Regressar\n");

        System.out.println(builder);
    }

    private String insertItem(Asset o)
    {
        StringBuilder builder = new StringBuilder();

        builder.append(o.getId())
               .append(" | ")
               .append(o.getName())
               .append(" | ")
               .append(o.getValue())
               .append(" | ")
               .append(o.getState())
               .append("\n");

        return builder.toString();
    }
}
