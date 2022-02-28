public class DBTest {
    public static void main(String[] args) {
        // Create database and dependency inject it into the repositories
        Database db = new Database();
        ContactRepository contactRepository = new ContactRepository(db.getSession());

        // Create like normal
        ContactDBO johnContact = new ContactDBO();
        johnContact.name = "John";

        ContactDBO taliaContact = new ContactDBO();
        taliaContact.name = "Talia";

        ContactDBO montyContact = new ContactDBO();
        montyContact.name = "Monty";

        // Insert all
        contactRepository.insertAll(johnContact, taliaContact, montyContact);

        // Iterate over all Contacts and print out their names
        for (ContactDBO contact : contactRepository.getAll()) {
            System.out.println("Contact found " + contact.id + ": " + contact.name);
        }

        // Close session to the database cleanly
        db.disconnect();
    }
}
