package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Item;

public class ItemDao implements Dao<Item,Integer>{
    Connection connection;
    
    public ItemDao(Connection conn){
        this.connection = conn;
    }

    public List<Item> findAll(){ //executes below query, adds an item to items list, and returns it
        List<Item> items = new ArrayList<>();

        try(Statement statement = connection.createStatement())
        {
            ResultSet result = statement.executeQuery("SELECT * FROM item");
            while(result.next())
            {
                Item item = new Item();
                item.setID(result.getInt("ID"));
                item.setName(result.getString("Name"));
                item.setDescription(result.getString("Description"));
                items.add(item);
            }
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return items;
    }

    public Item findById(Integer id)       //Receives an id as a parameter, replaces "?" with received id, executes query, 
                                           //instanciates item object from result in workbench and returns it
    {
        Item item = new Item();
        String select = "SELECT * FROM item WHERE id=?";

        try(PreparedStatement ps = connection.prepareStatement(select);){
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();

            if(result.next())
            {
                item.setName(result.getString("Name"));
                item.setDescription(result.getString("Description"));
                item.setID(result.getInt("ID"));
            }
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return item;
    }

    public void insert(Item item)       //Receives instanciated in Visual Studio Code item object, then inserts that item into the database
    {
        try(Statement statement = connection.createStatement())
        {
            String insert = "INSERT INTO item VALUES(?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, item.getID());
            ps.setString(2, item.getDescription());
            ps.setString(3, item.getName());
            ps.executeUpdate();

        }
            catch(SQLException e)
            {
                System.err.println(e.getMessage());
            }
        }

        public Boolean update(Item item)  //Receives an item object, executes an update query
        {
            Boolean success = true;
            String update = "UPDATE item SET name=? WHERE id=?";

            try(PreparedStatement ps = connection.prepareStatement(update);){
                ps.setString(1, item.getName());
                ps.setInt(2, item.getID());
                ps.executeUpdate();
            }
            catch(SQLException e)
            {
                System.err.println(e.getMessage());
                success = false;
            }
            return success;
        }

        public Boolean delete(Integer pk)  //Receives id as a parameter, replaces "?" with received id, and deletes an entity from database
        {
            Boolean success = false;
            String delete = "DELETE FROM item WHERE id=?";

            try(PreparedStatement ps = connection.prepareStatement(delete)){
                ps.setInt(1, pk);

                if(ps.executeUpdate() != 0)
                {
                    success = true;
                }
            }
            catch(SQLException e)
            {
                System.err.println(e.getMessage());
            }
            return success;
            }
        }
