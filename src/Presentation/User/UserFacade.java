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
    private int userID;

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
        this.userID = -1;
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
        portfolioMenu.drawSecondMenu(essTrading.getPortfolio(userID));
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
        double credit = essTrading.getUserCredit(userID);
        System.out.println("Crédito: " + credit + "\n");
        openPortfolioMenu();
    }

    private void openTotalInvested()
    {
        double invested = essTrading.getTotalInvestedByUser(userID);
        System.out.println("Total Invested: "+invested + " \n");
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
        Collection<Asset> assets = new ArrayList<>();
        stocksMenu.drawWatchListMenu(essTrading.getInvestorWatchList(userID));
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
                removeItemWatchList();
                break;
            case 3:
                openPortfolioMenu();
                break;
        }
    }

    private void removeItemWatchList()
    {
        watchListMenu.drawRemoveItemMenu();
        int option = watchListMenu.intInput();
        // TODO ESS LOGIC
        essTrading.removeItemFromWatchList(userID,option);
        openWatchListMenu();
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
            case 4:
                openStartUpMenu();
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

    //positionType : 1 - compra; 2 - venda
    public void processBuyInput(int assetID, int positionType)
    {
        double tp = 0;
        double sl = 0;
        System.out.println("Indique o número de ativos a adquirir\n");
        int numberOfAssets = buyMenu.intInput();
        boolean userhasMoney = essTrading.checkUserCredit(userID, assetID, numberOfAssets);
        if(userhasMoney)
        {
            buyMenu.drawSLTPValues();
            String option = buyMenu.stringInput();
            if(option.equals("Y") || option.equals("y"))
            {
                buyMenu.tpValueDraw();
                tp = buyMenu.doubleInput();
                buyMenu.slValueDraw();
                sl = buyMenu.doubleInput();
            }
            essTrading.createCFD(userID,positionType, assetID,numberOfAssets,tp,sl);
        }
        else
        {
            buyMenu.printNoCredit();
        }
        openStartUpMenu();
    }

    public void openSellMenu()
    {
        sellMenu.drawMainMenu();
        int cfdToClose = sellMenu.intInput();
        essTrading.closePosition(userID,cfdToClose);
    }

    public void openBugReportMenu()
    {
        bugMenu.drawMainMenu();
        int input = bugMenu.intInput();
        if(input == 1)
        {
            String bug = bugMenu.stringInput();
            essTrading.reportBug(userID,bug);
        }
        openStartUpMenu();
    }

    public void exit()
    {
        this.authenticated = false;
        this.userID = -1;
        sharedFacade.openStartUpMenu();
    }

    public void setAuthentication(boolean authentication, int userID)
    {
        this.authenticated = authentication;
        this.userID = userID;
    }
}
