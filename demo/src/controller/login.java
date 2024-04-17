package controller;


	

	import java.net.URL;
import java.sql.Connection;
	import java.sql.Date;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.util.ArrayList;
	import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
	import database.jd;
	import javafx.animation.TranslateTransition;
	import javafx.collections.FXCollections;
	import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
	import javafx.scene.control.Alert.AlertType;
	import javafx.scene.control.Button;
	import javafx.scene.control.ComboBox;
	import javafx.scene.control.Hyperlink;
	import javafx.scene.control.Label;
	import javafx.scene.control.PasswordField;
	import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

	public class login implements Initializable{


		 @FXML
		    private AnchorPane side_changepass;

		    @FXML
		    private AnchorPane side_forgot;

		 @FXML
		    private TextField forgot_answer;
		   @FXML
		    private ImageView imagev;
		  
		    @FXML
		    private Button forgot_proceed;

		    @FXML
		    private ComboBox<?> forgot_question;


		    @FXML
		    private Hyperlink si_forgotpass;

		    @FXML
		    private Button si_loginbtn;

		    @FXML
		    private PasswordField si_password;

		    @FXML
		    private Hyperlink si_register;

		    @FXML
		    private TextField si_username;

		    @FXML
		    private AnchorPane side_form;

		    @FXML
		    private AnchorPane side_loginform;

		    @FXML
		    private AnchorPane side_signupform;

		    @FXML
		    private TextField su_answer;

		    @FXML
		    private Hyperlink su_login;

		    @FXML
		    private PasswordField su_password;

		    @FXML
		    private ComboBox<?> su_question;

		    @FXML
		    private Button su_signupbtn;
		    
		    @FXML
		    private Label useralready;

		    @FXML
		    private Label shortpass;
		    @FXML
		    private Label forgot_errorq;

		    @FXML
		    private TextField forgot_username;
		    
		    @FXML
		    private Label forgot_eusername;
		    @FXML
		    
		    private Label change_shortpass;
		    @FXML
		    private PasswordField change_newpass;
		    @FXML
		    private Button change_back;

		    @FXML
		    private Button change_change;

		    @FXML
		    private PasswordField change_confirm;

		    @FXML
		    private Label change_equal;
		    
		    
		    private Connection c;
		    private PreparedStatement prepare;
		    private ResultSet result;
		    private Alert alert;
		    private String[] questionList = {"What is your favorite Color?", "What is your favorite food?","What is your favorite number?"};
		    
		    
		    
		    
		    //ĐĂNG NHẬP
		    public void loginBtn() {
		    	
		    	if(si_username.getText().isEmpty()||si_password.getText().isEmpty()) {
		    		
		    		alert=new Alert(AlertType.ERROR);
		    		alert.setTitle("Error Message");
		    		alert.setHeaderText(null);
		    		alert.setContentText("Incorrect Username/password");
		    		alert.showAndWait();
		    	}else {
		    		String selectData="SELECT username,password FROM acc WHERE username=? and password =?";
		    		c=jd.getConnection();
		    		try {
		    			prepare =c.prepareStatement(selectData);
		    			prepare.setString(1, si_username.getText());
		    			prepare.setString(2, si_password.getText());
		    			
		    			result=prepare.executeQuery();
		    			if(result.next()) {
		    				modul.data.username=si_username.getText();
		    				alert=new Alert(AlertType.INFORMATION);
		    	    		alert.setTitle("Informaton Message");
		    	    		alert.setHeaderText(null);
		    	    		alert.setContentText("Successfully Login!");
		    	    		alert.showAndWait();
		    	    		
		    	    		Parent root = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));

		    	             Stage stage=new Stage();
		    	            Scene scene = new Scene(root);

		    	          
		    	           stage.setTitle("LamMart");
		    	           stage.setMinWidth(1100);
		    	           stage.setMinHeight(600);
		    	           stage.setScene(scene);
		    	           stage.show();
		    	           si_loginbtn.getScene().getWindow().hide();
		    				
		    			}else {
		    				

		    				alert=new Alert(AlertType.ERROR);
		    	    		alert.setTitle("Error Message");
		    	    		alert.setHeaderText(null);
		    	    		alert.setContentText("Incorrect Username/password");
		    	    		alert.showAndWait();
		    				
		    				
		    				
		    			}
		    			
		    			
		    		}catch(Exception e) {
		    			e.printStackTrace();
		    		}
		    	}
		    }
		    //QUÊN MẬT KHẨU
		    public void ForgotQuestion() {
		    	
		    	List<String> ListQ= new ArrayList<>();
		    	for(String data:questionList) {
		    		ListQ.add(data);
		    	}ObservableList ListData = FXCollections.observableArrayList(ListQ);
		    	forgot_question.setItems(ListData);
		    	
		    }
		    //TẠO TÀI KHOẢN MỚI 
		    public void ReBtn()
		    {
		    	if(su_username.getText().isEmpty()||su_password.getText().isEmpty()||su_question.getSelectionModel().getSelectedItem()==null
		    			||su_answer.getText().isEmpty()) {
		    		alert=new Alert(AlertType.ERROR);
		    		alert.setTitle("Error");
		    		alert.setHeaderText(null);
		    		alert.setContentText("Please fill all blank efields");
		    		alert.showAndWait();
		    	}
		    	else {
		    		String reData="INSERT INTO acc (username, password,question, answer) "+ "VALUES(?,?,?,?)";
		    		c=jd.getConnection();
		    		try {
		    			
		    			String CheckUsername ="SELECT username FROM acc where username= '"+su_username.getText()+"'";
		    			prepare = c.prepareStatement(CheckUsername);
		    			result=prepare.executeQuery();
		    			if(result.next()) {
		    				su_username.setText("");
		    				useralready.setVisible(true);
		    			}
		    			else if(su_password.getText().length()<8) {
		    				su_password.setText("");
		    				shortpass.setVisible(true);
		    			}else {
		    				
		    			
		    			prepare=c.prepareStatement(reData);
		    			prepare.setString(1,su_username.getText());
		    			prepare.setString(2,su_password.getText());
		    			prepare.setString(3,(String)su_question.getSelectionModel().getSelectedItem());
		    			prepare.setString(4,su_answer.getText());
		    		
		    			prepare.executeUpdate();
		    			
		    			alert=new Alert(AlertType.INFORMATION);
			    		alert.setTitle("Information Message");
			    		alert.setHeaderText(null);
			    		alert.setContentText("Sucessfully registered Account!");
			    		alert.showAndWait();

		    			su_username.setText("");
		    			su_password.setText("");
		    			su_question.getSelectionModel().clearSelection();
		    			su_answer.setText("");
		    			
		    			TranslateTransition slider = new TranslateTransition();

		    		  	 slider.setNode(side_form);
					        slider.setToX(0);

					    
					    slider.setDuration(Duration.seconds(.5));
					    slider.play();
		    			}
		    		}catch(Exception e) {
		    			e.printStackTrace();
		    			
		    		}
		    	}
		    }
		    
		    public void reLquestionList() {
		    	List<String> ListQ= new ArrayList<>();
		    	
		    	for(String data: questionList) {
		    		ListQ.add(data);
		    	}
		    	ObservableList  ListData= FXCollections.observableArrayList(ListQ);
		    	su_question.setItems(ListData);
		    }
		    
		    public void proceedBtn() {
		        if (forgot_username.getText().isEmpty()) {
		            forgot_eusername.setVisible(true);
		        } else if (forgot_question.getSelectionModel().getSelectedItem() == null || forgot_answer.getText().isEmpty()) {
		            forgot_errorq.setVisible(true);
		        } else {
		            String selectData = "SELECT username, question, answer FROM acc WHERE username=? and question=? and answer=?";
		            c = jd.getConnection();

		            try {
		                prepare = c.prepareStatement(selectData);
		                prepare.setString(1, forgot_username.getText());
		                prepare.setString(2, (String) forgot_question.getSelectionModel().getSelectedItem());
		                prepare.setString(3, forgot_answer.getText());
		                 result = prepare.executeQuery();

		                if (result.next()) {
		                    side_changepass.setVisible(true);
		                    side_forgot.setVisible(false);
		                } else {
		                    Alert alert = new Alert(AlertType.ERROR);
		                    alert.setTitle("Error Message");
		                    alert.setHeaderText(null);
		                    alert.setContentText("Incorrect Information");
		                    alert.showAndWait();
		                }
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        }
		    }
		    
		    public void changePassBtn() {
		    	
		    	if (change_newpass.getText().length() < 8) {
		    	    change_shortpass.setVisible(true);
		    	} else if (change_confirm.getText().equals(change_newpass.getText())) {
		    	    c = jd.getConnection();

		    	    try {
		    	        String Updatepass = "UPDATE acc SET password = ? WHERE username = ?";
		    	        prepare = c.prepareStatement(Updatepass);
		    	        prepare.setString(1, change_newpass.getText());
		    	        prepare.setString(2, forgot_username.getText());
		    	        Alert successAlert = new Alert(AlertType.INFORMATION);
		    	        successAlert.setTitle("successfully!");
		    	        successAlert.setHeaderText(null);
		    	        successAlert.setContentText("Password changed successfully!");
		    	        successAlert.showAndWait();	
		    	        side_loginform.setVisible(true);
		    	        side_changepass.setVisible(false);
		    	    } catch (Exception e) {
		    	        e.printStackTrace();
		    	    }
		    	} else {
		    	    change_equal.setVisible(true);
		    	}
		    }

		    @FXML
		    private TextField su_username;
				TranslateTransition slider = new TranslateTransition();
				
				public void switchForgot() {
					side_forgot.setVisible(true);
					side_loginform.setVisible(false);
					ForgotQuestion();
					
				}
				
				public void backLogin() {
				side_loginform.setVisible(true);
				side_forgot.setVisible(false);
				forgot_answer.setText("");
				forgot_username.setText("");
				forgot_eusername.setVisible(false);
				}
				
				public void backForgot() {
					side_forgot.setVisible(false);
					side_forgot.setVisible(true);
					change_newpass.setText("");
					change_confirm.setText("");
					change_shortpass.setVisible(false);
					change_equal.setVisible(false);
					forgot_answer.setText("");
					forgot_username.setText("");
					forgot_eusername.setVisible(false);
					side_changepass.setVisible(false);

					}
				
				
				
				@FXML
				public void switchForm(ActionEvent event) {
					 slider = new TranslateTransition();
				    slider.setNode(side_form);
				    if (event.getSource() == si_register) {
				    	 slider.setNode(side_form);
				        slider.setToX(300);
				        
				        reLquestionList();
				        
				        


				    } else if (event.getSource() == su_login) {
				    	 slider.setNode(side_form);
				        slider.setToX(0);
				        

				    }
				    slider.setDuration(Duration.seconds(.5));
				    slider.play();
				}
				
				@Override
				public void initialize(URL arg0, ResourceBundle arg1) {
					// TODO Auto-generated method stub
					
				}
			}






