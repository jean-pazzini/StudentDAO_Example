package br.dev.joaquim.StudentApp.ihm;

import java.util.Scanner;

import br.dev.joaquim.StudentApp.dao.CourseDAO;
import br.dev.joaquim.StudentApp.entities.Course;

public class CourseIHM {
    
    private CourseDAO courseDAO;

    public CourseIHM(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            System.out.println("=== Course Management Menu ===");
            System.out.println("1. Add Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Update Course");
            System.out.println("4. Delete Course");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addCourse(scanner);
                    break;
                case 2:
                    viewAllCourses();
                    break;
                case 3:
                    updateCourse(scanner);
                    break;
                case 4:
                    deleteCourse(scanner);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }

    private void addCourse(Scanner scanner) {
        System.out.print("Enter course name: ");
        String name = scanner.nextLine();

        Course course = new Course(name);
        course.setName(name);

        courseDAO.create(course);
        System.out.println("Course added successfully.");
    }

    private void viewAllCourses() {
        System.out.println("=== List of Courses ===");
        for (Course course : courseDAO.findAll()) {
            System.out.println(course);
        }
    }

    private void updateCourse(Scanner scanner) {
        System.out.print("Enter course ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Course course = courseDAO.findById(id);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        course.setName(name);

        courseDAO.update(course);
        System.out.println("Course updated successfully.");
    }

    private void deleteCourse(Scanner scanner) {
        System.out.print("Enter course ID to delete: ");
        int id = scanner.nextInt();
        courseDAO.delete(id);
        System.out.println("Course deleted successfully.");
    }
}
