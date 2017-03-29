package local.david.shop.common.exception;

/**
 * Created by [david] on 21.03.17.
 */
public class DocumentNotFoundException extends RuntimeException {
    public DocumentNotFoundException() {
        super("Document not found");
    }
}
