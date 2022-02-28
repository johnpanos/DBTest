import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class Database {
    // Classes which will be used for the database
    private static final Class<?>[] databaseClasses = { ContactDBO.class };

    // Session that will be used for the database
    private final Session session;

    public Database() {
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
        for (Class<?> classToAdd : Database.databaseClasses) {
            cfg.addAnnotatedClass(classToAdd);
        }

        SessionFactory sessionFactory = cfg.buildSessionFactory();

        this.session = sessionFactory.openSession();
    }

    public Session getSession() {
        return this.session;
    }

    public void disconnect() {
        this.session.close();
    }
}
