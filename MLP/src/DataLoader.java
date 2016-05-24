import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class DataLoader {
	private ArrayList<Record> data;

	public DataLoader(String filename) throws NumberFormatException, IOException {
		super();
		data = new ArrayList<Record>();

		FileReader inputFile = new FileReader(filename);
		BufferedReader bufferReader = new BufferedReader(inputFile);
		String line;
		int id = 1;
		while ((line = bufferReader.readLine()) != null) {
			String[] values = line.split(" ");
			Record record = new Record();
			record.setRecordId(id);
			for (int i = 0; i < values.length - 1; i++) {
				record.getFeatures()[i] = new Double(values[i]);

			}
			record.setStateOfNature(new Integer(values[21]));
			id++;
			data.add(record);
		}

		bufferReader.close();
	}

	public ArrayList<Record> getData() {
		return data;
	}

	public void setData(ArrayList<Record> data) {
		this.data = data;
	}

	public void duplication() {
		int class1_count = 0;
		int class2_count = 0;
		int class3_count = 0;

		Collections.shuffle(data);
		ArrayList<Record> balanced_data = new ArrayList<Record>();
		for (int i = 0; i < 10000; i++) {
			for (Record instance : data) {
				if (instance.getStateOfNature() == 1 && class1_count != 3200) {
					class1_count++;
					balanced_data.add(instance);

				}
				if (instance.getStateOfNature() == 2 && class2_count != 3200) {
					class2_count++;
					balanced_data.add(instance);
				}
				if (instance.getStateOfNature() == 3 && class3_count != 3200) {
					class3_count++;
					balanced_data.add(instance);
				}
			}
		}
		data = balanced_data;
	}
	public void undersampling() {
		int class1_count = 0;
		int class2_count = 0;
		int class3_count = 0;

		Collections.shuffle(data);
		ArrayList<Record> balanced_data = new ArrayList<Record>();
		for (int i = 0; i < 93*3; i++) {
			for (Record instance : data) {
				if (instance.getStateOfNature() == 1 && class1_count != 93) {
					class1_count++;
					balanced_data.add(instance);

				}
				if (instance.getStateOfNature() == 2 && class2_count != 93) {
					class2_count++;
					balanced_data.add(instance);
				}
				if (instance.getStateOfNature() == 3 && class3_count != 93) {
					class3_count++;
					balanced_data.add(instance);
				}
			}
		}
		data = balanced_data;
	}
	public void normalization() {
		double max = 0;
		// find maximum feature value
		for (Record instance : data) {
			for (double value : instance.getFeatures()) {
				if (value > max)
					max = value;
			}
		}
		for (Record instance : data) {
			for (double value : instance.getFeatures()) {
				value = max / value;
			}
		}
	}

}
