package gameDevelopmentInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import data.AttributeData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;

public class AttributeSelectorPane extends VBox {
	private final double prefWidth = 150;
	private final double prefHeight = 200;
	private AttributeDataFactory fileToDataConverter;
	private List<String> attributeNames;
	private List<String> presetAttributes;
	private List<String> userCreatedAttributes;
	private List<String> usedAttributes;
	private AttributeData attributeHolder;

	public AttributeSelectorPane(AttributeData attributeHolder) {
		fileToDataConverter=new AttributeDataFactory();
		this.attributeHolder=attributeHolder;
		makeAttributeLists();
		Node customAttributesDisplay = makeAttributesList("Add Custom Attributes", attributeNames);
		Node presetAttributesDisplay = makeAttributesList("Add Preset Attributes", presetAttributes);
		Node userCreatedAttributesDisplay = makeAttributesList("Add User-Created Attributes", userCreatedAttributes);
		Node thisClassesAttributes = makeAttributesList("Edit This Class' Attributes", usedAttributes);
		this.setPrefSize(prefWidth, prefHeight);
		this.getChildren().addAll(customAttributesDisplay, presetAttributesDisplay, userCreatedAttributesDisplay,
				thisClassesAttributes);
	}

	private void makeAttributeLists() {
		attributeNames = new ArrayList<String>();
		attributeNames.add("Actor");
		attributeNames.add("Sprite");
		attributeNames.add("EntityFinder");
		attributeNames.add("ObjectCreator");
		attributeNames.add("SoundPlayer");

		presetAttributes = new ArrayList<String>();
		presetAttributes.add("Tower");
		presetAttributes.add("Monster");
		presetAttributes.add("Map");

		usedAttributes = new ArrayList<String>();

		userCreatedAttributes = new ArrayList<String>();
	}

	private Node makeAttributesList(String listTitle, List<String> attributes) {
		VBox attributeDisplay = new VBox();
		ObservableList<String> observableAttributeNames = FXCollections.observableList(attributes);
		ListView<String> attributesBox = new ListView<String>();
		attributesBox.setItems(observableAttributeNames);

		attributesBox.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> list) {
				return new AttributeCustomizerOption();
			}
		});

		attributesBox.setPrefWidth(prefWidth);
		Label title = new Label(listTitle);
		attributeDisplay.getChildren().addAll(title, attributesBox);
		return attributeDisplay;
	}

	class AttributeCustomizerOption extends ListCell<String> {
		@Override
		public void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			if (item == null) {
				return;
			}
			Button attributeCustomizer = new Button(item);
			attributeCustomizer.setOnAction((c) -> {
				FileChooser fc = new FileChooser();
				fc.setInitialDirectory(new File(System.getProperty("user.dir")));
				new AttributeCustomizerPopup(fileToDataConverter.produceAttribute(fc.showOpenDialog(new Stage())));
			});
			attributeCustomizer.setPrefWidth(prefWidth);
			setGraphic(attributeCustomizer);
		}
	}

}
