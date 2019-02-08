package com.netcracker.metsko.dao;

import com.netcracker.metsko.connection.ConnectionDB;
import com.netcracker.metsko.entity.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultCourseDAO implements CourseDAO{

    private final String GET_COURSES = "SELECT id, coursename, surname, name, fathername, hours FROM course";
    private final String GET_COURSE_BY_ID = "SELECT id, coursename, surname,name, fathername, hours FROM course WHERE id= ?";
    private final String DELETE_BY_ID = "DELETE FROM course WHERE id =?";
    private final String INSERT_COURSE = "INSERT INTO course (id, coursename, surname, name, fathername, hours) VALUES (?,?,?,?,?,?)";
    private final String UPDATE_COURSE = "UPDATE course SET coursename =?,surname=?,name=?,fathername=?, hours=? WHERE id= ?";

    private ConnectionDB connectionDB = ConnectionDB.getInstance();

    private DefaultCourseDAO() {
    }

    @Override
    public List<Course> getCourses() {
        List<Course> courses = new ArrayList<Course>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            connection= connectionDB.getConnection();
            statement=connection.createStatement();
            resultSet=statement.executeQuery(GET_COURSES);
            while(resultSet.next())
            {
                Course course = new Course(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(4),resultSet.getString(5), resultSet.getInt(6));
                courses.add(course);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet!=null)
                {
                    resultSet.close();
                }
                if (statement!=null)
                {
                    statement.close();
                }
                if (connection!=null)
                {
                    connection.close();
                }

            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }


        }
       return courses;
    }

    @Override
    public Course getByID (long id)    {
        Course course = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection= connectionDB.getConnection();
            preparedStatement = connection.prepareStatement(GET_COURSE_BY_ID);
            preparedStatement.setLong(1,id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                course = new Course(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6));

            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet!=null)
                {
                    resultSet.close();
                }
                if (preparedStatement!=null)
                {
                    preparedStatement.close();
                }
                if (connection!=null)
                {
                    connection.close();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

        }
        return course;
    }

    @Override
    public void saveCourse(Course course) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionDB.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_COURSE);
            preparedStatement.setLong(1, course.getId());
            preparedStatement.setString(2, course.getCoursename());
            preparedStatement.setString(3, course.getSurname());
            preparedStatement.setString(4, course.getName());
            preparedStatement.setString(5,course.getFathername());
            preparedStatement.setLong(6, course.getHours());
            preparedStatement.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try {
                if(resultSet!=null)
                {
                    resultSet.close();
                }
                if(preparedStatement != null)
                {
                    preparedStatement.close();
                }
                if (connection !=null)
                {
                    connection.close();
                }

            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void update(Course course) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionDB.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_COURSE);

            preparedStatement.setString(1, course.getCoursename());
            preparedStatement.setString(2,course.getSurname());
            preparedStatement.setString(3, course.getName());
            preparedStatement.setString(4,course.getFathername());
            preparedStatement.setLong(5, course.getHours());
            preparedStatement.setLong(6, course.getId());
            preparedStatement.executeUpdate();

        }
        catch (SQLException e ){
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet!= null)
                {
                    resultSet.close();
                }
                if (preparedStatement!= null)
                {
                    preparedStatement.close();
                }
                if (connection!= null)
                {
                    connection.close();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteById(long id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection= connectionDB.getConnection();
            preparedStatement= connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet!=null)
                {
                    resultSet.close();
                }
                if (preparedStatement!=null)
                {
                    preparedStatement.close();
                }
                if (connection !=null)
                {
                    connection.close();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

    }


    private static  class Holder    {
        private final static DefaultCourseDAO INSTANCE = new DefaultCourseDAO();
    }

    public static DefaultCourseDAO getInstance()
    {
        return  Holder.INSTANCE;
    }
}
