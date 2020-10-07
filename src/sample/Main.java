package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main extends Application {
    GridPane gp =  new GridPane();
    GridPane sp = new GridPane();
    Button[] l = new Button[17];

    private  String num ="";
    private List<String> history = new ArrayList<String>();

    private Button createbutton(String Number){
        Button btn = new Button(Number);
        btn.setMinSize(70, 70);
        return btn;
    }

    private int[] fillGp(){
        int row = 1;
        int col = 0;
        int j = 0;
        Button btn;
        for(int i=0;i<=10;i++){
            if(i == 3){
                btn = createbutton("C");
                btn.setFont(Font.font("Aerial",30));
                l[j++] = btn;
                gp.addRow(row,btn);
                btn = createbutton(Integer.toString(i));
                btn.setFont(Font.font("Aerial",30));
                l[j++] = btn;
                gp.addColumn(col,btn);
                row++;
            }else if(i==7){
                btn = createbutton(Integer.toString(i));
                btn.setFont(Font.font("Aerial",30));
                l[j++] = btn;
                gp.addColumn(col,btn);
                col++;
                row++;
            }else if(i == 10){
                btn = createbutton("AC");
                btn.setFont(Font.font("Aerial",26));
                l[j++] = btn;
                gp.addRow(row,btn);
            }else{
                btn = createbutton(Integer.toString(i));
                btn.setFont(Font.font("Aerial",30));
                l[j++] = btn;
                gp.addRow(row,btn);
            }
        }
        return new int[]{row+1, col-1};
    }

    private void fillCompsGp(int row, int col){
        Button btn;
        String[] s = {"+","-","x","=","<="};
        btn = createbutton(s[0]);
        btn.setFont(Font.font("Aerial",30));
        l[12] = btn;
        gp.addColumn(col,btn);
        for(int i=1;i<s.length-1;i++){
            btn =createbutton(s[i]);
            btn.setFont(Font.font("Aerial",30));
            l[12+i] = btn;
            gp.addRow(row,btn);
        }
        btn = createbutton(s[s.length-1]);
        btn.setFont(Font.font("Aerial",25));
        l[12+s.length-1] = btn;
        gp.addColumn(col+1,btn);
    }

    private int Compute(String num) {
        String[] split = num.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
        int a,b;
        if(split.length == 4){
                if(split[1].length() > 1){
                    if(split[1].split("")[0].equals("x") && split[1].split("")[1].equals("-")){
                        return Integer.parseInt(split[0])*Integer.parseInt(split[1].split("")[1]+split[2]);
                    }
                }
            }
        if(split.length == 5){
            if(split[0].equals("-")) {
                a = Integer.parseInt(split[0] + split[1]);
                b = Integer.parseInt(split[3]);
                if(split[2].equals("+")) {
                    System.out.println(a + b);
                    return a + b;
                } else if (split[2].equals("-")) {
                    System.out.println(-(-a + b));
                    return -(-a + b);
                } else if (split[2].equals("x")) {
                    System.out.println(a * b);
                    return a * b;
                }
            }
        }
        if(split.length == 4 && !split[0].equals("-")){
            if(split[1].equals("+")){
                a = Integer.parseInt(split[0]);
                b = Integer.parseInt(split[2]);
                return a+b;
            }else if(split[1].equals("-")){
                a = Integer.parseInt(split[0]);
                b = Integer.parseInt(split[2]);
                return  a-b;
            }else if(split[1].equals("x")){
                a = Integer.parseInt(split[0]);
                b = Integer.parseInt(split[2]);
                return a*b;
            }else if(split[3].equals("=")){
                Compute(num.substring(0,num.length()-1));
            }else if(split[2].equals("-")){
                a = Integer.parseInt(split[0]);
                b = Integer.parseInt(split[2]+split[3]);
                System.out.println(a*b);
                return  a*b;
            }
        }else{
            if(split[0].equals("-") && split.length == 5) {
                if (split[2].length() > 1) {
                    if (split[2].split("")[0].equals("x") && split[2].split("")[1].equals("-")){
                        return Integer.parseInt(split[0]+split[1])*Integer.parseInt(split[2].split("")[1]+split[3]);
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");

        // History Text area

        TextArea tf = new TextArea("");
        tf.setMinSize(280,45);
        tf.setMaxSize(280,45);
        tf.setEditable(false);

        // Editable Text area

        TextArea tf2 = new TextArea("");
        tf2.setMinSize(280,45);
        tf2.setMaxSize(280,45);
        tf2.setEditable(false);

        tf2.setFont(Font.font(Font.getFamilies().get(1),30));

        // This is for Panes
        sp.addColumn(0, tf);
        sp.addColumn(0, tf2);
        // Buttons 0 to 9, C and AC

        int[] r = fillGp();
        // Computational Buttons.

        final int[] com = {0};
        final String[] prev = {""};
        final int[] j = {0};
        fillCompsGp(r[0],r[1]);
        for(int i=0;i<l.length;i++){
            int finalI = i;
            l[finalI].setOnMouseClicked(ActionEvent->{
                try {
                     prev[0] = num.substring(num.length() - 1);
                }catch (Exception ex){
                    prev[0] = "";
                }
                if(l[finalI].getText().equals("0") && num.equals("")){
                    num="";
                }
                else if(l[finalI].getText().equals("AC")) {
                    history.clear();
                    num = "";
                } else if(l[finalI].getText().equals("C")) {
                       num = "";
                }else if(l[finalI].getText().equals("<=")){
                    try {
                        num = num.substring(0, num.length() - 1);
                    }catch (Exception ex){
                        tf2.setText("");
                    }
                }else if(l[finalI].getText().equals("+") && prev[0].equals("+")){
                    com[0] = Compute(num);
                }else if(l[finalI].getText().equals("-") && prev[0].equals("-")){
                    num = num.substring(0,num.length() - 1);
                    num+='+';
                }else if(l[finalI].getText().equals("-") && prev[0].equals("+")){
                    num = num.substring(0,num.length() - 1);
                    num+="-";
                }else if(l[finalI].getText().equals("+") && prev[0].equals("x")){
                    num = num.substring(0,num.length()-1);
                    num+="x";
                }else if(l[finalI].getText().equals("=") && Character.isDigit(prev[0].charAt(0)) && com[0] != 0){
                    num=num;
               } else if(l[finalI].getText().equals("+") && prev[0].equals("-")){
                    num = num.substring(0,num.length()-1);
                    num+="-";
                }
                else{
                    num+=l[finalI].getText();
                }
                String lastint;
                com[0] =  Compute(num);
                if( com[0] !=0) {
                    if(String.valueOf(com[0]).length() > 10){
                        num = "ERR";
                    }
                    if (!num.equals("ERR")) {
                        history.add(num.substring(0, num.length() - 1) + " = " + com[0]);
                        lastint = num.substring(num.length() - 1);
                        if (lastint.equals("=")) {
                            lastint = "";
                        }
                        num = Integer.toString(com[0]);
                        try {
                            if (!Character.isDigit(lastint.charAt(0)))
                                num += lastint;
                        } catch (Exception ex) {
                            num += lastint;
                        }
                    }
                }
                if(num.equals("ERR")) {
                    tf2.setText(num);
                    num="";
                }else {
                    tf2.setText(num);
                }
                Collections.reverse(history);
                tf.setText(String.join("\n", history));
                Collections.reverse(history);
                if(tf2.getText().equals("")){
                    num = "";
                }
            });
        }
        sp.addColumn(0, gp);
        //Prevent Full Screen
        primaryStage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                primaryStage.setMaximized(false);
        });
        System.out.println(Integer.MAX_VALUE);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(sp, 280, 445));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
