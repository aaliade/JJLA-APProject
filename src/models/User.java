package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.DiscriminatorType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Columns;

import factories.SessionFactoryBuilder;

/* Created to superclass to not be abstract so 
 * that we can create a user object to send to the 
 * database.
 */

@Entity(name = "user")
@Table(name = "user")
//each subclass has its own table, and there is a table for the superclass containing common fields.
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

	@Column(name = "address")
	private String address;

	@Column(name = "email")
	private String email;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "password")
	private String password;

	@Column(name = "phoneNum")
	private String phone;

	@Column(name = "userType")
	private String userType;

	@Id
	@Column(name = "username")
	private String username;

	private static final Logger logger = LogManager.getLogger(User.class);

	// Default Constructor
	public User() {
		this.username = "";
		this.password = "";
		this.firstName = "";
		this.lastName = "";
		this.phone = "";
		this.email = "";
		this.address = "";
		this.userType = "";
		logger.info("User initialized");
	}

	// Primary Constructor
	public User(String username, String password, String firstName, String lastName, String phone, String email,
			String address, String userType) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.userType = userType;
	}

	// Copy Constructor
	public User(User user) {
		this.username = user.username;
		this.password = user.password;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.phone = user.phone;
		this.email = user.email;
		this.address = user.address;
		this.userType = user.userType;
		logger.info("User copied");
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public static Logger getLogger() {
		return logger;
	}

    @SuppressWarnings("unchecked")
    public List<User> readAll() {
        List<User> userList = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;
        Logger logger = LogManager.getLogger(User.class);

        try {
            session = SessionFactoryBuilder.getUserSessionFactroy().getCurrentSession();
            transaction = session.beginTransaction();
            userList = (List<User>) session.createQuery("From User").getResultList();
            transaction.commit();
        } catch (Exception e) {
            // Log the exception
            logger.error("Error occurred while reading users", e);

            // Rollback the transaction if it is still active
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            // Rethrow the exception or handle it accordingly based on your requirements
            throw new RuntimeException("Error occurred while reading users", e);
        } finally {
            // Close the session in a finally block to ensure it is always closed
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return userList;
    }

	public abstract boolean login();

}
