package rpn.rpn3;

public interface Bus {
    void publish(Message message);
    void subscribe(String eventType, Consumer consumer);
}
