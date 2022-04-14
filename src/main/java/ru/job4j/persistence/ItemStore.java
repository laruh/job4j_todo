package ru.job4j.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Item;

import java.util.List;
import java.util.function.Function;

@Repository
public class ItemStore {
    private final SessionFactory sf;

    public ItemStore(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public Item add(Item item) {
        return this.tx(session -> {
            session.save(item);
            return item;
        });
    }

    public List<Item> getALLItems() {
        return this.tx(session -> session.createQuery("select i from Item i").list());
    }

    public Item findById(int id) {
        return this.tx(session -> session.get(Item.class, id));
    }

    public Object update(Item item) {
        return this.tx(session -> {
            session.update(item);
            return new Object();
        });
    }

    public List<Item> findByConditionDone(boolean condition) {
        return this.tx(session -> session.createQuery("from Item where done = :condition")
                .setParameter("condition", condition)
                .list());
    }

    public Integer makeDoneById(int id) {
        return this.tx(session -> session
                .createQuery("update Item i set i.done = :condition where i.id = :id")
                .setParameter("condition", true)
                .setParameter("id", id)
                .executeUpdate());
    }

    public Object deleteById(int id) {
        return this.tx(session -> {
            Item item = new Item();
            item.setId(id);
            session.delete(item);
            return new Object();
        });
    }
}
