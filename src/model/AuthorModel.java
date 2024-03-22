package model;

import database.CRUD;
import database.ConfigDB;
import entity.Author;
import entity.Coder;

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
    public Object insert(Object object) {

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
            String sqlQuery = "DELETE FROM authors WHERE id = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, author.getId_author());

            int resultadoFilasAfectadas = preparedStatement.executeUpdate();

            if (resultadoFilasAfectadas > 0)
            {
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"Se borraron correctamente la fila --> " + resultadoFilasAfectadas);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No se pudo Eliminar");
            }

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error en el Delete: " +  e.getMessage());
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
            Coder coder = (Coder) object;

            String sqlQuery = "UPDATE coder SET name = ?, age = ?, clan = ? WHERE id = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);

            //Se le pasa el dato al statement
            preparedStatement.setString(1, coder.getName());            //Se le pasa el dato al statement
            preparedStatement.setInt(2, coder.getAge());
            preparedStatement.setString(3, coder.getClan());
            preparedStatement.setInt(4, coder.getId());

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
    public Author findById(int id)
    {
        Connection conexion = ConfigDB.openConnection();

        //Global
        Author author = null;

        try
        {
            String sqlQuery = "SELECT * FROM coder WHERE id = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sqlQuery);

            //Le pasamos el ID al query
            preparedStatement.setInt(1, id);

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

                author.setId_author(resultado.getInt("id"));
                author.setName(resultado.getString("name"));
                author.setNationality(resultado.getString("nationality"));

            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Hubo un error al buscar el coder");
        }

        ConfigDB.closeConnection();
        return author;
    }

    public List<Object> findByName(String name)
    {
        // 1. Crear lista para guardar lo que nos devuelve la base de datos
        List<Object> listCoders = new ArrayList<>();

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            // 3. Escribimos el query en Sql
            String sqlQuery = "SELECT *  FROM coder WHERE name LIKE ?;";

            //4. Usar el prepareStatement ----  Se le pasa el dato al statement
            PreparedStatement preparedStatement = objConnection.prepareStatement(sqlQuery);

            preparedStatement.setString(1, "%" + name + "%");

            //5. Ejecutar el query y obtener el resultado (ResulSet)

            ResultSet resultado = preparedStatement.executeQuery();

            // 6. Mientras haya un resultado siguiente hacer:
            while (resultado.next()){

                // 6.1 Crear un coder
                Coder objCoder = new Coder();

                //6.2 Llenar el objeto con la información de la base de datos del objeto ques está iterando
                objCoder.setName(resultado.getString("name"));
                objCoder.setAge(resultado.getInt("age"));
                objCoder.setId(resultado.getInt("id"));
                objCoder.setClan(resultado.getString("clan"));

                //6.3 Agregamos el Coder a la lista
                listCoders.add(objCoder);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        //Paso 7. Cerrar la conexión
        ConfigDB.closeConnection();

        return listCoders;
    }
}
