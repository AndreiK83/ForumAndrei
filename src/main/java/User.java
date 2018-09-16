import javax.persistence.*;
import java.util.Objects;
@NamedQueries({
        @NamedQuery(
                name = "get_user_by_username",
                query = "select p from User p where username = :username"
        )
})


@Entity
public class User implements Comparable<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 50, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, length = 50, unique = true)
    private String mail;

    public User(){

    }

    public User(Integer id, String username, String password, String mail) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
    }

    public User( String username, String password, String mail) {
        this.username = username;
        this.password = password;
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return
                Objects.equals(username, user.username) &&
                        Objects.equals(mail, user.mail);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return username +
                ", mail='" + mail + '\'';
    }

    public boolean isThisPasswordMine(String password) {
        if (this.password.equals(password)) {
            return true;
        }
        else{
            return false;
        }
    }

    public Integer getId() {
        return id;
    }

    @Override
    public int compareTo(User o) {
        int usernameCompResult = this.username.compareTo(o.username);
        return usernameCompResult;
    }
}
