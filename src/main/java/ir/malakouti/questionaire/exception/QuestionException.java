package ir.malakouti.questionaire.exception;

public class QuestionException extends RuntimeException {

    public QuestionException() {
    }

    public QuestionException(String message) {
        super(message);
    }

    public QuestionException(String message, Throwable cause) {
        super(message, cause);
    }
}
