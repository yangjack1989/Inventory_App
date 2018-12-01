import javafx.scene.image.Image;

public class Car {
    private String name,description;
    private double price;
    private Image  image;
    private int units;

    public Car(String name, String description, double price, Image image, int units) {
         setImage(image);
         setName(name);
         setDescription(description);
         setPrice(price);
        setUnits(units);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.length()==0)
            throw new IllegalArgumentException("name can not be empty");
         else
             this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description.length()==0)
            throw new IllegalArgumentException("description can not be empty");
        else
            this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price>0)
            this.price = price;
        else
            throw new IllegalArgumentException("price must be greater than 0");

    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        if(units>0)
            this.units = units;
        else
            throw new IllegalArgumentException("units must greater than 0" );
    }
    public void sellUnit(Car car){
        if (car.units>1)
        {
            setUnits(units-1);
        }
        else throw new IllegalArgumentException("units must greater than 0" );

    }
    public String toString(){
        return String.format("car: %s   price: $%.2f   units: %d ",name,price,units);
    }
}
