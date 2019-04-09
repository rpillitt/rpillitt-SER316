package main.java;

/**
 * class for managing course statistics
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;



public class Course {

    // maps student names (asurite) to their points
    public HashMap<String, Integer> points = new HashMap<>(); 
    private String name; // course name
    ArrayList<Student> students  = new ArrayList<Student>();


    public Course(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**Prints the values of students and their average within the course.
     * 
     */
    public void printCourseStats() {
        //SER316 TASK 2 SPOTBUGS FIX
        //ArrayList<Integer> values = new ArrayList<Integer>(points.values());

        System.out.print("Average Grades without max and without min: ");
        System.out.println(this.calculateAverageWithoutMinWithoutMax());
    }

    /**Calculates averages with no min or max values provided
     * 
     * @return
     * @throws NullPointerException thrown when no values in points.
     */
    public double calculateAverageWithoutMinWithoutMax() throws NullPointerException {
        ArrayList<Integer> collection = new ArrayList<Integer>(points.values());

        int counter = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        //SER316 Start
        for (int i = collection.size(); i > 0; i--) {
            if (collection.get(i - 1) < 0) {
                collection.remove(i - 1);
            }
        }
        //SER316 End
        //Above section added to truly throw out negative points and thus not count them

        if (collection.size() == 1) {
            return collection.get(0);
        } else if (collection.size() == 2) {
            return (double)(collection.get(0) + collection.get(1)) / 2;
        } else {
            int allPoints = 0;
            for (int point: collection) {
                counter++;
                if (point < min) {
                    min = point;
                }
                if (point > max) {
                    max = point;
                }
                allPoints = allPoints + point;
            }  
            int totalPoints = allPoints - max - min;
            return totalPoints / (double) (counter - 2);
            //Above line had (counter-1), which does not work when removing two values
        }
    }

    // REACH at least 95% Code coverage  (assign 3)
    // if student with the name (asurite member) is not yet included 
    // student needs to be added to student list 
    // sets points for a student 
    
    /**Sets points for a given student in points HashMap.
     * 
     * @param name Name of student to change points
     * @param points Points value to be assigned to the student
     */
    public void set_points(String name, int points) {
        //System.out.println(points);
        //SER316 Start
        ArrayList<String> asurites = new ArrayList<>();
        for (int i = 0; i < this.getStudents().size(); i++) {
            asurites.add(this.getStudents().get(i).getAsurite());
        }

        if (!asurites.contains(name)) {
            this.addStudent(new Student(name, null));
            this.points.put(name,  points);
        } else {
            this.points.put(name, points);
        }
        //SER316 End
    }


    // REACH at least 95% Code coverage  (assign 3)
    // Students should only be added when they are not yet in the course 
    // (names (asurite member) needs to be unique)
    
    /**Adds a student to the collection of students in a course.
     * 
     * @param s The student object to be added to the course
     * @return Boolean value representing successful add
     */
    public boolean addStudent(Student s) {
        //SER316 Start
        boolean added = false;
        if (!students.contains(s) && s != null) {
            students.add(s);
            points.put(s.getAsurite(), -1);
            added = true;
        }
        return added;
        //SER316 End
        //Needed changing because there was no check for duplication prior to edit
    }


    public HashMap<String, Integer> getPoints() {
        return points;
    }

    public int getStudent_Points(Student student) {
        return points.get(student.getAsurite());
    }


    public ArrayList<Student> getStudents() {
        return students;
    }

    public HashMap<String, Integer> countOccurencesLetterGrades() throws NullPointerException {
        return null;
    }
}