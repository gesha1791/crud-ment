package chaplinskiy.crud.model;

public class Specialty {
    private Long id;
    private String name;

    public Specialty(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Specialty(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Specialty{" +
                "id=" + id +
                ", specialty='" + name + '\'' +
                '}';
    }
}
