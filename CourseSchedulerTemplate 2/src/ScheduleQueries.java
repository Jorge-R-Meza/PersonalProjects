
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jorgemezacabrera
 */
public class ScheduleQueries {
    private static Timestamp timestamp;
    private static Timestamp enrollTime;
    private static Connection connection;
    private static PreparedStatement addSchedule;
    private static PreparedStatement getWaitListedStudents;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getScheduledStudentsByCourse;
    private static PreparedStatement dropStudentScheduleByCourse;
    private static PreparedStatement dropScheduleByCourse;
    private static PreparedStatement updateScheduleEntry;
    private static PreparedStatement dropScheduleByStudent;
    private static PreparedStatement fixSeats;
    private static PreparedStatement getScheduledCourses;
    private static ResultSet scheduleSet;
    private static ResultSet resultScheduledCourses;
    private static ResultSet studentSet;
    private static ResultSet waitlistedSet;
    private static ScheduleEntry displayschedule;
    private static ScheduleEntry studentsByCourse;
    private static ScheduleEntry waitlistedStudents;
    
    
    
            
    public static void addScheduleEntry(ScheduleEntry entry)
    {
        connection = DBConnection.getConnection();
        try
        {
           
            
            addSchedule = connection.prepareStatement("insert into app.SCHEDULEENTRY (semester, studentid, coursecode, status, timestamp) values (?, ?, ?, ?, ?)");
            addSchedule.setString(1, entry.getSemester());
            addSchedule.setString(2, entry.getStudentID());
            addSchedule.setString(3, entry.getCourseCode());
            addSchedule.setString(4, entry.getStatus());
            addSchedule.setTimestamp(5, entry.getTimestamp());
            addSchedule.executeUpdate();
            
           
           
        }
        catch(SQLException sqlException)
        {
        }
        
        
    }
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID)
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> scheduleByStudent = new ArrayList<>();
        
        try
        {
            
            getScheduleByStudent = connection.prepareStatement("select coursecode, status, TIMESTAMP from app.scheduleentry where SEMESTER like (?) and studentid like (?)");
            getScheduleByStudent.setString(1,semester);
            getScheduleByStudent.setString(2,studentID);
            scheduleSet = getScheduleByStudent.executeQuery();
            
            while(scheduleSet.next())
            {
                
                
                String koursekode = scheduleSet.getString(1);
                String ztatuz = scheduleSet.getString(2);
                timestamp = scheduleSet.getTimestamp(3);
                
                
                displayschedule = new ScheduleEntry(semester, koursekode, studentID, ztatuz, timestamp);
                scheduleByStudent.add(displayschedule);
                
              
            }

   
        }
        
        catch(SQLException sqlException)
        {
        }
        return scheduleByStudent;
        
    }
    
    public static ArrayList<ScheduleEntry> getScheduledStudentsByCourse(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> scheduledStudentsByCourse = new ArrayList<>();
        String status = "S";
        try
        {
        getScheduledStudentsByCourse = connection.prepareStatement("select STUDENTID, TIMESTAMP from app.scheduleentry where SEMESTER like (?) and COURSECODE like (?) and STATUS like (?)");
        getScheduledStudentsByCourse.setString(1, semester);
        getScheduledStudentsByCourse.setString(2, courseCode);
        getScheduledStudentsByCourse.setString(3, status);
        studentSet = getScheduledStudentsByCourse.executeQuery();
        
        while (studentSet.next())
        {
            String studentIDs = studentSet.getString(1);
            enrollTime = studentSet.getTimestamp(2);
            
            studentsByCourse = new ScheduleEntry(semester, courseCode, studentIDs, status, enrollTime);
            scheduledStudentsByCourse.add(studentsByCourse);
            
        }
        
        }
        catch(SQLException sqlException)
        {
        }
        return scheduledStudentsByCourse;
        
    }
    
    public static ArrayList<ScheduleEntry> getWaitlistedStudentsByCourse(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> waitlistedStudentsByCourse = new ArrayList<>();
        String status = "W";
        try
        {
        getWaitListedStudents = connection.prepareStatement("select STUDENTID, TIMESTAMP from app.scheduleentry where SEMESTER like (?) and COURSECODE like (?) and STATUS like (?)");
        getWaitListedStudents.setString(1, semester);
        getWaitListedStudents.setString(2, courseCode);
        getWaitListedStudents.setString(3, status);
        waitlistedSet = getWaitListedStudents.executeQuery();
        
        while (waitlistedSet.next())
        {
            String studentID = waitlistedSet.getString(1);
            enrollTime = waitlistedSet.getTimestamp(2);
            
            waitlistedStudents = new ScheduleEntry(semester, courseCode, studentID, status, enrollTime);
            waitlistedStudentsByCourse.add(waitlistedStudents);
        }
        }
        
        catch(SQLException sqlException)
        {
        }
        return waitlistedStudentsByCourse;
    }
    
    public static void dropStudentScheduleByCourse(String semester, String studentID, String courseCode)
    {
        connection = DBConnection.getConnection();
        try
        {
            
           dropStudentScheduleByCourse = connection.prepareStatement("DElETE from app.SCHEDULEENTRY where semester like (?) and STUDENTID like (?) and coursecode like (?) ");
           dropStudentScheduleByCourse.setString(1, semester);
           dropStudentScheduleByCourse.setString(2, studentID);
           dropStudentScheduleByCourse.setString(3, courseCode);
           
           dropStudentScheduleByCourse.executeUpdate();
           
        }
        catch(SQLException sqlException)
        {
        }
    }
    
    public static void dropScheduleByCourse(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        try
        {
           dropScheduleByCourse = connection.prepareStatement("DElETE from app.SCHEDULEENTRY where semester like (?) and coursecode like (?) ");
           dropScheduleByCourse.setString(1, semester);
           dropScheduleByCourse.setString(2, courseCode);
           
           dropScheduleByCourse.executeUpdate();
           
        }
        catch(SQLException sqlException)
        {
        }
        
    }
    
    public static void dropScheduleByStudent(String studentID)
    {
        connection = DBConnection.getConnection();
        try
        {
           dropScheduleByStudent = connection.prepareStatement("DElETE from app.SCHEDULEENTRY where STUDENTID like (?)");
           dropScheduleByStudent.setString(1, studentID);
           dropScheduleByStudent.executeUpdate();
           
           
        }
        catch(SQLException sqlException)
        {
        }
    }
    
     public static void dropScheduleByStudentCourseSemester(String studentID, String coursecode, String semester)
    {
        connection = DBConnection.getConnection();
        try
        {
           dropScheduleByStudent = connection.prepareStatement("DElETE from app.SCHEDULEENTRY where STUDENTID like (?) and COURSECODE like (?) and SEMESTER like (?) ");
           dropScheduleByStudent.setString(1, studentID);
           dropScheduleByStudent.setString(2, coursecode);
           dropScheduleByStudent.setString(2, semester);
           dropScheduleByStudent.executeUpdate();
           
           
        }
        catch(SQLException sqlException)
        {
        }
    }
    
    public static void updateScheduleEntry(String Semester, ScheduleEntry entry)
    {
        String courseCode = new String();
        String studentID = new String();
        studentID = entry.getStudentID();
        courseCode = entry.getCourseCode();
        connection = DBConnection.getConnection();
        try
        {
            updateScheduleEntry = connection.prepareStatement("UPDATE app.SCHEDULEENTRY SET STATUS = (?) where SEMESTER like (?) and COURSECODE like (?) and STUDENTID like (?)");
            updateScheduleEntry.setString(1, "S");
            updateScheduleEntry.setString(2, Semester);
            updateScheduleEntry.setString(3, courseCode);
            updateScheduleEntry.setString(4, studentID);
            updateScheduleEntry.executeUpdate();
            
            
        }
        catch(SQLException sqlException)
        {
        }
                
    }
    
    public static ArrayList<String> getScheduledCourses(String semester)
    {
        ArrayList<String> scheduledCourse = new ArrayList<>();
        
        connection = DBConnection.getConnection();
        try
        {
           getScheduledCourses = connection.prepareStatement("SELECT COURSECODE from app.SCHEDULEENTRY where SEMESTER like (?) ");
           getScheduledCourses.setString(1, semester);
           resultScheduledCourses = getScheduledCourses.executeQuery();
           while (resultScheduledCourses.next())
           {
               scheduledCourse.add(resultScheduledCourses.getString(1));
           }
           
           
        }
        catch(SQLException sqlException)
        {
        }
        Set<String> set = new HashSet<>(scheduledCourse);
        scheduledCourse.clear();
        scheduledCourse.addAll(set);
        return scheduledCourse;
        
    }
     public static ArrayList<String> getScheduledStudents(String semester)
    {
        ArrayList<String> scheduledCourse = new ArrayList<>();
        ArrayList<String> scheduledStudents = new ArrayList<>();
        
        connection = DBConnection.getConnection();
        try
        {
           getScheduledCourses = connection.prepareStatement("SELECT STUDENTID from app.SCHEDULEENTRY where SEMESTER like (?)");
           getScheduledCourses.setString(1, semester);
           
           resultScheduledCourses = getScheduledCourses.executeQuery();
           while (resultScheduledCourses.next())
           {
               scheduledCourse.add(resultScheduledCourses.getString(1));
           }
           
           
        }
        catch(SQLException sqlException)
        {
        }
        Set<String> set = new HashSet<>(scheduledCourse);
        scheduledCourse.clear();
        scheduledCourse.addAll(set);
        
        int val = 0;
        while (scheduledCourse.size() > val)
        {
            StudentEntry currstudent = StudentQueries.getStudent(scheduledCourse.get(val));
            String studentName = currstudent.getFirstName();
            
            scheduledStudents.add(studentName);
                    
            val++;
        }
       
        
        return scheduledStudents;
        
    }
    
}
    

