package com.app.warehouse;

import com.app.warehouse.view.PrimaryStageView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WarehouseApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        launchApp(WarehouseApplication.class, PrimaryStageView.class, args);
        SpringApplication.run(WarehouseApplication.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
    }

}
