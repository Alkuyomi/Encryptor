/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package encryptor;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Ahmed
 */
public class Encryptor extends Application {
    
    TextArea  text , en_text;
    TextField  password ;
    Button en_button , de_button , clean_button;
    
    
    String text_str = "" ;
    
       final char alphabet[] = {'a' , 'b' , 'c' , 'd' , 'e' , 'f' , 'g' , 'h' , 'i' ,
                                'j' , 'k' , 'l' , 'm' , 'n' , 'o' , 'p' , 'q' , 'r' ,
                                's' , 't' , 'u' , 'v' , 'w' , 'x' , 'y' , 'z' , ' ' ,
                                'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G' , 'H' , 'I' ,
                                'J' , 'K' , 'L' , 'M' , 'N' , 'O' , 'P' , 'Q' , 'R' ,
                                'S' , 'T' , 'U' , 'V' , 'W' , 'X' , 'Y' , 'Z'  };
        
        final char encrypted_alphabet[] = {'~' , '!' , '`' , '@' , '#' , '$' , '%' , '^' , '&' ,
                                           '*' , '(' , '_' , ')' , '-' , '+' , '=' , '}' , '{' ,
                                           '"' , ':' , ';' , '|' , '/' , '?' , '>' , '<' , 'm' ,
                                           'n' , 'b' , 'v' , 'c' , 'x' , 'z' , 'a' , 's' , 'd' ,
                                           'f' , 'g' , 'h' , 'j' , 'k' , 'l' , 'q' , 'w' , 'e' ,
                                           'r' , 't' , 'y' , 'u' , 'i' , 'o' , 'p' , 'H'      };
        
    int c = 0 ;
    @Override
    public void start(Stage window) {
        
     
        clean_button = new Button("Clean");
        text = new TextArea();
        en_button  = new Button("Encrypt");
        en_text = new TextArea();
        password = new TextField();
        de_button = new Button("Decrypt");
        
        text.setPromptText("Enter the text or code here");
        password.setPromptText("Password");
        en_text.setEditable(false);
        HBox layout = new HBox(27);
        
       
        layout.getChildren().add(clean_button);
        layout.getChildren().addAll(en_button ,de_button ,password);
        Alert msg = new Alert(AlertType.WARNING);
        msg.setTitle("WARNING MESSAGE");
        msg.setHeaderText(null);
       
        clean_button.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent t) {
                text.setText("");
                password.setText("");
                en_text.setText("");
                
            }
        
    });
      
        en_button.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent t) {
                       

                c = 0 ;
                text_str = "" ;
                
                if(text.getText().length() == 0 || password.getText().length() == 0){
                     msg.setContentText("Please enter the requsted data ");
                    msg.showAndWait();
                }else if (password.getText().length() > text.getText().length()){
                     msg.setContentText("The password must be less than the text");
                    msg.showAndWait();
                }else{
              for (int i = 0 ; i < text.getText().length() ; i++){
                  
                  for(int y = 0 ; y < alphabet.length ; y++){
                      if(text.getText().charAt(i) == alphabet[y]){
                          text_str += encrypted_alphabet[y] ;
                          break ;
                      }
                  }
                  
                  for(int y = 0 ; y < alphabet.length ; y++){
                      if(c < password.getText().length() ){
                        if(password.getText().charAt(c) == alphabet[y]){
                            text_str += encrypted_alphabet[y] ;
                            c += 1;
                            break ;
                          }
                      }else{
                            c = 0 ;
                            if(password.getText().charAt(c) == alphabet[y]){
                            text_str += encrypted_alphabet[y] ;
                            c++ ;
                            break ;
                              }
                        }
                  }
                  
                  
                  
                  
              }
             
              text_str += password.getText().length();
              en_text.setText(text_str);
                }
            }
         
    });
        
        de_button.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent t) {
                 text_str = "" ;
                //last char
               
                 char c = text.getText().charAt(text.getText().length()-1) ;
                String cs = ""+c ;
                int n = Integer.parseInt(cs);
                
                //password
                String pass = "";
                for(int i = 1 ; i < n*2 ;i += 2){
                    for (int y = 0 ; y < alphabet.length ; y++)
                         if(text.getText().charAt(i) == encrypted_alphabet[y]){
                          pass +=  alphabet[y];
                          break ;
                      }
                }
                
                //------------
               
                    
                    for(int i = 0 ; i < text.getText().length()-2 ;i += 2){
                    for (int y = 0 ; y < alphabet.length ; y++)
                         if(text.getText().charAt(i) == encrypted_alphabet[y]){
                          text_str +=  alphabet[y];
                          break ;
                      }
                }
                  
               boolean checkPass = false ;
               try{
               for(int i = 0 ; i < pass.length() ; i++){
                  
                    if (password.getText().charAt(i) == pass.charAt(i) && pass.length() == password.getText().length()){
                       checkPass = true ;
                   }
                   else{
                          checkPass = false ;
                          break ;
                     }
               }}catch(Exception e){
                    checkPass = false ;
               }
              
            if(checkPass == true)
                en_text.setText(text_str);
            else{
                 msg.setContentText("You have entered a wrong password !");
                 msg.showAndWait();
            }
                
              
            }
        
    });
        
       
        
        BorderPane main_layout = new BorderPane();
        main_layout.setTop(text);
        main_layout.setCenter(en_text);
        main_layout.setBottom(layout);
      
        Scene scene = new Scene(main_layout , 450 , 450);
        
        scene.getStylesheets().add(Encryptor.class.getResource("Style.css").toExternalForm());
        text.getStyleClass().add("comp");
        password.getStyleClass().add("password");
        en_text.getStyleClass().add("comp");
        
        
        en_button.getStyleClass().add("btn");
        de_button.getStyleClass().add("btn");
        clean_button.getStyleClass().add("btn");
        window.getIcons().add(new Image(Encryptor.class.getResourceAsStream("en.png")));
        main_layout.getStyleClass().add("layout");
        
        window.setScene(scene);
        window.setTitle("Encryptor");
        window.show();
       
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
