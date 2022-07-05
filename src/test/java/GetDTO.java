public class GetDTO {
    private String id;
    private String name;
    private String location;
    private String position;
    private String age;

    public GetDTO(String id, String name, String location, String position, String age) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.position = position;
        this.age = age;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "GetDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", position='" + position + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
