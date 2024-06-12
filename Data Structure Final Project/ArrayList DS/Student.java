import java.util.ArrayList;


public class Student {
    
    private String name;
    private int registrationYear;
    private int studentId;
    private ArrayList<String> enrolledCourses = new ArrayList<>();
    private int balance;

    public Student(String name, int registrationYear, int studentId) {
        this.name = name;
        this.registrationYear = registrationYear;
        this.studentId = studentId;
        this.balance = 0; // Initial balance is 0
    }

    public String getName() {
        return name;
    }

    public int getRegistrationYear() {
        return registrationYear;
    }

    public int getStudentId() {
        return studentId;
    }

    public ArrayList<String> getEnrolledCourses() {
        return new ArrayList<>(enrolledCourses); // Return a copy to avoid external modification
    }

    public int getBalance() {
        return balance;
    }

    public void enrollCourse(String course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
            balance += 500; // Increment balance by $500 for each enrolled course
            System.out.println("Course " + course + " enrolled successfully. New balance: $" + balance);
        } else {
            System.out.println("Already enrolled in " + course + ".");
        }
    }

    public void dropCourse(String course) {
        if (enrolledCourses.contains(course)) {
            enrolledCourses.remove(course);
            System.out.println("Course " + course + " dropped successfully.");
        } else {
            System.out.println("Not enrolled in " + course + ".");
        }
    }

    public void payTuition(int amount) {
        if (amount > 0) {
            balance -= amount;
            System.out.println("Paid $" + amount + " successfully. New balance: $" + balance);
        } else {
            System.out.println("Invalid amount. Payment failed.");
        }
    }
}