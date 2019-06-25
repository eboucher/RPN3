package rpn.message;

public class EndOfToken implements Message {
    public final static String MESSAGE_TYPE = "eot";
    public final String expressionId;

    public EndOfToken(String _expressionId) {
        expressionId = _expressionId;
    }


    @Override
    public String messageType() {
        return null;
    }

    @Override
    public String getToken() {
        return null;
    }

}
