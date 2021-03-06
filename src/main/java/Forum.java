import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Forum implements ForumCapable {

    private static final Logger LOGGER = Logger.getLogger(Forum.class.getName());
    private HibernateHandler db;
    //    private MySQLHandler mySQLHandler;
    private User userLoggedIn = null;
    private Category currentCategory = null;

    public Forum() {
//        mySQLHandler = new MySQLHandler(dbUrl, userDb, passDb);
        db = new HibernateHandler();
    }

    public boolean login(String username, String password) {
        boolean loginSuccesful = false;
//        User user = mySQLHandler.getUserByUsername(username);
        User user = db.getUserByUsername(username);
        if (user != null && user.isThisPasswordMine(password)) {
            LOGGER.log(Level.INFO,username + " logged in succesfully");
            loginSuccesful = true;
            userLoggedIn = user;
        } else {
            LOGGER.log(Level.WARNING,"invalid login ! ! !");
        }
        return loginSuccesful;
    }

    public void logout() {
        userLoggedIn = null;
    }

    public void exitCategory() {
        currentCategory = null;
    }

    public void register(String username, String password, String mail) {
//        mySQLHandler.insertUser(username, password, mail);
        User user = new User(username,password,mail);
        try {
            db.insert(user);
        } catch (Exception e){
            System.out.println("Va rog sa utilizati un alt loghin, LOGHIN EXISTENT");
        }
    }

    public void insertCategory(String subject) {
//        LOGGER.log(Level.INFO,"Insert subject for the new category = ");
//        mySQLHandler.insertNewCategory(subject, userLoggedIn.getId());
    }

    public List<Category> getAllCategories() {
        return (List<Category>) db;  /*mySQLHandler.getAllCategories();*/
    }

    public boolean enterCategory(Integer categoryId) {

        boolean enterSuccesfully = false;
//        Category category = mySQLHandler.getCategoryById(categoryId);
//
//        if (category == null) {
//            LOGGER.log(Level.SEVERE,"Invalid category ! ! !");
//        }
//        else
//        {
//            currentCategory = category;
//            enterSuccesfully = true;
//        }
        return enterSuccesfully;
    }

    public String whoIsLoggedIn() {
        return userLoggedIn != null ? userLoggedIn.getUsername() : "";
    }

    public String whichCategory() {
        return currentCategory != null ? currentCategory.getSubject() : "";
    }

    public void cleanupEntireDB() {
//        mySQLHandler.cleanup();
    }

    public HibernateHandler getDb() {
        return db;
    }

    public List<User> getAllUsersSorted()
    {
        List<Object> userListAsObj = db.getAll(User.class);
        List<User> userList = new ArrayList<>();
        for(Object obj:userListAsObj)
        {
            userList.add((User)obj);
        }

        //sorteaza userii in ordinea data de
        //metoda compareTo din clasa User
      //  Collections.sort(userList);

        Collections.sort(userList, new Comparator<User>(){
        @Override
        public int compare(User o1, User o2) {
            return o2.getUsername().compareTo(
                    o1.getUsername());
        }
    });

        Collections.sort(userList, new Comparator<User>(){
            @Override
            public int compare(User o1, User o2) {
                return o1.getUsername().length()
                        - o2.getUsername().length();
            }
        });
        return userList;
    }
}
