import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.LinkedList;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class InventoryController implements Initializable {
    @FXML
    private ListView<ObservableList<Car>> listView;

    @FXML
    private ImageView imageView;

    @FXML
    private ComboBox<String> ConboxView;

    @FXML
    private RadioButton highLowRadioButton;

    @FXML
    private RadioButton lowHighRadioButton;

    @FXML
    private RadioButton aZRadioButton;

    @FXML
    private RadioButton ZaRadioButton;

    @FXML
    private Button selectButton;

    @FXML
    private Label InventoryValueLabel;

    @FXML
    private Label cateogoryValueLabel;

    private TreeMap<String, LinkedList<Car>> inventory;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Car car1 = new Car("Honda Fit", "japan", 10000,new Image("hondafit.jpg"), 7);
        Car car2 = new Car("Fiat 500", "Italy", 13000,new Image("fiat500.jpg"),  6);
        Car car3 = new Car("Ferrari", "famous", 1000000, new Image("ferrai.jpg"), 3);
        Car car4 = new Car("Audi R8", "nice", 800000, new Image("audir8.jpg"), 3);
        Car car5 = new Car("SUV Audi", "cool", 20000,new Image("suvaudi.png") , 4);
        Car car6 = new Car("Honda SUV", "cool", 20000, new Image("hondasuv.jpg") , 4);
        Car car7 = new Car("Hyundai Truck", "cool", 20000,new Image("hyundaitruck.jpg") ,  4);


        LinkedList<Car> suv = new LinkedList<>();
        LinkedList<Car> sporty = new LinkedList<>();
        LinkedList<Car> truck = new LinkedList<>();
        LinkedList<Car> economy = new LinkedList<>();
        suv.add(car5);
        suv.add(car6);
        sporty.add(car3);
        sporty.add(car4);
        truck.add(car7);
        economy.add(car1);
        economy.add(car2);
        inventory = new TreeMap<String, LinkedList<Car>>();
        inventory.put("SUV", suv);
        inventory.put("Sporty", sporty);
        inventory.put("Truck", truck);
        inventory.put("Economy", economy);
        //load all categories
        this.ConboxView.setItems(Inventory.allCategories(inventory));

        // show all cars
        for(String k:inventory.keySet()) {
            this.listView.getItems().addAll(Inventory.allCars(inventory, k));
        }
        //load all  prices
        double prices=0;
        for(String k:inventory.keySet()) {
             prices+=totalValue(Inventory.allCars(inventory, k));
        }


        this.InventoryValueLabel.setText(Double.toString(prices));
        //set defalut value
        this.cateogoryValueLabel.setText("N/A");







    }
    
    public void  comboBoxUpdated(){
        double valueCategory=totalValue(Inventory.carsPerCategorie(inventory,this.ConboxView.getValue()));
        this.cateogoryValueLabel.setText(Double.toString(valueCategory));


    }
    public static  double totalCarsValue(TreeMap<String,LinkedList<Car>> treeMap , String k){
          double totalValue=0;
          for(Car car : treeMap.get(k)){
              totalValue+=car.getPrice()*car.getUnits();

          }
          return totalValue;
    }
    public static double totalValue(ObservableList<Car> cars){
        double totalValue=0;
        for(Car car : cars){
            totalValue+=car.getPrice()*car.getUnits();

        }
        return totalValue;
    }




}
