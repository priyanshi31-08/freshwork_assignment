package project_KeyValue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.HashMap;

public class SecondaryData {
	
	public static String getProcessFlow() {
		String process_Flow = ManagementFactory.getRuntimeMXBean().getName();
		return process_Flow;
	}
//Key Validation
	public static boolean isValidKeyValue(String keyName) {
		if (keyName.length() > 32) { //Maximum Key Length
			return false;
		}
		return true;
	}
//Check if key present or not
	public static boolean isKeyPresent(String keyName, String fileLoc) {
		boolean isKeyPresent = false;
		ObjectInputStream o_InputStream = null;
		FileInputStream f_InputStream = null;
		ObjectOutputStream o_OutputStream = null;
		FileOutputStream f_OutputStream = null;
		HashMap<String, DataFile> data_Value = new HashMap<String, DataFile>();
		try {
			File fileName = new File(fileLoc);
			if (fileName.exists()) {
				o_InputStream = new ObjectInputStream(f_InputStream);
				f_InputStream = new FileInputStream(fileName);
				data_Value = (HashMap<String, DataFile>) o_InputStream.readObject();
				if (data_Value.containsKey(keyName)) {
					isKeyPresent = true;
				}
				o_InputStream.close();
				f_InputStream.close();
			}
			if (isKeyPresent) {
				DataFile dataName = data_Value.get(keyName);
				long presentDateMilliTime = new Date().getTime();
				if (dataName.getAliveTime() > 0
						&& (presentDateMilliTime - dataName
								.getCreationDateMilliTime()) >= (dataName
								.getAliveTime() * 1000)) { //1000-> Milliseconds
					data_Value.remove(keyName);
					f_OutputStream = new FileOutputStream(fileName);
					o_OutputStream = new ObjectOutputStream(f_OutputStream);
					o_OutputStream.writeObject(data_Value);
					f_OutputStream.close();
					o_OutputStream.close();
					isKeyPresent = false;
				}
			}
		} 
		catch (Exception E) {
			E.printStackTrace();
		} 
		finally {
			if (f_InputStream != null) {
				try {
					f_InputStream.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (o_InputStream != null) {
				try {
					o_InputStream.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return isKeyPresent;
	}
/*
 * Operations (Write,Read,Delete)
 */
	public static boolean writeOperation(DataFile dataName, String fileLoc) {
		
		ObjectInputStream o_InputStream = null;
		FileInputStream f_InputStream = null;
		ObjectOutputStream o_OutputStream = null;
		FileOutputStream f_OutputStream = null;
		HashMap<String, DataFile> data_Value = null;
		try {
			File fileName = new File(fileLoc);
			if (fileName.exists()) {// Read existing file data
				f_InputStream = new FileInputStream(fileName);
				o_InputStream = new ObjectInputStream(f_InputStream);
				data_Value = (HashMap<String, DataFile>) o_InputStream.readObject();
				f_InputStream.close();
				o_InputStream.close();

				// Add new element
				data_Value.put(dataName.getKey(), dataName);

				// Write data to file
				f_OutputStream = new FileOutputStream(fileName);
				o_OutputStream = new ObjectOutputStream(f_OutputStream);
				o_OutputStream.writeObject(data_Value);
				f_OutputStream.close();
				o_OutputStream.close();

				return true;
			}
			else {
				data_Value = new HashMap<String, DataFile>();
				data_Value.put(dataName.getKey(), dataName);

				// write the data to file
				f_OutputStream = new FileOutputStream(fileName);
				o_OutputStream = new ObjectOutputStream(f_OutputStream);
				o_OutputStream.writeObject(data_Value);
				f_OutputStream.close();
				o_OutputStream.close();

				return true;
			}
		} 
		catch (Exception E) {
			return false;
		} 
		finally {
			if (f_InputStream != null) {
				try {
					f_InputStream.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (o_InputStream != null) {
				try {
					o_InputStream.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (f_OutputStream != null) {
				try {
					f_OutputStream.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (o_OutputStream != null) {
				try {
					o_OutputStream.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static DataFile readOperation(String keyName, String fileLoc) {
	
		ObjectInputStream o_InputStream = null;
		FileInputStream f_InputStream = null;
		HashMap<String, DataFile> data_Value = null;
		try {
			File fileName = new File(fileLoc);
			if (fileName.exists()) {
				// read the existing file data
				f_InputStream = new FileInputStream(fileName);
				o_InputStream = new ObjectInputStream(f_InputStream);
				data_Value = (HashMap<String, DataFile>) o_InputStream.readObject();

				f_InputStream.close();
				o_InputStream.close();
				return data_Value.get(keyName);
			} 
			else {
				return null;
			}
		} 
		catch (Exception E) {
			E.printStackTrace();
			return null;
		} 
		finally {
			if (f_InputStream != null) {
				try {
					f_InputStream.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (o_InputStream != null) {
				try {
					o_InputStream.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static boolean deleteOperation(String keyName, String fileLoc) {

		ObjectInputStream o_InputStream = null;
		FileInputStream f_InputStream = null;
		ObjectOutputStream o_OutputStream = null;
		FileOutputStream f_OutputStream = null;
		HashMap<String, DataFile> data_Value = null;
		try {
			File fileName = new File(fileLoc);
			if (fileName.exists()) {// Read existing file data
				f_InputStream = new FileInputStream(fileName);
				o_InputStream = new ObjectInputStream(f_InputStream);
				data_Value = (HashMap<String, DataFile>) o_InputStream.readObject();

				f_InputStream.close();
				o_InputStream.close();

				// Add new element
				data_Value.remove(keyName);

				// Write data to file
				f_OutputStream = new FileOutputStream(fileName);
				o_OutputStream = new ObjectOutputStream(f_OutputStream);
				o_OutputStream.writeObject(data_Value);
				f_OutputStream.close();
				o_OutputStream.close();

				return true;
			}
			return false;
		} 
		catch (Exception E) {
			return false;
		} 
		finally {
			if (f_InputStream != null) {
				try {
					f_InputStream.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (o_InputStream != null) {
				try {
					o_InputStream.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (f_OutputStream != null) {
				try {
					f_OutputStream.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (o_OutputStream != null) {
				try {
					o_OutputStream.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
