import java.sql.Connection;
import java.util.List;

import daos.ItemDao;
import entities.Database;
import entities.Item;

public class App {
    public static void main(String[] args) throws Exception {
        Connection connection = Database.getDatabaseConnection();
        System.out.println("Hello, World!");
        
        //findALL
        List<Item> itemList;
        ItemDao itemDao = new ItemDao(connection);
        itemList = itemDao.findAll();

        System.out.println("Items printed:");

        for(Item item:itemList){
            System.out.println("Item ID: " + item.getID() + "| Item Name: " + item.getName() + "| Item Description " + item.getDescription());
        }

        //Insert new item
        Item insertItem = new Item();
        insertItem.setID(7);
        insertItem.setDescription("Fresh blueberries");
        insertItem.setName("Blueberries");

        itemDao.insert(insertItem);

        //Update an item
        Item updateItem = itemDao.findById(3);
        updateItem.setName("Cosmetics");
        Boolean success = itemDao.update(updateItem);

        //Delete an item
        Boolean succeeded = itemDao.delete(7);
    }
}

