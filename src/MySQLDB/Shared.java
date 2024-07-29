/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MySQLDB;

import java.util.prefs.Preferences;


/**
 *
 * @author nguye
 */
public class Shared {

    private static final String USER_KEY = "user";
    private static final String PASS_KEY = "pass";
    private static final String Role_KEY="role";
    String Username = getUser();
    String Password = getPassword();
    String Role=getRole();
    

    public static void saveCredentials(String user, String pass, String role){
        Preferences prefs = Preferences.userNodeForPackage(Shared.class);
        prefs.put(USER_KEY, user);
        prefs.put(PASS_KEY, pass);
        prefs.put(Role_KEY,role);
    }

    public static String getUser() {
        Preferences prefs = Preferences.userNodeForPackage(Shared.class);
        return prefs.get(USER_KEY, null);
    }

    public static String getPassword() {
        Preferences prefs = Preferences.userNodeForPackage(Shared.class);
        return prefs.get(PASS_KEY, null);
    }

    public static void clearCredentials() {
        Preferences prefs = Preferences.userNodeForPackage(Shared.class);
        prefs.remove(USER_KEY);
        prefs.remove(PASS_KEY);
        prefs.remove(Role_KEY);
    }

    public static String getRole() {
         Preferences prefs = Preferences.userNodeForPackage(Shared.class);
        return prefs.get(Role_KEY, null);
    }
    
}
