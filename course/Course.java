package course;

public class Course {
    private String id;
    private String name;
    private String credits;

    public Course (String id, String name, String credits) {
        super();
        this.id = id;
        this.name= name;
        this.credits = credits;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "course.Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", credits='" + credits + '\'' +
                '}';
    }
}
