package Services;

import BusinessModel.Assets.Asset;
import BusinessModel.Assets.AssetType;
import BusinessModel.Subscriber;
import com.intrinio.api.SecurityApi;
import com.intrinio.invoker.ApiClient;
import com.intrinio.invoker.ApiException;
import com.intrinio.invoker.Configuration;
import com.intrinio.invoker.auth.ApiKeyAuth;
import com.intrinio.models.RealtimeStockPrice;
import javafx.util.Pair;

import java.util.*;

public class BackgroundWorker implements Subscriber, Runnable {
    Map<String, Asset> assets;
    List<Pair<String, Double>> names;
    boolean exit;

    public BackgroundWorker(Map<Integer, Asset> assetsmap) {
        assets = new HashMap<>();
        for (Asset a:  assetsmap.values()) {
            assets.put(a.getCompany(), a);
        };
        this.names = new ArrayList<>();
        this.exit = false;
    }

    @Override
    public void addObserver(Asset a) {
        assets.put(a.getCompany(), a);
    }

    @Override
    public void notifyObservers() {
        for (Pair name: names) {
            assets.get(name.getKey()).update((Double) name.getValue());
        }
    }

    @Override
    public void run() {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth auth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
        auth.setApiKey("OjU4MmY0MDY1NjFkZTZiMjgxOGY1NDFjNjBjODk4ZDk4");

        try {
            while (!exit) {
                for (Asset a : assets.values()) {
                    if(a.getCompany().equals(AssetType.STOCK))
                    {
                        SecurityApi securityApi = new SecurityApi();
                        RealtimeStockPrice result = securityApi.getSecurityRealtimePrice(a.getCompany(), null);
                        if(result.getLastPrice().doubleValue() != a.getValue()){
                            a.update(result.getLastPrice().doubleValue());
                            System.out.println(result.getLastPrice().toString());
                        }
                    }
                }
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
