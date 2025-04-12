package calculator.salarycalculator.error;

public class CalculatorResponseError {
    String attribute;
    String code;
    String message;

    public CalculatorResponseError(String attribute, String code, String message) {
        this.attribute = attribute;
        this.code = code;
        this.message = message;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
