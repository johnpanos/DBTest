import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Properties;

public class DBTest {
    public static void main(String[] args) {
        // Setup properties for hibernate
        Properties properties = new Properties();

        // Use in memory database, can change this to postgres
        properties.setProperty("hibernate.connection.url", "jdbc:h2:mem:test");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.setProperty("hibernate.connection.username", "sa");
        properties.setProperty("hibernate.connection.password", "");
        properties.setProperty("hibernate.connection.driver_class", "org.h2.Driver");

        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop"); // Auto create/drop objects from Java annotations
        properties.setProperty("show_sql", "true"); // Show SQL as it happens in the console

        // Add properties to a configuration object, and let hibernate know which classes we are mapping to the DB
        Configuration cfg = new Configuration().addProperties(properties);
        cfg.addAnnotatedClass(ContactDBO.class);

        // Connect to database
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();

        // Start a transaction to insert data
        session.beginTransaction();

        // Create new contact to insert
        ContactDBO johnContact = new ContactDBO();
        johnContact.name = "John";

        ContactDBO taliaContact = new ContactDBO();
        taliaContact.name = "Talia";

        ContactDBO montyContact = new ContactDBO();
        montyContact.name = "Monty";

        // Save changes
        session.save(johnContact);
        session.save(taliaContact);
        session.save(montyContact);

        // Commit all saved changes to the database at once
        session.getTransaction().commit();

        // Fetch all ContactDBO objects stored in the database
        List<ContactDBO> contacts = session.createQuery("SELECT a FROM ContactDBO a", ContactDBO.class).getResultList();

        // Iterate over all Contacts and print out their names
        for (ContactDBO contact : contacts) {
            System.out.println("Contact found " + contact.id + ": " + contact.name);
        }

        // Close session to the database cleanly
        session.close();
    }
}
