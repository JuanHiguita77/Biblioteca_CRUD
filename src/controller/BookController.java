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
            String list = "--- AUTHORS LIST --- \n";

            for (Object author: objectList){
                Author authorEntity = (Author) author;
                list += authorEntity.toString() + "\n";
            }

            return list;
        }

        public static void listAuthors()
        {
            AuthorModel authorModel = new AuthorModel();

            JOptionPane.showMessageDialog(null, listAll(authorModel.list()));
        }


        //Solo listamos todos los autores
        public static String listAllAuthors()
        {
            AuthorModel authorModel = new AuthorModel();
            String listAuthors = "AUTHORS LIST \n";

            for (Object author: authorModel.list()){

                Author authorNew = (Author) author;
                listAuthors += authorNew.toString() + "\n";
            }

            return listAuthors;
        }

        public static void delete()
        {
            AuthorModel authorModel = new AuthorModel();

            String authorsLists = listAllAuthors();
            int id = Integer.parseInt(JOptionPane.showInputDialog(authorsLists + "Input the Author ID"));

            //Buscamos primero si existe
            Author author = authorModel.findById(id);

            if (author == null)
            {
                JOptionPane.showInputDialog(null,"Unknown Author");
            }
            else
            {
                int confirm = JOptionPane.showConfirmDialog(null,"Are you sure to delete? -- > " + author.toString());

                if (confirm == 1)
                {
                    JOptionPane.showMessageDialog(null,"Stopped!");
                }
                else
                {
                    authorModel.delete(author);
                    JOptionPane.showMessageDialog(null, "Deleted sucessfully! --> " + author.toString());
                }
            }
        }

        public static void update()
        {
            AuthorModel authorModel = new AuthorModel();

            String authorsList = listAllAuthors();

            int idUpdated = Integer.parseInt( JOptionPane.showInputDialog(authorsList + "Enter Author ID to edit"));

            Author author = authorModel.findById(idUpdated);

            if (author == null)
            {
                JOptionPane.showMessageDialog(null, "Unknown Author");
            }
            else
            {
                String name = JOptionPane.showInputDialog(null, "Input the author name or leave default name", author.getName());
                String nationality = JOptionPane.showInputDialog("Input the author Nationality ", author.getNationality());

                author.setName(name);
                author.setNationality(nationality);

                authorModel.update(author);
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
            AuthorModel authorModel = new AuthorModel();
            Author author = new Author();

            int id_author = Integer.parseInt(JOptionPane.showInputDialog("Input the Author ID to search"));

            Author authorReceived =  authorModel.findById(id_author);

            if (authorReceived == null)
            {
                JOptionPane.showMessageDialog(null, "ID Author not Found");
            }
            else
            {
                author.setId_author(authorReceived.getId_author());
                author.setName(authorReceived.getName());
                author.setNationality(authorReceived.getNationality());

                JOptionPane.showMessageDialog(null, author.toString());
            }
        }
}
