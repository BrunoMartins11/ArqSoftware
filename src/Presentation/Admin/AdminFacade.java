package Presentation.Admin;

import BusinessModel.ESSTrading;
import BusinessModel.Report.Bug;
import Presentation.Shared.InputInsert;
import Presentation.Shared.SharedFacade;

import java.util.List;

public class AdminFacade
{
    private SharedFacade sharedFacade;
    private boolean authenticated;
    private ESSTrading essTrading;
    private MainMenu mainMenu;
    private InputInsert input;
    private int userID;

    public AdminFacade(SharedFacade sharedFacade)
    {
        this.sharedFacade = sharedFacade;
    }

    public void setEssTrading(ESSTrading ess)
    {
        essTrading = ess;
    }

    public void openMainMenu()
    {
        mainMenu = new MainMenu();
        mainMenu.drawMainMenu();
        input = new InputInsert();
        int scanned = input.getIntInput();
        if(scanned == 1){
            sharedFacade.openStocksMenu();
        }
        if(scanned == 2) {
            List<Bug> bugs= essTrading.getBugs();
            for (Bug b: bugs) {
                System.out.println(b.getId() + ". " + b.getError());
            }
        }
        mainMenu.drawMainMenu();
    }

    public void setAuthentication(boolean b, int userID)
    {
        this.authenticated = b;
        this.userID = userID;
    }
}
