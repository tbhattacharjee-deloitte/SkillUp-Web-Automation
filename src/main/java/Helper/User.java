package Helper;

public class User {
    public String name, email, username, designation, bio, role, password, age, workex;;
    public String[] skills;

    public User(String name, String email, String username, String designation, String bio, int workex, int age, String role, String password, String[] skills) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.designation = designation;
        this.bio = bio;
        this.role = role;
        this.password = password;
        this.age = Integer.toString(age);
        this.workex = Integer.toString(workex);
        this.skills = skills;
    }

}
