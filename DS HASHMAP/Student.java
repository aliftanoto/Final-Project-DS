import java.util.ArrayList;

public class Student {
    private String name;
    private int registrationYear;
    private ArrayList<String> enrolledCourses;
    private int balance;

    public Student(String name, int registrationYear) {
        this.name = name;
        this.registrationYear = registrationYear;
        enrolledCourses = new ArrayList<>();
        balance = 0; // initialize balance to 0
    }

    public String getName() {
        return name;
    }

    public int getRegistrationYear() {
        return registrationYear;
    }

    public ArrayList<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public int getBalance() {
        return balance;
    }

    public void enrollCourse(String course) {
        enrolledCourses.add(course);
        balance += 500; //increment balance when enrolling courses
        System.out.println(name + " has enrolled in course: " + course + " ," + "balance:"  + balance);
    }

    public void dropCourse(String course) {
        if (enrolledCourses.contains(course)) {
            enrolledCourses.remove(course);
            balance -= 500; //decrease balance when dropping course
            System.out.println(name + " has dropped course: " + course);
        } else {
            System.out.println(name + " is not enrolled in course: " + course);
        }
    }

    public void payTuition(int amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println(name + "" +  "has paid $" + amount + "tuition fee. Remaining balance: $" + balance);
        } else {
            System.out.println("Insufficient balance");
        }
    }
}
