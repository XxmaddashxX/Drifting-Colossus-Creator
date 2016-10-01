package net.driftingcolossus.creator.graphics.gui.splash;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import net.driftingcolossus.creator.management.VersionProvider;
import sun.misc.BASE64Decoder;

public class SplashScreen extends Pane{

	private static SplashScreen instance;

	private ProgressIndicator indicator_services;
	private ProgressIndicator indicator_io;
	private ProgressIndicator indicator_guis;
	private ProgressIndicator indicator_plugins;
	private ProgressIndicator indicator_update;
	private ProgressIndicator indicator_utils;
	private Label label_services;
	private Label label_io;
	private Label label_guis;
	private Label label_plugins;
	private Label label_update;
	private Label label_utils;

	public SplashScreen(){

		try {
			addBackground();
			addIndicators();
			addVersion();
			addLabels();
			addTitle();
			instance = this;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}










	private void addBackground() throws IOException{

		BASE64Decoder d = new BASE64Decoder();
		ByteArrayInputStream b = new ByteArrayInputStream(d.decodeBuffer(getClass().getResourceAsStream("spl.ca")));


		ImageView view = new ImageView();

		view.prefWidth(480);
		view.prefHeight(260);

		view.setViewport(new Rectangle2D(400, 460, 480, 260));

		view.setImage(new Image(b));

		getChildren().add(view);

		b.close();
	}
	private void addIndicators() {

		indicator_services = createNewIndicator(15.0f, 147.0f, "-fx-accent: red;", Color.RED);
		indicator_io = createNewIndicator(90.0f, 147.0f, "-fx-accent: orange;", Color.ORANGE);
		indicator_guis = createNewIndicator(165.0f, 147.0f, "-fx-accent: yellow;", Color.YELLOW);
		indicator_plugins = createNewIndicator(241.0f, 147.0f, "-fx-accent: lime;", Color.LIME);
		indicator_update = createNewIndicator(316.0f, 147.0f, "-fx-accent: cyan;", Color.CYAN);
		indicator_utils = createNewIndicator(391.0f, 147.0f, "-fx-accent: magenta;", Color.MAGENTA);

		getChildren().addAll(indicator_services, indicator_io, indicator_guis, indicator_plugins, indicator_update, indicator_utils);

	}

	private void addLabels() {

		label_services = new Label();
		{
			label_services.setText("Searching");
			label_services.setLayoutX(23);
			label_services.setLayoutY(130);
			label_services.setFont(new Font("System Bold", 11));
		}

		label_io = new Label();
		{
			label_io.setText("Searching");
			label_io.setLayoutX(111);
			label_io.setLayoutY(130);
			label_io.setFont(new Font("System Bold", 11));
		}

		label_guis = new Label();
		{
			label_guis.setText("Searching");
			label_guis.setLayoutX(180);
			label_guis.setLayoutY(130);
			label_guis.setFont(new Font("System Bold", 11));
		}

		label_plugins = new Label();
		{
			label_plugins.setText("Searching");
			label_plugins.setLayoutX(251);
			label_plugins.setLayoutY(130);
			label_plugins.setFont(new Font("System Bold", 11));
		}

		label_update = new Label();
		{
			label_update.setText("Searching");
			label_update.setLayoutX(326);
			label_update.setLayoutY(130);
			label_update.setFont(new Font("System Bold", 11));
		}

		label_utils = new Label();
		{
			label_utils.setText("Searching");
			label_utils.setLayoutX(407);
			label_utils.setLayoutY(130);
			label_utils.setFont(new Font("System Bold", 11));
		}

		getChildren().addAll(label_services, label_io, label_guis, label_plugins, label_update, label_utils);

	}


	private void addTitle() {
		Label title = new Label();
		title.setText("Drifting Colossus Creator");
		title.setFont(new Font("System Bold", 25.0));
		title.setAlignment(Pos.BASELINE_RIGHT);
		title.setLayoutX(14.0);
		title.setLayoutY(14.0);
		title.setPrefHeight(51.0);
		title.setPrefWidth(456.0);

		getChildren().add(title);

	}

	private void addVersion(){
		Label label = new Label();
		label.setText(VersionProvider.getVersionAsString());
		label.setLayoutX(374);
		label.setLayoutY(229);

		getChildren().add(label);
	}

	private ProgressIndicator createNewIndicator(float x, float y, String style, final Color textColor){
		final ProgressIndicator in = new ProgressIndicator();
		in.setLayoutX(x);
		in.setLayoutY(y);
		in.setPrefSize(75, 70);
		in.setStyle(style);
		in.setProgress(ProgressBar.INDETERMINATE_PROGRESS);

		in.progressProperty().addListener(new ChangeListener<Number>(){

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				in.applyCss();
				Text text = (Text)in.lookup(".text.percentage");
				if(text != null){
					text.setFill(textColor);
				}
			}



		});
		return in;
	}


	public void setServicesAmount(int done, int total){
		label_services.setText("Services [" + done + "/" + total + "]");
	}
	public void setIOAmount(int done, int total){
		label_io.setText("IO [" + done + "/" + total + "]");
	}
	public void setGUISAmount(int done, int total){
		label_guis.setText("GUIS [" + done + "/" + total + "]");
	}
	public void setPluginsAmount(int done, int total){
		label_plugins.setText("Plugins [" + done + "/" + total + "]");
	}
	public void setUpdateAmount(int done, int total){
		label_update.setText("Update [" + done + "/" + total + "]");
	}
	public void setUtilsAmount(int done, int total){
		label_utils.setText("Utils [" + done + "/" + total + "]");
	}


	public static SplashScreen getInstance(){
		return instance;
	}



}
