
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class StudentQueries {
    
    private static Connection connection;
    //private static ArrayList<String> faculty = new ArrayList<String>();
    private static String StudentID;
    private static String FirstName;
    private static String LastName;
    private static StudentEntry oneStudent;
    
    private static StudentEntry studentinfo;
    
   
    
    private static PreparedStatement addStudent;
    private static PreparedStatement getAllStudents;
    private static PreparedStatement getOneStudent;
    private static PreparedStatement dropStudent;
    private static ResultSet resultSet;
    private static ResultSet resultStudent;
    
    public static void addStudent(StudentEntry student)//String StudentID, String FirstName, String LastName)      
    {
        connection = DBConnection.getConnection();
        try
        {
           
            
            addStudent = connection.prepareStatement("insert into app.STUDENTENTRY (STUDENTID, FIRSTNAME, LASTNAME) values (?,?,?)");
            addStudent.setString(1, student.getStudentID());
            addStudent.setString(2, student.getFirstName());
            addStudent.setString(3, student.getLastName());
            addStudent.executeUpdate();
            
           
           
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        
    }
    
    public static ArrayList<StudentEntry> getAllStudents()
    {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> student = new ArrayList<>();
        
        
        try
        {
            getAllStudents = connection.prepareStatement("select * from app.studententry");
            resultSet = getAllStudents.executeQuery();
            
            while(resultSet.next())
            {
                String studentid = (resultSet.getString(1));
                String firstname = (resultSet.getString(2));
                String lastname = (resultSet.getString(3));
                
                studentinfo = new StudentEntry(studentid, firstname, lastname);
                student.add(studentinfo);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return student;
  
    }
    
    
 
    public static String getStudentIDfromFirstName (String firstname)
    {
        connection = DBConnection.getConnection();
        String studentID = new String();
        try
        {
            getOneStudent = connection.prepareStatement("Select STUDENTID from app.STUDENTENTRY where FIRSTNAME like (?)");
            getOneStudent.setString(1, firstname);
            resultStudent = getOneStudent.executeQuery();
            
            while (resultStudent.next())
            {
            studentID = resultStudent.getString(1);
            }
            
                
            
        }
        catch(SQLException sqlException)
        {
        }
        
        return studentID;
    }
    public static StudentEntry getStudent(String StudentID)
    {
        connection = DBConnection.getConnection();
        
        String firstName = new String();
        String lastName = new String();
        try
        {
            getOneStudent = connection.prepareStatement("Select FIRSTNAME, LASTNAME from app.STUDENTENTRY where STUDENTID like (?)");
            getOneStudent.setString(1, StudentID);
            resultStudent = getOneStudent.executeQuery();
            
            while (resultStudent.next())
            {
            firstName = resultStudent.getString(1);
            lastName = resultStudent.getString(2);
            oneStudent = new StudentEntry(StudentID, firstName, lastName);
            }
            
                
            
        }
        catch(SQLException sqlException)
        {
        }
        
        return oneStudent;
    }
    
    public static void dropStudent(String StudentID)
    {
        connection = DBConnection.getConnection();
        try
        {
           dropStudent = connection.prepareStatement("DElETE from app.studententry where STUDENTID like (?)");
           dropStudent.setString(1, StudentID);

           dropStudent.executeUpdate();
           
           
        }
        catch(SQLException sqlException)
        {
   
        }
        ScheduleQueries.dropScheduleByStudent(StudentID);
    }
    
    
}

