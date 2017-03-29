package local.david.shop.ws.interfaces;

import local.david.shop.model.Book;

import javax.jws.WebMethod;

/**
 * Created by [david] on 21.03.17.
 */
public interface BookService {
    @WebMethod
    public Book searchById(Long id);

    @WebMethod
    public Long publish(Book book);

    @WebMethod
    public Book update(Book book);

    @WebMethod
    public void delete(Long id);
}
