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
            var rsl = session.createQuery("select i from Item i").list();
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public Item findById(int id) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            var rsl = session.get(Item.class, id);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void update(Item item) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            session.update(item);
            tx.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public List<Item> findByConditionDone(boolean condition) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            var rsl = session.createQuery("from Item where done = :condition")
                    .setParameter("condition", condition)
                    .list();
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void makeDoneById(int id) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            session.createQuery("update Item i set i.done = :condition where i.id = :id")
                    .setParameter("condition", true)
                    .setParameter("id", id)
                    .executeUpdate();
            tx.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void deleteById(int id) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            Item item = new Item();
            item.setId(id);
            session.delete(item);
            tx.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
