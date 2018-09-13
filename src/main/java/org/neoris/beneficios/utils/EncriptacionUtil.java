/**
 * 
 */
package org.neoris.beneficios.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * The class EncriptacionUtil.
 *
 */
public class EncriptacionUtil {
	
	private static final String ENCODE = "UTF-8";
	
	/**
	 * Encryption mode enumeration
	 */
	private enum EncryptMode {
		ENCRYPT, DECRYPT;
	}

	// cipher to be used for encryption and decryption
	Cipher cx;

	// encryption key and initialization vector
	byte[] key;
	byte[] iv;

	public EncriptacionUtil() throws NoSuchAlgorithmException, NoSuchPaddingException {
		// initialize the cipher with transformation AES/CBC/PKCS5Padding
		cx = Cipher.getInstance("AES/CBC/PKCS5Padding");
		key = new byte[32]; //256 bit key space
		iv = new byte[16]; //128 bit IV
	}
	
	/**
	 * Note: This function is no longer used. 
	 * This function generates md5 hash of the input string
	 * @param inputString
	 * @return md5 hash of the input string
	 */
	public static final String md5(final String inputString) {
	    final String MD5 = "MD5";
	    try {
	        // Create MD5 Hash
	        MessageDigest digest = java.security.MessageDigest
	                .getInstance(MD5);
	        digest.update(inputString.getBytes());
	        byte messageDigest[] = digest.digest();

	        // Create Hex String
	        StringBuilder hexString = new StringBuilder();
	        for (byte aMessageDigest : messageDigest) {
	            String h = Integer.toHexString(0xFF & aMessageDigest);
	            while (h.length() < 2)
	                h = "0" + h;
	            hexString.append(h);
	        }
	        return hexString.toString();

	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return "";
	}
	
	/**
	 * 
	 * @param _inputText
	 *            Text to be encrypted or decrypted
	 * @param _encryptionKey
	 *            Encryption key to used for encryption / decryption
	 * @param _mode
	 *            specify the mode encryption / decryption
	 * @param _initVector
	 * 	      Initialization vector
	 * @return encrypted or decrypted string based on the mode
	 * @throws UnsupportedEncodingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	 	private String encryptDecrypt(String inputText, String encryptionKey,
			EncryptMode mode, String initVector) throws UnsupportedEncodingException,
			InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException {
		String out = "";// output string
		
		int len = encryptionKey.getBytes(ENCODE).length; // length of the key	provided
		
		if (encryptionKey.getBytes(ENCODE).length > key.length)
			len = key.length;
		
		int ivlen = initVector.getBytes(ENCODE).length;
		
		if(initVector.getBytes(ENCODE).length > iv.length)
			ivlen = iv.length;
		
		System.arraycopy(encryptionKey.getBytes(ENCODE), 0, key, 0, len);
		System.arraycopy(initVector.getBytes(ENCODE), 0, iv, 0, ivlen);

		SecretKeySpec keySpec = new SecretKeySpec(key, "AES"); // Create a new SecretKeySpec
									// for the
									// specified key
									// data and
									// algorithm
									// name.
		
		IvParameterSpec ivSpec = new IvParameterSpec(iv); // Create a new
								// IvParameterSpec
								// instance with the
								// bytes from the
								// specified buffer
								// iv used as
								// initialization
								// vector.

		// encryption
		if (mode.equals(EncryptMode.ENCRYPT)) {
			// Potentially insecure random numbers on Android 4.3 and older.
			// Read
			// https://android-developers.blogspot.com/2013/08/some-securerandom-thoughts.html
			// for more info.
			cx.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);// Initialize this cipher instance
			byte[] results = cx.doFinal(inputText.getBytes(ENCODE)); // Finish
										// multi-part
										// transformation
										// (encryption)
			out = Base64.getEncoder().encodeToString(results);
										// output
		}

		// decryption
		if (mode.equals(EncryptMode.DECRYPT)) {
			cx.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);// Initialize this ipher instance
			
			byte[] decodedValue = Base64.getDecoder().decode(inputText.getBytes());
			byte[] decryptedVal = cx.doFinal(decodedValue); // Finish
									// multi-part
									// transformation
									// (decryption)
			out = new String(decryptedVal);
		}
		System.out.println(out);
		return out; // return encrypted/decrypted string
	}

	/***
	 * This function computes the SHA256 hash of input string
	 * @param text input text whose SHA256 hash has to be computed
	 * @param length length of the text to be returned
	 * @return returns SHA256 hash of input text 
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String SHA256 (String text, int length) throws NoSuchAlgorithmException, UnsupportedEncodingException {

	    String resultStr;
		MessageDigest md = MessageDigest.getInstance("SHA-256");

	    md.update(text.getBytes(ENCODE));
	    byte[] digest = md.digest();
	    
	    StringBuilder result = new StringBuilder();
	    for (byte b : digest) {
	        result.append(String.format("%02x", b)); //convert to hex
	    }
	    
	    if(length > result.toString().length())
	    {
	    	resultStr = result.toString();
	    }
	    else 
	    {
	    	resultStr = result.toString().substring(0, length);
	    }

	    return resultStr;
	    
	}
	
	/***
	 * This function encrypts the plain text to cipher text using the key
	 * provided. You'll have to use the same key for decryption
	 * 
	 * @param _plainText
	 *            Plain text to be encrypted
	 * @param _key
	 *            Encryption Key. You'll have to use the same key for decryption
	 * @param _iv
	 * 	    initialization Vector
	 * @return returns encrypted (cipher) text
	 * @throws InvalidKeyException
	 * @throws UnsupportedEncodingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	 
	public String encrypt(String plainText, String key, String iv)
			throws InvalidKeyException, UnsupportedEncodingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException {
		return encryptDecrypt(plainText, key, EncryptMode.ENCRYPT, iv);
	}
	
	public String encryptSimple(String _plainText, String _key, String _iv)
            throws InvalidKeyException, UnsupportedEncodingException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException,
            BadPaddingException, NoSuchAlgorithmException {
        return encryptDecrypt(_plainText, EncriptacionUtil.SHA256(_key, 32), EncryptMode.ENCRYPT, _iv);
    }
	
	/***
	 * This funtion decrypts the encrypted text to plain text using the key
	 * provided. You'll have to use the same key which you used during
	 * encryprtion
	 * 
	 * @param _encryptedText
	 *            Encrypted/Cipher text to be decrypted
	 * @param _key
	 *            Encryption key which you used during encryption
	 * @param _iv
	 * 	    initialization Vector
	 * @return encrypted value
	 * @throws InvalidKeyException
	 * @throws UnsupportedEncodingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public String decrypt(String encryptedText, String key, String iv)
			throws InvalidKeyException, UnsupportedEncodingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException {
		return encryptDecrypt(encryptedText, key, EncryptMode.DECRYPT, iv);
	}
	
	public String decryptSimple(String _encryptedText, String _key, String _iv)
            throws InvalidKeyException, UnsupportedEncodingException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException,
            BadPaddingException, NoSuchAlgorithmException {
        return encryptDecrypt(_encryptedText, EncriptacionUtil.SHA256(_key, 32), EncryptMode.DECRYPT, _iv);
    }
	
	/**
 	* this function generates random string for given length
 	* @param length
 	* 				Desired length
 	* * @return 
 	*/
	public static String generateRandomIV(int length)
	{
		SecureRandom ranGen = new SecureRandom();
		byte[] aesKey = new byte[16];
		ranGen.nextBytes(aesKey);
		StringBuilder result = new StringBuilder();
	    for (byte b : aesKey) {
	        result.append(String.format("%02x", b)); //convert to hex
	    }
	    if(length> result.toString().length())
	    {
	    	return result.toString();
	    }
	    else
	    {
	    	return result.toString().substring(0, length);
	    }
	}

}
