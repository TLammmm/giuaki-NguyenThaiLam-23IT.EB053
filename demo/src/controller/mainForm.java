package controller;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import modul.productdata;
import com.mysql.cj.xdevapi.Statement;

import database.jd;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import modul.customerData;
import modul.data;
import modul.productdata;

public class mainForm implements Initializable {
	@FXML
	private AnchorPane main_form;
	@FXML
	private TableColumn<productdata, String> inventory_costatus;
	@FXML
	private Button customers_btn;

	@FXML
	private Button inventory_add;

	@FXML
	private Button inventory_btn;
	@FXML
	private AnchorPane inven_form;
	@FXML
	private TableColumn<customerData, String> customer_cocashier;

	@FXML
	private TableColumn<customerData, String> customer_cocusid;

	@FXML
	private TableColumn<customerData, String> customer_codate;

	@FXML
	private TableColumn<customerData, String> customer_cototal;

	@FXML
	private TableView<customerData> customer_table;
	@FXML
	private Button inventory_clear;

	@FXML
	private Button inventpry_logout;
	@FXML
	private TableColumn<productdata, String> inventory_coid;

	@FXML
	private TableColumn<productdata, String> inventory_coname;

	@FXML
	private TableColumn<productdata, String> inventory_coprice;

	@FXML
	private TableColumn<productdata, String> inventory_costock;

	@FXML
	private TableColumn<productdata, String> inventory_cotype;

	@FXML
	private Button inventory_delete;

	@FXML
	private AnchorPane tableview_form;
	@FXML
	private AnchorPane menu_form;

	@FXML
	private TextField inventory_idtext;

	@FXML
	private ImageView inventory_image;

	@FXML
	private Button inventory_import;

	@FXML
	private TextField inventory_nametext;
	@FXML

	private Label username;

	@FXML
	private TextField inventory_pricetext;

	@FXML
	private ComboBox<?> inventory_status;

	@FXML
	private TextField inventory_stocktext;

	@FXML
	private TableView<productdata> inventory_table;

	@FXML
	private ComboBox<?> inventory_type;

	@FXML
	private Button inventory_update;

	@FXML
	private AnchorPane menu;

	@FXML
	private TextField menu_amount;

	@FXML
	private Button menu_btn;

	@FXML
	private Label menu_change;

	@FXML
	private TableColumn<productdata, String> menu_coprice;

	@FXML
	private TableColumn<productdata, String> menu_coproduct;

	@FXML
	private TableColumn<productdata, String> menu_coquantity;

	@FXML
	private GridPane menu_gridpane;

	@FXML
	private Button menu_paybtn;

	@FXML
	private Button menu_receiptbtn;

	@FXML
	private Button menu_removebtn;

	@FXML
	private ScrollPane menu_scrollpane;

	@FXML
	private TableView<productdata> menu_table;

	@FXML
	private Label menu_total;

	@FXML
	private AnchorPane customers_form;

	private Image image;
	private Connection c;
	private PreparedStatement prepare;
	private ResultSet result;
	private java.sql.Statement statement;

