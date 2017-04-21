package ie.gmit.sw.ai.fuzz;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Runner {
	public static void main(String[] args) {
		FIS fis = FIS.load("./fcl/energy.fcl",true);
		FunctionBlock fb = fis.getFunctionBlock("Project");
		
		JFuzzyChart.get().chart(fb);
		fis.setVariable("energy", 38);
		fis.setVariable("damage", 9);
		fis.evaluate();
		Variable damage = fb.getVariable("damage");
		System.out.println("damage?" + damage.getValue() + "%");
		JFuzzyChart.get().chart(damage, true);
		JFuzzyChart.get().chart(damage.getDefuzzifier(), "Result damage (%)", true);
	}
}
