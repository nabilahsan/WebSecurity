import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.BadPaddingException;
import java.security.MessageDigest;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
 
public class HW2 {    
  static void P1() throws Exception {
    byte[] cipherText = Files.readAllBytes(Paths.get("cipher1.txt"));
    
    // BEGIN SOLUTION
    byte[] iv = new byte[] { 0, 0, 0, 0, 
                             0, 0, 0, 0, 
                             0, 0, 0, 0, 
                             0, 0, 0, 0 }; //This is the key
    
    
    byte[] key = new byte[] { 0, 0, 0, 0, 
                              0, 0, 0, 0, 
                              0, 0, 0, 0, 
                              0, 0, 0, 0 }; //This is the key
  
    Cipher cipherTxt = Cipher.getInstance("AES/CBC/ISO10126Padding"); // Initialized cipher
    
    
    cipherTxt.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(iv)); // Set cipher to decrypt
    byte[] plainText = cipherTxt.doFinal(cipherText); // Decrypted plainText Initialized
    System.out.println("Problem 1: ");
    // END SOLUTION
    
    System.out.println(new String(plainText, StandardCharsets.UTF_8));
  }

  static void P2() throws Exception {    
    // BEGIN SOLUTION

    MessageDigest messageSHA256 = MessageDigest.getInstance("SHA-256");

    // System.out.println( "\n" + messageSHA256.getProvider().getInfo() );

    for (int i = 0; i < 1000; i++) { //to iterate through 1000 files.
      byte[] message = Files.readAllBytes(Paths.get(String.format("messages/plain2%d.txt", i)));
      byte[] hashed = messageSHA256.digest(message);

      // From P1 
      // The first two bytes of the hash value of the real message are 3 and 59, respectively.
      if (hashed[0] == 3 && hashed[1]==59) {
        System.out.println("2"+i + "\n");
      }
    }
    // END SOLUTION
  }

  static void P3() throws Exception {
    byte[] cipherText = Files.readAllBytes(Paths.get("cipher3.txt"));
    
    // BEGIN SOLUTION
    byte[] key =  new byte[] { 0, 0, 0, 0, 
                               0, 0, 0, 0, 
                               0, 0, 0, 0, 
                               0, 0, 0, 0 };

    
    byte[] iv =  new byte[] { 0, 0, 0, 0, 
                              0, 0, 0, 0, 
                              0, 0, 0, 0, 
                              0, 0, 0, 0 };
                               
    Cipher cipher3 = Cipher.getInstance("AES/CBC/NoPadding");
    cipher3.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(iv));
    byte[] plainText = cipher3.doFinal(cipherText);    

    // //ciphertext are blocks of 16bits that should be rearranged

    // END SOLUTION   
    System.out.println(new String(plainText, StandardCharsets.UTF_8));
  }

  // static void P4() throws Exception {
  //   byte[] iv = new byte[] { 0, 0, 0, 0, 
  //                            0, 0, 0, 0, 
  //                            0, 0, 0, 0, 
  //                            0, 0, 0, 0 };
  //   byte[] cipherText = Files.readAllBytes(Paths.get("cipher4.txt"));
    
  //   // BEGIN SOLUTION
  //   byte[] plainText = cipherText;    
  //   // END SOLUTION
    
  //   System.out.println(new String(plainText, StandardCharsets.UTF_8));
  // }

  public static void main(String [] args) {
    try {  
      P1();
      P2();
      P3();
      // P4();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
