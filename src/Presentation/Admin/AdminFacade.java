package Presentation.Admin;

import BusinessModel.ESSTrading;
import Presentation.Shared.SharedFacade;

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
        mainMenu.drawMainMenu();
    }

    public void setAuthentication(boolean b) {
        this.authenticated = b;
    }
}
