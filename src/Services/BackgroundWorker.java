package Services;

import BusinessModel.Assets.Asset;
import BusinessModel.Assets.AssetType;
import Presentation.User.UserFacade;
import com.intrinio.api.SecurityApi;
import com.intrinio.invoker.ApiClient;
import com.intrinio.invoker.ApiException;
import com.intrinio.invoker.Configuration;
import com.intrinio.invoker.auth.ApiKeyAuth;
import com.intrinio.models.RealtimeStockPrice;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BackgroundWorker implements Subscriber, Runnable {
    private Map<String, Observer> assets;
    private List<Pair<String, Double>> names;
    private UserFacade userFacade;
    private boolean exit;

    public BackgroundWorker() {
        assets = new HashMap<>();
        this.names = new ArrayList<>();
        this.exit = false;
    }

    @Override
    public void addObserver(Observer o) {
        if(o instanceof Asset)
        {
            Asset a = (Asset) o;
            this.assets.put(a.getCompany(),a);
        }
        if(o instanceof UserFacade){
            //System.out.println("Registei"); TODO CHECK
            userFacade = (UserFacade) o;
        }
    }

    @Override
    public void removeObserver(Observer o) {

    }

    @Override
    public void notifyObservers() {
        for (Pair name: names) {
            Asset a = (Asset) assets.get(name.getKey());
            if(a.getValue()/ (Double) name.getValue() > 1.05 && userFacade != null){
                userFacade.update(a.getId(), (a.getValue()/ (Double) name.getValue() -1)*100 );
            } else if(a.getValue()/ (Double) name.getValue() > 0.05 && userFacade != null){
                userFacade.update(a.getId(), (a.getValue()/ (Double) name.getValue())*100 );
            }
            a.update(a.getId(), (Double) name.getValue());

        }
    }

    @Override
    public void run() {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth auth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
        auth.setApiKey("OjU4MmY0MDY1NjFkZTZiMjgxOGY1NDFjNjBjODk4ZDk4");

        try {
            while (!exit) {
                names = new ArrayList<>();
                for (Observer o : assets.values()) {
                    if(o instanceof Asset)
                    {
                        Asset a = (Asset) o;
                        if(a.getType() == AssetType.STOCK)
                        {
                            SecurityApi securityApi = new SecurityApi();
                            RealtimeStockPrice result = securityApi.getSecurityRealtimePrice(a.getCompany(), null);
                            if(result.getLastPrice().doubleValue() != a.getValue()){
                                names.add(new Pair<>(a.getCompany(), result.getLastPrice().doubleValue()));
                            }
                        }
                    }
                }
                notifyObservers();
                Thread.sleep(10000);
            }
        }
        catch (ApiException | InterruptedException e ) {
            System.err.println("Exception when calling SecurityApi#getSecurityStockPrices");
            e.printStackTrace();
        }
    }

    public void stop()
    {
        this.exit = true;
    }
}
