package ru.job4j.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Item;

import java.util.List;

@Repository
public class ItemStore {
    private final SessionFactory sf;

    public ItemStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Item save(Item item) {
        try (Session session = sf.openSession()) {
            session.save(item);
            return item;
        }
    }

    public List<Item> getAll() {
        try (Session session = sf.openSession()) {
            return session.createQuery("from ru.job4j.model.Item.class").list();
        }
    }
}
