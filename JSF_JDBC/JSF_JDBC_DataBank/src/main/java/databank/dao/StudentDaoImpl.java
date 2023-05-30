/******************************************************
 * File:  StudentDaoImpl.java Course materials (22F) CST8277
 *
 * @author Teddy Yap (modified by Dongkwan Kim)
 * @author Shariar (Shawn) Emami
 * @author (original) Mike Norman
 */
package databank.dao;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import databank.model.StudentPojo;

@SuppressWarnings("unused")
/**
 * Description:  Implements the C-R-U-D API for the database
 */
//TODO don't forget this object is a managed bean with a application scope
@Named
@ApplicationScoped
public class StudentDaoImpl implements StudentDao, Serializable {
	/** Explicitly set serialVersionUID */
	private static final long serialVersionUID = 1L;

	private static final String DATABANK_DS_JNDI = "java:app/jdbc/databank";
	private static final String READ_ALL = "select * from student";
	private static final String READ_STUDENT_BY_ID = "select * from student where id = ?";
	private static final String INSERT_STUDENT = "insert into student(last_name, first_name, email, phone, level, program, created) values (?, ?, ?, ?, ?, ?, now())";
//	private static final String INSERT_STUDENT = "insert into student(last_name, first_name, email, phone, level, program) values (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STUDENT_ALL_FIELDS = "update student set last_name = ?, first_name = ?, email = ?, phone = ?, level = ?, program = ? where id = ?";
	private static final String DELETE_STUDENT_BY_ID = "delete from student where id = ?";

	@Inject
	protected ExternalContext externalContext;

	private void logMsg(String msg) {
		((ServletContext) externalContext.getContext()).log(msg);
	}

	@Resource(lookup = DATABANK_DS_JNDI)
	protected DataSource databankDS;

	protected Connection conn;
	protected PreparedStatement readAllPstmt;
	protected PreparedStatement readByIdPstmt;
	protected PreparedStatement createPstmt;
	protected PreparedStatement updatePstmt;
	protected PreparedStatement deleteByIdPstmt;

	@PostConstruct
	protected void buildConnectionAndStatements() {
		try {
			logMsg("building connection and stmts");
			conn = databankDS.getConnection();
			readAllPstmt = conn.prepareStatement(READ_ALL);
			createPstmt = conn.prepareStatement(INSERT_STUDENT, RETURN_GENERATED_KEYS);
			//TODO Initialize other PreparedStatements
			 
			readByIdPstmt = conn.prepareStatement(READ_STUDENT_BY_ID);               //assignment01
			updatePstmt = conn.prepareStatement(UPDATE_STUDENT_ALL_FIELDS);          // assignment01
			deleteByIdPstmt = conn.prepareStatement(DELETE_STUDENT_BY_ID);           //assignment01
			
			
			
		} catch (Exception e) {
			logMsg("something went wrong getting connection from database:  " + e.getLocalizedMessage());
		}
	}

	@PreDestroy
	protected void closeConnectionAndStatements() {
		try {
			logMsg("closing stmts and connection");
			readAllPstmt.close();
			createPstmt.close();
			//TODO Close other PreparedStatements
			readByIdPstmt.close();                  //assignment01
			updatePstmt.close();                    //assignment01
			deleteByIdPstmt.close();                //assignment01
			
			
			conn.close();
		} catch (Exception e) {
			logMsg("something went wrong closing stmts or connection:  " + e.getLocalizedMessage());
		}
	}

	@Override
	public List<StudentPojo> readAllStudents() {
		logMsg("reading all students");
		List<StudentPojo> students = new ArrayList<>();
		try (ResultSet rs = readAllPstmt.executeQuery();) {

			while (rs.next()) {
				StudentPojo newStudent = new StudentPojo();
				newStudent.setId(rs.getInt("id"));
				newStudent.setLastName(rs.getString("last_name"));
				//TODO Complete the student initialization
				newStudent.setFirstName(rs.getString("first_name"));      //assignment01
				newStudent.setEmail(rs.getString("email"));
				newStudent.setPhoneNumber(rs.getString("phone"));
				newStudent.setLevel(rs.getString("level"));
				newStudent.setProgram(rs.getString("program"));              //assignment01
				newStudent.setCreated(rs.getTimestamp("created").toLocalDateTime());
				
	//			logMsg(rs.getString("last_name"));
				
				students.add(newStudent);
			}
		
		} catch (SQLException e) {
			logMsg("something went wrong accessing database:  " + e.getLocalizedMessage());
		}
		
		return students;

	}

	@Override
	public StudentPojo createStudent(StudentPojo student) {
		logMsg("creating a student");
		//TODO Complete the insertion of a new student
		//TODO Be sure to use try-and-catch statement
		try {
				createPstmt.setInt(1, student.getId());
				createPstmt.setString(1, student.getLastName());
				createPstmt.setString(2, student.getFirstName());
				createPstmt.setString(3, student.getEmail());
				createPstmt.setString(4, student.getPhoneNumber());
				createPstmt.setString(5, student.getLevel());
				createPstmt.setString(6, student.getProgram());
				createPstmt.execute();
		}
		

		
		catch (SQLException e) {
			logMsg("something went wrong adding new student : " + e.getLocalizedMessage());
		}
		
		                                                                     //assignment01
		
		return null;
	}

	@Override
	public StudentPojo readStudentById(int studentId) {
		logMsg("read a specific student");
		//TODO Complete the retrieval of a specific student by its id
		//TODO Be sure to use try-and-catch statement
		StudentPojo spj = new StudentPojo();              //assignment01
		try {
			readByIdPstmt.setLong(1, studentId);
			ResultSet rs = readByIdPstmt.executeQuery();
			if (rs.next()) {
				spj.setId(rs.getInt("id"));
				spj.setLastName(rs.getString("last_name"));
				spj.setFirstName(rs.getString("first_name"));
				spj.setEmail(rs.getString("email"));
				spj.setPhoneNumber(rs.getString("phone"));
				spj.setLevel(rs.getString("level"));
				spj.setProgram(rs.getString("program"));
			}
		}
		catch (SQLException e) {
			logMsg("Something went wrong accessing database : " + e.getLocalizedMessage());
		}
		
		
		return spj;                                   //assignment01
	//	return null;
	}

	@Override
	public void updateStudent(StudentPojo student) {
		logMsg("updating a specific student");
		//TODO Complete the update of a specific student
		//TODO Be sure to use try-and-catch statement
		try {                                                              //assignment01
			updatePstmt.setString(1, student.getLastName());
			updatePstmt.setString(2, student.getFirstName());
			updatePstmt.setString(3, student.getEmail());
			updatePstmt.setString(4, student.getPhoneNumber());
			updatePstmt.setString(5, student.getLevel());
			updatePstmt.setString(6, student.getProgram());
			updatePstmt.setLong(7, student.getId());
	//		updatePstmt.setTimestamp(7, student.getCreated().toLocalDateTime());
			updatePstmt.executeUpdate();			
		}
		catch (SQLException e) {
			logMsg("Something went wrong updating database : " + e.getLocalizedMessage());
		}                                                                                   //assignment01
		
		
	}

	@Override
	public void deleteStudentById(int studentId) {
		logMsg("deleting a specific student");
		//TODO Complete the deletion of a specific student
		//TODO Be sure to use try-and-catch statement
		try {                                                          //assignment01
			deleteByIdPstmt.setLong(1, studentId);
			deleteByIdPstmt.execute();
		}
		catch (SQLException e) {
			logMsg("Something went wrong deleting database : " + e.getLocalizedMessage());
		}                                                                                    //assignment01
		  
	}

}