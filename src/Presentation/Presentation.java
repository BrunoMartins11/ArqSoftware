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

    public Presentation(ESSTrading ess) {
        sharedFacade = new SharedFacade();
        userFacade = new UserFacade(sharedFacade);
        adminFacade = new AdminFacade(sharedFacade);
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
                sharedFacade.openStocksMenu();
                break;
            case 2:
                openLoginMenu();
                break;
            case 3:
                openRegistrationMenu();
                break;
            default:
                break;
        }
    }

    // LOGIN MENU - opens login menu
    public void openLoginMenu() {
        String email, password;
        sharedFacade.openLoginMenu();
        email = sharedFacade.getEmailInput();

        System.out.println("Insira a Password\n");
        password = sharedFacade.getPasswordInput();
        if (authentication(email, password)) {
            if (true) // IS USER
            {
                userFacade.setAuthentication(true);
                userFacade.openStartUpMenu();
                userFacade.openStartUpMenu();
            } else // IS ADMIN
            {
                adminFacade.openStartUpMenu();
                adminFacade.setAuthentication(true);
                adminFacade.openStartUpMenu();
            }

        } else // authentication failed
        {
            System.out.println("Email ou Password Errados\n");
            sharedFacade.openStartUpMenu();
        }
    }

    // REGISTRATION MENU - opens registration menu
    public void openRegistrationMenu() {
        String emailInput, passwordInput = "";

        sharedFacade.openRegistrationMenu();
        emailInput = sharedFacade.getEmailInput();
        System.out.println("Insira uma Password\n");
        passwordInput = sharedFacade.getPasswordInput();

        saveNewUser(emailInput, passwordInput);
    }

    private void saveNewUser(String emailInput, String passwordInput) {
        if (essTrading.saveNewUser(emailInput,passwordInput))
        {
            System.out.println("Registado com Sucesso!\n");
        } else {
            System.out.println("Utilizador não registado\n");
        }
        openStartUpMenu();
    }


    private boolean authentication(String email, String password) {
        return essTrading.loginUser(email, password);
    }


}
