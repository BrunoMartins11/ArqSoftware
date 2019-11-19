package Presentation.Shared;

import Presentation.User.UserFacade;

public class SharedFacade
{
    private Presentation.Shared.Registration registration;
    private StartupMenu startMenu;
    private Presentation.Shared.Login login;
    private UserFacade userFacade;

    public SharedFacade()
    {
        registration = new Registration();
        startMenu = new StartupMenu();
        login = new Login();
    }

    public void setUserFacade(UserFacade user)
    {
        this.userFacade = user;
    }

    // START UP MENU - opens start up menu
    public boolean openStartUpMenu()
    {
        int option;
        startMenu.drawMainMenu();
        option = startMenu.intInput();
        return processStartUp(option);
    }
    // START UP MENU - manages user input
    private boolean processStartUp(int option)
    {
        boolean ret = false;
        switch (option)
        {
            case 1:
                openStocksMenu();
                break;
            case 2:
                openLoginMenu();
                ret = true;
                break;
            case 3:
                openRegistrationMenu();
                break;
            default:
                break;
        }

        return ret;
    }

    // STOCK MENU
    public void openStocksMenu()
    {
        //AssetList assetList = new AssetList();
        int stockType = 0;
        String stock = "";
        //stocks.drawMenu();
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

    // REGISTRATION MENU - opens registration menu
    public void openRegistrationMenu()
    {
        String emailInput, passwordInput = "";

        registration.drawMainMenu();
        emailInput = registration.stringInput();
        System.out.println("Insira uma Password\n");
        passwordInput = registration.stringInput();

        saveNewUser(emailInput,passwordInput);
    }

    private void saveNewUser(String emailInput, String passwordInput)
    {
        //TODO CALL BUSINESS
        System.out.println("Registado com Sucesso!");
    }

    // LOGIN MENU - opens login menu
    public void openLoginMenu()
    {
        String email, password;
        login.drawMainMenu();
        email = login.stringInput();
        System.out.println("Insira a Password\n");
        password = login.stringInput();

        if(authentication(email,password))
        {
            System.out.println("Login Efetuado");
            userFacade.openStartUpMenu();
        }
        else // authentication failed
        {
            System.out.println("Email ou Password Errados\n");
            openStartUpMenu();
        }
    }

    public boolean authentication(String email, String password)
    {
        if(true)//this.users.authentication(email,password))
        {
            userFacade.setAuthentication(true);
            return true;
        }
        else
            return false;
    }
}
