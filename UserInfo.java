import java.util.Scanner;

public class UserInfo {
    private String username;
    private String userID;

    public UserInfo(String name) {
        this.username = name;
        this.userID = generateUserID(name);
    }

    public String getUsername() {
        return username;
    }

    public String getUserID() {
        return userID;
    }

    private String generateUserID(String name) {
        if (name == null) {
            return "guest";
        }

        String[] nameParts = name.trim().split("\\s+");
        if (nameParts.length < 2) {
            return "guest";
        }

        char firstInitial = Character.toUpperCase(nameParts[0].charAt(0));
        String surname = nameParts[nameParts.length - 1];
        return firstInitial + surname;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first name and surname: ");
        String name = input.nextLine();
        UserInfo user = new UserInfo(name);
        System.out.println("User ID: " + user.getUserID());
        input.close();
    }
}
