import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.TreeMap;

public class Inventory {

    public  static ObservableList<String> allCategories(TreeMap<String,LinkedList<Car>> treeMap ) {
        ObservableList<String> categories= FXCollections.observableArrayList();
        for (String k : treeMap.keySet()) {
            System.out.println("keys: " + k);
            categories.add(k);
        }
        return categories;
    }


    public  static ObservableList<Car> allCars(TreeMap<String,LinkedList<Car>> treeMap , String k){
        ObservableList<Car> cars= FXCollections.observableArrayList();
        for (Car car : treeMap.get(k)){
            System.out.println(car);
            cars.add(car);
        }
        return cars;
    }

    public static ObservableList<Car> carsPerCategorie(TreeMap<String,LinkedList<Car>> treeMap , String k){
        ObservableList<Car> cars= FXCollections.observableArrayList();
        for (Car car: treeMap.get(k)){
            System.out.println("SELECTED:"+car);
            cars.add(car);
        }
        return  cars;
    }

    public static void addCar(TreeMap<String,LinkedList<Car>> treeMap , Car car,String k){
      LinkedList<Car> cars=new LinkedList<>();
        for(Car c:treeMap.get(k)){
            cars.add(c);
            System.out.println("old list:"+c);
        }
        cars.add(car);
        for(Car c: cars){
            System.out.println("NEW list:"+c);
        }
    }


}
