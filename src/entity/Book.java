package entity;

public class Book
{
    private int id_book;
    private String tittle;
    private double price;
    private String release_data;
    private int fk_author;

    public Book() {
    }

    public Book(int id_book, String tittle, double price, String release_data, int fk_author) {
        this.id_book = id_book;
        this.tittle = tittle;
        this.price = price;
        this.release_data = release_data;
        this.fk_author = fk_author;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRelease_data() {
        return release_data;
    }

    public void setRelease_data(String release_data) {
        this.release_data = release_data;
    }

    public int getFk_author() {
        return fk_author;
    }

    public void setFk_author(int fk_author) {
        this.fk_author = fk_author;
    }

    @Override
    public String toString() {
        return "book: " +
                "id_book=" + id_book +
                ", tittle='" + tittle + '\'' +
                ", price=" + price +
                ", release_data='" + release_data + '\'' +
                ", fk_author=" + fk_author;
    }
}
