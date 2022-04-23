package Helper;

public class User {
    public String name, email, username, designation, bio, role, password;
    public int age, workex;
    public String[] skills;

    public User(String name, String email, String username, String designation, String bio, String role, String password, int age, int workex, String[] skills) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.designation = designation;
        this.bio = bio;
        this.role = role;
        this.password = password;
        this.age = age;
        this.workex = workex;
        this.skills = skills;
    }

}
