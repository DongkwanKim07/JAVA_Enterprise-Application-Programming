/********************************************************************************************************
 * File:  GenerateRandomStudentRecords.java Course materials (22F) CST8277
 * 
 * @date 2022 09
 * @author Dongkwan Kim

 */
package jdbccmd;

import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.Instant;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jdbccmd.LoggingOutputStream.LogLevel;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.ParameterException;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DefaultClassInfoStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Helper class that generates random Students and writes them to the database
 */
@Command(description = "Generate random students", name = "jdbccmd.GenerateRandomStudentRecords")
public class GenerateRandomStudentRecords {
	
	//Get the current class type, in this case "jdbccmd.GenerateRandomStudentRecords"
	protected static final Class<?> MY_KLASSNAME = MethodHandles.lookup().lookupClass();
	
	//Create a logger based on the type of the current class
	private static final Logger logger = LoggerFactory.getLogger(MY_KLASSNAME);

	//Error message to be customized for CMD display
	protected static final String CMDLINE_PARSING_ERROR_MSG = "cmdLine parsing error:  {}";
	
	//Message to be customized for time taken to complete the current task.
	protected static final String ELAPSED_TIME_MSG = "Elapsed time = {} ms";
	
	//Empty the table before adding new content to it.
	private static final String TRUNC_STUDENT = "TRUNCATE TABLE STUDENT";
	
	//Insert statement for student table
	//TODO add the level and program fields into the INSERT statement
	protected static final String INSERT_STUDENT = "INSERT INTO STUDENT(LAST_NAME, FIRST_NAME, EMAIL, PHONE, LEVEL, PROGRAM, CREATED) VALUES (?, ?, ?, ?, ?, ?, now())";  //lab01

	
	public static void main(String[] args) {
		
		//Create an instance of CmdLineOptions class which has the variables we need from CMD.
		CmdLineOptions cmdLineOptions = new CmdLineOptions();
		
		//Pass the instance of CmdLineOptions class to picocli.CommandLine to automatically pass CMD arguments.
		CommandLine cmdLine = new CommandLine(cmdLineOptions);
		
		//Pass the name of the class which is operating CommandLine instance.
		cmdLine.setCommandName(MY_KLASSNAME.getName());
		
		try {
			
			//Parse incoming arguments 
			cmdLine.parseArgs(args);
			
		} catch (ParameterException e) {
			
			//Let user know the parsing has failed.
			logger.error(CMDLINE_PARSING_ERROR_MSG, e.getLocalizedMessage());
			logCmdLineUsage(e.getCommandLine(), LogLevel.ERROR);
			System.exit(-1);
			
		}
		
		if (cmdLineOptions.helpRequested) {
			
			//Display help
			logCmdLineUsage(cmdLine, LogLevel.INFO);
			
		} else {
			
			//Generate data for DB
			generateStudents(cmdLineOptions.jdbcUrl, cmdLineOptions.username, cmdLineOptions.password,
					cmdLineOptions.count);
			
		}
		
	}

	public static void generateStudents(String jdbcUrl, String username, String password, int genCount) {
		Instant startTime = Instant.now();

		//Create a properties object to store the password and username
		Properties dbProps = new Properties();
		dbProps.put("user", username);
		dbProps.put("password", password);

		//TODO Complete the try with resource below.
		 
        try ( //try-with-resources:  auto-close'ing resources makes catch-finally logic easier
       
        	//Create a new connection using jdbcUrl and the db properties object above.
        	Connection connection = DriverManager.getConnection(jdbcUrl, dbProps);
    		
           	//TODO Create a PreparedStatement for INSERT_STUDENT.  We also want the ability to get the
            //generated id back - use Statement.RETURN_GENERATED_KEYS as second parameter
          
        		PreparedStatement pstmtInsert = connection.prepareStatement(INSERT_STUDENT, Statement.RETURN_GENERATED_KEYS); //lab01
        		
        	//	PreparedStatement pstmtInsert = null;

        	//	pstmtInsert = connection.preparedStatement(INSERT_STUDENT, Statement.RETURN_GENERATED_KEYS);
        		
        		
       		//TODO Create a PreparedStatement for TRUNC_STUDENT.
        		
        	//	PreparedStatement pstmtTrunc = connection.prepareStatement(TRUNC_STUDENT, Statement.RETURN_GENERATED_KEYS);
        		
        		PreparedStatement pstmtTrunc = connection.prepareStatement(TRUNC_STUDENT); //lab01
        		
        //    PreparedStatement pstmtTrunc = null;
        		
        ) {
        	
            //TODO Don't forget to execute the truncate statement here before performing inserts
        	
        	pstmtTrunc.execute();                   //lab01
    
			//PODAM - POjo DAta Mocker
			PodamFactory factory = new PodamFactoryImpl();
			ClassInfoStrategy classInfoStrategy = factory.getClassStrategy();
			
			//No need to generate primary key (id), database will do that for us
			((DefaultClassInfoStrategy) classInfoStrategy).addExcludedField(Student.class, "id");
			factory.getStrategy().addOrReplaceTypeManufacturer(String.class, new StudentManufacturer());
			
			for (int cnt = 0, numRandomStudents = genCount; cnt < numRandomStudents; cnt++) {
				
				Student randomStudent = factory.manufacturePojoWithFullData(Student.class);
				
				//Write randomStudent to database
				//Setters are chosen based on the data type, setString, setInt, setDouble, etc.
				//The number as first argument represent the order of the '?' in the INSERT_STUDENT statement.
				pstmtInsert.setString(1, randomStudent.getLastName());
				pstmtInsert.setString(2, randomStudent.getFirstName());
				pstmtInsert.setString(3, randomStudent.getEmail());
				pstmtInsert.setString(4, randomStudent.getPhoneNumber());
				pstmtInsert.setString(5, randomStudent.getLevel());               //lab01
				pstmtInsert.setString(6, randomStudent.getProgram());             //lab01
				
				//TODO Set the level and program fields of the INSERT statement.
				
				//Execute the query, return true if successful
				pstmtInsert.execute();                                            //lab01
				
				//Get the generated keys from DB as the result of INSERT_STUDENT statement.
				//The ResultSet is AutoCloseable so it can be placed in a try-with-resource to be auto closed.
				try (ResultSet generatedKeys = pstmtInsert.getGeneratedKeys()) {
					
					if (generatedKeys.next()) { //If a key is returned
						
						int id = generatedKeys.getInt(1); //Get the key
						
						//Set the key to the student and print it
						randomStudent.setId(id);
						
						logger.debug("created random student \r\n\t{}", randomStudent);
						
					} else {
						
						logger.error("could not retrieve generated PK");
						
					}
					
				}
				
			}
			
		//Catch any exceptions that might have been thrown from Connection, PreparedStatement, and/or ResultSet
		} catch (SQLException e) {
			
			logger.error("something went wrong inserting new student, ", e);
			
		}

		Instant endTime = Instant.now();
		long elapsedTime = Duration.between(startTime, endTime).toMillis();
		logger.info(ELAPSED_TIME_MSG, elapsedTime);
		
	}

	/**
	 * Print the content of the LoggingOutputStream to the logger
	 * 
	 * @param cmdLine - command line instance
	 * @param los     - custom SLF4J instance
	 */
	protected static void logCmdLineUsage(CommandLine cmdLine, LogLevel level) {
		
		//Create a new custom output stream for SLF4J to print to logger.
		LoggingOutputStream los = new LoggingOutputStream(logger, level);
		PrintWriter pw = new PrintWriter(los);
		cmdLine.usage(pw);
		pw.flush();
		los.line();
		
	}

}
