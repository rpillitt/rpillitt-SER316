package test.java;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import main.java.Course;
import main.java.Major;
import main.java.Student;

public class CourseTest {
	
	Student duplicateTest;
	Course threeStudentCourse;
	Course twoCountedCourse;
	Course twoStudentCourse;
	Course oneStudentCourse;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void duplicateAdd() {
		twoCountedCourse = new Course("SER315");
		duplicateTest = new Student("John", Major.valueOf("SER"));
		twoCountedCourse.addStudent(duplicateTest);
		assertFalse(twoCountedCourse.addStudent(duplicateTest));
	}
	
	@Test
	public void nullAdd() {
		twoCountedCourse = new Course("SER315");
		assertFalse(twoCountedCourse.addStudent(null));
	}
	
	/**Description: Tests sequences <49, 51, 52, 53, 54, 57, 60, 61, 62, 63, 65, 66, 67, 69, 70, 72, 74, 62, 76, 77>, 
	 * <49, 51, 52, 53, 54, 57, 60, 61, 62, 63, 65, 66, 69, 72, 74, 62, 76, 77>
	 * */
	@Test
	public void threeStudents() {
		threeStudentCourse = new Course("CSE240");
		threeStudentCourse.addStudent(new Student("Jessie", Major.valueOf("SER")));
		threeStudentCourse.set_points("Jessie", 60);
		threeStudentCourse.set_points("Jamie", 70);
		threeStudentCourse.set_points("Jordan", 80);
		double ans = threeStudentCourse.calculateAverageWithoutMinWithoutMax();
		assertTrue(ans == 70.0);
	}

	/**Description: Tests sequence <49, 51, 52, 53, 54, 57, 60, 61, 62, 63, 72, 74, 62, 76, 77>
	 * 
	 */
	@Test
	public void twoCounted() {
		twoCountedCourse = new Course("SER315");
		twoCountedCourse.set_points("John", -50);
		twoCountedCourse.set_points("James", 50);
		twoCountedCourse.set_points("Jared", 100);
		double ans = twoCountedCourse.calculateAverageWithoutMinWithoutMax();
		assertTrue(ans == 75.0);
	}
	
	/**Description: Tests sequence <49, 51, 52, 53, 54, 57, 58>
	 * 
	 */
	@Test
	public void twoStudents() {
		twoStudentCourse = new Course("SER316");
		twoStudentCourse.set_points("Jeremy", 80);
		twoStudentCourse.set_points("Janet", 100);
		double ans = twoStudentCourse.calculateAverageWithoutMinWithoutMax();
		assertTrue(ans == 90.0);
	}
	
	/**Description: Tests sequence <49, 51, 52, 53, 54, 55>
	 * 
	 */
	@Test
	public void oneStudent() {
		oneStudentCourse = new Course("EGR280");
		oneStudentCourse.set_points("Jenny", 45);
		double ans = oneStudentCourse.calculateAverageWithoutMinWithoutMax();
		assertTrue(ans == 45.0);
	}
}
