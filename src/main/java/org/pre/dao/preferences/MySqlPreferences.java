package org.pre.dao.preferences;

import java.util.prefs.Preferences;

public class MySqlPreferences {
    
    private static Preferences prefs;
    // jdbc:mysql://localhost:3306/
    private static String ID1 = "dbURL";
    private static String ID2 = "dbUser";
    private static String ID3 = "dbPassword";


    public String getdbURL() {
        prefs = Preferences.userNodeForPackage( this.getClass() );
        return prefs.get(ID1, "dbURL");
    }
    
    public String getdbUser() {
        prefs = Preferences.userNodeForPackage( this.getClass() );
        return prefs.get(ID2, "dbUser");
    }

    public String getdbPassword() {
        prefs = Preferences.userNodeForPackage( this.getClass() );
        return prefs.get(ID3, "dbPassword");
    }

    public static void setdbURL(String text) {
        prefs.put(ID1, text);
    }

    public static void setdbUser(String text) {
        prefs.put(ID2, text);
    }

    public static void setdbPassword(String text) {
        prefs.put(ID3, text);
    }
}
