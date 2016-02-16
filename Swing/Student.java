/**
 * My Homework 5 Solution to build a Student Directory App.
 * @author Yuanyuan Ma yuanyuam
 */

public class Student implements Cloneable {
    /**
     * the andrew id of the student.
     */
    private String andrewId;

    /**
     * the first name of the student.
     */
    private String firstName;

    /**
     * the last name of the student.
     */
    private String lastName;

    /**
     * the phone number of the student.
     */
    private String phoneNumber;

    /**
     * Constructor with a parameter.
     * @param andrewId the andrew id of the student
     */
    public Student(String andrewId) {
        super();
        this.andrewId = andrewId;
    }

    /**
     * Constructor with parameters.
     * @param andrewId andrew id
     * @param firstName first name
     * @param lastName last name
     * @param phoneNumber phone number
     */
    public Student(String andrewId, String firstName, String lastName,
            String phoneNumber) {
        super();
        this.andrewId = andrewId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Getter for andrewId.
     * @return the andrew id of the student
     */
    public String getAndrewId() {
        return andrewId;
    }

    /**
     * Getter for firstName.
     * @return the first name of the student
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter for lastName.
     * @return the last name of the student
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter for phoneNumber.
     * @return the phoneNumber of the student
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Setter for firstName.
     * @param firstName first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Setter for lastName.
     * @param lastName last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Setter for phoneNumber.
     * @param phoneNumber phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @Override Override toString().
     * @return String
     */
    public String toString() {
        return "Andrew ID: " + this.getAndrewId() + ", Phone Number: " + this.getPhoneNumber();
    }

    /**
     * Override the clone().
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
