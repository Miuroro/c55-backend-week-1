package com.hyfacademy.util;

public final class GradeUtils {
	public static final int MODULE_PASS_MARK = 55;
	public static final double TRACK_PASS_AVERAGE = 60.0;
	public static final int MODULE_COUNT = 5;
	public static final String[] MODULE_NAMES = {
		"Java Basics",
		"Control Flow",
		"OOP Fundamentals",
		"Arrays & Collections",
		"Input & Output"
	};

	private GradeUtils() {
	}

	public static double calculateAverage(int[] grades) {
		if (grades == null || grades.length == 0) {
			return 0.0;
		}
		int total = 0;
		for (int grade : grades) {
			total += grade;
		}
		return (double) total / grades.length;
	}

	public static boolean isPassing(double average) {
		return average >= TRACK_PASS_AVERAGE;
	}

	public static boolean isModulePassing(int grade) {
		return grade >= MODULE_PASS_MARK;
	}

	public static String getLetterGrade(double average) {
		if (average >= 90.0) {
			return "A";
		}
		if (average >= 80.0) {
			return "B";
		}
		if (average >= 70.0) {
			return "C";
		}
		if (average >= 60.0) {
			return "D";
		}
		return "F";
	}

// %3d to print integer with 3 characters width, right-aligned
	public static String formatGrade(int grade) {
		return String.format("%3d", grade);
	}
}
