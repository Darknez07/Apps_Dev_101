package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.regex.Pattern;
import java.util.regex.MatchResult;
import java.util.concurrent.atomic.AtomicReference;

public class Main extends Application {
public int onlyNums(String s){
    int i =0;
    while (Character.isDigit(s.charAt(i))){
        i++;
    }
    return i;
}
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        StackPane sp = new StackPane();
        Rectangle r = new Rectangle();
        Region rect = new Region();
        rect.setStyle("-fx-border-color: black; " +"-fx-max-width: 250; -fx-max-height: 250;"+
                "-fx-border-style: dashed; -fx-border-radius: 0px 0px 0px 0px;");
        Pane p = new Pane();
        r.setStyle("-fx-border-width: 5;");
        rect.setLayoutX(200);
        rect.setLayoutY(200);
        TextField t = new TextField();
        TextField t2 = new TextField();
        TextField t3 = new TextField();
        TextField t4 = new TextField();
        t.setLayoutX(100);
        t.setLayoutY(100);
        t.setPrefWidth(50);
        t2.setLayoutX(350);
        t2.setLayoutY(100);
        t2.setPrefWidth(50);
        t3.setLayoutY(375);
        t3.setLayoutX(350);
        t3.setPrefWidth(50);
        t4.setLayoutY(375);
        t4.setLayoutX(100);
        t4.setPrefWidth(50);
        TextField ts = new TextField("0px 0px 0px 0px");
        ts.setLayoutX(150);
        ts.setLayoutY(200);
        ts.setEditable(false);
        ts.setStyle("    -fx-background-color: -fx-control-inner-background;" +
                "    -fx-background-insets: 0;" +
                "    -fx-padding: 1 3 1 3;");
        String olds = rect.getStyle();
        String finalOlds1 = olds;

        t4.textProperty().addListener((off,my,desk)->{
            String oner = rect.getStyle();
            if(desk.isEmpty()) {
                desk = Integer.toString(0);
            }if(desk.matches(".*[A-Za-z].*")){
                desk = Integer.toString(0);
            }
//            System.out.println("Four");
            int bods = oner.indexOf(" -fx-border-radius:");
            String adder = oner.substring(0,bods);
            int indexer = oner.indexOf("px")+2;
            String useful = oner.substring(bods,indexer);
            String only_bods = oner.substring(bods);
            String add_1 = only_bods.substring(0,only_bods.indexOf("px")+2);
            String next_px = only_bods.substring(only_bods.indexOf("px")+2);
            String add_2 = next_px.substring(0,next_px.indexOf("px")+2);
            String next_to_px = next_px.substring(next_px.indexOf("px")+2);
            String close = next_to_px.substring(next_to_px.indexOf("px")+2);
            String last = close.substring(close.indexOf("px"));
            last = desk+last;
            last = next_px.substring(0,next_px.length() - close.indexOf("px") - 2)+last;
            useful = add_1+last;
            String ps = useful.substring(useful.indexOf(":")+1);
            ts.setText(ps.substring(0,ps.length() -1));
            adder = adder + add_1+last;
            rect.setStyle(adder);
        });

        t3.textProperty().addListener((on,my,foot)->{
            String oner = rect.getStyle();
            if(foot.isEmpty()) {
                foot = Integer.toString(0);
            }if(foot.matches(".*[A-Za-z].*")){
                foot = Integer.toString(0);
            }
            int bods = oner.indexOf(" -fx-border-radius:");
            String adder = oner.substring(0,bods);
            int indexer = oner.indexOf("px")+2;
            String useful = oner.substring(bods,indexer);
            String only_bods = oner.substring(bods);
            String add_1 = only_bods.substring(0,only_bods.indexOf("px")+2);
            String next_px = only_bods.substring(only_bods.indexOf("px")+2);
            String add_2 = next_px.substring(0,next_px.indexOf("px")+2);
            String useful_right = oner.substring(indexer);
            String newuseful = useful_right.substring(useful_right.indexOf("px"));
            String finals = newuseful.substring(newuseful.indexOf("px")+2);
            String third = finals.substring(finals.indexOf("px"));
            finals = foot+third;
            finals = add_1+add_2+" "+finals;
            String ps = finals.substring(useful.indexOf(":")+1);
            ts.setText(ps.substring(0,ps.length() -1));
            adder = adder+finals;
            rect.setStyle(adder);
        });

        t2.textProperty().addListener((in,to,the)->{
            String oner = rect.getStyle();
            if(the.isEmpty()) {
                the = Integer.toString(0);
            }if(the.matches(".*[A-Za-z].*")){
                the = Integer.toString(0);
            }
            int bods = oner.indexOf(" -fx-border-radius:");
            String adder = oner.substring(0,bods);
            int indexer = oner.indexOf("px")+2;
            String useful = oner.substring(bods,indexer);
            String useful_right = oner.substring(indexer);
            String newuseful = the+useful_right.substring(useful_right.indexOf("px"));
            useful = useful+" "+newuseful;
            String ps = useful.substring(useful.indexOf(":")+1);
            ts.setText(ps.substring(0,ps.length() -1));
            adder = adder+useful;
            rect.setStyle(adder);
        });

        t.textProperty().addListener((observableValue, s, t1) -> {
            String oner = rect.getStyle();
            if(t1.isEmpty()) {
                t1 = Integer.toString(0);
            }if(t1.matches(".*[A-Za-z].*")){
                t1 = Integer.toString(0);
            }
            int on = oner.indexOf("-fx-border-radius");
            String simple = oner.substring(0,on);
            String complex = oner.substring(on);
            if( on!=-1){
                complex = complex.substring(complex.indexOf("px")+2);
                complex = "-fx-border-radius:"+t1+"px"+complex;
                String ps = complex.substring(complex.indexOf(":")+1);
                ts.setText(ps.substring(0,ps.length() -1));
                rect.setStyle(simple+complex);
            }
        });
        p.getChildren().add(t);
        p.getChildren().add(t2);
//        p.getChildren().add(rect);
        p.getChildren().add(t3);
        p.getChildren().add(t4);
        p.getChildren().add(ts);
        sp.getChildren().add(rect);
        sp.getChildren().add(p);
//        sp.getChildren().add(ps);
        Scene s = new Scene(sp,500,500);
        primaryStage.setScene(s);
        primaryStage.setFullScreen(false);
        primaryStage.toFront();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
