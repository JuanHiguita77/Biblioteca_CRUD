package model;

import database.CRUD;
import database.ConfigDB;
import entity.Author;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorModel implements CRUD
{
    @Override
    public Object create(Object object) {

        Connection objConnection = ConfigDB.openConnection();

        Author author = (Author) object;

        try {
            //El id se pone automatico por la base de datos
            String sql = "INSERT INTO authors (name, nationality) VALUES (?,?);";

            PreparedStatement preparedStatement = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,author.getName());
            preparedStatement.setString(2,author.getNationality());

            preparedStatement.execute();

            ResultSet respuesta = preparedStatement.getGeneratedKeys();

            //Le damos el id correspondiente a cada nueva entidad
            while (respuesta.next()){
                author.setId_author(respuesta.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "New Author successful.");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());

        }

        ConfigDB.closeConnection();

        return author;
    }

    @Override
    public List<Object> list() {

        Connection conexion = ConfigDB.openConnection();

        List<Object> authorsList = new ArrayList<>();

        try
        {
            String sqlQuery = "SELECT * FROM authors;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            ResultSet resultado = preparedStatement.executeQuery();

            while (resultado.next()){

                Author author = new Author();

                author.setId_author(resultado.getInt("id_author"));
                author.setName(resultado.getString("name"));
                author.setNationality(resultado.getString("nationality"));

                authorsList.add(author);
            }
        }
        catch (SQLException error)
        {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return authorsList;
    }

    @Override
    public boolean delete(Object object)
    {
        Author author = (Author) object;

        Connection  conexion = ConfigDB.openConnection();

        boolean isDeleted = false;

        try
        {
            String sqlQuery = "DELETE FROM authors WHERE id_author = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, author.getId_author());

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
            JOptionPane.showMessageDialog(null, "Error in delete: " +  e.getMessage());
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
            Author author = (Author) object;

            String sqlQuery = "UPDATE authors SET name = ?, nationality = ? WHERE Id_author = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);

            //Se le pasa posicion y dato al statement
            preparedStatement.setInt(3, author.getId_author());
            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getNationality());


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
    public Author findById(int id_author)
    {
        Connection conexion = ConfigDB.openConnection();

        //Global
        Author author = null;

        try
        {
            String sqlQuery = "SELECT * FROM authors WHERE id_author = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            //Le pasamos el ID al query
            preparedStatement.setInt(1, id_author);

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
                author = new Author();

                author.setId_author(resultado.getInt("id_author"));
                author.setName(resultado.getString("name"));
                author.setNationality(resultado.getString("nationality"));

            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Author model error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return author;
    }

}
