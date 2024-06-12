import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystem {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int studentIdCounter = 1; // Counter to generate unique student IDs

    // List of available courses
    private static String[] availableCourses = {"Math", "Science", "History", "English"};

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Enroll Course");
            System.out.println("3. Drop Course");
            System.out.println("4. Pay tuition fee");
            System.out.println("5. Search Student Status by ID");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();

                if (choice == 1) {
                    addStudent();
                } else if (students.isEmpty()) {
                    System.out.println("There is no student. Try to add the student first.");
                } else if (choice == 2) {
                    enrollCourse();
                } else if (choice == 3) {
                    dropCourse();
                } else if (choice == 4) {
                    payTuition();
                } else if (choice == 5) {
                    searchStudentStatus();
                } else if (choice == 6) {
                    System.out.println("Exiting...");
                    System.exit(0);
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // clear the invalid input
            }
        }
    }

    private static void addStudent() {
        System.out.print("How many students do you want to add? ");
        if (scanner.hasNextInt()) {
            int numOfStudents = scanner.nextInt();
            scanner.nextLine(); // consume newline

            for (int i = 0; i < numOfStudents; i++) {
                System.out.println("\nStudent " + (i + 1));
                System.out.print("Enter student name: ");
                String name = scanner.nextLine();
                if (!name.matches("^[a-zA-Z\\s']+$")) {
                    System.out.println("Invalid name. Only alphabetic characters are allowed. Student not added.");
                    continue;
                }
                System.out.print("Enter registration Year: ");
                if (scanner.hasNextInt()) {
                    int registrationYear = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    // Generate student ID with first two digits as the last two digits of registration year
                    int studentId = generateStudentId(registrationYear);

                    Student student = new Student(name, registrationYear, studentId);
                    students.add(student);
                    System.out.println("Student added successfully. Student ID: " + studentId);
                } else {
                    System.out.println("Invalid input for registration year. Student not added.");
                    scanner.next(); // clear the invalid input
                }
            }
        } else {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // clear the invalid input
        }
    }

    // Method to generate student ID with first two digits as the last two digits of registration year
    private static int generateStudentId(int registrationYear) {
        String yearStr = String.valueOf(registrationYear);
        String idStr = yearStr.substring(2) + String.format("%03d", studentIdCounter++); 
        return Integer.parseInt(idStr);
    }
    //display available courses, checks if the entered course is valid, and enrolls the student in the selected course
    private static void enrollCourse() {
        System.out.print("Enter student ID: ");
        if (scanner.hasNextInt()) {
            int studentId = scanner.nextInt();
            Student student = findStudentById(studentId);
            if (student != null) {
                boolean done = false;
                while (!done) {
                    displayAvailableCourses();
                    System.out.print("Enter course to enroll (or 'done' to finish): ");
                    String course = scanner.next();
                    if (course.equalsIgnoreCase("done")) {
                        done = true;
                    } else if (isValidCourse(course)) {
                        student.enrollCourse(course);
                    } else {
                        System.out.println("Invalid course. Please enter a valid course from the list.");
                    }
                }
            } else {
                System.out.println("Student not found.");
            }
        } else {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // clear the invalid input
        }
    }
    //by inputing the student ID to drop the course
    private static void dropCourse() {
        System.out.print("Enter student ID: ");
        if (scanner.hasNextInt()) {
            int studentId = scanner.nextInt();
            Student student = findStudentById(studentId);
            if (student != null) {
                System.out.print("Enter course to drop: ");
                String course = scanner.next();
                student.dropCourse(course);
            } else {
                System.out.println("Student not found.");
            }
        } else {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // clear the invalid input
        }
    }
    //displayin the students student's balance, prompts for the payment amount, and processes the payment.
    private static void payTuition() {
        System.out.print("Enter student ID: ");
        if (scanner.hasNextInt()) {
            int studentId = scanner.nextInt();
            Student student = findStudentById(studentId);
            if (student != null) {
                System.out.println("Student " + student.getName() + "'s balance: $" + student.getBalance());
                System.out.print("Enter amount to pay: $");
                if (scanner.hasNextInt()) {
                    int amount = scanner.nextInt();
                    student.payTuition(amount);
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // clear the invalid input
                }
            } else {
                System.out.println("Student not found. Enter the correct ID.");
            }
        } else {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // clear the invalid input
        }
    }
    //displayin yhe student name, registration year, courses, and student balance by inputing the student ID.
    private static void searchStudentStatus() {
        System.out.print("Enter student ID: ");
        if (scanner.hasNextInt()) {
            int studentId = scanner.nextInt();
            Student student = findStudentById(studentId);
            if (student != null) {
                System.out.println("Student Name: " + student.getName());
                System.out.println("Registration Year: " + student.getRegistrationYear());
                System.out.println("Enrolled Courses: " + String.join(", ", student.getEnrolledCourses()));
                System.out.println("Student balance: $" + student.getBalance());
            } else {
                System.out.println("Student not found.");
            }
        } else {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // clear the invalid input
        }
    }
    //search for a student by the ID
    private static Student findStudentById(int studentId) {
        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    // Method to display available courses
    private static void displayAvailableCourses() {
        System.out.println("Available Courses:");
        for (String course : availableCourses) {
            System.out.println(course);
        }
    }

    // Method to check if the input course is valid
    private static boolean isValidCourse(String course) {
        for (String availableCourse : availableCourses) {
            if (availableCourse.equalsIgnoreCase(course)) {
                return true;
            }
        }
        return false;
    }
}

