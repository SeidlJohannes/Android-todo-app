package com.example.presidentlist;

import java.util.Comparator;
import java.util.List;

public class Item {
    private int id;
    private String name;
    private int value;
    private String imageURL;
    private String creationDate;
    private List<ItemLog> itemLogList;

    public Item(int id, String name, int valueOfItem, String imageURL, String creationDate) {
        this.id = id;
        this.name = name;
        this.value = valueOfItem;
        this.imageURL = imageURL;
        this.creationDate = creationDate;
    }

    public static Comparator<Item> PresidentNameAZComparator = new Comparator<Item>() {
        @Override
        public int compare(Item p1, Item p2) {
            return p1.getName().compareTo(p2.getName());
        }
    };

    public static Comparator<Item> PresidentNameZAComparator = new Comparator<Item>() {
        @Override
        public int compare(Item p1, Item p2) {
            return p2.getName().compareTo(p1.getName());
        }
    };

    public static Comparator<Item> PresidentDateAscendingComparator = new Comparator<Item>() {
        @Override
        public int compare(Item p1, Item p2) {
            return p1.getValue() - p2.getValue();
        }
    };

    public static Comparator<Item> PresidentDateDescendingComparator = new Comparator<Item>() {
        @Override
        public int compare(Item p1, Item p2) {
            return p2.getValue() - p1.getValue();
        }
    };

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", imageURL='" + imageURL + '\'' +
                ", creationDate='" + creationDate + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public List<ItemLog> getItemLogList() {
        return itemLogList;
    }
}
