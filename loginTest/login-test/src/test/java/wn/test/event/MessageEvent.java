package wn.test.event;

public class MessageEvent {

    private String msg;
    private int age;
    private String address;

    public MessageEvent(String msg, int age, String address) {
        this.msg = msg;
        this.age = age;
        this.address = address;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "msg='" + msg + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
