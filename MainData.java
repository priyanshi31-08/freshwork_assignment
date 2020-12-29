package project_KeyValue;

import org.json.JSONException;
import org.json.JSONObject;

public class MainData {
	public static void main(String[] args) throws JSONException  {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("firstName", "Harini");
		jsonObject.put("collegeName", "SKCET");
		jsonObject.put("degree", "B.E");
		
		//Create Opreation
		System.out.println("CREATE"); 
		PrimaryData Datas = new PrimaryData(
				"C:\\Users\\HARINI\\DataStore.txt");
		System.out.println(Datas.create("one", jsonObject, 55));//Success
		System.out.println(Datas.create("one", jsonObject, 55));//Failure
		System.out.println(Datas.create("one", jsonObject));//Failure
		System.out.println(Datas.create("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz123", new JSONObject()));//Failure
		try {
			Thread.sleep(5000);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("AFTER WAIT"); //After sleep time
		jsonObject.put("pincode", "600125");
		System.out.println(Datas.create("two", jsonObject, 55));//Success
		System.out.println(Datas.create("one", jsonObject));//Failure
		System.out.println(Datas.create("two", jsonObject, 55));//Failure
		
		//Read Operation
		System.out.println("READ"); 
		System.out.println(Datas.read("one"));//Success
		System.out.println(Datas.read("two"));//Success
		System.out.println(Datas.read("three"));//Failure
		System.out.println(Datas.read("abcdefghijklmnopqrstuvwxyz123"));//Failure
		try {
			Thread.sleep(5000);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("AFTER WAIT"); //After sleep time
		System.out.println(Datas.read("one"));//Failure
		System.out.println(Datas.read("two"));//Success
	
		//Delete Operation
		System.out.println("DELETE");
		System.out.println(Datas.delete("one"));//Failure
		System.out.println(Datas.delete("two"));//Success
		System.out.println(Datas.delete("two"));//Failure
		System.out.println(Datas.delete("three"));//Failure
		System.out.println(Datas.delete("1234567890qwertyuiopasdfghjklzxcvbnm"));//Failure
	}
}
