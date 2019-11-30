package Presentation.Admin;

import BusinessModel.Assets.Asset;
import BusinessModel.ESSTrading;
import BusinessModel.Report.Bug;
import Presentation.InputInsert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdminFacade
{
    private boolean authenticated;
    private ESSTrading essTrading;
    private MainMenu mainMenu;
    private Stocks stocksMenu;
    private InputInsert input;
    private int userID;

    public AdminFacade()
    {
        this.mainMenu = new MainMenu();
        this.input = new InputInsert();
        this.stocksMenu = new Stocks();
        this.authenticated = false;
    }

    public void setEssTrading(ESSTrading ess)
    {
        essTrading = ess;
    }

    public void openMainMenu() {
        mainMenu.drawMainMenu();

        if(authenticated)
        {
            int in = this.input.getIntInput();
            processInput(in);
        }
        else
        {
            System.out.println("-- User not Authenticated --");
        }
    }

    private void openStocksMenu()
    {
        this.stocksMenu.drawMainMenu();
        int inp = input.getIntInput();
        processInputStocks(inp);
    }

    private void processInputStocks(int inp)
    {
        String type = "";
        Collection<Asset> assets = new ArrayList<>();
        switch (inp)
        {
            case 1:
                type = "COMMODITY";
                assets = essTrading.getAssetsByType(type).values();
                this.stocksMenu.drawSecondMenu(assets,type);
                break;
            case 2:
                type = "COIN";
                assets = essTrading.getAssetsByType(type).values();
                this.stocksMenu.drawSecondMenu(assets,type);
                break;
            case 3:
                type = "STOCK";
                assets = essTrading.getAssetsByType(type).values();
                this.stocksMenu.drawSecondMenu(assets,type);
                break;
            default:
                openMainMenu();
                break;
        }
    }

    private void processInput(int in)
    {
        switch (in)
        {
            case 1:
                openStocksMenu();
                openMainMenu();
                break;
            case 2:
                openBugsMenu();
                break;
            default:
                exit();
                break;
        }
    }

    private void openBugsMenu()
    {
        List<Bug> bugs= essTrading.getBugs();
        for (Bug b: bugs) {
            System.out.println(b.getId() + ". " + b.getError());
        }

        openMainMenu();
    }

    public void setAuthentication(boolean b, int userID)
    {
        this.authenticated = b;
        this.userID = userID;
    }

    private void exit()
    {
        this.authenticated = false;
        this.userID = -1;
        essTrading.stopThread();
    }
}
