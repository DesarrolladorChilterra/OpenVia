/*
 * Aplicación       : Despachos.
 * Cliente          : Copeval.
 * Desarrollado por : Miguel Vega B.
 */

package app;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 *
 * @author Miguelon
 */
public class WFormConfApp extends Window {
    FormConfApp form;

    public WFormConfApp() {
        super();

        this.setCaption("Configurar parámetros aplicación");
        this.setStyleName("light");
        this.setWidth("530px");
        this.setPositionX(170);
        this.setPositionY(60);
//        this.setDraggable(false);
//        this.setResizable(false);

        form = new FormConfApp();
 
        Button aplicar = new Button("Aplicar", new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                aplicarCambios();
            }
        });
        HorizontalLayout hl = new HorizontalLayout();
        hl.addComponent(aplicar);
        hl.setWidth("100%");
        hl.setComponentAlignment(aplicar, Alignment.MIDDLE_RIGHT);

        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.addComponent(form);
        layout.addComponent(hl);
        setContent(layout);

        getDatos();
    }

    private void aplicarCambios() {
        try {
            form.commit();
        } catch (InvalidValueException e) {
            return;
        }
//        setDatos();
//        ConfigAppServ.guardaParametros();
//        this.getParent().showNotification( Util.notifInfo("Datos guardados.", null, 1000));
//        ((Aplicacion)getApplication()).mainWindow.opcionMenu(0);
    }

    private void getDatos() {
        // Conf ERP
//        form.getField("servidorERP").setValue(ConfigApp.getServidorERP());
//        form.getField("puertoERP").setValue(ConfigApp.getPuertoERP());
//        form.getField("baseDatosERP").setValue(ConfigApp.getBaseDatosERP());
//        form.getField("usuarioERP").setValue(ConfigApp.getUsuarioERP());
//        form.getField("passwordERP").setValue(ConfigApp.getPasswordERP());
//        form.getField("ownerERP").setValue(ConfigApp.getOwnerERP());
//        // Conf App
//        form.getField("servidorApp").setValue(ConfigApp.getServidorApp());
//        form.getField("puertoApp").setValue(ConfigApp.getPuertoApp());
//        form.getField("baseDatosApp").setValue(ConfigApp.getBaseDatosApp());
//        form.getField("usuarioApp").setValue(ConfigApp.getUsuarioApp());
//        form.getField("passwordApp").setValue(ConfigApp.getPasswordApp());
//        // Conf LDAP
//        form.getField("servidorLDAP").setValue(ConfigApp.getServidorLDAP());
//        form.getField("puertoLDAP").setValue(ConfigApp.getPuertoLDAP());
//        form.getField("contextoLDAP").setValue(ConfigApp.getContextoLDAP());
//        // Pass Admin
//        form.getField("passAdmin").setValue(ConfigApp.getPassAdmin());
    }

    private void setDatos() {
        // Conf ERP
//        ConfigApp.setServidorERP((String) form.getField("servidorERP").getValue());
//        ConfigApp.setPuertoERP((String) form.getField("puertoERP").getValue());
//        ConfigApp.setBaseDatosERP((String) form.getField("baseDatosERP").getValue());
//        ConfigApp.setUsuarioERP((String) form.getField("usuarioERP").getValue());
//        ConfigApp.setPasswordERP((String) form.getField("passwordERP").getValue());
//        ConfigApp.setOwnerERP((String) form.getField("ownerERP").getValue());
//        // Conf App
//        ConfigApp.setServidorApp((String) form.getField("servidorApp").getValue());
//        ConfigApp.setPuertoApp((String) form.getField("puertoApp").getValue());
//        ConfigApp.setBaseDatosApp((String) form.getField("baseDatosApp").getValue());
//        ConfigApp.setUsuarioApp((String) form.getField("usuarioApp").getValue());
//        ConfigApp.setPasswordApp((String) form.getField("passwordApp").getValue());
//        // Conf LDAP
//        ConfigApp.setServidorLDAP((String) form.getField("servidorLDAP").getValue());
//        ConfigApp.setPuertoLDAP((String) form.getField("puertoLDAP").getValue());
//        ConfigApp.setContextoLDAP((String) form.getField("contextoLDAP").getValue());
//        // Pass Admin
//        ConfigApp.setPassAdmin((String) form.getField("passAdmin").getValue());
    }

    private class FormConfApp extends Form {
        GridLayout layout = new GridLayout(3,6);

        public FormConfApp() {
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
            tf = new TextField("Servidor ERP");
            tf.setRequired(true);
            tf.setRequiredError("Servidor ERP no puede ser nulo");
            tf.setNullRepresentation("");
            tf.setTabIndex(1);
            this.addField("servidorERP", tf );

            tf = new TextField("Puerto ERP");
            tf.setRequired(true);
            tf.setRequiredError("Puerto ERP no puede ser nulo");
            tf.setNullRepresentation("");
            tf.setTabIndex(2);
            this.addField("puertoERP", tf );

            tf = new TextField("BaseDatos ERP");
            tf.setRequired(true);
            tf.setRequiredError("BaseDatos ERP no puede ser nulo");
            tf.setNullRepresentation("");
            tf.setTabIndex(3);
            this.addField("baseDatosERP", tf );

            tf = new TextField("Usuario ERP");
            tf.setRequired(true);
            tf.setRequiredError("Usuario ERP no puede ser nulo");
            tf.setNullRepresentation("");
            tf.setTabIndex(4);
            this.addField("usuarioERP", tf );

            tf = new TextField("Password ERP");
            tf.setRequired(true);
            tf.setRequiredError("Password ERP no puede ser nulo");
            tf.setNullRepresentation("");
            tf.setTabIndex(5);
            this.addField("passwordERP", tf );

            tf = new TextField("Owner tablas ERP");
            tf.setRequired(true);
            tf.setRequiredError("Owner tablas ERP no puede ser nulo");
            tf.setNullRepresentation("");
            tf.setTabIndex(6);
            this.addField("ownerERP", tf );

            // Conf App
            tf = new TextField("Servidor App");
            tf.setRequired(true);
            tf.setRequiredError("Servidor App no puede ser nulo");
            tf.setNullRepresentation("");
            tf.setTabIndex(7);
            this.addField("servidorApp", tf );

            tf = new TextField("Puerto App");
            tf.setRequired(true);
            tf.setRequiredError("Puerto App no puede ser nulo");
            tf.setNullRepresentation("");
            tf.setTabIndex(8);
            this.addField("puertoApp", tf );

            tf = new TextField("BaseDatos App");
            tf.setRequired(true);
            tf.setRequiredError("BaseDatos App no puede ser nulo");
            tf.setNullRepresentation("");
            tf.setTabIndex(9);
            this.addField("baseDatosApp", tf );

            tf = new TextField("Usuario App");
            tf.setRequired(true);
            tf.setRequiredError("Usuario App no puede ser nulo");
            tf.setNullRepresentation("");
            tf.setTabIndex(10);
            this.addField("usuarioApp", tf );

            tf = new TextField("Password App");
            tf.setRequired(true);
            tf.setRequiredError("Password App no puede ser nulo");
            tf.setNullRepresentation("");
            tf.setTabIndex(11);
            this.addField("passwordApp", tf );

            // Conf LDAP
            tf = new TextField("Servidor LDAP");
            tf.setRequired(true);
            tf.setRequiredError("Servidor LDAP no puede ser nulo");
            tf.setNullRepresentation("");
            tf.setTabIndex(12);
            this.addField("servidorLDAP", tf );

            tf = new TextField("Puerto LDAP");
            tf.setRequired(true);
            tf.setRequiredError("Puerto LDAP no puede ser nulo");
            tf.setNullRepresentation("");
            tf.setTabIndex(13);
            this.addField("puertoLDAP", tf );

            tf = new TextField("Contexto LDAP");
            tf.setRequired(true);
            tf.setRequiredError("ContextoLDAP no puede ser nulo");
            tf.setNullRepresentation("");
            tf.setTabIndex(14);
            this.addField("contextoLDAP", tf );

            // Pass Admin
            tf = new TextField("Password Admin");
            tf.setRequired(true);
            tf.setRequiredError("Password Admin no puede ser nulo");
            tf.setNullRepresentation("");
            tf.setTabIndex(15);
            this.addField("passAdmin", tf );
        }

        @Override
        protected void attachField(Object propertyId, Field field) {

            // Conf ERP
            if (propertyId.equals("servidorERP")){
                layout.addComponent(field, 0, 0);
            }
            if (propertyId.equals("puertoERP")){
                layout.addComponent(field, 0, 1);
            }
            if (propertyId.equals("baseDatosERP")){
                layout.addComponent(field, 0, 2);
            }
            if (propertyId.equals("usuarioERP")){
                layout.addComponent(field, 0, 3);
            }
            if (propertyId.equals("passwordERP")){
                layout.addComponent(field, 0, 4);
            }
            if (propertyId.equals("ownerERP")){
                layout.addComponent(field, 0, 5);
            }
            // Conf App
            if (propertyId.equals("servidorApp")){
                layout.addComponent(field, 1, 0);
            }
            if (propertyId.equals("puertoApp")){
                layout.addComponent(field, 1, 1);
            }
            if (propertyId.equals("baseDatosApp")){
                layout.addComponent(field, 1, 2);
            }
            if (propertyId.equals("usuarioApp")){
                layout.addComponent(field, 1, 3);
            }
            if (propertyId.equals("passwordApp")){
                layout.addComponent(field, 1, 4);
            }
            // Conf LDAP
            if (propertyId.equals("servidorLDAP")){
                layout.addComponent(field, 2, 0);
            }
            if (propertyId.equals("puertoLDAP")){
                layout.addComponent(field, 2, 1);
            }
            if (propertyId.equals("contextoLDAP")){
                layout.addComponent(field, 2, 2);
            }
            // Pass Admin
            if (propertyId.equals("passAdmin")){
                layout.addComponent(field, 2, 4);
            }
        }

    }

}
