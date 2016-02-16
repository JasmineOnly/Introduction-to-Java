import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * My Homework 5 Solution to build a Student Directory App.
 * @author Yuanyuan Ma yuanyuam
 */

public class Directory {
    /**
     *  This Map has the student's Andrew ID as the key and will map from Andrew ID to Student object.
     */
    private HashMap<String, Student> andrewIdMap;

    /**
     *  This Map has the student's firstName as the key and will map from first name to Student object.
     */
    private HashMap<String, List<Student>> firstNameMap;

    /**
     *  This Map has the student's lastName as the key and will map from last name to Student object.
     */
    private HashMap<String, List<Student>> lastNameMap;

    /**
     * Constructor without parameter.
     */
    public Directory() {
        andrewIdMap = new HashMap<String, Student>();
        firstNameMap = new HashMap<String, List<Student>>();
        lastNameMap = new HashMap<String, List<Student>>();
    }

    /**
     * This method is used to add the new student into the three maps.
     * If the given student's Andrew ID is not present in the directory.
     * If the student's Andrew ID is present, throwIllegalArgumentException.
     * @param s Student object
     * @throws IllegalArgumentException throw it when the input is not the legal argument
     */
    public void addStudent(Student s) throws IllegalArgumentException {
        if (s == null || !(s instanceof Student)) {
            throw new IllegalArgumentException();
        }

        String id = s.getAndrewId();
        String fn = s.getFirstName();
        String ln = s.getLastName();
        String pn = s.getPhoneNumber();
        Student ss = new Student(id, fn, ln, pn);

        if (!andrewIdMap.containsKey(s.getAndrewId())) {
            andrewIdMap.put(s.getAndrewId(), ss);
        }

        List<Student> sl;
        if (!firstNameMap.containsKey(s.getFirstName())) {
            sl = new LinkedList<Student>();
        } else {
            sl = firstNameMap.get(s.getFirstName());
        }

        sl.add(ss);
        firstNameMap.put(s.getFirstName(), sl);

        List<Student> sl2;
        if (!lastNameMap.containsKey(s.getLastName())) {
            sl2 = new LinkedList<Student>();
        } else {
            sl2 = lastNameMap.get(s.getLastName());
        }

        sl2.add(ss);
        lastNameMap.put(s.getLastName(), sl2);
    }

    /**
     * This method is used remove the corresponding student object from the three maps if present.
     * If no andrew id matches, throw IllegalArgumentException.
     * @param andrewId the andrew id of a student object
     * @throws IllegalArgumentException throw it when the input is not the legal argument
     */
    public void deleteStudent(String andrewId) throws IllegalArgumentException {
        if (andrewId == null || !(andrewId instanceof String)) {
            throw new IllegalArgumentException();
        }

        if (andrewIdMap.containsKey(andrewId)) {
            Student s = andrewIdMap.get(andrewId);
            andrewIdMap.remove(andrewId);

            if (firstNameMap.containsKey(s.getFirstName())) {
                List<Student> sl = firstNameMap.get(s.getFirstName());
                sl.remove(s);
                firstNameMap.put(s.getFirstName(), sl);
            }

            if (lastNameMap.containsKey(s.getLastName())) {
                List<Student> sl = new LinkedList<Student>();
                sl = lastNameMap.get(s.getLastName());
                sl.remove(s);
                lastNameMap.put(s.getLastName(), sl);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * This method is used to search Student by andrew id.
     * @param andrewId is the andrew id of student
     * @return  the student in the directory
     * @throws IllegalArgumentException throw it when the input is not the legal argument
     */
    public Student searchByAndrewId(String andrewId) throws IllegalArgumentException {
        if (andrewId == null || !(andrewId instanceof String)) {
            throw new IllegalArgumentException();
        }

        if (andrewIdMap.containsKey(andrewId)) {
            return andrewIdMap.get(andrewId);
        } else {
            return null;
        }
    }

    /**
     * This method is used to search Student by first name.
     * @param firstName the first name of student
     * @return List<Student> a list containing all students that match the given first name
     * @throws CloneNotSupportedException throw it when the object can not be cloned
     */
    public List<Student> searchByFirstName(String firstName) throws CloneNotSupportedException {
        if (firstName == null || !(firstName instanceof String)) {
            throw new IllegalArgumentException();
        }

        List<Student> ls = new LinkedList<Student>();
        if (firstNameMap.containsKey(firstName)) {
            ls = firstNameMap.get(firstName);
        }

        List<Student> result = new LinkedList<Student>();
        for (Student s : ls) {
            result.add((Student) s.clone());
        }
        return result;
    }

    /**
     * This method is used to search Student by last name.
     * @param lastName the last name of student
     * @return List<Student> a list containing all students that match the given last name
     * @throws CloneNotSupportedException throw it when the object can not be cloned
     */
    public List<Student> searchByLastName(String lastName) throws CloneNotSupportedException {
        if (lastName == null || !(lastName instanceof String)) {
            throw new IllegalArgumentException();
        }

        List<Student> ls = new LinkedList<Student>();
        if (lastNameMap.containsKey(lastName)) {
            ls = lastNameMap.get(lastName);
        }

        List<Student> result = new LinkedList<Student>();
        for (Student s : ls) {
            result.add((Student) s.clone());
        }
        return result;
    }

    /**
     * This method is used to return the number of students.
     * @return the number of students in the directory
     */
    public int size() {
        return andrewIdMap.size();
    }
}
