package Services;


public interface Subscriber {
    public void addObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}
