package local.david.shop.dao;

import local.david.shop.common.AbstractBaseDAO;
import local.david.shop.model.Book;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by [david] on 21.03.17.
 */
@Service
public class BookDAO extends AbstractBaseDAO<Book> {
    private static final Logger logger = LoggerFactory.getLogger(BookDAO.class);
    @PersistenceContext
    private EntityManager entityManager;
    private static final AtomicLong atomicLong = new AtomicLong(0);

    public BookDAO() {
        super(Book.class, "BOOK");
    }

    @Override
    public Book findById(Object id) {
        if (atomicLong.getAndAdd(1) == 1) {
            transactionsTest2(new Long(String.valueOf(id)));
        } else {
            transactionsTest(new Long(String.valueOf(id)));
        }
        return null;
    }

    @Transactional
    private Book transactionsTest(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Book book = session.load(Book.class, id, LockMode.PESSIMISTIC_FORCE_INCREMENT);
        logger.warn("{}", session.getCurrentLockMode(book));
        logger.warn("1 : {}", book.getAmount());
        book.setAmount((int) atomicLong.get());
        session.saveOrUpdate(book);
        logger.warn("2 : {}", book.getAmount());
        return book;
    }

    @Transactional
    private Book transactionsTest2(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Book book = session.load(Book.class, id, LockMode.PESSIMISTIC_FORCE_INCREMENT);
        logger.warn("1 : {}", book.getAmount());
        book.setAmount((int) atomicLong.get());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        session.save(book);
        logger.warn("2 : {}", book.getAmount());
        return book;
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }


}
