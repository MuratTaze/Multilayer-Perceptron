
import java.util.Random;

public class MLP {
	private int INPUT_NEURONS = 2;
	private int HIDDEN_NEURONS = 2;
	private int OUTPUT_NEURONS = 3;

	private double LEARN_RATE = 0.06; // Rho.
	private int numberOfIterations = 10000;

	// Input to Hidden Weights (with Biases).
	private double wih[][];

	// Hidden to Output Weights (with Biases).
	private double who[][];

	// Activations.
	private double inputs[];

	private double hidden[];

	private double target[];

	private double computedOutputs[];

	// Unit errors.
	private double erro[];

	private double errh[];

	private int MAX_SAMPLES = 3700;

	private double trainInputs[][];

	private double[][] trainOutput;

	private void assignRandomWeights() {
		for (int inp = 0; inp <= INPUT_NEURONS; inp++) // Do not subtract 1
														// here.
		{
			for (int hid = 0; hid < HIDDEN_NEURONS; hid++) {
				// Assign a random weight value between -0.5 and 0.5
				wih[inp][hid] = new Random().nextDouble() - 0.5;
			} // hid
		} // inp

		for (int hid = 0; hid <= HIDDEN_NEURONS; hid++) // Do not subtract 1
														// here.
		{
			for (int out = 0; out < OUTPUT_NEURONS; out++) {
				// Assign a random weight value between -0.5 and 0.5
				who[hid][out] = new Random().nextDouble() - 0.5;
			} // out
		} // hid
		return;
	}

	private void backPropagate() {
		// Calculate the output layer error (step 3 for output cell).
		for (int out = 0; out < OUTPUT_NEURONS; out++) {
			erro[out] = (target[out] - computedOutputs[out]) * sigmoidDerivative(computedOutputs[out]);
		}

		// Calculate the hidden layer error (step 3 for hidden cell).
		for (int hid = 0; hid < HIDDEN_NEURONS; hid++) {
			errh[hid] = 0.0;
			for (int out = 0; out < OUTPUT_NEURONS; out++) {
				errh[hid] += erro[out] * who[hid][out];
			}
			errh[hid] *= sigmoidDerivative(hidden[hid]);
		}

		// Update the weights for the output layer (step 4).
		for (int out = 0; out < OUTPUT_NEURONS; out++) {
			for (int hid = 0; hid < HIDDEN_NEURONS; hid++) {
				who[hid][out] += (LEARN_RATE * erro[out] * hidden[hid]);
			} // hid
			who[HIDDEN_NEURONS][out] += (LEARN_RATE * erro[out]); // Update the
																	// bias.
		} // out

		// Update the weights for the hidden layer (step 4).
		for (int hid = 0; hid < HIDDEN_NEURONS; hid++) {
			for (int inp = 0; inp < INPUT_NEURONS; inp++) {
				wih[inp][hid] += (LEARN_RATE * errh[hid] * inputs[inp]);
			} // inp
			wih[INPUT_NEURONS][hid] += (LEARN_RATE * errh[hid]); // Update the
																	// bias.
		} // hid
		return;
	}

	private void feedForward() {
		double sum = 0.0;

		// Calculate input to hidden layer.
		for (int hid = 0; hid < HIDDEN_NEURONS; hid++) {
			sum = 0.0;
			for (int inp = 0; inp < INPUT_NEURONS; inp++) {
				sum += inputs[inp] * wih[inp][hid];
			} // inp

			sum += wih[INPUT_NEURONS][hid]; // Add in bias.
			hidden[hid] = sigmoid(sum);
		} // hid

		// Calculate the hidden to output layer.
		for (int out = 0; out < OUTPUT_NEURONS; out++) {
			sum = 0.0;
			for (int hid = 0; hid < HIDDEN_NEURONS; hid++) {
				sum += hidden[hid] * who[hid][out];
			} // hid

			sum += who[HIDDEN_NEURONS][out]; // Add in bias.
			computedOutputs[out] = sigmoid(sum);
		} // out
		return;
	}

	public double[] getActual() {
		return computedOutputs;
	}

	public double[] getErrh() {
		return errh;
	}

	public double[] getErro() {
		return erro;
	}

	public int getHIDDEN_NEURONS() {
		return HIDDEN_NEURONS;
	}

	public double[] getHidden() {
		return hidden;
	}

	public int getINPUT_NEURONS() {
		return INPUT_NEURONS;
	}

	public double[] getInputs() {
		return inputs;
	}

	public double getLEARN_RATE() {
		return LEARN_RATE;
	}

	public int getMAX_SAMPLES() {
		return MAX_SAMPLES;
	}

	public int getOUTPUT_NEURONS() {
		return OUTPUT_NEURONS;
	}

	public double[] getTarget() {
		return target;
	}

	public int getTRAINING_REPS() {
		return numberOfIterations;
	}

	public double[][] getWho() {
		return who;
	}

	public double[][] getWih() {
		return wih;
	}

	public void setActual(double[] actual) {
		this.computedOutputs = actual;
	}

	public void setErrh(double[] errh) {
		this.errh = errh;
	}

	public void setErro(double[] erro) {
		this.erro = erro;
	}

	public void setHIDDEN_NEURONS(int hIDDEN_NEURONS) {
		HIDDEN_NEURONS = hIDDEN_NEURONS;
	}

	public void setHidden(double[] hidden) {
		this.hidden = hidden;
	}

	public void setINPUT_NEURONS(int iNPUT_NEURONS) {
		INPUT_NEURONS = iNPUT_NEURONS;
	}

	public void setInputs(double[] inputs) {
		this.inputs = inputs;
	}

	public void setLEARN_RATE(double lEARN_RATE) {
		LEARN_RATE = lEARN_RATE;
	}

