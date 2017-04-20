package ie.gmit.sw.ai.nn;

import ie.gmit.sw.ai.nn.activator.*;

/*
 * This class was taken from once of the classes we worked on in a lab, it's original name was GameCharacter.
 */

public class SpartanWarriorNeuralNet {

	private static NeuralNetwork nn;
		
	private static double[][] data = { //Health, Sword, Gun, Enemies
			{ 2, 0, 0, 0 }, { 2, 0, 0, 1 }, { 2, 0, 1, 1 }, { 2, 0, 1, 2 }, { 2, 1, 0, 2 },
			{ 2, 1, 0, 1 }, { 1, 0, 0, 0 }, { 1, 0, 0, 1 }, { 1, 0, 1, 1 }, { 1, 0, 1, 2 }, 
			{ 1, 1, 0, 2 }, { 1, 1, 0, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 1 }, { 0, 0, 1, 1 }, 
			{ 0, 0, 1, 2 }, { 0, 1, 0, 2 }, { 0, 1, 0, 1 } };

	private static double[][] expected = { //Panic, Attack, Hide, Run
			{ 0.0, 0.0, 1.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0 }, { 1.0, 0.0, 0.0, 0.0 }, { 1.0, 0.0, 0.0, 0.0 }, 
			{ 0.0, 0.0, 0.0, 1.0 }, { 1.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 }, 
			{ 1.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 }, { 0.0, 0.0, 0.0, 1.0 }, { 0.0, 0.0, 0.0, 1.0 }, 
			{ 0.0, 0.0, 1.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 }, { 0.0, 0.0, 0.0, 1.0 }, { 0.0, 1.0, 0.0, 0.0 }, 
			{ 0.0, 1.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 } };
		
	static {
		nn = new NeuralNetwork(Activator.ActivationFunction.Sigmoid, 4, 3, 4);
		
		Trainator trainer = new BackpropagationTrainer(nn);
		trainer.train(data, expected, 0.6, 100);
	}
	
	public SpartanWarriorNeuralNet() throws Exception {
		// TODO Auto-generated constructor stub
	}
	
	public void action(double health, double sword, double gun, double enemies) throws Exception {
		double[] params = {health, sword, gun, enemies};
		double[] result = nn.process(params);
		
		for (double val : result) {
			System.out.println(val);
		}
		
		int action = Utils.getMaxIndex(result) + 1;
		
		switch (action)	{
			case 1:
				panic();
				break;
			
			case 2:
				attack();
				break;
			
			case 3: 
				hide();
				break;
			
			default:
				runAway();
				break;
		}
	}
	
	private void panic() {
		System.out.println("Panic...");
	}
	
	private void attack() {
		System.out.println("Attack...");
	}
	
	private void hide() {
		System.out.println("Hide...");
	}
	
	private void runAway() {
		System.out.println("Run...");
	}
	
	public static void main(String[] args) throws Exception {
		SpartanWarriorNeuralNet spartan = new SpartanWarriorNeuralNet();
		spartan.action(
				Double.parseDouble(args[0]),
				Double.parseDouble(args[1]),
				Double.parseDouble(args[2]),
				Double.parseDouble(args[3])
		);
	}
}
