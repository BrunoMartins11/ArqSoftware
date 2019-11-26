package Presentation.User;
import BusinessModel.Assets.Asset;
import BusinessModel.ESSTrading;
import Presentation.Shared.InputInsert;
import Presentation.Shared.SharedFacade;

import java.util.ArrayList;
import java.util.Collection;

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
    private InputInsert input;
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
        this.input = new InputInsert();
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
            processStartUpInput(input.getIntInput());
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
        double valor = input.getDoubleInput();
        essTrading.insertCredit(userID,valor);
        //System.out.println("Montante adicionado!\n");
        openStartUpMenu();
    }

    private void openWithdrawCreditMenu()
    {
        withdrawCredit.drawMainMenu();
        double valor = input.getDoubleInput();
        essTrading.takeCredit(userID,valor);
        //System.out.println("Montante retirado!\n");
        openStartUpMenu();
    }

    private void openPortfolioMenu()
    {
        portfolioMenu.drawMainMenu();
        portfolioMenu.drawSecondMenu(essTrading.getPortfolio(userID));
        int in = input.getIntInput();
        processPortfolioInput(in);
    }

    private void processPortfolioInput(int in)
    {
        switch(in)
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
        int number = input.getIntInput();
        if(number == -1)
        {
            openPortfolioMenu();
        }
        else
        {
            positionManage.drawSLTPValues();
            String response = input.getStringInput();
            if(response.equals("y") || response.equals("Y"))
            {
                positionManage.tpValueDraw();
                double tp = input.getDoubleInput();
                positionManage.slValueDraw();
                double sl = input.getDoubleInput();
                //TODO LOGIC
            }
            openPortfolioMenu();
        }
    }

    private void openWatchListMenu()
    {
        Collection<Asset> assets = new ArrayList<>();
        watchListMenu.drawWatchListMenu(essTrading.getInvestorWatchList(userID));
        int in = input.getIntInput();
        processWatchListInput(in);
    }

    private void processWatchListInput(int in)
    {
        Collection<Asset> assetList = new ArrayList<>();
        switch (in)
        {
            case 1:
                assetList = essTrading.getInvestorWatchList(userID);
                openBuyMenu(assetList);
                break;
            case 2:
                assetList = essTrading.getInvestorWatchList(userID);
                removeItemWatchList(assetList);
                break;
            case 3:
                openPortfolioMenu();
                break;
        }
    }

    private void removeItemWatchList(Collection<Asset> assetList)
    {
        watchListMenu.drawRemoveItemMenu();
        int option = input.getIntInput();
        if(assetList.stream().filter(a -> a.getId() == option).count() > 0)
        {
            essTrading.removeItemFromWatchList(userID,option);
        }
        else
        {
            removeItemWatchList(assetList);
        }
        openWatchListMenu();
    }

    public void openStocksMenu()
    {
        int in;
        if(authenticated)
        {
            this.stocksMenu.drawMainMenu();
            in = input.getIntInput();
            processMarketInput(in);
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
                openSecondStockMenu(assets, type);
                break;
            case 2:
                type = "COIN";
                assets = essTrading.getAssetsByType(type).values();
                openSecondStockMenu(assets, type);
                break;
            case 3:
                type = "STOCK";
                assets = essTrading.getAssetsByType(type).values();
                openSecondStockMenu(assets, type);
                break;
            case 4:
                openStartUpMenu();
                break;
        }
    }

    public void openSecondStockMenu(Collection<Asset> assetList, String stock)
    {
        int in;
        this.stocksMenu.drawSecondMenu(assetList, stock);
        in = input.getIntInput();

        switch(in)
        {
            case 1:
                openBuyMenu(assetList);
                break;
            case 2:
                openAddToWatchList();
                break;
            case 3:
                openStocksMenu();
                break;
        }
    }

    private void openAddToWatchList()
    {
        watchListMenu.addToWatchListMenu();
        int in = input.getIntInput();
        essTrading.addItemToWatchList(userID,in);
        openStartUpMenu();
    }

    public void openBuyMenu(Collection<Asset> assetList)
    {
        int positionType, itemNumber;

        buyMenu.drawMainMenu();
        itemNumber = input.getIntInput();
        if(itemNumber != -1)
        {
            if(assetList.stream().filter(a -> a.getId() == itemNumber).count() > 0)
            {
                buyMenu.drawPositionType();
                positionType = input.getIntInput();
                processBuyInput(itemNumber,positionType);
            }
            else
            {
                System.out.println("Item não se encontra na lista!");
                openBuyMenu(assetList);
            }
        }
        else
        {
            openStartUpMenu();
        }
    }

    //positionType : 1 - compra; 2 - venda
    public void processBuyInput(int assetID, int positionType)
    {
        double tp = 0;
        double sl = 0;
        System.out.println("Indique o número de ativos a adquirir\n");
        double numberOfAssets = input.getDoubleInput();
        boolean userhasMoney = essTrading.checkUserCredit(userID, assetID, numberOfAssets);
        if(userhasMoney)
        {
            buyMenu.drawSLTPValues();
            String option = input.getStringInput();
            if(option.equals("Y") || option.equals("y"))
            {
                buyMenu.tpValueDraw();
                tp = input.getDoubleInput();
                buyMenu.slValueDraw();
                sl = input.getDoubleInput();
            }
            essTrading.createCFD(userID,positionType, assetID,numberOfAssets,tp,sl);
            openStartUpMenu();
        }
        else
        {
            buyMenu.printNoCredit();
            openStartUpMenu();
        }
    }

    public void openSellMenu()
    {
        sellMenu.drawMainMenu();
        int cfdToClose = input.getIntInput();
        essTrading.closePosition(userID,cfdToClose);
    }

    public void openBugReportMenu()
    {
        bugMenu.drawMainMenu();
        int in = input.getIntInput();
        if(in == 1)
        {
            String bug = input.getStringInput();
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
