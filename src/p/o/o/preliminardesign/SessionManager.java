
package p.o.o.preliminardesign;


public class SessionManager {
   private static GlobalUser currentUser = null;

    public static void login(GlobalUser user) {
        currentUser = user;
    }

    public static GlobalUser getCurrentUser() {
        return currentUser;
    }

    public static void logout() {
        currentUser = null;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }
}
