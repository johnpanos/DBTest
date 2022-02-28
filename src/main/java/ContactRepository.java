import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

// Repository which provides fetching and inserting for ContactDBO
public class ContactRepository {
    public Session db;

    public ContactRepository(Session dbToUse) {
        this.db = dbToUse;
    }

    public List<ContactDBO> getAll() {
        return this.db.createQuery("SELECT a FROM ContactDBO a", ContactDBO.class).getResultList();
    }

    public void insertAll(ContactDBO... contacts) {
        Transaction insertTransaction = this.db.beginTransaction();
        for (ContactDBO contact : contacts) {
            this.db.save(contact);
        }
        insertTransaction.commit();
    }
}
