package com.hyfacademy.service;

import com.hyfacademy.model.Student;
import com.hyfacademy.util.GradeUtils;

import java.util.Scanner;

public class GradeService {
	private static final int MAX_STUDENTS = 20;

	private final Student[] students;
	private int studentCount;
	private final Scanner scanner;

	public GradeService() {
		this.students = new Student[MAX_STUDENTS];
		this.scanner = new Scanner(System.in);
	}

	public void addStudent() {
		if (studentCount >= MAX_STUDENTS) {
			System.out.println("Student limit reached.");
			return;
		}

		System.out.print("Enter student name: ");
		String name = scanner.nextLine().trim();
		students[studentCount] = new Student(name, String.format("HYF-%03d", studentCount + 1));
		studentCount++;
		System.out.println("Added.");
	}

	public void enterGrades() {
		System.out.print("Enter student ID: ");
		Student student = findStudentById(scanner.nextLine().trim());
		if (student == null) {
			System.out.println("Student not found.");
			return;
		}

		for (int i = 0; i < GradeUtils.MODULE_COUNT; i++) {
			System.out.print("Enter grade for " + GradeUtils.MODULE_NAMES[i] + ": ");
			student.setGrade(i, readInt());
		}
	}

	public void viewAllStudents() {
		if (studentCount == 0) {
			System.out.println("No students yet.");
			return;
		}

		String border = "══════════════════════════════════════════════════════════════";

		System.out.println();
		System.out.println(border);
		System.out.printf("  %-10s %-20s %-9s %-7s %-6s%n", "ID", "NAME", "AVERAGE", "GRADE", "STATUS");
		System.out.println(border);
		
		int passing = 0;
		for (int i = 0; i < studentCount; i++) {
			Student student = students[i];
			double average = GradeUtils.calculateAverage(student.getGrades());
			if (GradeUtils.isPassing(average)) {
				passing++;
			}
			System.out.printf("  %-10s %-20s %-9.2f %-7s %-6s%n", 
				student.getStudentId(), 
				student.getName(), 
				average,
				GradeUtils.getLetterGrade(average),
				GradeUtils.isPassing(average) ? "PASS" : "FAIL");
		}
		
		int failing = studentCount - passing;
		System.out.println(border);
		System.out.println("  Total students: " + studentCount + "   Passing: " + passing + "   Failing: " + failing);
		System.out.println(border);
	}

	public void viewStudentReport() {
		System.out.print("Enter student ID: ");
		Student student = findStudentById(scanner.nextLine().trim());
		if (student == null) {
			System.out.println("Student not found.");
			return;
		}

		double average = GradeUtils.calculateAverage(student.getGrades());
		String separator = "══════════════════════════════════════";
		String dashedLine = "──────────────────────────────────────";

		System.out.println();
		System.out.println(separator);
		System.out.println("  STUDENT REPORT");
		System.out.println(separator);
		System.out.println("  ID      : " + student.getStudentId());
		System.out.println("  Name    : " + student.getName());
		System.out.println(dashedLine);
		System.out.println("  MODULE GRADES");
		System.out.println(dashedLine);
		
		int[] grades = student.getGrades();
		for (int i = 0; i < GradeUtils.MODULE_COUNT; i++) {
			int grade = grades[i];
			String moduleName = GradeUtils.MODULE_NAMES[i];
			String status = GradeUtils.isModulePassing(grade) ? "PASS" : "FAIL";
			System.out.printf("  %-25s : %3d   %s%n", moduleName, grade, status);
		}
		
		System.out.println(dashedLine);
		System.out.printf("  Average  :  %.2f%n", average);
		System.out.println("  Grade    :  " + GradeUtils.getLetterGrade(average));
		String statusSymbol = GradeUtils.isPassing(average) ? "✓" : "✗";
		System.out.println("  Status   :  " + statusSymbol + " " + (GradeUtils.isPassing(average) ? "PASS" : "FAIL"));
		System.out.println(separator);
	}

	public void run() {
		while (true) {
			System.out.println();
			System.out.println("╔══════════════════════════════════════╗");
			System.out.println("║         HYF ACADEMY — GRADE MGR      ║");
			System.out.println("╚══════════════════════════════════════╝");
			System.out.println("  1. Add student");
			System.out.println("  2. Enter grades");
			System.out.println("  3. View all students");
			System.out.println("  4. View student report");
			System.out.println("  5. Exit");
			System.out.println("══════════════════════════════════════");
			System.out.print("Choose an option: ");


			switch (readInt()) {
				case 1 -> addStudent();
				case 2 -> enterGrades();
				case 3 -> viewAllStudents();
				case 4 -> viewStudentReport();
				case 5 -> { return; }
				default -> System.out.println("Invalid option.");
			}
		}
	}

	private Student findStudentById(String id) {
		for (int i = 0; i < studentCount; i++) {
			if (students[i].getStudentId().equalsIgnoreCase(id)) {
				return students[i];
			}
		}
		return null;
	}

	private int readInt() {
		while (!scanner.hasNextInt()) {
			scanner.nextLine();
			System.out.print("Enter a valid number: ");
		}

		int value = scanner.nextInt();
		scanner.nextLine();
		return value;
	}
}
