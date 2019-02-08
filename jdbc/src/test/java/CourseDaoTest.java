
import com.netcracker.metsko.dao.CourseDAO;
import com.netcracker.metsko.dao.DefaultCourseDAO;
import com.netcracker.metsko.entity.Course;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class CourseDaoTest {
    private static CourseDAO courseDAO;
    private static Random random;

    private final static long DELETE_ID = 5;
    private final static long GET_ID = 1;
    private final static long INSERT_ID = 6;
    private final static String INSERT_COURSENAME = "HTML/CSS";
    private final static String INSERT_SURNAME = "Kabanov";
    private final static String INSERT_NAME = "Timofej";
    private final static String INSERT_FATHERNAME = "Illarionovich";
    private final static int INSERT_HOURS = 58;
    private final static long UPDATE_ID = 1;
    private final static String UPDATE_COURSENAME = "Node.js";
    private final static int UPDATE_HOURS =144;

    private Course course = new Course();

    @BeforeClass
    public static void init() {
        courseDAO = DefaultCourseDAO.getInstance();
        random = new Random();
    }

    @Test
    public void getById() throws Exception    {
        Course course=courseDAO.getByID(GET_ID);
        assertNotNull(course);
    }

    @Test
    public void deleteById() throws Exception    {
       courseDAO.deleteById(DELETE_ID);
    }

    @Test
    public void saveCourse() throws Exception{
        course.setId(INSERT_ID);
        course.setCoursename(INSERT_COURSENAME);
        course.setSurname(INSERT_SURNAME);
        course.setName(INSERT_NAME);
        course.setFathername(INSERT_FATHERNAME);
        course.setHours(INSERT_HOURS);

        courseDAO.saveCourse(course);

        Course courseSaved = courseDAO.getByID(INSERT_ID);

        assertEquals(INSERT_ID,courseSaved.getId());
        assertEquals(INSERT_COURSENAME,courseSaved.getCoursename());
        assertEquals(INSERT_SURNAME, courseSaved.getSurname());
        assertEquals(INSERT_NAME, courseSaved.getName());
        assertEquals(INSERT_FATHERNAME, courseSaved.getFathername() );
        assertEquals(INSERT_HOURS,courseSaved.getHours());

        //courseDAO.deleteById(courseSaved.getId());
    }

    @Test
    public void update() throws  Exception{
        Course course = courseDAO.getByID(UPDATE_ID);
        course.setCoursename(UPDATE_COURSENAME);
        course.setHours(UPDATE_HOURS);
        courseDAO.update(course);
        Course courseUpdate = courseDAO.getByID(UPDATE_ID);
        assertEquals(UPDATE_COURSENAME, courseUpdate.getCoursename());
        assertEquals(UPDATE_HOURS,courseUpdate.getHours());

    }

    @Test
    public void getCourses() throws Exception    {
        List<Course> courses = courseDAO.getCourses();
        assertNotNull(courses);
        assertEquals(5, courses.size());

    }

}
