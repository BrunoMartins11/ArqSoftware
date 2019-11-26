package BusinessModel;

import BusinessModel.Assets.Asset;

public interface Subscriber {
    public void addObserver(Asset o);
    public void notifyObservers();
}