	public void setMAX_SAMPLES(int mAX_SAMPLES) {
		MAX_SAMPLES = mAX_SAMPLES;
	}

	public void setOUTPUT_NEURONS(int oUTPUT_NEURONS) {
		OUTPUT_NEURONS = oUTPUT_NEURONS;
	}

	public void setTarget(double[] target) {
		this.target = target;
	}

	public void setTRAINING_REPS(int tRAINING_REPS) {
		numberOfIterations = tRAINING_REPS;
	}

	public void setWho(double[][] who) {
		this.who = who;
	}

	public void setWih(double[][] wih) {
		this.wih = wih;
	}

	public int getNumberOfIterations() {
		return numberOfIterations;
	}

	public void setNumberOfIterations(int numberOfIterations) {
		this.numberOfIterations = numberOfIterations;
	}

	public double[][] getTrainInputs() {
		return trainInputs;
	}

	public void setTrainInputs(double[][] trainInputs) {
		this.trainInputs = trainInputs;
	}

	public double[][] getTrainOutput() {
		return trainOutput;
	}

	public void setTrainOutput(double[][] trainOutput) {
		this.trainOutput = trainOutput;
	}

	private double sigmoid(double val) {
		return (1.0 / (1.0 + Math.exp(-1*val)));
	}

	private double sigmoidDerivative(double val) {
		return (val * (1.0 - val));
	}
	 private  int maximum(final double[] vector)
	    {
	        // This function returns the index of the maximum of vector().
	        int sel = 0;
	        double max = vector[sel];

	        for(int index = 0; index < OUTPUT_NEURONS; index++)
	        {
	            if(vector[index] > max){
	                max = vector[index];
	                sel = index;
	            }
	        }
	        return sel;
	    }

	public void testNetworkTraining(double[][] testInput, double[][] testOutput) {
		double precision;
		double numberOfWrongPrediction=0;
		double numberOfCorrectPrediction=0;
		double[] confusionMatrix1=new double[3];
		double[] confusionMatrix2=new double[3];
		double[] confusionMatrix3=new double[3];
		// This function simply tests the training vectors against network.
		for (int i = 0; i < testInput.length; i++) {
			for (int j = 0; j < INPUT_NEURONS; j++) {
				inputs[j] = testInput[i][j] ;
			} // j

			feedForward();
		

		
			
			if((maximum(testOutput[i])+1)==1){
				confusionMatrix1[(maximum(computedOutputs))]++;
			}
			if((maximum(testOutput[i])+1)==2){
				confusionMatrix2[(maximum(computedOutputs))]++;
			}
			if((maximum(testOutput[i])+1)==3){
				confusionMatrix3[(maximum(computedOutputs))]++;
			}
			if((maximum(computedOutputs)+1)==(maximum(testOutput[i])+1))
				numberOfCorrectPrediction++;
			else
				numberOfWrongPrediction++;
		} // i
		precision=numberOfCorrectPrediction/(numberOfCorrectPrediction+numberOfWrongPrediction);
		System.out.println("\n\nThe precision:"+precision);
		System.out.println();
		printConfusionMatrix(confusionMatrix1,confusionMatrix2,confusionMatrix3);
		return;
	}

	private void printConfusionMatrix(double[] confusionMatrix1, double[] confusionMatrix2, double[] confusionMatrix3) {
		System.out.println("Actual class\tPredicted class");
		System.out.println("\t\t1\t2\t3");
		System.out.print("1:\t\t"+confusionMatrix1[0]+"\t"+confusionMatrix1[1]+"\t"+confusionMatrix1[2]+"\n");
		System.out.print("2:\t\t"+confusionMatrix2[0]+"\t"+confusionMatrix2[1]+"\t"+confusionMatrix2[2]+"\n");
		System.out.print("3:\t\t"+confusionMatrix3[0]+"\t"+confusionMatrix3[1]+"\t"+confusionMatrix3[2]+"\n");
		
		double accuracy1=confusionMatrix1[0]/(confusionMatrix1[0]+confusionMatrix1[1]+confusionMatrix1[2]);
		double accuracy2=confusionMatrix2[1]/(confusionMatrix2[0]+confusionMatrix2[1]+confusionMatrix2[2]);
		double accuracy3=confusionMatrix3[2]/(confusionMatrix3[0]+confusionMatrix3[1]+confusionMatrix3[2]);
		System.out.println("\n\nClass based Accuracies:\n");
		System.out.println("Class 1:"+accuracy1);
		System.out.println("Class 2:"+accuracy2);
		System.out.println("Class 3:"+accuracy3);
	}

	public void initialize() {
		wih = new double[INPUT_NEURONS + 1][HIDDEN_NEURONS];

		// Hidden to Output Weights (with Biases).
		who = new double[HIDDEN_NEURONS + 1][OUTPUT_NEURONS];

		// Activations.
		inputs = new double[INPUT_NEURONS];

		hidden = new double[HIDDEN_NEURONS];

		target = new double[OUTPUT_NEURONS];

		computedOutputs = new double[OUTPUT_NEURONS];

		// Unit errors.
		erro = new double[OUTPUT_NEURONS];

		errh = new double[HIDDEN_NEURONS];
	}

	public void train() {
		int sample = 0;

		assignRandomWeights();

		// Train the network.
		for (int epoch = 0; epoch < numberOfIterations; epoch++) {

			if (sample == MAX_SAMPLES) {
				sample = 0;
			}

			for (int i = 0; i < INPUT_NEURONS; i++) {
				inputs[i] = trainInputs[sample][i];
			} // i

			for (int i = 0; i < OUTPUT_NEURONS; i++) {
				target[i] = trainOutput[sample][i];
			} // i

			feedForward();

			backPropagate();
			sample += 1;
		} // epoch
		return;
	}

}