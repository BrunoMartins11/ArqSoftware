import BusinessModel.ESSTrading;
import Presentation.Presentation;
import Presentation.Shared.StartupMenu;

public class Main
{
    private static ESSTrading essTrading;
    private static Presentation presentation;

    public static void main(String[] args) {
        essTrading = new ESSTrading();
        presentation = new Presentation(essTrading);
    }
}
