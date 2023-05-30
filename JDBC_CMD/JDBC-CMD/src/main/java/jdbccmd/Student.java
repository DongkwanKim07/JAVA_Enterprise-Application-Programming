/*****************************************************
 * File:  Student.java Course materials (22F) CST8277
 * 
 * @date 2022 09
 * @author Dongkwan Kim

 */
package jdbccmd;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Simple POJO for student.
 * 
 * @author Dongkwan Kim

 */
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	protected int id;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String phoneNumber;
	
	protected String level;     //lab01
	protected String program;   //lab01
	//TODO Add the level and program fields
	protected LocalDateTime created;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	public String getLevel() {              //lab01 line 77 to 91
		return level;                       
	}                                       
	
	public void setLevel(String level) {      
		this.level = level;                  
	}
	
	public String getProgram() {
		return program;
	}
	
	public void setProgram(String program) {
		this.program = program;
	}
	

	//TODO Add getter and setter methods for the level and program fields

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	@Override
	public int hashCode() {
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Student)) {
			return false;
		}
		Student other = (Student) obj;
		return id != other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student[id = ").append(id);
		if (firstName != null) {
			builder.append(", fName = ").append(firstName);
		}
		if (lastName != null) {
			builder.append(", lName = ").append(lastName);
		}
		if (email != null) {
			builder.append(", email = ").append(email);
		}
		if (phoneNumber != null) {
			builder.append(", phone# = ").append(phoneNumber);
		}
		
		if (level != null) {                                    //lab01 line 138 to 144
			builder.append(", level = ").append(level);
		}
		
		if (program != null) {
			builder.append(", program = ").append(program);
		}
		
		
		//TODO Append the level and program fields here
		if (created != null) {
			builder.append(", created = ").append(created);
		}
		return builder.append("]").toString();
	}
	
}