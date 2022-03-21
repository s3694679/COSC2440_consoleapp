package enrolment;

public interface StudentEnrolmentManager {
    public void add (StudentEnrolment enrolment);
    public void update(StudentEnrolment enrolment);
    public void delete (StudentEnrolment enrolment);
    public void getOne();
    public void getAll();
}
