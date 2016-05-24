import java.util.Arrays;

public class Record {
	private int recordId;
	private int stateOfNature;
	private double[] features;

	public Record() {
		super();
		features = new double[21];
	}

	@Override
	public String toString() {
		return "Record [recordId=" + recordId + ", stateOfNature=" + stateOfNature + ", features="
				+ Arrays.toString(features) + "]\n";
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public int getStateOfNature() {
		return stateOfNature;
	}

	public void setStateOfNature(int stateOfNature) {
		this.stateOfNature = stateOfNature;
	}

	public double[] getFeatures() {
		return features;
	}

	public void setFeatures(double[] features) {
		this.features = features;
	}

}
