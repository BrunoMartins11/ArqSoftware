package Presentation.Shared;

import BusinessModel.ESSTrading;

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
        //AssetList assetList = new AssetList();
        int stockType = 0;
        String stock = "";
        stocks.drawMainMenu();
        //stockType = stocks.input();

        switch (stockType)
        {
            case 1:
                //assetList.assetGenerator_Commodities();
                stock = "Commodities";
                break;
            case 2:
                //assetList.assetGenerator_Coin();
                stock = "Coin";
                break;
            case 3:
                //assetList.assetGenerator_Stocks();
                stock = "Stocks";
                break;
            default:
                openStartUpMenu();
        }
        if(stockType != 4)
            System.out.println("TODO");
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
