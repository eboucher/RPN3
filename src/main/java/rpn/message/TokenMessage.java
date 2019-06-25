package rpn.message;

public class TokenMessage implements Message {
    public static final String MESSAGE_TYPE = "token";

    private final String token;
    private final String expressionId;

    public TokenMessage(String token, String expressionId) {
        this.token = token;
        this.expressionId = expressionId;
    }

    @Override
    public String messageType() {
        return MESSAGE_TYPE;
    }

    @Override
    public String getToken() {
        return token;
    }

    public String getExpressionId() { return expressionId; }
}
