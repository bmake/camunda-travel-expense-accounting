package th.brandenburg.reisekostenabrechnung_Prozess;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class GeldUeberweisen implements JavaDelegate {

	
	 // TODO: Set Mail Server Properties
	  private static final String HOST = "smtp.gmail.com";
	  private static final String USER = "demodemo1234512345@gmail.com";
	  private static final String PWD = "google12345";

	  private final static Logger LOGGER = Logger.getLogger("geldÜberweisung");

	  public void execute(DelegateExecution execution) throws Exception {
	      String anred = (String) execution.getVariable("anrede");    
	      String var = (String) execution.getVariable("name");   
	      String reisekostenabrechnugId = (String) execution.getVariable("reisekostenabrechnugId");
	      String recipient = (String) execution.getVariable("emailAdress");
	      Double summe = (Double) execution.getVariable("summe");
	      
	      
	      Email email = new SimpleEmail();
	      email.setCharset("utf-8");
	      email.setHostName(HOST);
	      email.setSmtpPort(465);
	      email.setAuthentication(USER, PWD);
	      email.setSSL(true);
	      
	      try {
	          email.setFrom("noreply@camunda.org");
	          email.setSubject("Bestätigung der Erstattung Ihrer Reisekosten Nr: " + reisekostenabrechnugId + " ");
	          email.setMsg("Sehr geehrte " + anred + " " + var + ", \n\n Wir freuen uns Ihnen mittelein zu können, dass die kosten Ihrer Reise mit der ID " + reisekostenabrechnugId + " erstatten wurde."
	          		+ "\n Die summe Ihrer Kosten beträgt ingesamt: " + summe +" Euro. "
	          		+ "\n\n Mit freundlichen Gruessen, "
	          		+ "\n Ihr Reisemanagement Team");

	          email.addTo(recipient);

	          email.send();
	          LOGGER.info("Task Assignment Email successfully sent to address: " + recipient); 

	        } catch (Exception e) {
	          LOGGER.log(Level.WARNING, "Could not send email to assignee", e);
	        }
	      
	    }

	}
