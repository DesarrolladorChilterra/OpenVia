
import java.math.BigDecimal;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Miguel Vega Brante
 */
public class TEST {

    public static void main(String[] args) {

        System.out.println( new BigDecimal("10.00") );
        System.out.println( "10,0".replace(".", "").replace(",", ".") );
//        System.out.println( new BigDecimal("10,00".replaceAll(".", "").replaceAll(",", ".")) );

    }

}
