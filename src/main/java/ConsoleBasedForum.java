import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class ConsoleBasedForum extends Forum {
    private Scanner sc;
    private String prompt;

    public ConsoleBasedForum() {
        super();
        sc = new Scanner(System.in);
        prompt = ">";
        mainMenu();
    }

    public void mainMenu() {
        while (true) {
            prompt = ">";
            switch (drawMainMenu()) {
                case 1:
                    System.out.println("Username:");
                    String username = sc.nextLine();
                    System.out.println("Password:");
                    String password = sc.nextLine();
                    if (super.login(username, password)) {
                        loginMenu();
                    }
                    break;
                case 2:
                    String readUserName = null;
                    String readPassword = null;
                    String readMail = null;

                    System.out.println("Insert username = ");
                    readUserName = sc.nextLine();

                    System.out.println("Insert password = ");
                    readPassword = sc.nextLine();

                    System.out.println("Insert mail = ");
                    readMail = sc.nextLine();
                    register(readUserName, readPassword, readMail);
                    break;
                case 3:
                    System.out.println("Have a nice day!");
//                    iesirea din program
                    getDb().exit();
                    return;
                case 4:
                    printAllUsersSorted();
                    break;
                default:
                    System.out.println("The option does not exist !!!");
                    break;
            }
        }
    }

    private void printAllUsersSorted()
    {
        System.out.println( getAllUsersSorted());
    }

    private void loginMenu() {
        while (true) {
            prompt = whoIsLoggedIn() + ">";
            switch (drawCategoryMenu()) {
                case 1:
                    System.out.println("Category subject:");
                    String subject = sc.nextLine();
                    insertCategory(subject);
                    break;
                case 2:
                    System.out.println(getAllCategories());
                    break;
                case 3:
                    System.out.println("Enter category = ");
                    Integer idCategory = sc.nextInt();
                    sc.nextLine();
                    if (enterCategory(idCategory)) {
                        categoryMenu();
                    }
                    break;
                case 4:
                    System.out.println("Have a nice day!");
                    return;
                default:
                    System.out.println("Optiunea nu exista");
                    break;
            }
        }
    }

    private void categoryMenu() {
        while (true) {
            prompt = whoIsLoggedIn() + ", " + whichCategory() + ">";
            switch (drawTopicMenu()) {
                case 1:
                    //  insertCategory();
                    break;
                case 2:
                    //  showAllCategories();
                    break;
                case 3:
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Optiunea nu exista");
                    break;
            }
        }
    }

    private Integer drawCategoryMenu() {
        System.out.println("1. Create category");
        System.out.println("2. Show all categories");
        System.out.println("3. Enter category");
        System.out.println("4. Return");
        System.out.print(prompt);
        Integer option = sc.nextInt();
        sc.nextLine();
        return option;
    }

    private Integer drawTopicMenu() {
        System.out.println("1. Create Topic");
        System.out.println("2. Show all topic");
        System.out.println("3. Enter Topic");
        System.out.println("4. Return");
        System.out.print(prompt);
        Integer option = sc.nextInt();
        sc.nextLine();
        return option;
    }

    public Integer drawMainMenu() {
        System.out.println("\n***********************************");
        System.out.println("Operations: ");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.println("4. Exit");
        System.out.print(prompt);
        Integer optiune = sc.nextInt();
        sc.nextLine();
        return optiune;

    }
}
