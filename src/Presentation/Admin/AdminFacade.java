package Presentation.Admin;

import BusinessModel.ESSTrading;
import Presentation.Shared.SharedFacade;

public class AdminFacade
{
    private SharedFacade sharedFacade;
    private boolean authenticated;
    private ESSTrading essTrading;

    public AdminFacade(SharedFacade sharedFacade)
    {
        this.sharedFacade = sharedFacade;
    }

    public void setEssTrading(ESSTrading ess)
    {
        essTrading = ess;
    }

    public void openStartUpMenu()
    {

    }

    public void setAuthentication(boolean b) {
        this.authenticated = b;
    }
}
