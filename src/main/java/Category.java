import java.util.List;
import java.util.Objects;

public class Category {
    private Integer category_id;
    private List<Topic> topics;
    private User user;
    private String subject;

    public Category(Integer category_id, User user, String subject) {
        this(user, subject);
        this.category_id = category_id;
    }

    public Category(User user, String subject) {
        this.user = user;
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return "Category{" +
                "#" + category_id +
                ", #" + user +
                ", subject='" + subject + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(subject, category.subject);
    }

    @Override
    public int hashCode() {

        return Objects.hash(category_id, topics, user, subject);
    }
}
