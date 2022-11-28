package ProducerBuyer;

import java.util.*;

public class ProducerBuyer {
    List<String> output = new ArrayList<>();

    Map<String, Set<String>> subscribers = new LinkedHashMap<>();

    public ProducerBuyer() {}

    public Map<String, Set<String>> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Map<String, Set<String>> subscribers) {
        this.subscribers = subscribers;
    }

    public List<String> getOutput() {
        return output;
    }

    public void setOutput(List<String> output) {
        this.output = output;
    }

    public void processInput(String command) {
        try {
            String[] input = command.split(",");
            if (command.startsWith("subscribe")) {
                addSubscriber(input[1].trim().toLowerCase(), input[2].trim().toLowerCase());
            } else if (command.startsWith("unsubscribe")) {
                removeSubscriber(input[1].trim().toLowerCase(), input[2].trim().toLowerCase());
            } else {
                publish(input[1].trim().toLowerCase(), input[2].trim().toLowerCase(), input[3].trim().toLowerCase(), input[4].trim().toLowerCase());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            subscribers.clear();
            output = new ArrayList<>();
        }
    }

    public List<String> getAggregatedOutput() {
        return output;
    }

    public void reset() {
        subscribers.clear();
    }

    public void addSubscriber(String buyerName, String prodCat) {
        ISubscriber subscriber = new Buyer(this);
        subscriber.subscribe(buyerName, prodCat);
    }

    public void removeSubscriber(String buyerName, String prodCat) {
        ISubscriber subscriber = new Buyer(this);
        subscriber.unsubscribe(buyerName, prodCat);
    }

    public void publish(String producerName, String prodCat, String modelType, String fuelType) {
        IPublisher publisher = new Producer(this);
        publisher.publish(producerName, prodCat, modelType, fuelType);
    }
}
