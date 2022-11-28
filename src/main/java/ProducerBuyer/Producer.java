package ProducerBuyer;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Producer implements IPublisher {

    private final ProducerBuyer producerBuyer;

    public Producer(ProducerBuyer producerBuyer) {
        this.producerBuyer = producerBuyer;
    }

    @Override
    public void publish(String name, String prodCat, String modelType, String fuelType) {
        notifyAll(producerBuyer.getSubscribers(), prodCat, name, modelType, fuelType);
    }

    public void notifyAll(Map<String, Set<String>> subscribers, String prodCat, String producerName, String modelType, String fuelType) {
        subscribers.forEach((buyer, subscriptions) -> {
            if (subscriptions.contains(prodCat)) {
                List<String> output = producerBuyer.getOutput();
                output.add(buyer + " notified " + prodCat + ": " + producerName + " " + modelType + ", " + fuelType + " fuel");
                producerBuyer.setOutput(output);
            }
        });
    }
}
