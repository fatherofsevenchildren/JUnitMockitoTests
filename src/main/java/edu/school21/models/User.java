package edu.school21.models;

public class User {

    Long Identifier;
    String Login;
    String Password;
    Boolean AuthenticationSuccessStatus = false;


    public User(Long identifier, String login, String password) {
        Login = login;
        Identifier = identifier;
        Password = password;
    }


    public Long getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(Long identifier) {
        Identifier = identifier;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Boolean getAuthenticationSuccessStatus() {
        return AuthenticationSuccessStatus;
    }

    public void setAuthenticationSuccessStatus() {
        AuthenticationSuccessStatus = true;
    }
}
