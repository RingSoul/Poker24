import java.util.NoSuchElementException;

public class NoCorrespondingOperationException extends NoSuchElementException {
    public NoCorrespondingOperationException(String message) {
        super(message);
    }
}
