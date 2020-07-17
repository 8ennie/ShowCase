package com.school.monitor;


import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.school.monitor.MonitorBrowserApplication.StageReadyEvent;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

	@Override
	public void onApplicationEvent(StageReadyEvent event) {
//		// TODO Auto-generated method stub
//		Stage stage = event.getStage();
//		Text test = new Text("Test");
//		VBox box = new VBox(test);
//		stage.setScene(new Scene(box, 800, 600));
//		stage.show();
	}

}
