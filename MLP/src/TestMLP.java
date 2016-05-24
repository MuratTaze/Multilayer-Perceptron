import java.io.IOException;

class TestMLP {
	static double[][] input;
	static double[][] output;

	public static void main(String[] args) throws NumberFormatException, IOException {
		DataLoader dt = new DataLoader("ann-train.data");
		
		dt.undersampling();
		//dt.duplication();
		dt.normalization();
		inputReader(dt);
		MLP learner = new MLP();
		learner.setINPUT_NEURONS(21);
		learner.setHIDDEN_NEURONS(3);
		learner.setTrainInputs(input);
		learner.setTrainOutput(output);
		learner.setTRAINING_REPS(30000000);
		learner.setMAX_SAMPLES(270);
		learner.setOUTPUT_NEURONS(3);
		learner.initialize();
		learner.train();
		
		//remove below comments to test ann-test.data
		dt = new DataLoader("ann-test.data");
		inputReader(dt);
		learner.testNetworkTraining(input, output);
		
	}

	private static void inputReader(DataLoader dt) {
		int sample = 0;
		input = new double[dt.getData().size()][21];
		output = new double[dt.getData().size()][3];
		for (Record record : dt.getData()) {

			for (int i = 0; i < 21; i++) {
				input[sample] = record.getFeatures();
			}
			if(record.getStateOfNature()==1)  
			output[sample] = new double[]{1,0,0};
			else if(record.getStateOfNature()==2)
				output[sample] = new double[]{0,1,0};
			else
				output[sample] = new double[]{0,0,1};
			sample++;
		}

	}
}