package Presentation.Shared;

import BusinessModel.Assets.Asset;
import BusinessModel.ESSTrading;
import Presentation.InputInsert;

import java.util.ArrayList;
import java.util.Collection;

public class SharedFacade
{
    private Registration registration;
    private StartupMenu startMenu;
    private Login login;
    private Stocks stocks;
    private InputInsert input;
    private ESSTrading essTrading;

    public SharedFacade()
    {
        registration = new Registration();
        startMenu = new StartupMenu();
        stocks = new Stocks();
        input = new InputInsert();
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
        option = input.getIntInput();
        return option;
    }

    // STOCK MENU
    public void openStocksMenu()
    {
        Collection<Asset> assets = new ArrayList<>();
        String stock = "";
        stocks.drawMainMenu();
        int stockType = input.getIntInput();

        switch (stockType)
        {
            case 1:
                stock = "COMMODITY";
                assets = essTrading.getAssetsByType(stock).values();
                stocks.drawSecondMenu(assets, stock);
                break;
            case 2:
                stock = "COIN";
                assets = essTrading.getAssetsByType(stock).values();
                stocks.drawSecondMenu(assets, stock);
                break;
            case 3:
                stock = "STOCK";
                assets = essTrading.getAssetsByType(stock).values();
                stocks.drawSecondMenu(assets, stock);
                break;
        }
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
        return input.getStringInput();
    }

    public String getPasswordInput() {
        return input.getStringInput();
    }

    public void drawPasswordOutput()
    {
        this.login.drawPasswordMenu();
    }
}
