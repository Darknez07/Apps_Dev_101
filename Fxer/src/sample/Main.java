package sample;

//import com.sun.prism.Image;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {
    public  static final int LIMIT = 8;
    @Override
    public void start(Stage primaryStage) throws Exception{
        // FXML file load for creating an App
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        // Button to do something
        final TextField textField = new TextField();
        textField.lengthProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                // Check if the new character is greater than LIMIT
                if (textField.getText().length() >= LIMIT) {

                    // if it's 11th character then just setText to previous
                    // one
                    textField.setText(textField.getText().substring(0, LIMIT));
                }
            }
        });

        FileInputStream input = new FileInputStream("C:\\wamp64\\www\\VideoTube\\assests\\images\\icons\\thumb-up.png");
        Image imag = new Image(input);
        ImageView img = new ImageView(imag);
        ColorAdjust ca = new ColorAdjust();
        ca.setBrightness(0.001);
        ca.setContrast(0.4);
        ca.setSaturation(0.001);
        ca.setHue(0.3);
        Label bin = new Label("Binary Number");
        Button b2 = new Button("Convert to Decimal");
        b2.setStyle("-fx-font-size: 8pt;");
        b2.setStyle("-fx-border-radius: 5px;");
        GridPane gp = new GridPane();
        b2.setWrapText(true);
        gp.setAlignment(Pos.CENTER_LEFT);
        // Attaching button with action Event
        gp.addRow(0, bin,textField);
        gp.addRow(1, b2);
        AtomicInteger p = new AtomicInteger(2);
        b2.setOnAction(actionEvent -> {
            var i = p.getAndIncrement();
            if(i > 2){
                    gp.getChildren().remove(gp.getChildren().toArray().length - 1);
            }
                b2.setEffect(ca);
                b2.setEffect(new DropShadow());
                int result = bin2dec(textField.getText());
                Label l = new Label();
                l.setFont(new Font("Arial", 24));
                if (result == -1) {
                    l.setText("Invalid Input");
                } else {
                    l.setText(Integer.toString(result));
                }
                gp.addRow(i,l);
        });
        //Setting the Stage- > Scene -> Graph.
        Scene s = new Scene(gp,300,300);
        primaryStage.setTitle("Bin2Dec");
        primaryStage.setScene(s);
        primaryStage.show();
    }

    private int bin2dec(String text) {
        int dec=0;
        for(int i=text.length()-1;i>=0;i--) {
            var c = text.charAt(text.length() - i - 1);
            String cm = String.valueOf(c);
            if (cm.equals("1") || cm.equals("0")) {
                try {
                    int val = Integer.parseInt(String.valueOf(c));
                    dec += Math.pow(2, i) * val;
                } catch (Exception ex) {
                    return -1;
                }
            }else{
                return -1;
            }
        }
        return dec;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
