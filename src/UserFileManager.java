public interface UserFileManager {

    void saveUserData(User user);
    User loadUserData();
    boolean userExists();
}