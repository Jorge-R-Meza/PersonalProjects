
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jorgemezacabrera
 */
public class CourseQueries{
    private static Connection connection;
    
    
   
    private static PreparedStatement getAllCourses;
    private static PreparedStatement addCourse;
    private static PreparedStatement getCourseCodes;
    private static PreparedStatement getCourseSeats;
    private static PreparedStatement  dropCourse;
    private static PreparedStatement  updateCourse;
    
    //private  static String semesters;
    //private  static String coursecodes;
    //private  static String coursedescriptions;
    //private  static int seatss;
    
    
    
    private  static CourseEntry courseinfo;
    
    
    
    private static ResultSet resultSet;
    private static ResultSet resultSets;
    private static ResultSet resultSetz;
    private static ResultSet resultdrop;
    private static ResultSet resultUpdate;
    
    
    
    
    public static void addCourse(CourseEntry course)
    {
        connection = DBConnection.getConnection();
        try
        {
           
            
            addCourse = connection.prepareStatement("insert into app.COURSEENTRY (semester, coursecode, description, seats) values (?, ?, ?, ?)");
            addCourse.setString(1, course.getSemester());
            addCourse.setString(2, course.getCourseCode());
            addCourse.setString(3, course.getCourseDescription());
            addCourse.setInt(4, course.getSeats());
            addCourse.executeUpdate();
            
           
           
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<CourseEntry> getAllCourses(String semester) 
    {
        
        connection = DBConnection.getConnection();
        ArrayList<CourseEntry> courses = new ArrayList<>();
        
        try
        {
            
            getAllCourses = connection.prepareStatement("select * from app.courseentry where SEMESTER like (?)");
            getAllCourses.setString(1, semester);
            
            
            
            resultSet = getAllCourses.executeQuery();
            
           
            while(resultSet.next())
            {
                String zemester = resultSet.getString(1);
                String  korse = resultSet.getString(2);
                String kode = resultSet.getString(3);
                int sit = resultSet.getInt(4);
            
                courseinfo = new CourseEntry(zemester,korse,kode,sit);
                
                
             
                courses.add(courseinfo);
             
            }

        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return courses;
    }
    
     public static ArrayList<String> getAllCourseCodes(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> allcourseCodes = new ArrayList<String>();
        
        try
        {
            getCourseCodes = connection.prepareStatement("select coursecode from app.courseentry where SEMESTER like (?)");
            getCourseCodes.setString(1, semester);
            resultSets = getCourseCodes.executeQuery();
            
            while(resultSets.next())
            {
                allcourseCodes.add(resultSets.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return allcourseCodes;
        
    }
    public static int getCourseSeats(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        int courseseats = 0;
        try
        {
            
            getCourseSeats = connection.prepareStatement("select seats from app.courseentry where SEMESTER like (?) and COURSECODE like (?)");
            getCourseSeats.setString(1,semester);
            getCourseSeats.setString(2,courseCode);
            resultSetz = getCourseSeats.executeQuery();
            
            while(resultSetz.next())
            {
                String seat = resultSetz.getString(1);
            
                
                
                int zeat = Integer.parseInt(seat);
                
             
                courseseats = zeat;
             
            }

   
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courseseats;
        
    }
    
    public static void dropCouse(String semester, String courseCode)
    {
        
        connection = DBConnection.getConnection();
        try
        {
           dropCourse = connection.prepareStatement("DElETE from app.COURSEENTRY where semester like (?) and coursecode like (?) ");
           dropCourse.setString(1, semester);
           dropCourse.setString(2, courseCode);
           
           dropCourse.executeUpdate();
           
           
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        
    }
    public static void addSeat(String semester, String courseCode)
    {
        int currSeats = getCourseSeats(semester, courseCode);
        connection = DBConnection.getConnection();
        try
        {
           updateCourse = connection.prepareStatement("UPDATE app.COURSEENTRY set SEATS = (?) where semester like (?) and coursecode like (?) ");
           updateCourse.setInt(1, currSeats++);
           updateCourse.setString(2, semester);
           updateCourse.setString(3, courseCode);
           
           updateCourse.executeUpdate();
           
           
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
}
