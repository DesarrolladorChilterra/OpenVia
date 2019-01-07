/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.common;

/**
 *
 * @author Miguelón
 */
public class NumeroEnPalabras {

    private static String[] _grupos = 
        { "", "millon","billon","trillon" };
    private static String[] _unidades = 
        { "","un","dos","tres","cuatro","cinco","seis","siete","ocho","nueve" };
    private static String[] _decena1 = 
        { "","once","doce","trece","catorce","quince","dieciseis","diecisiete","dieciocho","diecinueve" };
    private static String[] _decenas = 
        { "","diez","veinte","treinta","cuarenta","cincuenta","sesenta","setenta","ochenta","noventa"};
    private static String[] _centenas = 
        { "","cien","doscientos","trescientos","cuatrocientos","quinientos","seiscientos","setecientos","ochocientos","novecientos"};
    
    /** Creates a new instance of NumeroEnPalabras */
    public NumeroEnPalabras() {
    }
    
    public static String get( long numero  ){
        String resultado = "";
        int grupo = 0;
        
        if (numero == 0) {
            return "Cero";
        }

        while ( numero != 0 && grupo < _grupos.length ) {
            
            long fragmento = numero % 1000000;
            int millarAlto = (int) (fragmento / 1000);
            int millarBajo = (int) (fragmento % 1000);
            numero = numero / 1000000;

            String nombreGrupo = _grupos[grupo];
            if (fragmento > 1 && grupo > 0)
                nombreGrupo += "es";

            if ((millarAlto != 0) || (millarBajo != 0)) {
                if (millarAlto > 1)
                    resultado = millarATexto(millarAlto) + " mil " + 
                    millarATexto(millarBajo) + " " +
                    nombreGrupo + " " +
                    resultado;

                if (millarAlto == 0) 
                    resultado = millarATexto(millarBajo) + " " +
                    nombreGrupo + " "+
                    resultado;

                if (millarAlto == 1)
                    resultado = "mil " + millarATexto(millarBajo) + " " +
                    nombreGrupo + " " +
                    resultado;
            }
            grupo++;
        }
        resultado = resultado.trim();
        resultado = resultado.substring(0,1).toUpperCase() + resultado.substring(1);
        return resultado;
        
    }
    
    private static String millarATexto( int n ) {
        if (n == 0)
            return "";

        int centenas = n / 100;
        n = n % 100;
        int decenas = n / 10;
        int unidades = n % 10;

        String sufijo = "";

        if ( decenas == 0 && unidades != 0 ) 
            sufijo = _unidades[unidades];

        if ( decenas == 1 && unidades != 0 )
            sufijo = _decena1[unidades];

        if ( decenas == 2 && unidades != 0 )
            sufijo   = "veinti"+_unidades[unidades];

        if ( unidades == 0) 
            sufijo = _decenas[decenas];

        if ( decenas > 2 && unidades != 0)
            sufijo = _decenas[decenas] + " y " + _unidades[unidades];

        if (centenas != 1)
            return _centenas[centenas] + " " + sufijo;

        if ( unidades == 0 && decenas == 0)
            return "cien";

        return "ciento "+sufijo; 
    }

    public static void main(String[] args) {
//        System.out.println( get(1211211223) );
    }
    
}
