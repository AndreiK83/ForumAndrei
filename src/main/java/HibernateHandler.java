import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateHandler {
    private static Configuration conf;
    private static SessionFactory f;
    private static Session s;

    public HibernateHandler() {
        conf = new Configuration().configure();
        f = conf.buildSessionFactory();
        s = f.openSession();
    }

    public void insert(Object obj) {
        s.beginTransaction();
        s.saveOrUpdate(obj);
        s.getTransaction().commit();
    }

    public List<Object> getAll(Class type) {
        return s.createCriteria(type).list();
    }

    public void exit(){
        s.close();
        f.close();
    }

    public User getUserByUsername(String username){
        return (User)s.getNamedQuery("get_user_by_username").setParameter("username", username).uniqueResult();
    }
}