	public ObservableList<productdata> inventoryDataList() {

		ObservableList<modul.productdata> listData = FXCollections.observableArrayList();
		String sql = "SELECT * FROM product";
		c = jd.getConnection();

		try {
			prepare = c.prepareStatement(sql);
			result = prepare.executeQuery();
			productdata prodData;
			while (result.next()) {

				prodData = new productdata(result.getString("id"), result.getString("name"), result.getString("type"),
						result.getString("status"), result.getInt("stock"), result.getDouble("price"),
						result.getString("image"));

				listData.add(prodData);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return listData;

	}

	private ObservableList<productdata> inventoryListData;

	// xuất dữ liệu ra table
	public void inventoryShowdata() {
		inventoryListData = inventoryDataList();

		inventory_coid.setCellValueFactory(new PropertyValueFactory<>("id"));
		inventory_coname.setCellValueFactory(new PropertyValueFactory<>("name"));
		inventory_cotype.setCellValueFactory(new PropertyValueFactory<>("type"));
		inventory_costock.setCellValueFactory(new PropertyValueFactory<>("stock"));
		inventory_coprice.setCellValueFactory(new PropertyValueFactory<>("price"));
		inventory_costatus.setCellValueFactory(new PropertyValueFactory<>("status"));

		inventory_table.setItems(inventoryListData);

	}

	private String[] typeList = { "Fresh produce", "Canned and packaged food", "Frozen food", "Beverages",
			"Household chemicals", "Snacks and confectionery", "Household items", "Office supplies" };

	private String[] statusList = { "Availible", "Unavailible" };

	public void inventoryTypeList() {

		List<String> typeL = new ArrayList<>();
		for (String data : typeList) {
			typeL.add(data);
		}

		ObservableList listData = FXCollections.observableArrayList(typeList);

		inventory_type.setItems(listData);
	}

	public void statusList() {

		List<String> typeL = new ArrayList<>();
		for (String data : statusList) {
			typeL.add(data);
		}

		ObservableList listData = FXCollections.observableArrayList(statusList);

		inventory_status.setItems(listData);
	}

	private Alert alert;

	public void logout() {

		try {
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Erorr Message");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure you want t logout?");
			Optional<ButtonType> Option = alert.showAndWait();

			if (Option.get().equals(ButtonType.OK)) {
				Stage currentStage = (Stage) inventpry_logout.getScene().getWindow();
				currentStage.close();

				Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
				Stage stage = new Stage();
				Scene scene = new Scene(root);
				stage.setTitle("LamMart");
				stage.setScene(scene);
				stage.show();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void displayUsername() {

		String user = modul.data.username;
		user = user.substring(0, 1).toUpperCase() + user.substring(1);
		username.setText(user);

	}

	public void invenAddBtn() {

		if (inventory_nametext.getText().isEmpty() || inventory_idtext.getText().isEmpty()
				|| inventory_pricetext.getText().isEmpty() || inventory_stocktext.getText().isEmpty()
				|| inventory_type.getSelectionModel().getSelectedItem() == null
				|| inventory_status.getSelectionModel().getSelectedItem() == null || data.path == null) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erorr");
			alert.setHeaderText(null);
			alert.setContentText("pleae fill all blank fields");
			alert.showAndWait();

		} else {

			String checkId = "SELECT id FROM product WHERE id='" + inventory_idtext.getText() + "'";
			c = jd.getConnection();
			try {

				statement = c.createStatement();
				result = statement.executeQuery(checkId);

				if (result.next()) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Erorr");
					alert.setHeaderText(null);
					alert.setContentText(inventory_idtext.getText() + "already taken");
					alert.showAndWait();

				} else {

					String insertData = "INSERT INTO product (id,name,type,price,stock,status,image)"
							+ "VALUES(?,?,?,?,?,?,?)";
					prepare = c.prepareStatement(insertData);
					prepare.setString(1, inventory_idtext.getText());
					prepare.setString(2, inventory_nametext.getText());
					prepare.setString(3, (String) inventory_type.getSelectionModel().getSelectedItem());

					prepare.setString(4, inventory_pricetext.getText());
					prepare.setString(5, inventory_stocktext.getText());
					prepare.setString(6, (String) inventory_status.getSelectionModel().getSelectedItem());

					String path = data.path;
					path = path.replace("\\", "\\\\");
					prepare.setString(7, path);
					prepare.executeUpdate();
					inventoryShowdata();
					invenClearBtn();
				}

			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}

	public void invenClearBtn() {

		inventory_idtext.setText("");
		inventory_pricetext.setText("");
		inventory_stocktext.setText("");
		inventory_nametext.setText("");
		inventory_type.getSelectionModel().clearSelection();
		inventory_status.getSelectionModel().clearSelection();
		inventory_image.setImage(null);
		data.id = "";
		data.path = "";
	}

	public void invenImportBtn() {

		FileChooser openFile = new FileChooser();
		openFile.getExtensionFilters().add(new ExtensionFilter("Open Image File", "*png", "*jpg"));
		File file = openFile.showOpenDialog(main_form.getScene().getWindow());
		if (file != null) {
			data.path = file.getAbsolutePath();
			image = new Image(file.toURI().toString(), 117, 109, false, true);
			inventory_image.setImage(image);

		}
	}

	public void inventorySelectData() {
		productdata prodData = inventory_table.getSelectionModel().getSelectedItem();
		int num = inventory_table.getSelectionModel().getSelectedIndex();
		if ((num - 1) < -1)
			return;

		inventory_idtext.setText(prodData.getId());
		inventory_nametext.setText(prodData.getName());

		inventory_pricetext.setText(String.valueOf(prodData.getPrice()));
		inventory_stocktext.setText(String.valueOf(prodData.getStock()));

		data.path = prodData.getImage();

		String path = "File:" + prodData.getImage();
		data.id = prodData.getId();

		extracted(path);
		inventory_image.setImage(image);

	}

	private void extracted(String path) {
		image = new Image(path, 117, 109, false, true);
	}

	public void updateBtn() {

		if (inventory_nametext.getText().isEmpty() || inventory_idtext.getText().isEmpty()
				|| inventory_pricetext.getText().isEmpty() || inventory_stocktext.getText().isEmpty()
				|| inventory_type.getSelectionModel().getSelectedItem() == null
				|| inventory_status.getSelectionModel().getSelectedItem() == null || data.path == null
				|| data.id == null) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erorr");
			alert.setHeaderText(null);
			alert.setContentText("pleae fill all blank fields");
			alert.showAndWait();

		} else {

			String path = data.path;
			path = path.replace("\\", "\\\\");

			String update = "UPDATE product SET id ='" + inventory_idtext.getText() + "',name= '"
					+ inventory_nametext.getText() + "', type ='" + inventory_type.getSelectionModel().getSelectedItem()
					+ "',stock = '" + inventory_stocktext.getText() + "',price ='" + inventory_pricetext.getText()
					+ "', status='" + inventory_status.getSelectionModel().getSelectedItem() + "',image='" + path
					+ "' WHERE id = '" + data.id + "'";

			c = jd.getConnection();

			try {

				alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Are you sure you want to UPDATE PRoduct ID: " + inventory_idtext.getText() + "?");
				Optional<ButtonType> option = alert.showAndWait();

				if (option.get().equals(ButtonType.OK)) {
					prepare = c.prepareStatement(update);
					prepare.executeUpdate();

					inventoryShowdata();
					invenClearBtn();
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("Cancelled");
					alert.showAndWait();

				}

			} catch (Exception e) {
				e.printStackTrace();

			}

		}
	}

	public void deleteBtn() {

		if (inventory_nametext.getText().isEmpty() || inventory_idtext.getText().isEmpty()
				|| inventory_pricetext.getText().isEmpty() || inventory_stocktext.getText().isEmpty()
				|| data.path == null || data.id == null) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erorr");
			alert.setHeaderText(null);
			alert.setContentText("pleae fill all blank fields");
			alert.showAndWait();

		} else {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Error Message");

			alert.setHeaderText(null);

			alert.setContentText("Are you sure you want to DELETE Product ID: " + inventory_idtext.getText());
			Optional<ButtonType> option = alert.showAndWait();
			if (option.get().equals(ButtonType.OK)) {

				String deleteData = "DELETE FROM product WHERE id = '" + data.id + "'";

				try {
					prepare = c.prepareStatement(deleteData);
					prepare.executeUpdate();
					alert = new Alert(AlertType.ERROR);

					alert.setTitle("Error Message");
					alert.setHeaderText(null);
					alert.setContentText("successfully Deleted!");

					inventoryShowdata();
					invenClearBtn();

				}

				catch (Exception e) {
					e.printStackTrace();
				}
			} else {

				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Cancelled");
				alert.showAndWait();

			}

		}
	}

	private ObservableList<productdata> cardListData = FXCollections.observableArrayList();

	public ObservableList<productdata> menuGetData() {
		ObservableList<productdata> listData = FXCollections.observableArrayList();

		String sql = "SELECT * FROM product";
		c = jd.getConnection();
		try {

			prepare = c.prepareStatement(sql);
			result = prepare.executeQuery();
			productdata prod;
			while (result.next()) {
				prod = new productdata(result.getString("id"), result.getString("name"), result.getDouble("price"),
						result.getString("image"));
				listData.add(prod);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return listData;
	}

	public void menu() {

		cardListData.clear();
		cardListData.addAll(menuGetData());

		int row = 0;
		int column = 0;
		menu_gridpane.getChildren().clear();
		menu_gridpane.getRowConstraints().clear();
		menu_gridpane.getColumnConstraints().clear();
		for (int q = 0; q < cardListData.size(); q++) {
			System.out.print(cardListData.size());
			System.out.print(cardListData.toString());

			try {
				FXMLLoader load = new FXMLLoader();
				load.setLocation(getClass().getResource("/view/cardProduct.fxml"));
				AnchorPane pane = load.load();
				card cardC = load.getController();
				cardC.setData(cardListData.get(q));

				if (column == 3) {
					column = 0;
					row++;
				}
				menu_gridpane.add(pane, column++, row);
				GridPane.setMargin(pane, new Insets(10));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void switchForm(ActionEvent event) {

		if (event.getSource() == inventory_btn) {

			inven_form.setVisible(true);
			tableview_form.setVisible(true);
			menu_form.setVisible(false);
			menu();

			displayUsername();
			inventoryTypeList();
			statusList();
			inventoryShowdata();
			customers_form.setVisible(false);

		} else if (event.getSource() == menu_btn) {
			tableview_form.setVisible(false);
			inven_form.setVisible(false);
			menu_form.setVisible(true);
			customers_form.setVisible(false);

			menu();
//			menuDislayOrder();
			menuGetTotal();
			menuDisPlaytotal();
			menushowOrderData();
			displayUsername();
			inventoryTypeList();
			statusList();
			inventoryShowdata();
		} else if (event.getSource() == customers_btn) {
			tableview_form.setVisible(false);
			inven_form.setVisible(false);
			menu_form.setVisible(false);
			customers_form.setVisible(true);

			customersShowData();
		}
	}

	public ObservableList<productdata> menuGetOrder() {
		ObservableList<productdata> listData = FXCollections.observableArrayList();
		String sql = "SELECT * FROM customer";

		c = jd.getConnection();

		try {
			prepare = c.prepareStatement(sql);
			result = prepare.executeQuery();
			productdata prod;
			while (result.next()) {

				prod = new productdata(result.getInt("customer_id"), result.getString("pro_name"),
						result.getInt("quantity"), result.getDouble("price"));
				listData.add(prod);

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return listData;
	}

	private ObservableList<productdata> menuListData;

	private ObservableList<productdata> menuOrderListData;

	public void menushowOrderData() {
		menuOrderListData = menuGetOrder();

		menu_coproduct.setCellValueFactory(new PropertyValueFactory<>("name"));
		menu_coquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		menu_coprice.setCellValueFactory(new PropertyValueFactory<>("price"));
		menu_table.setItems(menuOrderListData);

	}

	private double total;

	public void menuGetTotal() {

		String totalS = "SELECT price FROM customer WHERE customer_id = (SELECT MAX(customer_id) FROM customer)";

		c = jd.getConnection();
		try {
			prepare = c.prepareStatement(totalS);
			result = prepare.executeQuery();
			while (result.next()) {
				total = total + result.getDouble("price");
			}
//    	menu_total.setText("S" + total);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void menuDisPlaytotal() {
//		menuGetTotal();
		menu_total.setText("S" + total);
	}

	public void payBtn() {
		if (total == 0) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error ");
			alert.setHeaderText(null);
			alert.setContentText("Please choose your order first!");
			alert.showAndWait();

		} else {
			menuGetTotal();
			String insertPay = "INSERT INTO receipt(total,date,employee)" + " VALUES (?,?,?)";

			try {
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Message");
				alert.setHeaderText(null);
				alert.setContentText("Are you sure?");
				Optional<ButtonType> option = alert.showAndWait();
				if (option.get().equals(ButtonType.OK)) {
					menuGetTotal();
					prepare = c.prepareStatement(insertPay);
					prepare.setString(1, String.valueOf(total));
					Date date = new Date();
					java.sql.Date sqlDate = new java.sql.Date(date.getTime());
					prepare.setString(2, String.valueOf(sqlDate));
					prepare.setString(3, data.username);
					prepare.executeUpdate();
					menushowOrderData();

					String delete = "DELETE FROM customer";
					prepare = c.prepareStatement(delete);
					prepare.executeUpdate();
					menushowOrderData();
					menuRestart();

					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Infomation Message");
					alert.setHeaderText(null);
					alert.setContentText("Successful");

				} else {
					alert = new Alert(AlertType.WARNING);
					alert.setTitle("Infomation Message");
					alert.setHeaderText(null);
					alert.setContentText("Cancelled");
					alert.showAndWait();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private int getId = 0;

	public void menuSelectOrder() {
		productdata prod = menu_table.getSelectionModel().getSelectedItem();
		int num = menu_table.getSelectionModel().getSelectedIndex();
		if (num < 0)
			return;

		getId = prod.getIdO();
	}

	public void removeBtn() {
		getId=0;
		menuSelectOrder();
				if (getId == 0) {
				alert = new Alert (AlertType. ERROR);
				alert.setTitle("Error Message");
				alert.setContentText("please choose");
				alert.setHeaderText(null);
				alert.showAndWait();
				} else {
					
					
					 String totalS = "SELECT price FROM customer WHERE customer_id = "+getId;
                     
					    c =jd.getConnection();
					    try {
					    	prepare=c.prepareStatement(totalS);
					    	result=prepare.executeQuery();
					    	while(result.next()) {
					    		total=total-result.getDouble("price");
					    	}
					    }catch(Exception e) {
					    			e.printStackTrace();
					    		}
				String deleteData= "DELETE FROM customer WHERE customer_id = " + getId;
				c = jd.getConnection();

				try {
					
				prepare = c.prepareStatement(deleteData);
				prepare.executeUpdate();
				menushowOrderData();
				menuDisPlaytotal();
				}catch (Exception e) {
				e.printStackTrace();			 
			}
	}
	}

//	private int cid;
//	public void customerID() {
//
//	 

//	String sql = "SELECT MAX(customer_id) FROM customer";
//	c = jd.getConnection();
//	try {
//	prepare = c.prepareStatement(sql) ;
//	result = prepare.executeQuery();
//
//	if(result.next()) {
//	cid = result.getInt("MAX(customer_id)");
//	}
//	String checkCid = "SELECT MAX(customer_id) FROM receipt" ;
//	prepare = c.prepareStatement(checkCid) ;
//	result = prepare.executeQuery();
//
//	int checkID = 0;
//
//	if(result.next()) {
//		
//	checkID = result.getInt ("MAX (customer_id)");
//	
//	}
//		if(cid==0) {
//			cid++;
//		}else if(cid == checkID) {
//				cid++;
//				
//		}
//		data.cid=cid;
//	}catch(Exception e) {
//		e.printStackTrace();
//	}
//	}

	public void menuRestart() {
		menu_total.setText("$0.0");
		total = 0;

	}

	public ObservableList<customerData> customersDataList() {

		ObservableList<customerData> listData = FXCollections.observableArrayList();
		String sql = "SELECT * FROM receipt ";
		c = jd.getConnection();
		try {
			prepare = c.prepareStatement(sql);
			result = prepare.executeQuery();
			customerData cusData;

			while (result.next()) {
				cusData = new customerData(result.getInt("id"), result.getDouble("total"), result.getString("employee"),
						result.getDate("Date"));
				listData.add(cusData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listData;
	}

	private ObservableList<customerData> customersListData;

	public void customersShowData() {
		customersListData = customersDataList();
		customer_cocusid.setCellValueFactory(new PropertyValueFactory<>("id"));
		customer_cototal.setCellValueFactory(new PropertyValueFactory<>("total"));
		customer_cocashier.setCellValueFactory(new PropertyValueFactory<>("cashier"));
		customer_codate.setCellValueFactory(new PropertyValueFactory<>("date"));
		customer_table.setItems(customersListData);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		menu();

		displayUsername();
		inventoryTypeList();
		statusList();
		inventoryShowdata();
		menuGetOrder();
		menushowOrderData();
		menuGetTotal();
		menuDisPlaytotal();
		customersShowData();

	}

}
