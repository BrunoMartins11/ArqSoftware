package Presentation.User;

import Presentation.MainUser;

public class BugReport implements MainUser {
    @Override
    public void drawMainMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Menu de Bugs\n")
               .append("1. Reportar novo Bug\n")
               .append("2. Regressar\n");

        System.out.println(builder);
    }

    public void drawSecondMenu()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Descreva o Bug (Carregue enter para terminar)\n");
    }
}
