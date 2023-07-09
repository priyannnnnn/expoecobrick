package Model;

import java.security.KeyStore.PrivateKeyEntry;
import java.util.Date;




public class User{
    private String name ;
      private String addres ;
        private String PhoneNumber;
     private String mail;
   private String password;
   
public User(String name, String addres, String phoneNumber, String mail, String password) {
    this.name = name;
    this.addres = addres;
    PhoneNumber = phoneNumber;
    this.mail = mail;
    this.password = password;
}
public String getName() {
    return name;
}
public String getAddres() {
    return addres;
}
public String getPhoneNumber() {
    return PhoneNumber;
}
public String getMail() {
    return mail;
}
public String getPassword() {
    return password;
}

   
   

    
   




     
}


