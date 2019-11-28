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
        this.mainMenu = new MainMenu();
        this.input = new InputInsert();
        this.authenticated = false;
    }

    public void setEssTrading(ESSTrading ess)
    {
        essTrading = ess;
    }

    public void openMainMenu() {
        mainMenu.drawMainMenu();

        int in = this.input.getIntInput();
        processInput(in);
    }

    private void processInput(int in)
    {
        switch (in)
        {
            case 1:
                sharedFacade.openStocksMenu();
                openMainMenu();
                break;
            case 2:
                openBugsMenu();
                break;
            case 3:
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
