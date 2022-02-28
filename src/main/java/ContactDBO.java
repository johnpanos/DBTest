import javax.persistence.*;
import java.io.Serializable;

/**
 * Create a class that maps to the database
 */
@Entity
@Table(name="Contact")
public class ContactDBO implements Serializable {
    // Assign an auto-incrementing ID to the class, the database should handle it
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int id;

    @Column(name="Name")
    public String name;
}
