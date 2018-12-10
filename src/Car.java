import javafx.scene.image.Image;

public class Car {
    private String name,description;
    private double price;
    private Image  image;
    private int units;

    /**
     * Constructor of Car class
     * @param name
     * @param description
     * @param price
     * @param image
     * @param units
     */
    public Car(String name, String description, double price, Image image, int units) {
         setImage(image);
         setName(name);
         setDescription(description);
         setPrice(price);
        setUnits(units);
    }

    /**
     *  return  car name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * set the car name and validate if it is empty
     * @param name
     */
    public void setName(String name) {
        if(name.length()==0)
            throw new IllegalArgumentException("name can not be empty");
         else
             this.name = name;
    }

    /**
     * return car desciption
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * set the description of car and validate if it is empty
     * @param description
     */
    public void setDescription(String description) {
        if(description.length()==0)
            throw new IllegalArgumentException("description can not be empty");
        else
            this.description = description;
    }

    /**
     * return car price
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * set the price of car and validate it is >0 and < 20000000
     * @param price
     */
    public void setPrice(double price) {
        if(price>0 && price<20000000)
            this.price = price;
        else
            throw new IllegalArgumentException("price must be greater than 0 and less than 20000000");

    }

    /**
     * return image file path
     * @return
     */
    public Image getImage() {
        return image;
    }

    /**
     * set the image of  the car
     * @param image
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * return car units
     * @return
     */
    public Integer getUnits() {
        return units;
    }

    /**
     * set the car of units and validate if it is less than 0
     * @param units
     */
    public void setUnits(Integer units) {
        if(units>=0)
            this.units = units;
        else
            throw new IllegalArgumentException("units must greater than 0" );
    }

    /**
     * this method checks if the unit >0 then units of the car minus 1 else throw exception
     */
    public  void  sellUnit(){
        if (units>0)
        {
            setUnits(units-1);
        }
        else throw new IllegalArgumentException("Selected car is sold out. Unit=0" );

    }

    /**
     * this method return a string that describes the car object
     * @return
     */
    public String toString(){
        return String.format("car: %s   price: $%.2f   units: %d ",name,price,units);
    }


}
