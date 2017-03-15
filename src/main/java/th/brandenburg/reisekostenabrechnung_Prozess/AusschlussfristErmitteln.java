package th.brandenburg.reisekostenabrechnung_Prozess;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.*;
import java.text.*;

public class AusschlussfristErmitteln implements JavaDelegate {

	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		GregorianCalendar worldTour = new GregorianCalendar(2017, Calendar.MARCH, 29);
	      worldTour.add(GregorianCalendar.DATE, - 180);
	      Date d = worldTour.getTime();
	      DateFormat df = DateFormat.getDateInstance();
	      String ausschlussfrist = df.format(d);
	      System.out.println(" Laut der Ausschlussfrist sollte die Dienstreise spätesten am " + ausschlussfrist +" beendet sein");

	}

}
