package entities;

import java.util.List;

public class Item {
    Integer ID;
    String Name;
    String Description;

    public Integer getID()
    {
        return ID;
    }

    public void setID(Integer id)
    {
        ID = id;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public String getDescription()
    {
        return Description;
    }

    public void setDescription(String description)
    {
        Description = description;
    }

   /* @Override public Item.toString()
    {
        return "Name: " + item.getName();

    }*/
}

