package entity;

public class Book
{
    private int id_book;
    private String tittle;
    private double price;
    private int release_date;
    private int fk_author;

    public Book() {
    }

    public Book(int id_book, String tittle, double price, int release_date, int fk_author) {
        this.id_book = id_book;
        this.tittle = tittle;
        this.price = price;
        this.release_date = release_date;
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

    public int getRelease_date() {
        return release_date;
    }

    public void setRelease_date(int release_date) {
        this.release_date = release_date;
    }

    public int getFk_author() {
        return fk_author;
    }

    public void setFk_author(int fk_author) {
        this.fk_author = fk_author;
    }

    @Override
    public String toString() {
        return "book: --> " +
                "id_book: " + id_book +
                ", tittle: " + tittle +
                ", price: " + price +
                ", release_date: '" + release_date +
                ", fk_author: " + fk_author;
    }
}
