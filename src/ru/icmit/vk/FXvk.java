package ru.icmit.vk;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FXvk extends Application {

public static void main(String[] args){
    launch(args);
}

@Override
public void start(Stage primaryStage) throws Exception {
    try{
        VBox pane = new VBox();

        Label label1 = new Label(" ID ????????????");
    
        pane.getChildren().addAll(label1);

        TextField textInput = new TextField();
        pane.getChildren().addAll(textInput);

        Button button = new Button("????????? ??????");
        pane.getChildren().addAll(button);

        Label label2 = new Label("?????????:");
        Label result = new Label("???:");
        pane.getChildren().addAll(label2, result);

        button.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                	VkUsers h1=new VkUsers(31712689, "61b9758c61b9758c61b9758c8661d014b7661b961b9758c3d28736c0290f32f58c9c547");
                	String userId = textInput.getText();
                	
                	result.setText(h1.getVkUser(userId).getFirstName());
                }
            });


        Scene scene = new Scene(pane, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("?????????? ???????? ???????");
        primaryStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

public String func(String arg){
    return arg;
}
}
