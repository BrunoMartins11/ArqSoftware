package Presentation;

import BusinessModel.ESSTrading;
import Presentation.Admin.AdminFacade;
import Presentation.Shared.SharedFacade;
import Presentation.User.UserFacade;

public class Presentation {
    private SharedFacade sharedFacade;
    private UserFacade userFacade;
    private AdminFacade adminFacade;
    private ESSTrading essTrading;

    public Presentation(ESSTrading ess)
    {
        sharedFacade = new SharedFacade();
        userFacade = new UserFacade(sharedFacade);
        adminFacade = new AdminFacade(sharedFacade);
        essTrading = ess;
        sharedFacade.openStartUpMenu();
    }
}
