import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serializer {

  //serializing a bank account
	public void serialize(BankAccount account, String fileName) {
		try {
			ObjectOutputStream ouputStream = new ObjectOutputStream(new FileOutputStream(fileName));
			ouputStream.writeObject(account);
			ouputStream.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//deserializing a bank account 
	public BankAccount deserialize(String fileName) {	
		BankAccount account = null;
		
		try{
			FileInputStream inputStream = new FileInputStream(fileName);
			ObjectInputStream reader = new ObjectInputStream(inputStream);
			account = (BankAccount)reader.readObject();
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
		return account;
	}
}
