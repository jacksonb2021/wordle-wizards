package model;

public class WordleAccount {
    private final String username;
    private final String password;

    public WordleAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }


}
