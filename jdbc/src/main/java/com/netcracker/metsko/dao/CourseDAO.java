package com.netcracker.metsko.dao;

import com.netcracker.metsko.entity.Course;

import java.util.*;

public interface CourseDAO {

   List<Course> getCourses();

   Course getByID(long id);

   void deleteById(long id);

   void saveCourse(Course course);

   void update(Course course);
}
