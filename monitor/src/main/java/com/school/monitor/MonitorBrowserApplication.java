package com.school.monitor;


import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MonitorBrowserApplication extends Application {

	public static Stage stage;
	private ConfigurableApplicationContext applicationContext;
	
	public static void changeWebsite(String url) {
		WebView webView = new WebView();
		webView.getEngine().load(url);
		stage.setScene(new Scene(webView, 800, 600));
		stage.show();
		stage.setFullScreen(true);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		applicationContext.publishEvent(new StageReadyEvent(primaryStage));
		stage = primaryStage;
    }

	@Override
	public void init() {
	    applicationContext = new SpringApplicationBuilder(MonitorApplication.class).run();
	}

	@Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }
	
	static class StageReadyEvent extends ApplicationEvent {

		/**
		 * 
		 */
		private static final long serialVersionUID = -527745509655500928L;

		public StageReadyEvent(Stage stage) {
            super(stage);
        }
		
		public Stage getStage() {
	        return ((Stage) getSource());
	    }
    }
}
