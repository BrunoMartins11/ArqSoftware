package Presentation;

import BusinessModel.ESSTrading;
import BusinessModel.User.Investor;
import BusinessModel.User.User;
import Presentation.Admin.AdminFacade;
import Presentation.Shared.SharedFacade;
import Presentation.User.UserFacade;

public class Presentation {
    private SharedFacade sharedFacade;
    private UserFacade userFacade;
    private AdminFacade adminFacade;
    private ESSTrading essTrading;

    public Presentation(ESSTrading ess) {
        sharedFacade = new SharedFacade();
        userFacade = new UserFacade();
        adminFacade = new AdminFacade();
        essTrading = ess;

        sharedFacade.setEssTrading(essTrading);
        userFacade.setEssTrading(essTrading);
        adminFacade.setEssTrading(essTrading);
    }

    public void openStartUpMenu() {
        int opt = sharedFacade.openStartUpMenu();
        processStartUp(opt);
    }

    // START UP MENU - manages user input
    private void processStartUp(int option) {
        switch (option) {
            case 1:
                openStocksMenu();
                break;
            case 2:
                openLoginMenu();
                break;
            case 3:
                openRegistrationMenu();
                break;
            case 4:
                essTrading.stopThread();
                break;
        }
    }

    public void openStocksMenu()
    {
        sharedFacade.openStocksMenu();
        openStartUpMenu();
    }

    // LOGIN MENU - opens login menu
    public void openLoginMenu() {
        String email, password;
        sharedFacade.openLoginMenu();
        email = sharedFacade.getEmailInput();

        sharedFacade.drawPasswordOutput();
        password = sharedFacade.getPasswordInput();
        User u;

        int userID = essTrading.getUserID(email);

        if ((u = authentication(email, password)) != null) {
            if (u instanceof Investor) // IS USER
            {
                userFacade.setAuthentication(true, userID);
                userFacade.openStartUpMenu();
                userFacade.registerObserver();
            } else // IS ADMIN
            {
                adminFacade.setAuthentication(true, userID);
                adminFacade.openMainMenu();
            }

        } else // authentication failed
        {
            System.out.println("Wrong Username or Password\n");
            openStartUpMenu();
        }
    }


    // REGISTRATION MENU - opens registration menu
    public void openRegistrationMenu() {
        String emailInput, passwordInput = "";

        sharedFacade.openRegistrationMenu();
        emailInput = sharedFacade.getEmailInput();
        System.out.println("Insert your Password\n");
        passwordInput = sharedFacade.getPasswordInput();

        saveNewUser(emailInput, passwordInput);
    }

    private void saveNewUser(String emailInput, String passwordInput) {
        if (essTrading.saveNewUser(emailInput,passwordInput))
        {
            System.out.println("Registed with Success!\n");
        } else {
            System.out.println("User not registred\n");
        }
        openStartUpMenu();
    }


    private User authentication(String email, String password) {
        return essTrading.loginUser(email, password);
    }


}
