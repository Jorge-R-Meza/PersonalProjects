

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;


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
 * @author acv
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    
    private Timestamp timestamp;
    private static Connection connection;
    private CourseEntry course;
    private StudentEntry student;
    private StudentEntry studentByCourseSE;
    private StudentEntry studentByCourseSEW;
    private String currentSemester; 
    private String status;
    private ArrayList<String> semesters;
    private ArrayList<String> semestercourses;
    private ArrayList<String> scheduledCourses;
    private ArrayList<String> totalStude;
    private ArrayList<String> allstudents;
    private ArrayList<String> waitlist;
    
    private static PreparedStatement getstudentnamez;
    private static PreparedStatement getstudentnames;
    private static PreparedStatement getstudentids;
    private static PreparedStatement getstudentlastnames;
    private static PreparedStatement updateSeats;
    private static PreparedStatement currstudentID;
    private static PreparedStatement studentIDByCourse;
    private static PreparedStatement studentIDByCourseW;
    private static PreparedStatement counting;
    private static PreparedStatement getDropStudentID;
    private static PreparedStatement getDropInfo;
    private static PreparedStatement getDropCourse;
    private static PreparedStatement getDroppedStudents;
    private static ResultSet resultnames;
    private static ResultSet resultDroppedStudent;
    private static ResultSet resultCourses;
    private static ResultSet studentnames;
    private static ResultSet resultids;
    private static ResultSet resultselectednames;
    private static ResultSet resultlastnames;
    private static ResultSet resultStudentIDS;
    private static ResultSet resultStudentIDSW;
    private static ResultSet resultStudentEntry;
    private static ResultSet getCount;
    private static ResultSet dropStudentIDSet;
    private static ResultSet dropInfoSet;
    private static ScheduleEntry schedule;
    private static StudentEntry getScheduledStudensSE;
    private static StudentEntry getWaitlistedStudensSE;
    private static String selectedStudentID;
    private static int size;
    
    
    
    public MainFrame() {
        initComponents();
        rebuildSemesterComboBoxes();
        rebuildCourseComboBoxes();
        rebuildStudentComboBoxes();
        rebuildStudentComboBoxesCurrSemester();
        
        
    }
    public void rebuildStudentComboBoxesCurrSemester()
    {
        
        connection = DBConnection.getConnection();
        ArrayList<String> allstudentnames= new ArrayList<>();
        
        try
        {
            getstudentids = connection.prepareStatement("select studentid from app.scheduleentry where semester like (?)");
            getstudentids.setString(1, currentSemester);
            
            
            
            resultids = getstudentids.executeQuery();
            
            while( resultids.next())
            {
                getstudentnamez = connection.prepareStatement("select firstname from app.studententry where studentid like (?)");
                
                getstudentnamez.setString(1, resultids.getString(1));
                
                resultselectednames = getstudentnamez.executeQuery();
                
                while (resultselectednames.next())
                {
                    allstudentnames.add(resultselectednames.getString(1));
                }
                
                
               
            }
            
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        Set<String> set = new HashSet<>(allstudentnames);
        allstudentnames.clear();
        allstudentnames.addAll(set);
        selectStudentBoxDisplay.setModel(new javax.swing.DefaultComboBoxModel(allstudentnames.toArray()));
        selectStudentDropCouseBox.setModel(new javax.swing.DefaultComboBoxModel(ScheduleQueries.getScheduledStudents(currentSemester).toArray()));
        
        
    }
    public void rebuildStudentComboBoxes()
    {
        
        connection = DBConnection.getConnection();
        ArrayList<String> allstudentnames = new ArrayList<String>();
        
        
        try
        {
            getstudentnames = connection.prepareStatement("select firstname from app.studententry");
            
            
            resultnames = getstudentnames.executeQuery();
            
            while( resultnames.next())
            {
                allstudentnames.add(resultnames.getString(1));
               
                
                
                
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        jBoxSelectStudent.setModel(new javax.swing.DefaultComboBoxModel(allstudentnames.toArray()));
        selectDropStudentBox.setModel(new javax.swing.DefaultComboBoxModel(allstudentnames.toArray()));
        
        
        
        
        
    }
    
    public void rebuildCourseComboBoxes()
    {
         
        semestercourses = CourseQueries.getAllCourseCodes(currentSemester);
        scheduledCourses = ScheduleQueries.getScheduledCourses(currentSemester);
        jBoxSelectCourse.setModel(new javax.swing.DefaultComboBoxModel(semestercourses.toArray()));
        selectCourseStudentsBox.setModel(new javax.swing.DefaultComboBoxModel(semestercourses.toArray()));
        courseToBeDroppedBox.setModel(new javax.swing.DefaultComboBoxModel(scheduledCourses.toArray()));
        selectDropCourseBox.setModel(new javax.swing.DefaultComboBoxModel(scheduledCourses.toArray()));
       
        
        
    }
    public void rebuildSemesterComboBoxes()
    {
        semesters = SemesterQueries.getSemesterList();
        currentSemesterComboBox.setModel(new javax.swing.DefaultComboBoxModel(semesters.toArray()));
        if (semesters.size() > 0)
        {
            currentSemesterLabel.setText(semesters.get(0));
            currentSemester = semesters.get(0);
        }
        else
        {
            currentSemesterLabel.setText("None, add a semester.");
            currentSemester = "None";
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        addSemesterTextfield = new javax.swing.JTextField();
        addSemesterSubmitButton = new javax.swing.JButton();
        addSemesterStatusLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabelStudentID = new javax.swing.JLabel();
        jLabelFirstName = new javax.swing.JLabel();
        jLabelLastName = new javax.swing.JLabel();
        addStudentFirstNameTextField = new javax.swing.JTextField();
        addStudentLastNameTextField = new javax.swing.JTextField();
        addStudentIDTextField = new javax.swing.JTextField();
        addStudentInfoButton = new javax.swing.JButton();
        jLabelStudentAdded = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabelCourseCode = new javax.swing.JLabel();
        addCourseCodeTextField = new javax.swing.JTextField();
        addCourseDescriptionTextField = new javax.swing.JTextField();
        jLabelCourseDescription = new javax.swing.JLabel();
        addSeatsSpinner = new javax.swing.JSpinner();
        jLabelSeats = new javax.swing.JLabel();
        jButtonSubmitCourse = new javax.swing.JButton();
        jLabelCourseAdded = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        selectCourseStudentsBox = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        studentsInCourseTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        selectedCourseStudentsDispalyButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        waitlistedStudentsTable = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        selectDropStudentBox = new javax.swing.JComboBox<>();
        dropStudentButton = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jPanel13 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        courseToBeDroppedBox = new javax.swing.JComboBox<>();
        adminDropCourseButton = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        dropCoursesTab = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        displayCoursesTable = new javax.swing.JTable();
        jButtonDisplayCourses = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabelSelectCourse = new javax.swing.JLabel();
        jLabelSelectStudent = new javax.swing.JLabel();
        jBoxSelectCourse = new javax.swing.JComboBox<>();
        jBoxSelectStudent = new javax.swing.JComboBox<>();
        jButtonSubmitCoursesSchedule = new javax.swing.JButton();
        waitlistOrScheduledLabel = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabelSelectStudent2 = new javax.swing.JLabel();
        jLabelSelectStudent3 = new javax.swing.JLabel();
        selectStudentBoxDisplay = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        displayScheduleTable = new javax.swing.JTable();
        jButtonDisplaySchedule = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        selectStudentDropCourseLabel = new javax.swing.JLabel();
        selectDropCourseLabel = new javax.swing.JLabel();
        selectStudentDropCouseBox = new javax.swing.JComboBox<>();
        selectDropCourseBox = new javax.swing.JComboBox<>();
        studentDropCourseButton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabelCurrentSemester = new javax.swing.JLabel();
        currentSemesterLabel = new javax.swing.JLabel();
        currentSemesterComboBox = new javax.swing.JComboBox<>();
        changeSemesterButton = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Heiti TC", 1, 35)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Course Scheduler");

        jLabel3.setText("Semester Name:");

        addSemesterTextfield.setColumns(20);

        addSemesterSubmitButton.setText("Submit");
        addSemesterSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSemesterSubmitButtonActionPerformed(evt);
            }
        });

        addSemesterStatusLabel.setText("                                                   ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addSemesterStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addSemesterSubmitButton)
                            .addComponent(addSemesterTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(359, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(addSemesterTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(addSemesterSubmitButton)
                .addGap(18, 18, 18)
                .addComponent(addSemesterStatusLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Add Semester", jPanel3);

        jLabelStudentID.setText("Student ID:");

        jLabelFirstName.setText("First Name:");

        jLabelLastName.setText("Last Name:");

        addStudentFirstNameTextField.setColumns(20);

        addStudentLastNameTextField.setColumns(20);

        addStudentIDTextField.setColumns(20);

        addStudentInfoButton.setText("Submit");
        addStudentInfoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStudentInfoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelStudentAdded)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabelStudentID)
                            .addGap(20, 20, 20)
                            .addComponent(addStudentIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabelFirstName)
                            .addGap(18, 18, 18)
                            .addComponent(addStudentFirstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabelLastName)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addStudentInfoButton)
                            .addComponent(addStudentLastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelStudentID)
                    .addComponent(addStudentIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addStudentFirstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFirstName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLastName)
                    .addComponent(addStudentLastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jLabelStudentAdded)
                .addGap(18, 18, 18)
                .addComponent(addStudentInfoButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        addStudentIDTextField.getAccessibleContext().setAccessibleName("");

        jTabbedPane2.addTab("Add Student", jPanel5);

        jLabelCourseCode.setText("Course Code:");

        addCourseCodeTextField.setColumns(20);

        addCourseDescriptionTextField.setColumns(20);

        jLabelCourseDescription.setText("Course Description:");

        jLabelSeats.setText("Seats:");

        jButtonSubmitCourse.setText("Submit");
        jButtonSubmitCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubmitCourseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCourseCode)
                            .addComponent(jLabelCourseDescription))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabelSeats)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSubmitCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addCourseCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCourseAdded)
                    .addComponent(addCourseDescriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addSeatsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(333, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCourseCode)
                    .addComponent(addCourseCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addCourseDescriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCourseDescription))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSeatsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSeats))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSubmitCourse)
                .addGap(24, 24, 24)
                .addComponent(jLabelCourseAdded)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Add Course", jPanel4);

        jLabel2.setText("Select Course:");

        selectCourseStudentsBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        studentsInCourseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Last Name", "First Name", "StudentID"
            }
        ));
        studentsInCourseTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(studentsInCourseTable);

        jLabel4.setText("Scheduled Students:");

        selectedCourseStudentsDispalyButton.setText("Display");
        selectedCourseStudentsDispalyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedCourseStudentsDispalyButtonActionPerformed(evt);
            }
        });

        jLabel7.setText("Waitlisted Students:");

        waitlistedStudentsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Last Name", "First Name", "Student ID"
            }
        ));
        jScrollPane6.setViewportView(waitlistedStudentsTable);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(228, 228, 228))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(selectCourseStudentsBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(selectedCourseStudentsDispalyButton)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(selectCourseStudentsBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectedCourseStudentsDispalyButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Display Course List of Students", jPanel11);

        jLabel5.setText("Select Student:");

        selectDropStudentBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        dropStudentButton.setText("Drop Student");
        dropStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropStudentAdmin(evt);
            }
        });

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane7.setViewportView(jTextArea3);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(selectDropStudentBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dropStudentButton)))
                .addContainerGap(249, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(selectDropStudentBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dropStudentButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Drop Student", jPanel12);

        jLabel6.setText("Select Course to be Dropped:");

        courseToBeDroppedBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        adminDropCourseButton.setText("Drop Course");
        adminDropCourseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminDropCourseButtonActionPerformed(evt);
            }
        });

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane8.setViewportView(jTextArea4);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(courseToBeDroppedBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(adminDropCourseButton)))
                .addContainerGap(248, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(courseToBeDroppedBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adminDropCourseButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Drop Course", jPanel13);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Admin", jPanel1);

        dropCoursesTab.setMinimumSize(new java.awt.Dimension(130, 46));
        dropCoursesTab.setPreferredSize(new java.awt.Dimension(775, 329));

        displayCoursesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Course Code", "Description", "Seats"
            }
        ));
        jScrollPane1.setViewportView(displayCoursesTable);

        jButtonDisplayCourses.setText("Display");
        jButtonDisplayCourses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDisplayCoursesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDisplayCourses))
                .addGap(0, 346, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDisplayCourses)
                .addGap(0, 21, Short.MAX_VALUE))
        );

        dropCoursesTab.addTab("Display Courses", jPanel6);

        jLabelSelectCourse.setText("Select Course:");

        jLabelSelectStudent.setText("Select Student:");

        jBoxSelectCourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jBoxSelectStudent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonSubmitCoursesSchedule.setText("Submit");
        jButtonSubmitCoursesSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonScheduleEntry(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSelectCourse)
                    .addComponent(jLabelSelectStudent))
                .addGap(32, 32, 32)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSubmitCoursesSchedule)
                    .addComponent(jBoxSelectStudent, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBoxSelectCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(527, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(waitlistOrScheduledLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSelectCourse)
                    .addComponent(jBoxSelectCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSelectStudent)
                    .addComponent(jBoxSelectStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonSubmitCoursesSchedule)
                .addGap(26, 26, 26)
                .addComponent(waitlistOrScheduledLabel)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        dropCoursesTab.addTab("Schedule Courses", jPanel7);

        jLabelSelectStudent3.setText("Select Sudent:");

        selectStudentBoxDisplay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        displayScheduleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Course Code", "Status"
            }
        ));
        jScrollPane2.setViewportView(displayScheduleTable);

        jButtonDisplaySchedule.setText("Display");
        jButtonDisplaySchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayScheduleButton(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSelectStudent2)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabelSelectStudent3)
                        .addGap(18, 18, 18)
                        .addComponent(selectStudentBoxDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDisplaySchedule))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(363, 363, 363))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSelectStudent3)
                    .addComponent(selectStudentBoxDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDisplaySchedule))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSelectStudent2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        dropCoursesTab.addTab("Display Schedule", jPanel8);

        selectStudentDropCourseLabel.setText("Select Student:");

        selectDropCourseLabel.setText("Select Course:");

        selectStudentDropCouseBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        selectDropCourseBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        studentDropCourseButton.setText("Drop Course");
        studentDropCourseButton.setAlignmentY(0.0F);
        studentDropCourseButton.setAutoscrolls(true);
        studentDropCourseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentDropCourseButtonActionPerformed(evt);
            }
        });

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane5.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(selectStudentDropCourseLabel)
                        .addGap(18, 18, 18)
                        .addComponent(selectStudentDropCouseBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(selectDropCourseLabel)
                        .addGap(18, 18, 18)
                        .addComponent(selectDropCourseBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(studentDropCourseButton)))
                .addContainerGap(171, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectStudentDropCourseLabel)
                    .addComponent(selectStudentDropCouseBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectDropCourseLabel)
                    .addComponent(selectDropCourseBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(studentDropCourseButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                .addContainerGap())
        );

        dropCoursesTab.addTab("Drop Course", jPanel10);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(dropCoursesTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dropCoursesTab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        dropCoursesTab.getAccessibleContext().setAccessibleName("DisplayCourses");

        jTabbedPane1.addTab("Student", jPanel2);

        jLabelCurrentSemester.setFont(new java.awt.Font("Damascus", 1, 16)); // NOI18N
        jLabelCurrentSemester.setText("Current Semester: ");

        currentSemesterLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        currentSemesterLabel.setText("           ");

        currentSemesterComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        currentSemesterComboBox.setToolTipText("");

        changeSemesterButton.setText("Change Semester");
        changeSemesterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeSemesterButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabelCurrentSemester, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currentSemesterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(currentSemesterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(changeSemesterButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(currentSemesterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(changeSemesterButton))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelCurrentSemester, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                        .addComponent(currentSemesterLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void changeSemesterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeSemesterButtonActionPerformed
        // TODO add your handling code here:
        
        
        
        String selectedSemester = (String) currentSemesterComboBox.getSelectedItem();
        int semesterPosition = semesters.indexOf(selectedSemester);
        semesters.remove(semesterPosition);
        semesters.add(0, selectedSemester);
        
        if (semesters.size() > 0)
        {
            currentSemesterLabel.setText(semesters.get(0));
            currentSemester = semesters.get(0);
        }
        else
        {
            currentSemesterLabel.setText("None, add a semester.");
            currentSemester = "None";
        }
        
        rebuildCourseComboBoxes();
        rebuildStudentComboBoxesCurrSemester();
        
        
        
       
    }//GEN-LAST:event_changeSemesterButtonActionPerformed

    private void jButtonSubmitCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubmitCourseActionPerformed
        // TODO add your handling code here:
        
        String semester = (String) currentSemester;//currentSemesterComboBox.getSelectedItem();
        String courseCode = addCourseCodeTextField.getText();
        String courseDescription = addCourseDescriptionTextField.getText();
        int seats = (Integer) addSeatsSpinner.getValue();
        
        course = new CourseEntry(semester,courseCode,courseDescription, seats);
        
        

        CourseQueries.addCourse(course);

        jLabelCourseAdded.setText(courseCode + " has been added to the "+ semester + " semester.");
        rebuildCourseComboBoxes();
        
    }//GEN-LAST:event_jButtonSubmitCourseActionPerformed

    private void addStudentInfoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStudentInfoButtonActionPerformed
        // TODO add your handling code here:
        //StudentEntry(String StudentID, String FirstName, String LastName)

        String StudentID = addStudentIDTextField.getText();
        String FirstName = addStudentFirstNameTextField.getText();
        String LastName = addStudentLastNameTextField.getText();
        
        student = new StudentEntry(StudentID, FirstName, LastName);
        
        StudentQueries.addStudent(student);
        

        jLabelStudentAdded.setText(LastName +", " + FirstName + " has been added.");
        
        rebuildStudentComboBoxes();

    }//GEN-LAST:event_addStudentInfoButtonActionPerformed

    private void addSemesterSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSemesterSubmitButtonActionPerformed
        
        String semester = addSemesterTextfield.getText();
        SemesterQueries.addSemester(semester);
        addSemesterStatusLabel.setText("Semester " + semester + " has been added.");
        rebuildSemesterComboBoxes();
        rebuildCourseComboBoxes();
        
    }//GEN-LAST:event_addSemesterSubmitButtonActionPerformed

    private void jButtonDisplayCoursesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDisplayCoursesActionPerformed
        // TODO add your handling code here:
        
        ArrayList<CourseEntry> courses = CourseQueries.getAllCourses(currentSemester);
        DefaultTableModel displayCoursesTableModel = (DefaultTableModel)displayCoursesTable.getModel();
        
 
        displayCoursesTableModel.setNumRows(0);
        
        Object[] rowData = new Object[3];
        
        for (CourseEntry course:courses)
        {
            rowData[0] = course.getCourseCode();
            rowData[1] = course.getCourseDescription();
            rowData[2] = course.getSeats();
            displayCoursesTableModel.addRow(rowData);
        }
        System.out.print(CourseQueries.getCourseSeats(currentSemester, "Phys 212"));
        System.out.print(StudentQueries.getAllStudents());
        System.out.print(CourseQueries.getAllCourseCodes(currentSemester));
        System.out.print(SemesterQueries.getSemesterList());
    }//GEN-LAST:event_jButtonDisplayCoursesActionPerformed

    private void jButtonScheduleEntry(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonScheduleEntry
        // TODO add your handling code here:
        
        
        ArrayList<String> waitlist = new ArrayList<>();
        String semester = (String) currentSemester;
        String coursecode = (String) jBoxSelectCourse.getSelectedItem();
        timestamp = new Timestamp(System.currentTimeMillis());
        
        connection = DBConnection.getConnection();
        String studentIDs = new String();
        String currStudentLastNameZ = new String();
        String currStudentName = (String) jBoxSelectStudent.getSelectedItem();
        
        try
        {
            getstudentnames = connection.prepareStatement("select studentid from app.studententry where firstname like (?)");
            getstudentnames.setString(1, currStudentName);

            resultnames = getstudentnames.executeQuery();
            

            while( resultnames.next())
            {
                studentIDs = (resultnames.getString(1));
             
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }

         try
        {
            getstudentlastnames = connection.prepareStatement("select lastname from app.studententry where firstname like (?)");
            getstudentlastnames.setString(1, currStudentName );

            resultlastnames = getstudentlastnames.executeQuery();
            

            while( resultlastnames.next())
            {
                currStudentLastNameZ = (resultlastnames.getString(1));
             
            }
        }
        
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }

        String studentID = (String)studentIDs;
        String currStudentLastName = (currStudentLastNameZ);

        
        
        int availableSeat = CourseQueries.getCourseSeats(semester, coursecode);
        if (availableSeat == 0)
        {
            status = "W";
            waitlistOrScheduledLabel.setText(currStudentLastName+ ", " + currStudentName + " has been waitlisted for " + coursecode);
            waitlist.add(currStudentName);
            
        }
        else
        {
            
            status = "S";
            waitlistOrScheduledLabel.setText(currStudentLastName+ ", " + currStudentName + " has been scheduled for " + coursecode);
            
            try
            {
            updateSeats = connection.prepareStatement("UPDATE app.courseentry set seats = seats - 1 where coursecode like (?)");
            updateSeats.setString(1, coursecode);
            updateSeats.executeUpdate();
            
            }
            
            catch(SQLException sqlException)
            
            {
                sqlException.printStackTrace();
            }
      
        }
        
        
        schedule = new ScheduleEntry(semester, coursecode, studentID, status, timestamp );
        ScheduleQueries.addScheduleEntry(schedule);
        rebuildStudentComboBoxesCurrSemester();
        rebuildCourseComboBoxes();
        System.out.print(availableSeat);
        System.out.print(waitlist);
    }//GEN-LAST:event_jButtonScheduleEntry

    private void displayScheduleButton(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayScheduleButton
        // TODO add your handling code here:
        
        String currSelectedStudentID = (String) selectStudentBoxDisplay.getSelectedItem();
        
        ArrayList<String> currSelectedStudent = new ArrayList<>();
        
        connection = DBConnection.getConnection();
        try
        {
            currstudentID = connection.prepareStatement("select studentid from app.STUDENTENTRY where firstname like (?)");
            currstudentID.setString(1, currSelectedStudentID );
            
            studentnames = currstudentID.executeQuery();
            while (studentnames.next())
                {
                    currSelectedStudent.add(studentnames.getString(1));
                }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        String currentStudent = new String();
        currentStudent = currSelectedStudent.get(0);
        
        ArrayList<ScheduleEntry> getSchedulebyStudents = ScheduleQueries.getScheduleByStudent(currentSemester, currentStudent);
        DefaultTableModel displayScheduleTableModel = (DefaultTableModel)displayScheduleTable.getModel();
        
 
        displayScheduleTableModel.setNumRows(0);
        
        
        
        
        Object[] rowDatas = new Object[2];
        
        for (ScheduleEntry entry:getSchedulebyStudents)
        {
            rowDatas[0] = entry.getCourseCode();
            rowDatas[1] = entry.getStatus();
            displayScheduleTableModel.addRow(rowDatas);
        }
     
        
    }//GEN-LAST:event_displayScheduleButton

    private void selectedCourseStudentsDispalyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectedCourseStudentsDispalyButtonActionPerformed
        // TODO add your handling code here:
        String currSelectedCourse = (String) selectCourseStudentsBox.getSelectedItem();
        
        
        ArrayList<ScheduleEntry> getScheduledEntry = new ArrayList<>();
        ArrayList<ScheduleEntry> getWaitlistedEntry = new ArrayList<>();
        
        ArrayList<String> getScheduledStudents = new ArrayList<>();
        ArrayList<String> getWaitlistedStudents = new ArrayList<>();
        
        ArrayList<StudentEntry> getScheduledStudensSEL = new ArrayList<>();
        ArrayList<StudentEntry> getWaitlistedStudentsSEL = new ArrayList<>();
        
        
        getScheduledEntry = ScheduleQueries.getScheduledStudentsByCourse(currentSemester,currSelectedCourse);
        getWaitlistedEntry = ScheduleQueries.getWaitlistedStudentsByCourse(currentSemester, currSelectedCourse);
        
        DefaultTableModel studentsInCourseTableModel = (DefaultTableModel)studentsInCourseTable.getModel();
        
        DefaultTableModel waitlistedStudentsTableModel = (DefaultTableModel)waitlistedStudentsTable.getModel();
        
        System.out.print(getScheduledEntry);

        
        for (ScheduleEntry entry: getScheduledEntry)
        {
           
            getScheduledStudents.add(entry.getStudentID());
            
        }
        
        for (ScheduleEntry waitentry: getWaitlistedEntry)
        {
            getWaitlistedStudents.add(waitentry.getStudentID());
        }
        System.out.print(getScheduledStudents);
        
        int val = 0;
        while (getScheduledStudents.size() > val)
        {
        
            connection = DBConnection.getConnection();
            try
            {
                studentIDByCourse = connection.prepareStatement("select FIRSTNAME , LASTNAME from app.STUDENTENTRY where STUDENTID like (?)");
                studentIDByCourse.setString(1, getScheduledStudents.get(val));
               
                resultStudentIDS = studentIDByCourse.executeQuery();

                    while (resultStudentIDS.next())
                    {

                        
                        getScheduledStudensSE = new StudentEntry(getScheduledStudents.get(val),resultStudentIDS.getString(1),resultStudentIDS.getString(2));
                        getScheduledStudensSEL.add(getScheduledStudensSE );
                    }
            }
            catch(SQLException sqlException)
            {
                
            }
            val++;
        }
        System.out.print(getScheduledStudensSEL);
        
        studentsInCourseTableModel.setNumRows(0);
        
        Object[] rowData = new Object[3];
        
        for (StudentEntry student:getScheduledStudensSEL)
        {
            rowData[0] = student.getLastName();
            rowData[1] = student.getFirstName();
            rowData[2] = student.getStudentID();
            studentsInCourseTableModel.addRow(rowData);
        }
        
        int val2 = 0;
        while (getWaitlistedStudents.size() > val2)
        {
        
            connection = DBConnection.getConnection();
            try
            {
                studentIDByCourse = connection.prepareStatement("select FIRSTNAME , LASTNAME from app.STUDENTENTRY where STUDENTID like (?)");
                studentIDByCourse.setString(1, getWaitlistedStudents.get(val2));
               
                resultStudentIDS = studentIDByCourse.executeQuery();

                    while (resultStudentIDS.next())
                    {

                        
                        getWaitlistedStudensSE = new StudentEntry(getWaitlistedStudents.get(val2),resultStudentIDS.getString(1),resultStudentIDS.getString(2));
                        getWaitlistedStudentsSEL.add(getWaitlistedStudensSE);
                    }
            }
            catch(SQLException sqlException)
            {
                
            }
            val2++;
        }
        System.out.print(getScheduledStudensSEL);
        
        waitlistedStudentsTableModel.setNumRows(0);
        
        Object[] rowDatas = new Object[3];
        
        for (StudentEntry student2:getWaitlistedStudentsSEL)
        {
            rowDatas[0] = student2.getLastName();
            rowDatas[1] = student2.getFirstName();
            rowDatas[2] = student2.getStudentID();
            waitlistedStudentsTableModel.addRow(rowDatas);
        }
        
        
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_selectedCourseStudentsDispalyButtonActionPerformed

    private void dropStudentAdmin(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropStudentAdmin
        // TODO add your handling code here:
       
        String currSelectedStudent = (String) selectDropStudentBox.getSelectedItem();
        ArrayList<String> dropInfo = new ArrayList<>();
        String dropStudentID = new String();
        String studentSemester = new String();
        String studentCourseCode = new String();
        String studentStatus = new String();
        
        connection = DBConnection.getConnection();
        try
        {
            getDropStudentID = connection.prepareStatement("select STUDENTID from app.studententry where FIRSTNAME like (?)");
            getDropStudentID.setString(1, currSelectedStudent);
            dropStudentIDSet = getDropStudentID.executeQuery();

            while (dropStudentIDSet.next())
            {
                dropStudentID = (dropStudentIDSet.getString(1));
            }
            
            getDropInfo = connection.prepareStatement("select SEMESTER, COURSECODE, STATUS  from app.scheduleentry where STUDENTID like (?)");
            getDropInfo.setString(1,dropStudentID);
            dropInfoSet = getDropInfo.executeQuery();

            while (dropInfoSet.next())
            {
                studentSemester = (dropInfoSet.getString(1));
                studentCourseCode = (dropInfoSet.getString(2));
                studentStatus = (dropInfoSet.getString(3)); 
                
                 jTextArea3.append("\n");
                
                if (studentStatus.equals("W"))
                {
                       
                        StudentQueries.dropStudent(dropStudentID);
                        System.out.print("W");
                        
                        jTextArea3.append(currSelectedStudent + " has been dropped from " + studentCourseCode + ", "+studentSemester+ "\n");
                }
                else
                {
                    //dropInfo.add(studentSemester +","+ studentCourseCode +","+ studentStatus);
                    StudentQueries.dropStudent(dropStudentID);
                    System.out.print("S");
                    
                    ArrayList<ScheduleEntry> waitlistedStudents = ScheduleQueries.getWaitlistedStudentsByCourse(studentSemester, studentCourseCode);
                    
                    int getNumberofWaitlistedStudents= waitlistedStudents.size();
                    System.out.print(getNumberofWaitlistedStudents);
                    
                        
                    if (getNumberofWaitlistedStudents == 0)
                    {
                        CourseQueries.addSeat(studentSemester, studentCourseCode);
                        jTextArea3.append(currSelectedStudent + " has been dropped from " + studentCourseCode + ", "+studentSemester+ "\n");
                        jTextArea3.append("A seat has been added to " + studentCourseCode + ", "+studentSemester+ "\n");
                        
                    }
                    else
                    {
                        ScheduleEntry firstStudent = waitlistedStudents.get(0);
                        String firstStudentID = firstStudent.getStudentID();
                        StudentEntry firstStudentNameEntry = StudentQueries.getStudent(firstStudentID );
                        String firstStudentName = firstStudentNameEntry.getFirstName();
                        String firstLastName = firstStudentNameEntry.getLastName();
                        ScheduleQueries.updateScheduleEntry(studentSemester, firstStudent);
                        
                        jTextArea3.append(currSelectedStudent + " has been dropped from " + studentCourseCode + ", "+studentSemester+ "\n");
                        jTextArea3.append(firstStudentName  +", "+firstLastName + " has been scheduled for "+ studentCourseCode + ", "+ studentSemester +"\n");
                    }
                    
                    
                        
                }
                   
                
                
                
            }
            
            
            
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        
        
        rebuildStudentComboBoxes();
        rebuildStudentComboBoxesCurrSemester();
        
        
        
        
        
        
    }//GEN-LAST:event_dropStudentAdmin

    private void adminDropCourseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminDropCourseButtonActionPerformed
        // TODO add your handling code here:
        
        String courseToDrop = (String) courseToBeDroppedBox.getSelectedItem();
        
        
        connection = DBConnection.getConnection();
        try
        {
            getDroppedStudents = connection.prepareStatement("select STUDENTID from app.scheduleentry where COURSECODE like (?) and SEMESTER like (?)");
            getDroppedStudents.setString(1, courseToDrop);
            getDroppedStudents.setString(2, currentSemester);
            
            resultDroppedStudent = getDroppedStudents.executeQuery();
            
            while (resultDroppedStudent.next())
            {
                String droppedStudentID = resultDroppedStudent.getString(1);
                StudentEntry droppedStudentEntry = StudentQueries.getStudent(droppedStudentID);
                String droppedStudentName = droppedStudentEntry.getFirstName();
                String droppedStudentLastName = droppedStudentEntry.getLastName();
                
                jTextArea4.append(droppedStudentName +", " +droppedStudentLastName + " has been dropped from " +courseToDrop+ ", " +currentSemester + "\n");
                ScheduleQueries.dropScheduleByCourse(currentSemester, courseToDrop);
                
            }
                    
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        

    }//GEN-LAST:event_adminDropCourseButtonActionPerformed

    private void studentDropCourseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentDropCourseButtonActionPerformed
        // TODO add your handling code here:
        String studentNameToDrop = (String)selectStudentDropCouseBox.getSelectedItem();
        String studentIDTodrop = StudentQueries.getStudentIDfromFirstName(studentNameToDrop);
        String courseToDrop = (String)selectDropCourseBox.getSelectedItem();
        
        connection = DBConnection.getConnection();
        try
        {
            getDroppedStudents = connection.prepareStatement("select COURSECODE, STATUS from app.scheduleentry where STUDENTID like (?) and SEMESTER like (?)");
            getDroppedStudents.setString(1, studentIDTodrop);
            getDroppedStudents.setString(2, currentSemester);
            
            resultDroppedStudent = getDroppedStudents.executeQuery();
            
            while (resultDroppedStudent.next())
            {
                String droppedCourseCode= resultDroppedStudent.getString(1);
                String droppedStudentStatus = resultDroppedStudent.getString(2);
                StudentEntry droppedStudentEntry = StudentQueries.getStudent(studentIDTodrop);
                String droppedStudentName = droppedStudentEntry.getFirstName();
                String droppedStudentLastName = droppedStudentEntry.getLastName();
                
                
                
                if (droppedStudentStatus.equals("W"))
                {
                    
                    ScheduleQueries.dropStudentScheduleByCourse(currentSemester, studentIDTodrop, courseToDrop);
                    System.out.print("W");
                    jTextArea2.append(droppedStudentName +", " +droppedStudentLastName + " has dropped " +courseToDrop+ ", " +currentSemester + "from their schedule\n");
                    
                    
                }
                else
                {
                    //dropInfo.add(studentSemester +","+ studentCourseCode +","+ studentStatus);
                    ScheduleQueries.dropStudentScheduleByCourse(currentSemester, studentIDTodrop, courseToDrop);
                    System.out.print("S");
                    
                    ArrayList<ScheduleEntry> waitlistedStudents = ScheduleQueries.getWaitlistedStudentsByCourse(currentSemester, courseToDrop);
                    
                    int getNumberofWaitlistedStudents= waitlistedStudents.size();
                    System.out.print(getNumberofWaitlistedStudents);
                    
                        
                    if (getNumberofWaitlistedStudents == 0)
                    {
                        CourseQueries.addSeat(currentSemester, courseToDrop);
                        jTextArea2.append(studentNameToDrop+ " has dropped " + courseToDrop + ", "+currentSemester+ " from their schedule\n");
                        jTextArea2.append("A seat has been added to " + courseToDrop + ", "+currentSemester+ "\n");
                        
                        
                    }
                    else
                    {
                        ScheduleEntry firstStudent = waitlistedStudents.get(0);
                        String firstStudentID = firstStudent.getStudentID();
                        StudentEntry firstStudentNameEntry = StudentQueries.getStudent(firstStudentID );
                        String firstStudentName = firstStudentNameEntry.getFirstName();
                        String firstLastName = firstStudentNameEntry.getLastName();
                        ScheduleQueries.updateScheduleEntry(currentSemester, firstStudent);
                        
                        jTextArea2.append( studentNameToDrop + " has dropped " + courseToDrop + ", "+currentSemester+ " from their schedule\n");
                        jTextArea2.append(firstStudentName  +", "+firstLastName + " has been scheduled for "+ courseToDrop + ", "+ currentSemester +"\n");
                        
                    }
                    
                    
                        
                }
                
                
                
            }
                    
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        rebuildStudentComboBoxes();
        rebuildStudentComboBoxesCurrSemester();
        
    }//GEN-LAST:event_studentDropCourseButtonActionPerformed
/**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
   
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addCourseCodeTextField;
    private javax.swing.JTextField addCourseDescriptionTextField;
    private javax.swing.JSpinner addSeatsSpinner;
    private javax.swing.JLabel addSemesterStatusLabel;
    private javax.swing.JButton addSemesterSubmitButton;
    private javax.swing.JTextField addSemesterTextfield;
    private javax.swing.JTextField addStudentFirstNameTextField;
    private javax.swing.JTextField addStudentIDTextField;
    private javax.swing.JButton addStudentInfoButton;
    private javax.swing.JTextField addStudentLastNameTextField;
    private javax.swing.JButton adminDropCourseButton;
    private javax.swing.JButton changeSemesterButton;
    private javax.swing.JComboBox<String> courseToBeDroppedBox;
    private javax.swing.JComboBox<String> currentSemesterComboBox;
    private javax.swing.JLabel currentSemesterLabel;
    private javax.swing.JTable displayCoursesTable;
    private javax.swing.JTable displayScheduleTable;
    private javax.swing.JTabbedPane dropCoursesTab;
    private javax.swing.JButton dropStudentButton;
    private javax.swing.JComboBox<String> jBoxSelectCourse;
    private javax.swing.JComboBox<String> jBoxSelectStudent;
    private javax.swing.JButton jButtonDisplayCourses;
    private javax.swing.JButton jButtonDisplaySchedule;
    private javax.swing.JButton jButtonSubmitCourse;
    private javax.swing.JButton jButtonSubmitCoursesSchedule;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelCourseAdded;
    private javax.swing.JLabel jLabelCourseCode;
    private javax.swing.JLabel jLabelCourseDescription;
    private javax.swing.JLabel jLabelCurrentSemester;
    private javax.swing.JLabel jLabelFirstName;
    private javax.swing.JLabel jLabelLastName;
    private javax.swing.JLabel jLabelSeats;
    private javax.swing.JLabel jLabelSelectCourse;
    private javax.swing.JLabel jLabelSelectStudent;
    private javax.swing.JLabel jLabelSelectStudent2;
    private javax.swing.JLabel jLabelSelectStudent3;
    private javax.swing.JLabel jLabelStudentAdded;
    private javax.swing.JLabel jLabelStudentID;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JComboBox<String> selectCourseStudentsBox;
    private javax.swing.JComboBox<String> selectDropCourseBox;
    private javax.swing.JLabel selectDropCourseLabel;
    private javax.swing.JComboBox<String> selectDropStudentBox;
    private javax.swing.JComboBox<String> selectStudentBoxDisplay;
    private javax.swing.JLabel selectStudentDropCourseLabel;
    private javax.swing.JComboBox<String> selectStudentDropCouseBox;
    private javax.swing.JButton selectedCourseStudentsDispalyButton;
    private javax.swing.JButton studentDropCourseButton;
    private javax.swing.JTable studentsInCourseTable;
    private javax.swing.JLabel waitlistOrScheduledLabel;
    private javax.swing.JTable waitlistedStudentsTable;
    // End of variables declaration//GEN-END:variables
}
