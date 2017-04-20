package ie.gmit.sw.ai.fuzz;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Runner {
	public static void main(String[] args) {
		FIS fis = FIS.load("./fcl/funding.fcl",true);
		FunctionBlock fb = fis.getFunctionBlock("Project");
		
		JFuzzyChart.get().chart(fb);
		fis.setVariable("funding", 38);
		fis.setVariable("staffing", 9);
		fis.evaluate();
		Variable risk = fb.getVariable("risk");
		System.out.println("Risk?" + risk.getValue() + "%");
		JFuzzyChart.get().chart(risk, true);
		JFuzzyChart.get().chart(risk.getDefuzzifier(), "Result Risk (%)", true);
	}
}
