package org.example.Controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.Dao.MedicineDao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateMedicineController {

    private Stage mainwindow ;
    private Scene scene ;
    @FXML
    private TextField medid;
    @FXML
    private TextField name;
    @FXML
    private TextField prix;
    @FXML
    private TextField company;
    @FXML
    private TextField quantity;
    @FXML 
    private TextField addquantity;
    @FXML
    private Button update;
    @FXML
    private Button search;
    @FXML
    private Label label ;
    @FXML 
    private Button back ;

    private MedicineDao m=new MedicineDao() ;

    private boolean bol=false ; //pour effectuer un test si l'user a chercher un medicament avant la modification

    private String id;
    private String name2;
    private String prix2;
    private String company2;
    private String quantity2;

    public void setmainwindow (Stage stage){
        this.mainwindow=stage;
    }

    @FXML
    public void search(ActionEvent event){
        try{
            id=medid.getText();
            if(id.trim().isEmpty()) afficheAlert(AlertType.WARNING,"Search failed","Medicine-id field is empty");
            else{
                ResultSet res= m.search1(id) ;
                if(!res.isBeforeFirst()) afficheAlert(AlertType.ERROR,"Incorrect input information","Medicine not found");
                else{
                    bol=true;
                    while(res.next()){
                        label.setText("");
                        name.setText(res.getString("name"));
                        prix.setText(String.valueOf(res.getInt("price")));
                        company.setText(res.getString("company"));
                        quantity.setText(String.valueOf(res.getInt("quantity")));
                    }
                        name2=name.getText();
                        prix2=prix.getText();
                        quantity2=quantity.getText();
                        company2=company.getText();
                }
            }
        }
        catch(SQLException e){
            e.getMessage();
        }
}

    @FXML
    public void update(ActionEvent event)throws IOException{
        if(!bol) afficheAlert(AlertType.WARNING,"update failed","search a medicine first"); //test de recherche avant modification
        else{
            TextField[] f={medid,name,prix,quantity,company};
            if(testfield(f)) afficheAlert(AlertType.WARNING,"update failed","one or more fields are empty");
            else{
                if(!id.equals(medid.getText()) || !name2.equals(name.getText()) || !company2.equals(company.getText()) || !quantity2.equals(quantity.getText())) 
                    {afficheAlert(AlertType.WARNING,"update failed","you can't Update this field");
                    medid.setText("");
                        name.setText("");
                        prix.setText("");
                        company.setText("");
                        quantity.setText("");
                        addquantity.setText("");
                }
                else{
                if (prix2.equals(prix.getText())  && addquantity.getText().isEmpty()) afficheAlert(AlertType.WARNING,"update failed","no fields are updated");
                else {
                    try{
                        m.update1(prix.getText(),id);
                        if(!addquantity.getText().isEmpty()){
                            int i=Integer.parseInt(addquantity.getText())+Integer.parseInt(quantity.getText());
                            String q=String.valueOf(i);
                            m.update2(q, id);
                        }
                        afficheAlert(AlertType.INFORMATION,"update done","update successfully done");
                        search(null);
                        addquantity.setText("");
                    }
                    catch(SQLException e){
                    e.getMessage();
                    }
                }
            }
        }
        }
        }
    
    @FXML
    public void back(ActionEvent event)throws IOException{
        
    }

    private boolean testfield(TextField[] textFields) {
        for (TextField textField : textFields) {
            if (textField.getText() == null || textField.getText().trim().isEmpty()) {
                return true; // Si un champ est vide
            }
        }
        return false; // Tous les champs sont remplis
    }

    private void afficheAlert(Alert.AlertType type,String title,String context){
        Alert err =new Alert(type);
        err.setTitle(title);
        err.setContentText(context);
        err.getDialogPane().setStyle(
            "-fx-font-family: 'Arial Bold'; " +
            "-fx-font-size: 20px; " +
            "-fx-background-color: #f9f9f9; " +
            "-fx-text-fill: #333;"
        );
        err.show();
    }
}



