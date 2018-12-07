import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class InventoryController implements Initializable {
    @FXML
    private ListView listView;

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
    private ObservableList<Car> carlist= FXCollections.observableArrayList();


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
        inventory = new TreeMap<>();
        inventory.put("SUV", suv);
        inventory.put("Sporty", sporty);
        inventory.put("Truck", truck);
        inventory.put("Economy", economy);

        //load all categories
        this.ConboxView.setItems(Inventory.allCategories(inventory));

        // show all cars
        carlist.addAll(Inventory.allCars(inventory));

        //sort
        Collections.sort(carlist);
        //Collections.sort(carlist,(c1,c2)->(c1.getPrice()>c2.getPrice()?1:-1));

        listView.setItems(carlist);
        //load selected images
        listView.getSelectionModel().selectFirst();
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Car>(){
            @Override
            public void changed(ObservableValue<? extends Car> observable, Car oldValue, Car newValue) {
                imageView.setImage(newValue.getImage());
            }

        }
        );
        //load all  prices
        double prices=0;
        prices+=totalValue(Inventory.allCars(inventory));
        this.InventoryValueLabel.setText("$"+Double.toString(prices));

        //set default value
        this.cateogoryValueLabel.setText("N/A");
        ObservableList<Car> selectedCategoryCarList= FXCollections.observableArrayList();

        // combo box changed
        this.ConboxView.valueProperty().addListener((arg, oldVal, newVal)->{
                selectedCategoryCarList.clear();
                selectedCategoryCarList.addAll(Inventory.carsPerCategorie(inventory,newVal));
                listView.setItems(selectedCategoryCarList);
                double valueCategory=totalValue(Inventory.carsPerCategorie(inventory,ConboxView.getValue()));
                cateogoryValueLabel.setText("$"+Double.toString(valueCategory));
            }
        );
        // toggle group radio button
        ToggleGroup group = new ToggleGroup();
        this.aZRadioButton.setToggleGroup(group);
        this.aZRadioButton.setOnAction(event -> Collections.sort(listView.getItems()));
        this.aZRadioButton.setSelected(true);
        this.ZaRadioButton.setToggleGroup(group);
        this.ZaRadioButton.setOnAction(event -> Collections.reverse(listView.getItems()));
        this.highLowRadioButton.setToggleGroup(group);
        this.highLowRadioButton.setOnAction(event -> Collections.sort(selectedCategoryCarList,(a,b)->{
              if(a.getPrice()>b.getPrice())
                  return -1;
              else
                  return 1;
        }));
        this.lowHighRadioButton.setToggleGroup(group);
        this.lowHighRadioButton.setOnAction(event -> Collections.sort(selectedCategoryCarList,(a,b)->{
            if(a.getPrice()>b.getPrice())
                return 1;
            else
                return -1;
        }));

        this.selectButton.setOnAction (new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                System.out.println("hello");
            }
        });







    }





    public static double totalValue(LinkedList<Car> cars){
        double totalValue=0;
        for(Car car : cars){
            totalValue+=car.getPrice()*car.getUnits();

        }
        return totalValue;
    }




}
