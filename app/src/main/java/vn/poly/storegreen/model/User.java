package vn.poly.storegreen.model;

public class User {
    public String account;
    public String passWord;
    public String email;

    public User() {
    }

    public User(String account, String passWord) {
        this.account = account;
        this.passWord = passWord;
    }

    public User(String account, String passWord, String email) {
        this.account = account;
        this.passWord = passWord;
        email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        email = email;
    }
}
