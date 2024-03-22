package model;

import database.CRUD;
import database.ConfigDB;
import entity.Author;
import entity.Book;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookModel implements CRUD
{

    @Override
    public Object create(Object object) {

        Connection objConnection = ConfigDB.openConnection();

        Author author = new Author();

        Book book = (Book) object;

        try {

            //El id se pone automatico por la base de datos
            String sql = "INSERT INTO books (tittle, price, release_data, fk_author) VALUES ( ?, ?, ? , ? );";

            PreparedStatement preparedStatement = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, book.getTittle());
            preparedStatement.setDouble(2, book.getPrice());
            preparedStatement.setString(3, book.getRelease_data());
            preparedStatement.setInt(4, author.getId_author());

            preparedStatement.execute();

            ResultSet respuesta = preparedStatement.getGeneratedKeys();

            //Le damos el id correspondiente a cada nueva entidad
            while (respuesta.next()){
                book.setId_book(respuesta.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "New Book successful.");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());

        }

        ConfigDB.closeConnection();

        return book;
    }

    @Override
    public List<Object> list() {

        Connection conexion = ConfigDB.openConnection();

        List<Object> bookLIst = new ArrayList<>();

        try
        {
            String sqlQuery = "SELECT * FROM books;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            ResultSet resultado = preparedStatement.executeQuery();

            while (resultado.next()){

                Book book = new Book();

                book.setId_book(resultado.getInt("id_book"));
                book.setTittle(resultado.getString("tittle"));
                book.setRelease_data(resultado.getString("release_data"));
                book.setPrice(resultado.getDouble("price"));
                book.setFk_author(resultado.getInt("fk_author"));

                bookLIst.add(book);
            }
        }
        catch (SQLException error)
        {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return bookLIst;
    }

    @Override
    public boolean delete(Object object)
    {
        Book book = (Book) object;

        Connection  conexion = ConfigDB.openConnection();

        boolean isDeleted = false;

        try
        {
            String sqlQuery = "DELETE FROM books WHERE id_book = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, book.getId_book());

            int resultadoFilasAfectadas = preparedStatement.executeUpdate();

            if (resultadoFilasAfectadas > 0)
            {
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"Delete correctly --> " + resultadoFilasAfectadas);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Dont deleted x.x");
            }

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error in Delete: " +  e.getMessage());
        }

        ConfigDB.closeConnection();

        return isDeleted;
    }

    @Override
    public boolean update(Object object) {

        Connection conexion = ConfigDB.openConnection();

        boolean isUpdated = false;

        try
        {
            Book book = (Book) object;

            String sqlQuery = "UPDATE books SET tittle = ?, release_data = ?, price = ?, fk_author = ? WHERE id_book = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);

            //Se le pasa posicion y dato al statement
            preparedStatement.setInt(5, book.getId_book());
            preparedStatement.setString(1, book.getTittle()));
            preparedStatement.setString(2, book.getRelease_data());
            preparedStatement.setDouble(3, book.getPrice());
            preparedStatement.setInt(4, book.getFk_author());

            int resultado = preparedStatement.executeUpdate();

            if (resultado > 0)
            {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Updated Successfully");
            }

        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null , "Error --> " + e.getMessage());
        }
        finally
        {
            ConfigDB.closeConnection();
        }

        return isUpdated;
    }

    //No viene del CRUD, es propio
    public Book findById(int id_book)
    {
        Connection conexion = ConfigDB.openConnection();

        //Global
        Book book = null;

        try
        {
            String sqlQuery = "SELECT * FROM books WHERE id_book = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            //Le pasamos el ID al query
            preparedStatement.setInt(1, id_book);

            //Tipo de dato del jdbc: ResultSet
            /*
             * execute: devuelve boolean
             * executeQuery: Devuelve algo
             * executeUpdate: Actualiza
             * */

            ResultSet resultado = preparedStatement.executeQuery();

            //Asignamos los datos encontrados
            if(resultado.next())
            {
                book = new Book();

                book.setId_book(resultado.getInt("id_book"));
                book.setTittle(resultado.getString("tittle"));
                book.setRelease_data(resultado.getString("release_data"));
                book.setPrice(resultado.getDouble("price"));
                book.setFk_author(resultado.getInt("fk_author"));

            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Book model error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return book;
    }

    public List<Object> findByName(String tittle)
    {
        List<Object> listBooks = new ArrayList<>();

        Connection conexion = ConfigDB.openConnection();

        try {

            String sqlQuery = "SELECT *  FROM books WHERE tittle LIKE ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            preparedStatement.setString(1, "%" + tittle + "%");

            ResultSet resultado = preparedStatement.executeQuery();

            while (resultado.next()){

                Book book = new Book();

                book.setId_book(resultado.getInt("id_book"));
                book.setFk_author(resultado.getInt("fk_author"));
                book.setTittle(resultado.getString("tittle"));
                book.setPrice(resultado.getDouble("price"));
                book.setRelease_data(resultado.getString("release_data"));

                listBooks.add(book);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return listBooks;
    }

    public List<Object> findByAuthor(String authorName)
    {
        List<Object> listBooks = new ArrayList<>();

        Connection conexion = ConfigDB.openConnection();

        try {
            String sqlQuery = "SELECT books.* FROM books  JOIN authors ON books. fk_author = authors.id_author WHERE authors.name LIKE ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            preparedStatement.setString(1, "%" + authorName + "%");

            ResultSet resultado = preparedStatement.executeQuery();

            while (resultado.next()){

                Book book = new Book();

                book.setId_book(resultado.getInt("id_book"));
                book.setTittle(resultado.getString("tittle"));
                book.setRelease_data(resultado.getString("release_data"));
                book.setPrice(resultado.getDouble("price"));

                listBooks.add(book);
            }

        }
        catch (SQLException error)
        {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return listBooks;
    }

}
