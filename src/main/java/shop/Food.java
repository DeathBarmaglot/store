package shop;

public class Food {
    private String name;
    private String date;
    private int price;
    private int id;

    public Food(String name, int price, String date, int id) {
        this.name = name;
        this.date = date;
        this.price = price;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }
}
