package Presentation.Shared;

import BusinessModel.Assets.Asset;
import BusinessModel.ESSTrading;

import java.util.ArrayList;
import java.util.List;

public class SharedFacade
{
    private Registration registration;
    private StartupMenu startMenu;
    private Login login;
    private Stocks stocks;
    private ESSTrading essTrading;

    public SharedFacade()
    {
        registration = new Registration();
        startMenu = new StartupMenu();
        stocks = new Stocks();
        login = new Login();
    }

    public void setEssTrading(ESSTrading ess)
    {
        essTrading = ess;
    }

    // START UP MENU - opens start up menu
    public int openStartUpMenu()
    {
        int option;
        startMenu.drawMainMenu();
        option = startMenu.intInput();
        return option;
    }

    // STOCK MENU
    public void openStocksMenu()
    {
        List<Asset> assets = new ArrayList<>(essTrading.getAssets().values());
        String stock = "";
        stocks.drawMainMenu();
        int stockType = stocks.intInput();

        switch (stockType)
        {
            case 1:
                stock = "Commodities";
                stocks.drawSecondMenu(assets, stock);
                openStartUpMenu();
                break;
            case 2:
                stock = "Coin";
                stocks.drawSecondMenu(assets, stock);
                openStartUpMenu();
                break;
            case 3:
                stock = "Stocks";
                stocks.drawSecondMenu(assets, stock);
                openStartUpMenu();
                break;
            default:
                openStartUpMenu();
        }
        if(stockType != 4)
            startMenu.drawMainMenu();
            //openSecondStockMenu(assetList,stock);
    }

    public void openSecondStockMenu(Object assetList, String stock)
    {
        int input;
        //stocks.drawSecondMenu(assetList,stock);
        //input = stocks.input();

        //if(input != 0)
        //    openStocksMenu();
    }


    // LOGIN MENU - opens login menu
    public void openLoginMenu()
    {
        login.drawMainMenu();
    }

    public void openRegistrationMenu()
    {
        registration.drawMainMenu();
    }

    public String getEmailInput() {
        return login.stringInput();
    }

    public String getPasswordInput() {
        return login.stringInput();
    }
}
