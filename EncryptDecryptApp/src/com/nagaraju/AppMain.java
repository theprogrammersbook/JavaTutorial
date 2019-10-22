package com.nagaraju;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AppMain {
  public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, InvalidAlgorithmParameterException {
	  String originalContent = "{\"MACAddress\":\"54-E1-AD-EF-5C-6E\",\"Version\":\"1.2\",\"ExpiryDate\":\"2019-03-23 12:23:0\"}";
	  String key = "ROBOTIOTA1$%#$%#";
	  getMacAddress("Good");
	  System.out.println(key.length());
	  int maxKeyLen = Cipher.getMaxAllowedKeyLength("AES");
	  System.out.println("MaxAllowedKeyLength=[" + maxKeyLen + "].");
      SecretKey secretKey =  new SecretKeySpec(key.getBytes(), "AES");

      FileEncrypterDecrypter fileEncrypterDecrypter = new FileEncrypterDecrypter(secretKey, "AES/CBC/PKCS5Padding");
      fileEncrypterDecrypter.encrypt(originalContent, "baz.enc");

      String decryptedContent = fileEncrypterDecrypter.decrypt("baz.enc");
		/* assertThat(decryptedContent, is(originalContent)); */
        System.out.println(decryptedContent);
      //new File("baz.enc").delete(); // cleanup  
}
  public static boolean getMacAddress(String macAddress) {
		ArrayList<String> macAddressAll = new ArrayList<String>();
		try {
			InetAddress ip = InetAddress.getLocalHost();
			Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
			while (networks.hasMoreElements()) {
				NetworkInterface network = networks.nextElement();
				byte[] mac = network.getHardwareAddress();

				if (mac != null) {
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < mac.length; i++) {
						sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
					}
					macAddressAll.add(sb.toString());
				}
			}
			System.out.println(macAddressAll.toString());
			return macAddressAll.contains(macAddress);
		} catch (UnknownHostException ex) {
			
		} catch (SocketException ex) {
			
		}
		return false;

	}
}
