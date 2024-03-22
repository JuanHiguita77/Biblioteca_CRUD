package database;

import java.util.List;

public interface CRUD {

    public Object insert(Object obj);
    public List<Object> list();

    public boolean update(Object obj);

    public boolean delete(Object obj);

    //public Object searchById(int id);
}
