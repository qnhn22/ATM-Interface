package mainpackage;

import java.util.ArrayList;
import java.util.Random;

public class Bank {

    private String name;

    private ArrayList<User> users;

    private ArrayList<Account> accounts;

    public Bank(String name) {
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    /**
     * Generate a new universally unique ID for a user
     * @return the uuid
     */
    public String getNewUserUUID() {
        // inits
        String uuid;
        Random rnd = new Random();
        int len = 6;
        boolean nonUnique;

        do {
            uuid = "";
            for (int i = 0; i < len; i++) {
                uuid += ((Integer)rnd.nextInt(10)).toString();
            }

            nonUnique = false;
            for (User u: this.users) {
                if (uuid.compareTo(u.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);

        return uuid;
    }

    public String getNewAccountUUID() {
            // inits
            String uuid;
            Random rnd = new Random();
            int len = 10;
            boolean nonUnique;

            do {
                uuid = "";
                for (int i = 0; i < len; i++) {
                    uuid += ((Integer)rnd.nextInt(10)).toString();
                }

                nonUnique = false;
                for (Account a: this.accounts) {
                    if (uuid.compareTo(a.getUUID()) == 0){
                        nonUnique = true;
                        break;
                    }
                }
            } while (nonUnique);

            return uuid;
    }

    /**
     *  Add an account
     * @param anAcct the account to add
     */
    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    public User addUser(String firstName, String lastString, String pin) {
        User newUser = new User(firstName, lastString, pin, this);
        this.users.add(newUser);

        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);

        return newUser;
    }

    public User userLogin(String userID, String pin) {
        for (User u: this.users) {
            if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
                return u;
            }
        }
        return null;
    }

    public String getName() {
        return this.name;
    }
}