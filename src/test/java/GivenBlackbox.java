package test.java;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import main.java.Course;
import main.java.CourseGrades1;
import main.java.CourseGrades2;
import main.java.CourseGrades5;
import main.java.CourseGrades4;
import main.java.CourseGrades0;
import main.java.CourseGrades3;

import java.lang.reflect.Constructor;

@RunWith(Parameterized.class)
public class GivenBlackbox {
    private Class<Course> classUnderTest;


    @SuppressWarnings("unchecked")
    public GivenBlackbox(Object classUnderTest) {
        this.classUnderTest = (Class<Course>) classUnderTest;
    }

    // Defining all the classes that need to be tested
    @Parameters
    public static Collection<Object[]> courseGradesUnderTest() {
        Object[][] classes = { 
                {CourseGrades0.class},
                {CourseGrades1.class},
                {CourseGrades2.class},
                {CourseGrades3.class},
                {CourseGrades4.class},
                {CourseGrades5.class}
        };
        return Arrays.asList(classes);
    }

    // method to call the correct constructor
    private Course createCourse(String name) throws Exception {
        Constructor<Course> constructor = classUnderTest.getConstructor(String.class);
        return constructor.newInstance(name);
    }


    // A sample course
    Course twoStudent;
    HashMap<String, Integer> twoStudentExpected = new HashMap<String, Integer>();
    Course zeroStudent;
    Course failingClass;
    Course justMissedClass;
    HashMap<String, Integer> justMissedExpected = new HashMap<>();
    Course negativeClass;
    HashMap<String, Integer> negativeClassExpected = new HashMap<>();
    Course negativeSoloClass;
    HashMap<String, Integer> negativeSoloExpected = new HashMap<>();
    Course lonelyClass;
    HashMap<String, Integer> lonelyExpected = new HashMap<>();


    @Before
    public void setUp() throws Exception {

        // all courses should be created like this


        // Course created with two Students having
        twoStudent = createCourse("SER316");
        twoStudent.set_points("Hanna",100);
        twoStudent.set_points("Karla",100);
        // this would be the expected result after the method countOccurencesLetterGrades is called
        twoStudentExpected.put("A", 2);
        twoStudentExpected.put("B", 0);
        twoStudentExpected.put("C", 0);
        twoStudentExpected.put("D", 0);
        twoStudentExpected.put("F", 0);

        zeroStudent = createCourse("SER334");

        failingClass = createCourse("CSE240");
        failingClass.set_points("Rob",0);
        failingClass.set_points("Trev",0);

        justMissedClass = createCourse("BIO181");
        justMissedClass.set_points("Brown", 100);
        justMissedClass.set_points("John", 80);
        justMissedClass.set_points("James", 65);
        justMissedClass.set_points("Jacob", 50);
        justMissedClass.set_points("Jordan", 35);

        justMissedExpected.put("A", 1);
        justMissedExpected.put("B", 1);
        justMissedExpected.put("C", 1);
        justMissedExpected.put("D", 1);
        justMissedExpected.put("F", 1);

        negativeClass = createCourse("SER321");
        negativeClass.set_points("Andrew", 100);
        negativeClass.set_points("Jacopo", -1);

        negativeClassExpected.put("A", 1);
        negativeClassExpected.put("B", 0);
        negativeClassExpected.put("C", 0);
        negativeClassExpected.put("D", 0);
        negativeClassExpected.put("F", 0);

        negativeSoloClass = createCourse("CHM111");
        negativeSoloClass.set_points("Jason", -5);

        negativeSoloExpected.put("A", 0);
        negativeSoloExpected.put("B", 0);
        negativeSoloExpected.put("C", 0);
        negativeSoloExpected.put("D", 0);
        negativeSoloExpected.put("F", 0);

        lonelyClass = createCourse("EGR280");
        lonelyClass.set_points("Bella", 1);

        lonelyExpected.put("A", 1);
        lonelyExpected.put("B", 0);
        lonelyExpected.put("C", 0);
        lonelyExpected.put("D", 0);
        lonelyExpected.put("F", 0);
    }


    // Sample test
    @Test
    public void twoStudent() {
        HashMap<String, Integer> ans = twoStudent.countOccurencesLetterGrades();
        //System.out.println(ans);
        assertTrue(ans.equals(twoStudentExpected));
    }

    @Test(expected = NullPointerException.class)
    public void zeroStudent() {
        //SER316 TASK 2 SPOTBUGS FIX
        zeroStudent.countOccurencesLetterGrades();
        //System.out.println(ans);
    }

    @Test(expected = ArithmeticException.class)
    public void failingClass() {
        //SER316 TASK 2 SPOTBUGS FIX
        failingClass.countOccurencesLetterGrades();
        //System.out.println(ans);
    }

    @Test
    public void justMissedClass() {
        HashMap<String, Integer> ans = justMissedClass.countOccurencesLetterGrades();
        //System.out.println(ans);
        assertTrue(ans.equals(justMissedExpected));
    }

    @Test
    public void negativeClass() {
        HashMap<String, Integer> ans = negativeClass.countOccurencesLetterGrades();
        //System.out.println(ans);
        assertTrue(ans.equals(negativeClassExpected));
    }

    @Test
    public void negativeSolo() {
        HashMap<String, Integer> ans = negativeSoloClass.countOccurencesLetterGrades();
        //System.out.println(ans);
        assertTrue(ans.equals(negativeSoloExpected));
    }

    @Test
    public void lonelyClass() {
        HashMap<String, Integer> ans = lonelyClass.countOccurencesLetterGrades();
        //System.out.println(ans);
        assertTrue(ans.equals(lonelyExpected));
    }
}
