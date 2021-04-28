/**
 * File: NameVornameComparator.java
 *
 * @author Lukas Ingold
 * @author Lorenz Pfäffli
 *
 */

public class NameVornameComparator implements java.util.Comparator<StudentIn> {


    /**
     * Compares two Students by Name then Firstname.
     * @param o1 Student one to be compared to student two.
     * @param o2 Student two to be compared to student one.
     * @return  -1 if o1<o2, 0 if o1=o2, 1 if o1>02.
     */
    public int compare(StudentIn o1, StudentIn o2) {
        if(o1.getName().compareTo(o2.getName())==0){return o1.getVorname().compareTo(o2.getVorname());}
        else return o1.getName().compareTo(o2.getName());
    }
}
