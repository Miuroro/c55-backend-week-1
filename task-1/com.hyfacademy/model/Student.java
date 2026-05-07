package com.hyfacademy.model;

import com.hyfacademy.util.GradeUtils;

public class Student {
	private String name;
	private String studentId;
	private int[] grades;
	private static int totalStudents = 0;

	public Student(String name, String studentId) {
		this.name = name;
		this.studentId = studentId;
		this.grades = new int[5];
		totalStudents++;
	}

// Getters
	public String getName() {
		return name;
	}
	public String getStudentId() {
		return studentId;
	}
	public int[] getGrades() {
		return grades;
	}
	public static int getTotalStudents() {
		return totalStudents;
	}

// Setter
	public void setGrade(int moduleIndex, int grade) {
		if (moduleIndex < 0 || moduleIndex > 4) {
			System.out.println("Error: moduleIndex must be between 0 and 4.");
			return;
		}
		if (grade < 0 || grade > 100) {
			System.out.println("Error: grade must be between 0 and 100.");
			return;
		}
		grades[moduleIndex] = grade;
	}

// %s for strings and %.2f for decimal numbers with 2 decimal numbers with 2 digits after the point, like 72.40
	public String toString() {
		double average = GradeUtils.calculateAverage(grades);
		return String.format("[%s] %s — Avg: %.2f — %s", studentId, name, average, GradeUtils.isPassing(average) ? "PASS" : "FAIL");
	}
}
