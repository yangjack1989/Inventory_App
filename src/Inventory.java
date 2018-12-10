import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.TreeMap;

public class Inventory {

     TreeMap<String, LinkedList<Car>> inventoryCar = new TreeMap<>();

    /**
     *  This method passes in a TreeMap and returns a  list of car categories in the inventory
      * @param inventory
     * @return
     */
    public LinkedList<String> allCategories(TreeMap<String,LinkedList<Car>> inventory ) {
        LinkedList<String> categories= new LinkedList<>();
        for (String category : inventory.keySet()) {
            System.out.println("keys: " + category);
            categories.add(category);
        }
        return categories;
    }

    /**
     * This method return a Linkedlist that contains all cars objects
     * @param inventory
     * @return
     */

    public LinkedList<Car> allCars(TreeMap<String,LinkedList<Car>> inventory ){
        LinkedList<Car> cars= new LinkedList<>();
        for (String category : inventory.keySet()){
            cars.addAll(inventory.get(category));
        }
        return cars;
    }

    /**
     * This method return a linkedlist contains car objects based on which category is passed
     * @param inventory
     * @param category
     * @return
     */

    public  LinkedList<Car> carsPerCategorie(TreeMap<String,LinkedList<Car>> inventory , String category){
        LinkedList<Car> cars= new LinkedList<>();
        for (Car car: inventory.get(category)){
            System.out.println("SELECTED:"+car);
            cars.add(car);
        }
        return  cars;
    }

    /**
     * This method  receives a TreeMap data ,category string  , car object then add new car object into specific
     * category car list in inventory.
     *
     * @param inventory
     * @param car
     * @param category
     */

    public void addCar(TreeMap<String,LinkedList<Car>> inventory , Car car,String category){
      LinkedList<Car> cars=new LinkedList<>();
        for(Car c:inventory.get(category)){
            cars.add(c);
            System.out.println("old list:"+c);
        }
        cars.add(car);
        for(Car c: cars){
            System.out.println("NEW list:"+c);
        }
    }




}
