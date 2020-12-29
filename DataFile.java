package project_KeyValue;

import java.io.Serializable;

import org.json.JSONObject;

public class DataFile implements Serializable {
	
	private static final long serialVersionUID = 1L;
	String keyName;
	int AliveTime;
	JSONObject valueName;
	long creationDateMilliTime;

	public String getKey() {
		return keyName;
	}

	public void setKey(String keyName) {
		this.keyName = keyName;
	}

	public JSONObject getValue() {
		return valueName;
	}

	public void setValue(JSONObject valueName) {
		this.valueName = valueName;
	}
	
	public int getAliveTime() {
		return AliveTime;
	}

	public void setAliveTime(int AliveTime) {
		this.AliveTime = AliveTime;
	}

	public long getCreationDateMilliTime() {
		return creationDateMilliTime;
	}

	public void setCreationDateMilliTime(long creationDateMilliTime) {
		this.creationDateMilliTime = creationDateMilliTime;
	}

}
