/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app;

import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 *
 * @author Miguelon
 */
public class WProcesoDTE1 extends Window {

    public WProcesoDTE1() {
        setCaption("Proceso DTE");
        setPositionX(100);
        setPositionY(50);
        setWidth("400px");


        VerticalLayout vl = new VerticalLayout();
        vl.setMargin(true);
        TextField tfEstado = new TextField("Estado Proceso");
        TextField tfIteraciones = new TextField("Iteraciones");
        tfEstado.setValue("Activo");
        tfEstado.setReadOnly(true);
        tfEstado.setStyleName("especial");

        tfIteraciones.setEnabled(false);
        vl.addComponent(tfEstado);
        vl.addComponent(tfIteraciones);

        setContent(vl);
    }


    
}
