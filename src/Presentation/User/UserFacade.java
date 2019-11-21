package Presentation.User;
import BusinessModel.Assets.Asset;
import BusinessModel.ESSTrading;
import Presentation.Shared.SharedFacade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserFacade
{
    private SharedFacade sharedFacade;
    private MainMenu mainMenu;
    private StocksMenu stocksMenu;
    private BuyMenu buyMenu;
    private SellMenu sellMenu;
    private BugReport bugMenu;
    private InsertCredit insertCredit;
    private WithdrawCredit withdrawCredit;
    private PortfolioMenu portfolioMenu;
    private WatchListMenu watchListMenu;
    private PositionManage positionManage;
    private ESSTrading essTrading;
    private boolean authenticated;

    public UserFacade(SharedFacade shared)
    {
        this.sharedFacade = shared;
        this.mainMenu = new MainMenu();
        this.stocksMenu = new StocksMenu();
        this.bugMenu = new BugReport();
        this.buyMenu = new BuyMenu();
        this.sellMenu = new SellMenu();
        this.portfolioMenu = new PortfolioMenu();
        this.watchListMenu = new WatchListMenu();
        this.insertCredit = new InsertCredit();
        this.withdrawCredit = new WithdrawCredit();
        this.positionManage = new PositionManage();
        this.authenticated = false;
    }

    public void setEssTrading(ESSTrading ess)
    {
        this.essTrading = ess;
    }

    public void openStartUpMenu()
    {
        if(authenticated)
        {
            mainMenu.drawMainMenu();
            processStartUpInput(mainMenu.intInput());
        }
        else
        {
            System.out.println("Não está Autenticado!");
        }
    }

    public void processStartUpInput(int input)
    {
        switch(input)
        {
            case 1:
                openStocksMenu();
                break;
            case 2:
                openWatchListMenu();
                break;
            case 3:
                openPortfolioMenu();
                break;
            case 4:
                openInsertCreditMenu();
                break;
            case 5:
                openWithdrawCreditMenu();
                break;
            case 6:
                openBugReportMenu();
                break;
            case 7:
                exit();
                break;
        }
    }

    private void openInsertCreditMenu()
    {
        insertCredit.drawMainMenu();
        int valor = insertCredit.intInput();
        System.out.println("Montante adicionado!\n");
        openStartUpMenu();
    }

    private void openWithdrawCreditMenu()
    {
        withdrawCredit.drawMainMenu();
        int valor = withdrawCredit.intInput();
        System.out.println("Montante retirado!\n");
        openStartUpMenu();
    }

    private void openPortfolioMenu()
    {
        portfolioMenu.drawMainMenu();
        //portfolioMenu.drawSecondMenu();
        int input = portfolioMenu.intInput();
        processPortfolioInput(input);
    }

    private void processPortfolioInput(int input)
    {
        switch(input)
        {
            case 1:
                openSellMenu();
                break;
            case 2:
                openManagePositionMenu();
                break;
            case 3:
                openTotalInvested();
                break;
            case 4:
                openCreditMenu();
                break;
            default:
                openStartUpMenu();
                break;
        }

    }

    private void openCreditMenu()
    {
        System.out.println("Crédito: \n");
        openPortfolioMenu();
    }

    private void openTotalInvested()
    {
        System.out.println("Total Invested: \n");
        openPortfolioMenu();
    }

    private void openManagePositionMenu()
    {
        this.positionManage.drawMainMenu();
        int number = positionManage.intInput();
        if(number == -1)
        {
            openPortfolioMenu();
        }
        else
        {
            positionManage.drawSLTPValues();
            String response = positionManage.stringInput();
            if(response.equals("y") || response.equals("Y"))
            {
                positionManage.tpValueDraw();
                int tp = positionManage.intInput();
                positionManage.slValueDraw();
                int sl = positionManage.intInput();
            }
            openPortfolioMenu();
        }
    }

    private void openWatchListMenu()
    {
        //stocksMenu.drawWatchListMenu(this.assets);
        int input = stocksMenu.intInput();
        processWatchListInput(input);
    }

    private void processWatchListInput(int input)
    {
        switch (input)
        {
            case 1:
                openBuyMenu();
                break;
            case 2:
                //removeItemWatchList();
                break;
            case 3:
                openPortfolioMenu();
                break;
        }
    }

    public void openStocksMenu()
    {
        int input;
        if(authenticated)
        {
            this.stocksMenu.drawMainMenu();
            input = this.stocksMenu.intInput();
            processMarketInput(input);
        }
        else
        {
            this.stocksMenu.printAuthenticationFault();
        }

    }

    private void processMarketInput(int input)
    {
        String type = "";
        Collection<Asset> assets = new ArrayList<>();
        switch(input)
        {
            case 1:
                type = "COMMODITY";
                assets = essTrading.getAssetsByType(type).values();
                break;
            case 2:
                type = "COIN";
                assets = essTrading.getAssetsByType(type).values();
                break;
            case 3:
                type = "STOCK";
                assets = essTrading.getAssetsByType(type).values();
                break;
        }
        openSecondStockMenu(assets, type);
    }

    public void openSecondStockMenu(Collection<Asset> assetList, String stock)
    {
        int input;
        this.stocksMenu.drawSecondMenu(assetList, stock);
        input = this.stocksMenu.intInput();
        if(input == 1)
        {
            openBuyMenu();
        }
        else if(input == 2)
             {
                 openWatchListMenu();
             }
             else openStocksMenu();
    }

    public void openBuyMenu()
    {
        int positionType, itemNumber;

        buyMenu.drawMainMenu();
        itemNumber = buyMenu.intInput();
        buyMenu.drawPositionType();
        positionType = buyMenu.intInput();
        processBuyInput(itemNumber,positionType);
    }

    public void processBuyInput(int itemNumber, int positionType)
    {
        System.out.println("Indique o número de ativos a adquirir\n");
        int number = buyMenu.intInput();
        // open position with item number, position type, number of actives
        boolean userhasMoney = true; //TODO LOGIC STUFF
        if(userhasMoney)
        {
            buyMenu.drawSLTPValues();
            String option = buyMenu.stringInput();
            if(option.equals("Y") || option.equals("y"))
            {
                buyMenu.tpValueDraw();
                String tp = buyMenu.stringInput();
                buyMenu.slValueDraw();
                String sl = buyMenu.stringInput();
            }
            if(true) // TODO INSERT CHECK CONDITION
            {
                buyMenu.printSuccessfulOperation();
            }
        }
        else
        {
            buyMenu.printNoCredit();
        }
        openStartUpMenu();
    }

    public void openSellMenu()
    {

    }

    public void openBugReportMenu()
    {
        bugMenu.drawMainMenu();
        int input = bugMenu.intInput();
        if(input == 1)
        {
            String bug = bugMenu.stringInput();
            openBugReportMenu();
        }
        else
        {
            openStartUpMenu();
        }
    }

    public void exit()
    {
        this.authenticated = false;
        sharedFacade.openStartUpMenu();
    }

    public void setAuthentication(boolean authentication)
    {
        this.authenticated = authentication;
    }
}
