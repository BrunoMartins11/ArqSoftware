import BusinessModel.ESSTrading;
import Presentation.Presentation;

public class Main
{
    private static ESSTrading essTrading;
    private static Presentation presentation;

    public static void main(String[] args) {
        essTrading = new ESSTrading();
        presentation = new Presentation(essTrading);
        presentation.openStartUpMenu();
    }
}
