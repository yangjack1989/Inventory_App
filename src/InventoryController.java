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
    private Label messageLabel;
    @FXML
    private ListView<Car> listView;

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

    private Inventory inventory = new Inventory();

    private ObservableList<Car> carlist = FXCollections.observableArrayList();
    private ObservableList<Car> changedCarlist = FXCollections.observableArrayList();
    private ToggleGroup group = new ToggleGroup();
    private ObservableList<String> categoryList = FXCollections.observableArrayList();
    private ObservableList<Car> selectedCategoryCarList = FXCollections.observableArrayList();

    /**
     *  Initialize method when the view loads
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //create car objects
        Car car1 = new Car("Honda Fit", "japan", 10000, new Image("hondafit.jpg"), 7);
        Car car2 = new Car("Fiat 500", "Italy", 13000, new Image("fiat500.jpg"), 6);
        Car car3 = new Car("Ferrari", "famous", 1000000, new Image("ferrai.jpg"), 3);
        Car car4 = new Car("Audi R8", "nice", 800000, new Image("audir8.jpg"), 3);
        Car car5 = new Car("SUV Audi", "cool", 18000, new Image("suvaudi.png"), 4);
        Car car6 = new Car("Honda SUV", "cool", 20000, new Image("hondasuv.jpg"), 4);
        Car car7 = new Car("Hyundai Truck", "cool", 20000, new Image("hyundaitruck.jpg"), 4);
        Car car8 = new Car("Toyota Fuel", "cool", 12000, new Image("Toyotafuel.jpg"), 5);
        Car car9 = new Car("Lamborghini", "cool", 900000, new Image("lamborghini.jpg"), 2);

        LinkedList<Car> suv = new LinkedList<>();
        LinkedList<Car> sporty = new LinkedList<>();
        LinkedList<Car> truck = new LinkedList<>();
        LinkedList<Car> economy = new LinkedList<>();
        suv.add(car5);
        suv.add(car6);
        sporty.add(car3);
        sporty.add(car4);
        sporty.add(car9);
        truck.add(car7);
        economy.add(car1);
        economy.add(car2);
        economy.add(car8);

        inventory.inventoryCar.put("SUV", suv);
        inventory.inventoryCar.put("Sporty", sporty);
        inventory.inventoryCar.put("Truck", truck);
        inventory.inventoryCar.put("Economy", economy);

        // get all cars
        carlist.addAll(inventory.allCars(inventory.inventoryCar));
        listView.getItems().addAll(carlist);
        aToZ();



        //set handle events for radio buttons
        // toggle group radio button
        this.aZRadioButton.setToggleGroup(group);
        this.aZRadioButton.setSelected(true);
        this.ZaRadioButton.setToggleGroup(group);
        this.highLowRadioButton.setToggleGroup(group);
        this.lowHighRadioButton.setToggleGroup(group);
        aZRadioButton.setOnAction(event -> aToZ());
        ZaRadioButton.setOnAction(event -> zToA());
        highLowRadioButton.setOnAction(event -> highToLow());
        lowHighRadioButton.setOnAction(event -> lowToHigh());

        //load all categories
        categoryList.addAll(inventory.allCategories(inventory.inventoryCar));
        this.ConboxView.setItems(categoryList);
        //default select 1st car in the list
        listView.getSelectionModel().selectFirst();
        //load fist image of all calls
        imageView.setImage(listView.getSelectionModel().getSelectedItem().getImage());


        //load selected images
        listView.getSelectionModel().selectedItemProperty().addListener(((arg, oldVal, newVal) -> {
            if (newVal != null) {
                imageView.setImage(newVal.getImage());
            }

        }));

        //load all  prices
        double allPrices = listView.getItems().stream()
                .mapToDouble(car -> car.getPrice()*car.getUnits())
                .sum();
        this.InventoryValueLabel.setText("$" + Double.toString(allPrices));
        //set default value
        this.cateogoryValueLabel.setText("N/A");


        // combo box changed
        this.ConboxView.valueProperty().addListener((arg, oldVal, newVal) -> {
                    messageLabel.setText("");
                    selectedCategoryCarList.clear();
                    selectedCategoryCarList.addAll(inventory.carsPerCategorie(inventory.inventoryCar, newVal));
                    listView.setItems(selectedCategoryCarList);
                    listView.getSelectionModel().select(0);
                    double valueCategory=listView.getItems().stream()
                                                             .mapToDouble(car -> car.getPrice()*car.getUnits())
                                                              .sum();

                    cateogoryValueLabel.setText("$" + Double.toString(valueCategory));

                    if(group.getSelectedToggle().equals(ZaRadioButton)) {
                        zToA();
                    }else if(group.getSelectedToggle().equals(aZRadioButton)){
                        aToZ();
                    }else  if(group.getSelectedToggle().equals(highLowRadioButton)){
                        highToLow();
                    }else {
                        lowToHigh();
                    }


                }
        );

        selectButton.setOnAction(event -> {
           try {
                 messageLabel.setText("");

              if(ConboxView.getValue()!=null) {
                  // sum car value for selected category
                  Car sellCar = listView.getSelectionModel().getSelectedItem();
                  sellCar.sellUnit();
                  listView.refresh();
                  double newTotalPrices =listView.getItems().stream()
                                                           .mapToDouble(car -> car.getPrice()*car.getUnits())
                                                           .sum();
                  this.cateogoryValueLabel.setText("$" + Double.toString(newTotalPrices));
                  System.out.println(carlist);

                  //sum the all cars value
                  double total=carlist.stream()
                          .mapToDouble(car -> car.getPrice()*car.getUnits())
                          .sum();
                  this.InventoryValueLabel.setText("$" + Double.toString(total));
              }
              else {
                  // inventory value without any category selected
                  Car sellCar = listView.getSelectionModel().getSelectedItem();
                  sellCar.sellUnit();
                  listView.refresh();
                  double newCarsValue=listView.getItems().stream()
                                                         .mapToDouble(car -> car.getPrice()*car.getUnits())
                                                         .sum();
                  this.InventoryValueLabel.setText("$" + Double.toString(newCarsValue));
              }


           }catch (IllegalArgumentException e){
               messageLabel.setText(e.getMessage());

           }

        });

    }


    /**
     * This method sort on alphabetic order
     */
    public void aToZ() {
        Collections.sort(listView.getItems(), (a, b) -> a.getName().compareToIgnoreCase(b.getName()));
        listView.getSelectionModel().select(0);

    }

    /**
     * this method reverse the order by Z -A
     */
    public void zToA() {
        Collections.sort(listView.getItems(), (a, b) -> b.getName().compareToIgnoreCase(a.getName()));

        listView.getSelectionModel().select(0);
    }

    /**
     * This method sort the car list from highest price to lowest price
     */
    public void highToLow() {
        Collections.sort(listView.getItems(), (a, b) -> {
            if (a.getPrice() > b.getPrice())
                return -1;
            else
                return 1;
        });

        listView.getSelectionModel().select(0);

    }

    /**
     * This method sorts car list from lowest price to highest price
     */
    public void lowToHigh() {
        Collections.sort(listView.getItems(), (a, b) -> {
            if (a.getPrice() > b.getPrice())
                return 1;
            else
                return -1;
        });

        listView.getSelectionModel().select(0);
    }




}
