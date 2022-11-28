package ProducerBuyer;

public interface ISubscriber {
    void subscribe(String name, String prodCat);
    void unsubscribe(String name, String prodCat);
}
