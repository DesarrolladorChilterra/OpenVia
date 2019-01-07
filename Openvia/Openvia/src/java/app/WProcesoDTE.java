/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app;

import app.common.Util;
import app.process.ProcesoDTE;
import app.process.ProcesoDTEThread;
import app.process.service.ProcesoService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguelon
 */
public class WProcesoDTE extends Window {
    private FormProcDTE form;
    private ProgressIndicator progress;
    private ActProc actProc;

    public WProcesoDTE() {
        this.setCaption("ProcesoDTE");
//        this.setStyleName("light");
        this.setWidth("530px");
        this.setPositionX(170);
        this.setPositionY(60);
        this.setClosable(false);
//        this.setDraggable(false);
//        this.setResizable(false);

        form = new FormProcDTE();

        Button btIniciar = new Button("Iniciar", new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                iniciaProceso();
            }
        });
        Button btDetener = new Button("Detener", new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                detieneProceso();
            }
        });
        btIniciar.setWidth("120px");
        btDetener.setWidth("120px");
        HorizontalLayout hl = new HorizontalLayout();
        hl.setWidth("100%");
        hl.addComponent(btIniciar);
        hl.setComponentAlignment(btIniciar, Alignment.MIDDLE_CENTER);
        hl.addComponent(btDetener);
        hl.setComponentAlignment(btDetener, Alignment.MIDDLE_CENTER);

//        progress = new ProgressIndicator( new Float(0.0));
        progress = new ProgressIndicator();
        progress.setImmediate(true);
//        progress.setIndeterminate(true);
        progress.setPollingInterval(1000);
        progress.setWidth("100%");
//        progress.setVisible(false);

        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.addComponent(progress);
        layout.addComponent(form);
        layout.addComponent(hl);
        setContent(layout);

        actProc = new ActProc();
        actProc.start();

    }

    private void iniciaProceso() {
        ProcesoService.activar(true);
    }

    private void detieneProceso() {
        ProcesoService.activar(false);
    }

    public void updateProc() {

        form.getField("Estado").setReadOnly(false);
        form.getField("Iteraciones").setReadOnly(false);
        form.getField("DoctosProc").setReadOnly(false);
        String estado = (ProcesoDTE.isActivo()?"ACTIVO":"DESACTIVO") + " - " + (ProcesoDTE.isInRango()?"DENTRO DEL RANGO":"FUERA DE RANGO");
        form.getField("Estado").setValue(estado);
        form.getField("Iteraciones").setValue(ProcesoDTE.getIteraciones());
        form.getField("DoctosProc").setValue(ProcesoDTE.getDoctos());
        form.getField("Estado").setReadOnly(true);
        form.getField("Iteraciones").setReadOnly(true);
        form.getField("DoctosProc").setReadOnly(true);

    }

    @Override
    public void close() {
        actProc.activo = false;
    }

    private class FormProcDTE extends Form {
        GridLayout layout = new GridLayout(2,3);

        public FormProcDTE() {
            super();

            layout.setMargin(true);
            layout.setSpacing(true);

            this.setSizeUndefined();
            this.setLayout(layout);
            this.setInvalidCommitted(false);
//            this.setWriteThrough(false);

            setCampos();

        }

        private void setCampos() {
            TextField tf;

            // Conf ERP
//            tf = new TextField();
//            tf.setRequired(true);
//            tf.setRequiredError("Servidor ERP no puede ser nulo");
//            tf.setNullRepresentation("");
//            tf.setTabIndex(1);
//            this.addField("servidorERP", tf );

            tf = new TextField();
            tf.setWidth("300px");
            tf.setNullRepresentation("");
            tf.setTabIndex(1);
            tf.setReadOnly(true);
            tf.setStyleName("especial");
            this.addField("Estado", tf );

            tf = new TextField();
            tf.setWidth("300px");
            tf.setNullRepresentation("");
            tf.setTabIndex(2);
            tf.setReadOnly(true);
            tf.setStyleName("especial");
            this.addField("Iteraciones", tf );

            tf = new TextField();
            tf.setWidth("300px");
            tf.setNullRepresentation("");
            tf.setTabIndex(3);
            tf.setReadOnly(true);
            tf.setStyleName("especial");
            this.addField("DoctosProc", tf );

        }

        @Override
        protected void attachField(Object propertyId, Field field) {

            // Estado
            if (propertyId.equals("Estado")){
                layout.addComponent(new Label("Estado proceso"),0,0);
                layout.addComponent(field, 1, 0);
            }
            // Iteraciones
            if (propertyId.equals("Iteraciones")){
                layout.addComponent(new Label("Iteraciones"),0,1);
                layout.addComponent(field, 1, 1);
            }
            // Documentos
            if (propertyId.equals("DoctosProc")){
                layout.addComponent(new Label("Doctos procesados"),0,2);
                layout.addComponent(field, 1, 2);
            }
        }

    }

    private class ActProc extends Thread implements Serializable {
        public boolean activo = true;

        @Override
        public void run() {
            while ( activo ) {
                Util.delay(1);
                updateProc();
            }
        }

    }

    
}
