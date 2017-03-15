package th.brandenburg.reisekostenabrechnung_Prozess;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


public class RkaZuruechweisen implements JavaDelegate {

  // TODO: Set Mail Server Properties
  private static final String HOST = "smtp.gmail.com";
  private static final String USER = "demodemo1234512345@gmail.com";
  private static final String PWD = "google12345";

  private final static Logger LOGGER = Logger.getLogger("RkaRückweisung");

  public void execute(DelegateExecution execution) throws Exception {
      String anred = (String) execution.getVariable("anrede");    
      String var = (String) execution.getVariable("name");   
      String reisekostenabrechnugId = (String) execution.getVariable("reisekostenabrechnugId");
      String recipient = (String) execution.getVariable("emailAdress");
      
      Email email = new SimpleEmail();
      email.setCharset("utf-8");
      email.setHostName(HOST);
      email.setSmtpPort(465);
      email.setAuthentication(USER, PWD);
      email.setSSL(true);
      
      try {
          email.setFrom("noreply@camunda.org");
          email.setSubject("Rückweisung der Reisekostenabrechnung Nr: " + reisekostenabrechnugId + " ");
          email.setMsg("Sehr geehrte " + anred + " " + var + ", \n\n Leider wurde der Antrag auf Erstattung Ihrer Reisekosten mit der ID " + reisekostenabrechnugId + " aufgrund fehlender Unterlage zurückgewiesen."
          		+ "Bitte vervollständigen Sie die Unterlagen."
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
