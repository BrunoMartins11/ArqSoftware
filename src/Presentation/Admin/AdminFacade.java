package Presentation.Admin;

import BusinessModel.ESSTrading;
import BusinessModel.Report.Bug;
import Presentation.Shared.SharedFacade;

import java.util.List;

public class AdminFacade
{
    private SharedFacade sharedFacade;
    private boolean authenticated;
    private ESSTrading essTrading;
    private MainMenu mainMenu;

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
        setAuthentication(true);
        int scanned = mainMenu.intInput();
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

    public void setAuthentication(boolean b) {
        this.authenticated = b;
    }
}
