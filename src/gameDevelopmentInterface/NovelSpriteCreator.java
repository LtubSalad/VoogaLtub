package gameDevelopmentInterface;

import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class NovelSpriteCreator extends VBox {
	private ComboBox<Button> myComponents = new ComboBox<Button>();
	private ComboBox<Button> mySkills = new ComboBox<Button>();
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "ComponentResource";
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	private Text title = new Text("Make a new sprite!");
	private Button saveSprite = new Button("Save");
	
	public NovelSpriteCreator() {
		makeComboBoxes();
		this.getChildren().addAll(title, myComponents, mySkills, saveSprite);
	}
	
	private void makeComboBoxes() {
		myResources.keySet().forEach(e -> {
			Button b = new Button(e);
			b.setOnAction(f -> {
				//pop up box with correct layout for this "e" component
			});
			myComponents.getItems().add(b);
		});
		//TODO skills
	}

}
