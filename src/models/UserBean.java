package models;

import java.util.UUID;

/**
 * Created by user01 on 17.05.2017.
 */
public class UserBean {
    private UUID id;
    private String login;
    private String password;
    private String name;
    private String lastName;

    public UserBean(UUID id, String login, String password, String name, String lastName) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
    }
    public UserBean(String login, String password, String name, String lastName) {
        id=UUID.randomUUID();
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
    }
    public UserBean() {
        id=UUID.randomUUID();
    }
    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
