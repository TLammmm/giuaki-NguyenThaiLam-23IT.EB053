package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

import database.jd;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import modul.data;
import modul.productdata;

public class card implements Initializable {

	@FXML
	private Button card_add;

	@FXML
	private AnchorPane card_form;

	@FXML
	private ImageView card_image;

	@FXML
	private Spinner<Integer> card_spinner;

	@FXML
	private Label pro_name;

	@FXML
	private Label pro_price;
	private Image image;
	private String id;
	private productdata prodData;
	private Connection c;
	private PreparedStatement prepare;
	private ResultSet result;
	private Alert alert;
	private String type;
    private String pro_image;

	public void setData(productdata prodData) {
		this.prodData = prodData;
		type=prodData.getType();
		id = prodData.getId();
		pro_image=prodData.getImage();
		pro_name.setText(prodData.getName());
		pro_price.setText("$ " + String.valueOf(prodData.getPrice()));
		String path = "File:" + prodData.getImage();
		image = new Image(path, 144, 93, false, true);
		card_image.setImage(image);
		price = prodData.getPrice();
	}

	private SpinnerValueFactory<Integer> spin;

	private int qty;

	public void setquantity() {
		spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
		card_spinner.setValueFactory(spin);

	}

	private double total;
	private double price;

	public void addBtn() {
		mainForm mForm=new mainForm();
		
			qty = card_spinner.getValue();
			String check = "";
			String checkAvailable = "SELECT status FROM product WHERE id = '"
					+ id + "'";
			c=jd.getConnection();
			
			try {
				prepare = c.prepareStatement(checkAvailable) ;
					result = prepare.executeQuery();
					if (result.next()) {
						check = result.getString("status");
					}

						if(check.equals("Available")||qty== 0){
						alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Message");
						alert.setHeaderText(null);
						alert.setContentText ("Something Wrong :3");
						alert.showAndWait();
						}else {
						    int stock = 0;
						    String checkStock = "SELECT stock FROM product WHERE id = '"
							+id +"'";
									prepare = c.prepareStatement(checkStock);
									result = prepare.executeQuery();
									if (result.next ()) {
								stock=	result.getInt("stock");
									}
								if(stock<0) {
								alert = new Alert (AlertType.ERROR) ;
								alert.setTitle("Error Message");
								alert.setHeaderText (null);
								alert.setContentText ("Invalid. This product is out of stock");
								alert.showAndWait();
						}else{
							
							String insertData = "INSERT INTO customer " 

								+ "( pro_name, quantity, price, employee) "
								+ "VALUES (?,?,?,?)";
								prepare = c.prepareStatement(insertData);
								prepare.setString(1,pro_name.getText());
								prepare.setString(2, String. valueOf(qty));
								total = qty * price;
								prepare.setString (3, String.valueOf(total));
						
								
								prepare.setString(4, data.username);
								prepare.executeUpdate();

							
							
								int upStock=stock-qty;
								pro_image = pro_image.replace("\\", "\\\\");
									
//								String updateStock = "UPDATE product SET name = '"
//										+ pro_name.getText()+ "', type = '"
//										+ type + "', stock ="+upStock+", price= "
//										+price+" status= '"+check+", image= "+pro_image+"where id='"+id;		
//		prepare=c.prepareStatement(upStock);
								String updateStock = "UPDATE product SET stock=" +upStock +" WHERE id='"+id+"'";
								prepare=c.prepareStatement(updateStock);
								prepare.executeUpdate();
								alert = new Alert (AlertType. INFORMATION) ;
								alert.setTitle("Information Message");
								alert.setHeaderText(null);
								alert.setContentText("Successfully Added!");
								alert.showAndWait();
								
							
						}
						}}catch(Exception e){
				e.printStackTrace();
			}
				
			}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setquantity();
	}

}
