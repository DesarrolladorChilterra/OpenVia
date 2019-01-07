/*
 * Aplicación       : Despachos.
 * Cliente          : Copeval.
 * Desarrollado por : Miguel Vega B.
 */

package app.common;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
//import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Miguelon
 */
public class Cifrador {
    private static byte[] SALT_BYTES = {
        (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
        (byte)0x56, (byte)0x35, (byte)0xE3, (byte)0x03};
    private static int ITERATION_COUNT = 19;
    private static String passPhrase = "CryptoCopeval";

    /** Creates a new instance of Cifrador */
    public Cifrador() {
    }

    public static String encriptarOld(String str) {
        Cipher ecipher = null;
        Cipher dcipher = null;

        try {
            // Crear la key
            KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(),
            SALT_BYTES, ITERATION_COUNT);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());

            // Preparar los parametros para los ciphers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(
            SALT_BYTES, ITERATION_COUNT);

            // Crear los ciphers
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        } catch (javax.crypto.NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (java.security.InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

//        try {
//            // Encodear la cadena a bytes usando utf-8
//            byte[] utf8 = str.getBytes("UTF8");
//            // Encriptar
//            byte[] enc = ecipher.doFinal(utf8);
//            // Encodear bytes a base64 para obtener cadena
//            return new String( Base64.encodeBase64(enc) );
////            return new sun.misc.BASE64Encoder().encode(enc);
//        } catch (javax.crypto.BadPaddingException e) {
//            e.printStackTrace();
//        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        return null;
    }

    public static String desencriptarOld(String str) {
        Cipher ecipher = null;
        Cipher dcipher = null;

        if (str == null || str.equals("")) {
            return null;
        }

        try {
            // Crear la key
            str = str.replaceAll(" ", "+");
            KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), SALT_BYTES, ITERATION_COUNT);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());
            // Preparar los parametros para los ciphers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(
            SALT_BYTES, ITERATION_COUNT);
            // Crear los ciphers
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        } catch (javax.crypto.NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (java.security.InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

//        try {
//            // Decodear base64 y obtener bytes
////            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
//            byte[] dec = Base64.decodeBase64(str.getBytes());
//            // Desencriptar
//            byte[] utf8 = dcipher.doFinal(dec);
//            // Decodear usando utf-8
//            return new String(utf8, "UTF8");
//        } catch (javax.crypto.BadPaddingException e) {
//            e.printStackTrace();
//        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (java.io.IOException e) {
//            e.printStackTrace();
//        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(encriptarOld("miguelon01"));
        System.out.println(desencriptarOld("XjXCf9H1QB7YRZRYR+Jx4Q=="));
    }

}
