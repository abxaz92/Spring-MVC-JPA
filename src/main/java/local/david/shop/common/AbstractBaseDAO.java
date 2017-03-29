package local.david.shop.common;

import local.david.shop.common.exception.DocumentNotFoundException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by [david] on 21.03.17.
 */
public abstract class AbstractBaseDAO<T> {
    private static final Logger log = LoggerFactory.getLogger(AbstractBaseDAO.class);
    protected Class type;
    protected String tablename;

    public AbstractBaseDAO(Class type, String tablename) {
        this.type = type;
        this.tablename = tablename;
    }

    protected abstract EntityManager getEntityManager();

    protected int getDefaultLimit() {
        return 50;
    }

    public T findById(Object id) {
        return (T) getEntityManager().find(type, id);
    }

    @Transactional
    public void post(T entity) {
        insert(entity);
    }

    @Transactional
    public void deleteById(Object id) {
        T entity = findById(id);
        if (entity == null)
            throw new DocumentNotFoundException();
        getEntityManager().remove(entity);
    }

    @Transactional
    public void update(T entity) {
        try (Session session = getEntityManager().unwrap(Session.class)) {
            session.merge(entity);
        }
    }


    public Object findAll(Integer skip, Integer limit, String count) {
        if (count == null) {
            TypedQuery<T> query = getEntityManager().createNamedQuery(type.getSimpleName() + ".getAll", type);
            if (skip != null)
                query.setFirstResult(skip);
            query.setMaxResults(limit != null ? limit : getDefaultLimit());

            return query.getResultList();
        } else {
            String sql = "SELECT COUNT(d) FROM " + type.getName() + " d";
            Query query = getEntityManager().createQuery(sql);
            return query.getSingleResult();
        }
    }


    private void insert(Object object) {
        try (Session session = getEntityManager().unwrap(Session.class)) {
            session.save(object);
        }
    }

    public void insert(List<T> entities) {
        try (Session session = getEntityManager().unwrap(Session.class)) {
            entities.stream().forEach(session::save);
        }

    }

}
