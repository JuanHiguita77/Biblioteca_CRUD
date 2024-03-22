import controller.AuthorController;
import database.ConfigDB;

import javax.swing.JOptionPane;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)
    {
        AuthorController authorController = new AuthorController();

        String optionPrincipalMenu;
        String authorOptionMenu;

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
                                2 - Add New Author
                                3 - Update New Author
                                4 - Delete Author
                                5 - Exit Author Menu
                                """);

                            switch (authorOptionMenu)
                            {
                                case "1":
                                    authorController.listAuthors();
                                    break;

                                case "4":
                                    authorController.delete();
                                    break;
                            }
                        }while (!authorOptionMenu.equals("5"));

                    break;
            }
        }while(!optionPrincipalMenu.equals("3"));


    }
}