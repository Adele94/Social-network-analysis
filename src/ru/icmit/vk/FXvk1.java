package ru.icmit.vk;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.responses.GetResponse;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

public class FXvk1 extends Application {

	Stage window;

	public static void main(String[] args) {
		launch(args);
	}

	public String spaceit(int x) {
		x = 24 - x;
		String s = "";

		while (x-- > 0) {
			s += " ";
		}
		return s;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			window = primaryStage;
			window.setTitle("VK");

			GridPane pane = new GridPane();
			pane.setPadding(new Insets(10, 10, 10, 10));
			pane.setVgap(10);
			pane.setHgap(13);

			Label label00 = new Label("User ID");
			GridPane.setConstraints(label00, 0, 0);

			TextField textInput = new TextField();
			textInput.setPromptText("Add user ID");
			GridPane.setConstraints(textInput, 1, 0);

			Label label11 = new Label(), label01 = new Label("First name");
			Label label12 = new Label(), label02 = new Label("Last name");
			Label label13 = new Label(), label03 = new Label("Hometown");
			Label label14 = new Label(), label04 = new Label("Grade date");
			Label label15 = new Label(), label05 = new Label("Education");
			Label label16 = new Label(), label06 = new Label("Interest");
			Label label17 = new Label(), label07 = new Label("Number of followers");
			Label label18 = new Label(), label08 = new Label("Domain");
			Label label19 = new Label(), label09 = new Label("Progress dynamic");

			Button button = new Button("Request data");
			GridPane.setConstraints(button, 2, 0);
			GridPane.setConstraints(label01, 0, 1);
			GridPane.setConstraints(label02, 0, 2);
			GridPane.setConstraints(label03, 0, 3);
			GridPane.setConstraints(label04, 0, 4);
			GridPane.setConstraints(label05, 0, 5);
			GridPane.setConstraints(label06, 0, 6);
			GridPane.setConstraints(label07, 0, 7);
			GridPane.setConstraints(label08, 0, 8);
			GridPane.setConstraints(label09, 0, 9);
			GridPane.setConstraints(label11, 1, 1);
			GridPane.setConstraints(label12, 1, 2);
			GridPane.setConstraints(label13, 1, 3);
			GridPane.setConstraints(label14, 1, 4);
			GridPane.setConstraints(label15, 1, 5);
			GridPane.setConstraints(label16, 1, 6);
			GridPane.setConstraints(label17, 1, 7);
			GridPane.setConstraints(label18, 1, 8);
			GridPane.setConstraints(label19, 1, 9);
			Label label010 = new Label("Progress prediction");
			Label label011 = new Label("Degree of adaptation:");
			Label label111 = new Label(), label110 = new Label();
			GridPane.setConstraints(label010, 0, 10);
			GridPane.setConstraints(label011, 0, 11);
			GridPane.setConstraints(label111, 1, 11);
			GridPane.setConstraints(label110, 1, 10);

			pane.getChildren().addAll(label00, textInput, button);
			pane.getChildren().addAll(label01, label11, label02, label12, label03, label13, label04, label14, label05,
					label15);
			pane.getChildren().addAll(label06, label16, label07, label17, label08, label18, label09, label19);
			pane.getChildren().addAll(label010, label011, label111, label110);
			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					setUserAgentStylesheet(STYLESHEET_CASPIAN);
					// com.vk.api.sdk.objects.friends.responses.GetResponse grf =
					// vk.friends().get(6801274).userId(10385605).execute();

					UserActor ua = new UserActor(31712689,
							"61b9758c61b9758c61b9758c8661d014b7661b961b9758c3d28736c0290f32f58c9c547");
					TransportClient transportClient = new HttpTransportClient();
					VkApiClient vk = new VkApiClient(transportClient);
					/*
					String c = line.substring(15);
					VkUsers h1 = new VkUsers(494345417,
							"d19d5e9b37ad36e13a2a4fd2e67911bef309c14f006a57371d4827ad86dad575f58b0425e0a76dac6a78f");

					try {
						int b = h1.getVkUser(c).getId();
						GetResponse getResponse = vk.groups().get(ua).userId(b).count(1000).execute();
					*/


					try {
						//VkUsers h1 = new VkUsers();
						TestVKAPI h2 = new TestVKAPI();
						String userId = textInput.getText();
						//h1.setActorId(31712689);
						//h1.setToken("d19d5e9b37ad36e13a2a4fd2e67911bef309c14f006a57371d4827ad86dad575f58b0425e0a76dac6a78f");
						//h1.getVkUser("31712689").getCity();

                        VkUsers h1 = new VkUsers(31712689,
                                "61b9758c61b9758c61b9758c8661d014b7661b961b9758c3d28736c0290f32f58c9c547");
						label11.setText(h1.getVkUser(userId).getFirstName());
						if (h1.getVkUser(userId).getCity() == null)
							label13.setText("This field is empty.");
						else
							label13.setText(h1.getVkUser(userId).getCity().getTitle());
                        Thread.sleep(1000);
                        if (h1.getVkUser(userId).getLastName().isEmpty())
							label12.setText("This field is empty.");
						else
							label12.setText("" + h1.getVkUser(userId).getLastName());
                        Thread.sleep(1000);
                        if (h1.getVkUser(userId).getEducationForm()== null)
							label14.setText("This field is empty");
						else
							label14.setText(h1.getVkUser(userId).getEducationForm());
                        Thread.sleep(1000);
						if (h1.getVkUser(userId).getUniversityName().isEmpty())
							label15.setText("This field is empty");
						else
							label15.setText(h1.getVkUser(userId).getUniversityName());
                        Thread.sleep(1000);
                        if (h1.getVkUser(userId).getFollowersCount() == null)
							label17.setText("This field is empty");
						else
							label17.setText(h1.getVkUser(userId).getFollowersCount().toString());
						label14.setText("19.03.2019");
						label19.setText("высокая");
						label110.setText("средний");
						label111.setText("высокая");
						label18.setText(h1.getVkUser(userId).getDomain());
						// label19.setText(h1.getVkUser(userId).getAbout());
                        if (h1.getVkUser(userId).getInterests().isEmpty())
                            label16.setText("This field is empty");
                        else
                            label16.setText(h1.getVkUser(userId).getInterests());
					} catch (Exception  e) {
						e.printStackTrace();
					}
				}
			});

			button.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

			pane.setStyle("-fx-background-color: AQUA;");

			Scene scene = new Scene(pane, 700, 300);
			window.setScene(scene);
			window.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String func(String arg) {
		return arg;
	}
}
