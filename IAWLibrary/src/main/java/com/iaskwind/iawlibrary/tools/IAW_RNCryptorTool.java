package com.iaskwind.iawlibrary.tools;


import org.cryptonode.jncryptor.AES256JNCryptor;
import org.cryptonode.jncryptor.CryptorException;
import org.cryptonode.jncryptor.JNCryptor;

/**
 * 
 * https://github.com/RNCryptor/JNCryptor
 * Created by winston on 16/10/19.
 */
public class IAW_RNCryptorTool {

//    private static final String key = "EasyUnionGetYou!";
    private static JNCryptor cryptor = new AES256JNCryptor();
    //加密
    public static String Encrypt(String data,String key) throws CryptorException {
        byte[]  c = cryptor.encryptData(data.getBytes(),key.toCharArray());
        String  strEncrypt = IAW_Base64Tool.encode(c);
//        String  strEncrypt  =  Base64.encodeToString(c,Base64.DEFAULT);
        return strEncrypt;
    }
    //解密
    public static String Decrypt(String data,String key) throws CryptorException {
//        byte[] a = Base64.decode(data.getBytes(), Base64.DEFAULT);
        byte[] a = IAW_Base64Tool.decode(data.getBytes());
        byte[] b = cryptor.decryptData(a,key.toCharArray());
        String strDecrypt = new String(b);
        return strDecrypt;
    }

}



