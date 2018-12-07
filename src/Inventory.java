import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.TreeMap;

public class Inventory {
    /**
     *  This method return a observable list that contains all categories of cars in inventory
      * @param inventory
     * @return
     */
    public static ObservableList<String> allCategories(TreeMap<String,LinkedList<Car>> inventory ) {
        ObservableList<String> categories= FXCollections.observableArrayList();
        for (String category : inventory.keySet()) {
            System.out.println("keys: " + category);
            categories.add(category);
        }
        return categories;
    }

    /**
     * This method retunn a linkedlist that contains all cars objects
     * @param inventory
     * @return
     */

    public  static  LinkedList<Car> allCars(TreeMap<String,LinkedList<Car>> inventory ){
        LinkedList<Car> cars= new LinkedList<>();
        for (String category : inventory.keySet()){
            cars.addAll(inventory.get(category));
        }
        return cars;
    }

    /**
     * This method return a linkedlist contains car objects based on which category has passed in
     * @param inventory
     * @param k
     * @return
     */

    public static  LinkedList<Car> carsPerCategorie(TreeMap<String,LinkedList<Car>> inventory , String category){
        LinkedList<Car> cars= new LinkedList<>();
        for (Car car: inventory.get(category)){
            System.out.println("SELECTED:"+car);
            cars.add(car);
        }
        return  cars;
    }

    /**
     * this method  receives a TreeMap data ,category string  , car object then add new car object into specific
     * category car list in inventory.
     *
     * @param treeMap
     * @param car
     * @param k
     */

    public static  void addCar(TreeMap<String,LinkedList<Car>> treeMap , Car car,String category){
      LinkedList<Car> cars=new LinkedList<>();
        for(Car c:treeMap.get(category)){
            cars.add(c);
            System.out.println("old list:"+c);
        }
        cars.add(car);
        for(Car c: cars){
            System.out.println("NEW list:"+c);
        }
    }


}
