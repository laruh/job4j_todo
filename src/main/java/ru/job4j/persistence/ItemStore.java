package ru.job4j.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Item;

import java.util.List;

@Repository
public class ItemStore {
    private final SessionFactory sf;

    public ItemStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Item add(Item item) {
        try (Session session = sf.openSession()) {
            session.save(item);
            return item;
        }
    }

    public List<Item> getALLItems() {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            var rsl = session.createQuery("from ru.job4j.model.Item.class").list();
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
