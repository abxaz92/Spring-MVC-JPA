package local.david.shop.ws;

import local.david.shop.dao.BookDAO;
import local.david.shop.model.Book;
import local.david.shop.ws.interfaces.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by [david] on 21.03.17.
 */
@Endpoint
@WebService(name = "BookService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class BookServiceImpl implements BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    @Autowired
    private BookDAO bookDAO;

    @Override
    public Book searchById(Long id) {
        return bookDAO.findById(id);
    }

    @Override
    public Long publish(Book book) {
        bookDAO.post(book);
        return book.getId();
    }

    @Override
    public Book update(Book book) {
        bookDAO.update(book);
        return book;
    }

    @Override
    public void delete(Long id) {
        bookDAO.deleteById(id);
    }

}
