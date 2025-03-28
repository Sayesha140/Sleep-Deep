import java.io.*;

public class UserCSVFileManager implements UserFileManager{
    private static final String USER_FILE_PATH="User_data.csv";
    @Override
    public void saveUserData(User user) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE_PATH))) {
            writer.write(user.toString());
        } catch (IOException e) {
            System.out.println("Error saving user data.");
        }

    }

    @Override
    public User loadUserData() {

        File file=new File(USER_FILE_PATH);

        if (!file.exists()){
            return null;
        }


        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE_PATH))) {
            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");
                double age = Double.parseDouble(data[0]);
                Gender gender = Gender.valueOf(data[1].toUpperCase());
                ActivityLevel activityLevel = ActivityLevel.valueOf(data[2].toUpperCase());
                return new User(age,gender,activityLevel);

            }
        } catch (IOException e) {
            System.out.println("Error loading user data.");
        }

        return null;

    }

    @Override
    public boolean userExists() {
        File profileFile = new File(USER_FILE_PATH);
        return profileFile.exists() && profileFile.length() > 0;
    }
}
