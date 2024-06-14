// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;

// public class CourseDropTest {
//     private static Scanner scanner = new Scanner(System.in);

//     public static void main(String[] args) {
//         Student student = new Student(12345);
//         for (int i = 0; i < 1000; i++) {
//             student.addCourse("Course" + i);
//         }

//         long startTime = System.nanoTime();
        
//         for (int i = 0; i < 100; i++) {
//             dropCourse(student, "Course" + i);
//         }

//         long endTime = System.nanoTime();
//         long duration = endTime - startTime; // duration in nanoseconds

//         System.out.println("Time taken to drop 1000 courses: " + duration + " ns");
//     }

//     private static void dropCourse(Student student, String course) {
//         student.dropCourse(course);
//     }
// }

// class Student {
//     private int id;
//     private List<String> courses;

//     public Student(int id) {
//         this.id = id;
//         this.courses = new ArrayList<>();
//     }

//     public void addCourse(String course) {
//         courses.add(course);
//     }

//     public void dropCourse(String course) {
//         courses.remove(course);
//     }

//     public int getId() {
//         return id;
//     }
// }

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CourseDropTest {
    private static HashMap<Integer, Student> students = new HashMap<>();
    private static java.util.Scanner scanner = new java.util.Scanner(System.in);

    public static void main(String[] args) {
        // Create a student and enroll in 100 courses
        Student student = new Student(1, "John Doe");
        for (int i = 1; i <= 1000; i++) {
            student.enrollCourse("course" + i);
        }
        students.put(1, student);

        // Measure time to drop 100 courses
        long startTime = System.nanoTime();
        for (int i = 1; i <= 1000; i++) {
            dropCourse(student.getId(), "course" + i);
        }
        long endTime = System.nanoTime();

        long duration = endTime - startTime;
        System.out.println("Time taken to drop 1000 courses: " + duration + " nanoseconds");
    }

    private static void dropCourse(int studentId, String course) {
        if (students.containsKey(studentId)) {
            Student student = students.get(studentId);
            // Convert course name to lowercase for case-insensitive comparison
            course = course.toLowerCase();
            if (student.getEnrolledCourses().contains(course)) {
                student.dropCourse(course);
            } else {
                System.out.println(student.getName() + " is not enrolled in course: " + course);
            }
        } else {
            System.out.println("Student not found.");
        }
    }
}

class Student {
    private int id;
    private String name;
    private Set<String> enrolledCourses;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.enrolledCourses = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollCourse(String course) {
        enrolledCourses.add(course.toLowerCase());
    }

    public void dropCourse(String course) {
        enrolledCourses.remove(course.toLowerCase());
    }
}
