import controller.AuthorController;
import controller.BookController;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args)
    {
        AuthorController authorController = new AuthorController();
        BookController bookController = new BookController();

        String optionPrincipalMenu;
        String authorOptionMenu;
        String bookOptionMenu;

        do
        {
            optionPrincipalMenu = JOptionPane.showInputDialog("""
                    1 - Authors Menu
                    2 - Books Menu
                    3 - Exit
                    """);

            switch (optionPrincipalMenu)
            {
                case "1":
                        do
                        {
                            authorOptionMenu = JOptionPane.showInputDialog("""
                                1 - List All Authors
                                2 - Search Author By ID
                                3 - Add New Author
                                4 - Update Author
                                5 - Delete Author
                                6 - Exit Author Menu
                                """);

                            switch (authorOptionMenu)
                            {
                                case "1":
                                    authorController.listAuthors();
                                    break;

                                case "2":
                                    authorController.findById();
                                    break;

                                case "3":
                                    authorController.create();
                                    break ;

                                case "4":
                                    authorController.update();
                                    break;

                                case "5":
                                    authorController.delete();
                                    break;

                            }
                        }while (!authorOptionMenu.equals("6"));

                break;

                case "1":
                        do
                        {
                            bookOptionMenu = JOptionPane.showInputDialog("""
                                    1 - List All Books
                                    2 - Search Book By ID
                                    3 - Search Book By Tittle
                                    4 - Search Book By Author Name
                                    5 - Add New Book
                                    6 - Update Book
                                    7 - Delete Book
                                    8 - Exit Book Menu
                                    """);

                            switch (bookOptionMenu)
                            {
                                case "1":
                                    bookController.listBooks();
                                    break;

                                case "2":
                                    bookController.findById();
                                    break;

                                case "3":
                                    bookController.findByTittle();
                                    break;

                                case "4":
                                    bookController.findByAuthor();
                                    break;

                                case "5":
                                    bookController.create();
                                    break;

                                case "6":
                                    bookController.update();
                                    break;

                                case "7":
                                    bookController.delete();
                                    break;

                            }
                        }while (!bookOptionMenu.equals("8"));
                break;
            }
        }while(!optionPrincipalMenu.equals("3"));
    }
}