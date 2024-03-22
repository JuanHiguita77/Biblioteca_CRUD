package controller;

import entity.Author;
import entity.Coder;
import model.AuthorModel;

import javax.swing.JOptionPane;
import java.util.List;

public class AuthorController
{
    public static void listAuthors()
    {
        AuthorModel authorModel = new AuthorModel();

        JOptionPane.showMessageDialog(null, listAll(authorModel.list()));
    }

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

    //Solo listamos todos los autores
    public static String listAllAuthors()
    {
        AuthorModel objModel = new AuthorModel();
        String listCoders = "🤷‍♂️ CODER LIST \n";

        for (Object iterador: objModel.list()){
            //Convertimos del Object a Coder
            Coder objCoder = (Coder) iterador;
            listCoders += objCoder.toString() + "\n";
        }

        return listCoders;
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


/*
    public static void create(){
        //Usamos el modelo
        CoderModel objCoderModel = new CoderModel();

        //Pedimos los datos al usuario
        String name = JOptionPane.showInputDialog("Insert name");
        String clan = JOptionPane.showInputDialog("Insert clan");
        int age = Integer.parseInt(JOptionPane.showInputDialog("Insert age"));

        //Creamos una instancia de coder
        Coder objCoder = new Coder();
        objCoder.setClan(clan);
        objCoder.setAge(age);
        objCoder.setName(name);

        //Llamamos al método de insercción y guardamos el objeto que retorna en coder previamente instanciado, debemos castearlo.
        objCoder = (Coder) objCoderModel.insert(objCoder);

        JOptionPane.showMessageDialog(null, objCoder.toString());
    }


    public static void findByName()
    {
        CoderModel coderModel = new CoderModel();

        String searchName = JOptionPane.showInputDialog("Ingrese el nombre a buscar");

        String listCoders = "🤷‍LISTA DE CODERS ENCONTRADOS \n";

        for (Object iterador: coderModel.findByName(searchName))
        {
            //Convertimos del Object a Coder
            Coder objCoder = (Coder) iterador;
            listCoders += objCoder.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null ,listCoders);
    }

    public static void update()
    {
        CoderModel coderModel = new CoderModel();

        String listCoders = getCodersList();

        int idUpdated = Integer.parseInt( JOptionPane.showInputDialog(listCoders + "Enter ID coder to edit"));

        Coder coder = coderModel.findById(idUpdated);

        if (coder == null)
        {
            JOptionPane.showMessageDialog(null, "No se encontró el coder");
        }
        else
        {
            String name = JOptionPane.showInputDialog(null, "Ingrese el nombre del coder de lo contrario dejar tal cual", coder.getName());
            int age = Integer.parseInt( JOptionPane.showInputDialog("Ingrese el nombre del coder de lo contrario dejar tal cual", String.valueOf(coder.getAge())));//Transformar a string de entero
            String clan = JOptionPane.showInputDialog("Ingrese el nombre del coder de lo contrario dejar tal cual", coder.getClan());

            coder.setName(name);
            coder.setAge(age);
            coder.setClan(clan);

            coderModel.update(coder);
        }
    }*/
}
