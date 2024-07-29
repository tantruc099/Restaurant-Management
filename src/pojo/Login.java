/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author nguye
 */
public class Login {

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Login(int Id, String Username, String Password, int role) {
        this.Id = Id;
        this.Username = Username;
        this.Password = Password;
        this.role = role;
    }

    public Login() {
    }


    private int Id;
    private String Username;
    private String Password ;
    private int role;
}
