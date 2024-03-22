package controller;

import entity.Author;
import entity.Book;
import model.AuthorModel;
import model.BookModel;

import javax.swing.JOptionPane;
import java.util.List;

public class BookController
{
        //Listar factorizado para cualquier objeto de lista
        public static String listAll(List<Object> objectList)
        {
            String listBooks = "--- BOOKS LIST --- \n";

            for (Object book: objectList){
                Book bookEntity = (Book) book;
                listBooks += bookEntity.toString() + "\n";
            }

            return listBooks;
        }

        public static void listBooks()
        {
            BookModel bookModel = new BookModel();

            JOptionPane.showMessageDialog(null, listAll(bookModel.list()));
        }

        //Solo listamos todos los autores
        public static String listAllBooks()
        {
            BookModel bookModel = new BookModel();

            String listBooks = "BOOKS LIST \n";

            for (Object author: bookModel.list()){

                Author authorNew = (Author) author;
                listBooks += authorNew.toString() + "\n";
            }

            return listBooks;
        }

        public static void delete()
        {
            BookModel bookModel = new BookModel();

            String booksList = listAllBooks();
            int id = Integer.parseInt(JOptionPane.showInputDialog(booksList + "Input the Book ID"));

            //Buscamos primero si existe
            Book book = bookModel.findById(id);

            if (book == null)
            {
                JOptionPane.showInputDialog(null,"Unknown Book");
            }
            else
            {
                int confirm = JOptionPane.showConfirmDialog(null,"Are you sure to delete? -- > " + book.toString());

                if (confirm == 1)
                {
                    JOptionPane.showMessageDialog(null,"Stopped!");
                }
                else
                {
                    bookModel.delete(book);
                    JOptionPane.showMessageDialog(null, "Deleted sucessfully! --> " + book.toString());
                }
            }
        }

        public static void update()
        {
            BookModel bookModel = new BookModel();

            String booksList = listAllBooks();

            int idUpdated = Integer.parseInt( JOptionPane.showInputDialog(booksList + "Enter Book ID to edit"));

            Book book = bookModel.findById(idUpdated);

            if (book == null)
            {
                JOptionPane.showMessageDialog(null, "Unknown Book");
            }
            else
            {
                String tittle = JOptionPane.showInputDialog(null, "Input the tittle name or leave default tittle", book.getTittle());
                String release_data = JOptionPane.showInputDialog(null, "Input the tittle name or leave default tittle", book.getRelease_data());
                double price = Double.parseDouble( JOptionPane.showInputDialog("Input the price or leave default price ", book.getPrice()));
                int id_author = Integer.parseInt(JOptionPane.showInputDialog("Input the ID author or leave the default", book.getFk_author()));

                book.setTittle(tittle);
                book.setPrice(price);
                book.setRelease_data(release_data);
                book.setFk_author(id_author);

                bookModel.update(book);
            }
        }

        public static void create(){

            AuthorController authorController = new AuthorController();
            BookModel bookModel = new BookModel();

            Book book = new Book();

            String tittle = JOptionPane.showInputDialog("Insert tittle book");
            Double price = Double.parseDouble(JOptionPane.showInputDialog("Insert book price"));
            String release_data = JOptionPane.showInputDialog("Insert book release_data");

            authorController.listAuthors();
            int fk_author = Integer.parseInt(JOptionPane.showInputDialog("Choose an ID author"));

            book.setTittle(tittle);
            book.setPrice(price);
            book.setRelease_data(release_data);
            book.setFk_author(fk_author);

            book = (Book) bookModel.create(book);

            JOptionPane.showMessageDialog(null, book.toString());
        }

        public static void findById()
        {
            BookModel bookModel = new BookModel();
            Book book = new Book();

            int id_book = Integer.parseInt(JOptionPane.showInputDialog("Input the Book ID to search"));

            Book bookReceived = bookModel.findById(id_book);

            if (bookReceived == null)
            {
                JOptionPane.showMessageDialog(null, "ID Book not Found");
            }
            else
            {
                book.setId_book(bookReceived.getId_book());
                book.setFk_author(bookReceived.getFk_author());
                book.setTittle(bookReceived.getTittle());
                book.setPrice(bookReceived.getPrice());
                book.setRelease_data(bookReceived.getRelease_data());

                JOptionPane.showMessageDialog(null, book.toString());
            }
        }

        public static void findByTittle()
        {
            BookModel bookModel = new BookModel();

            String searchTittle = JOptionPane.showInputDialog("Input search tittle");

            String listBooks = "🤷‍LIST BOOKS FINDED... \n";

            for (Object book: bookModel.findByName(searchTittle))
            {
                Book newBook = (Book) book;
                listBooks += newBook.toString() + "\n";
            }

            JOptionPane.showMessageDialog(null ,listBooks);
        }

        public static void findByAuthor()
        {
            BookModel bookModel = new BookModel();

            String searchAuthorName = JOptionPane.showInputDialog("Input Author name");

            String listBooks = "🤷‍LIST BOOKS FINDED... \n";

            for (Object book: bookModel.findByAuthor(searchAuthorName))
            {
                Book newBook = (Book) book;
                listBooks += newBook.toString() + "\n";
            }

            JOptionPane.showMessageDialog(null ,listBooks);
        }

}
