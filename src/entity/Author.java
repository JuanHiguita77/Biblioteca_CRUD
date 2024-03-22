package entity;

public class Author
{
    private int id_author;
    private String name;
    private String nationality;

    public Author() {
    }

    public Author(int id_author, String name, String nationality) {
        this.id_author = id_author;
        this.name = name;
        this.nationality = nationality;
    }

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "author -->" +
                "id_author=" + id_author +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'';
    }
}
