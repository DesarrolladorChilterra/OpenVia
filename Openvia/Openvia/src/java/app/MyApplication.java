/*
 * MyApplication.java
 *
 * Created on 19 de junio de 2013, 04:00 PM
 */
 
package app;           

import com.vaadin.Application;
import com.vaadin.ui.*;
/** 
 *
 * @author Miguelon
 * @version 
 */

public class MyApplication extends Application {

    @Override
    public void init() {
        setTheme("openvia");
	Window mainWindow = new Window("MyApplication");
        Label label = new Label("Hello Vaadin user");
	mainWindow.addComponent(label);
	setMainWindow(mainWindow);

        mainWindow.addWindow(new WProcesoDTE());

    }

}
