package ir.malakouti.questionaire.exception;

public class AnswerException extends RuntimeException{
    public AnswerException() {
    }

    public AnswerException(String message) {
        super(message);
    }

    public AnswerException(String message, Throwable cause) {
        super(message, cause);
    }
}
