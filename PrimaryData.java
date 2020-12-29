package project_KeyValue;

import java.util.Date;
import org.json.JSONObject;

public final class PrimaryData {
		public static final String DataStoreLocation = "C:\\Users\\HARINI";

		private String p_dataLoc = "";
		private String p_dataName = "";

		public PrimaryData() {
			try {
				p_dataLoc = DataStoreLocation;
				p_dataName = "Primarydata-" + SecondaryData.getProcessFlow();
			} 
			catch (Exception E) {

			}
		}

		public PrimaryData(String fileLoc) {
			try {
				p_dataLoc = fileLoc;
				p_dataName = "Primarydata-" + SecondaryData.getProcessFlow();
			}
			catch (Exception E) {

			}
		}
		 /*
		 * Operations (Create,Write,Delete)
		 */
		public synchronized String create(String keyName, JSONObject valueName) {
			try { //Without alive time
				return create(keyName, valueName, -1);
			} 
			catch (Exception E) {
				return "Creation Operation failed";
			}
		}

		public synchronized String create(String keyName, JSONObject valueName, int AliveTime) { //With alive time
			try {
				String fileLoc = p_dataLoc + "/" + p_dataName;
				if (!SecondaryData.isValidKeyValue(keyName)) {
					return "Operation failed! Key length exceeded";
				}
				if (SecondaryData.isKeyPresent(keyName, fileLoc)) {
					return "Operation failed! Key already available";
				}
				DataFile dataName = new DataFile();
				dataName.setKey(keyName);
				if (AliveTime > 0) {
					dataName.setAliveTime(AliveTime);
				}
				else {
					dataName.setAliveTime(-1);
				}
				dataName.setValue(valueName);
				dataName.setCreationDateMilliTime(new Date().getTime());
				
				if (SecondaryData.writeOperation(dataName, fileLoc)) {
					return "Create operation successful";
				} 
				else {
					return "Create operation failed";
				}
			} 
			catch (Exception E) {
				return "Create operation failed";
			}
		}

		
		public synchronized Object read(String keyName) {
			try {
				String fileLoc = p_dataLoc + "/" + p_dataName;
				if (!SecondaryData.isValidKeyValue(keyName)) {
					return "Operation failed! Key length exceeded";
				}
				if (!SecondaryData.isKeyPresent(keyName, fileLoc)) {
					return "Operation failed! Key not available";
				}
			
				DataFile dataName = SecondaryData.readOperation(keyName, fileLoc);
				if (null != dataName) {
					return dataName.getValue();
				}
				return "Read operation failed";
			} 
			catch (Exception E) {
				E.printStackTrace();
				return "Read operation failed";
			}
		}

		public synchronized Object delete(String keyName) {
			try {
				String fileLoc = p_dataLoc + "/" + p_dataName;
				if (!SecondaryData.isValidKeyValue(keyName)) {
					return "Operation failed! Key length exceeded";
				}
				if (!SecondaryData.isKeyPresent(keyName, fileLoc)) {
					return "Operation failed! Key not available";
				}
				if (SecondaryData.deleteOperation(keyName, fileLoc)) {
					return "Record deletion successful";
				}
				return "Record deletion failed";
			}
			catch (Exception E) {
				E.printStackTrace();
				return "Record deletion failed";
			}
		}
	}
