package sempath;

import javax.servlet.http.HttpServlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.db.GraphSession;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;

import edu.stanford.smi.protegex.owl.model.RDFIndividual;

import sempath.engine.PatientOntology;
import sempath.xsd.*;
import sempath.xsd.exam.ExamRequest;

/**
 * Servlet Class
 *
 * @web.servlet              name="SemPath"
 *                           display-name="Name for SemPath"
 *                           description="Description for SemPath"
 * @web.servlet-mapping      url-pattern="/SemPath"
 * @web.servlet-init-param   name="A parameter"
 *                           value="A value"
 */
public class SemPathServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HashMap map = new HashMap();
	HashMap examMap = new HashMap();
	PatientOntology ontology;
	int patientId = 0;
	
        /*
	JbpmConfiguration conf = JbpmConfiguration.getInstance();
	JbpmContext cxt = conf.createJbpmContext();
	GraphSession graph = cxt.getGraphSession();
        */
        
	public PatientOntology getOntology(int id)
	{
		this.ontology = (PatientOntology) map.get(id);
		if(this.ontology == null) {
			if(id != 0) return null;
			String owlfile = this.getServletContext().getRealPath("sempath.owl");
                        //String owlfile = new String("/home/panos/Downloads/CarePaths/ontologies/SemPath.owl");
			this.ontology = new PatientOntology(owlfile);
			this.ontology.setId(id);
			map.put(id, this.ontology);
		}
		
		this.patientId = id;
		return this.ontology;
	}
	
	public PatientOntology getOntology()
	{
		return this.getOntology(this.patientId);
	}
	
	public SemPathServlet() {
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter("action");
		String idstr = req.getParameter("id");
		int pid = 0;
		if(idstr != null) {
			pid = Integer.parseInt(idstr);
			this.getOntology(pid);
		}
		
		if(action == null) {
			this.info(pid, req, resp);
		} else {
			this.handleAction(req, resp, action, pid);
		}

		this.controls(pid, req, resp);
	}
	
	public void addPatientState(int pid, String state)
	{
		this.getOntology(pid).addPatientState(state);
	}

	public void runEngine(int pid)
	{
		this.getOntology(pid).runEngine();
	}
	
	public void executePendingSteps(int pid)
	{
		String[] steps = this.getOntology(pid).getStringPendingSteps();
		if(steps == null || steps.length == 0) return;
		try {
			int i = 0;
				String step = steps[i];
	
				//String processName = this.getOntology(pid).getStepProcessName(step);
				//String url = this.getOntology(pid).getStepWebServiceURL(step);
//				ProcessDefinition definition = graph.findLatestProcessDefinition(processName);
//				ProcessInstance instance = definition.createProcessInstance();
				String exam = this.getOntology(pid).getExamXML(step);
				System.out.println(step);
                                System.out.println(exam);
//				instance.getContextInstance().setVariable("url", url);
//				instance.getContextInstance().setVariable("exam", exam);
//				instance.signal();
				
//				while(!instance.hasEnded() || this.getExamRequest(pid, step)== null);
				//while(this.getExamRequest(pid, step)== null)
                                    System.out.println("Waiting");
                                
				sempath.xsd.exam.ExamRequest ereq = this.getExamRequest(pid, step);
				sempath.xsd.exam.State[] results = ereq.getState();
				for(int r = 0; r < results.length; r++) {
					System.out.println("add state: " + results[r].getName());
					if(results[r].getValue() == null) {
						this.getOntology(pid).addPatientState(results[r].getName());
					} else {
						this.getOntology(pid).addPatientState(results[r].getName(),
								Float.parseFloat(results[r].getValue()));						
					}
				}
				
				System.out.println("Step executed: " + step);
				this.getOntology(pid).finishStep(step);
				this.getOntology(pid).addPatientInfo(this.getPatientInfo(pid));
				System.out.println("Proceed.. " + step);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		steps = this.getOntology(pid).getStringPendingSteps();
		if(steps != null && steps.length != 0) {
			if(!this.hasCuredState(steps)) {
				this.executePendingSteps(pid);
			}
		}
	}
	
	private boolean hasCuredState(String[] steps) {
		for(int i = 0; i < steps.length; i++) {
			if(steps[i].equalsIgnoreCase("Cured")) return true;
		}
		
		return true;
	}
	
	private void handleAction(HttpServletRequest req, HttpServletResponse resp, String action, int pid) {
		String redURL = "index.jsp?id=" + pid;
		if(action.equalsIgnoreCase("addstate")) {
			String state = req.getParameter("state");
			this.addPatientState(pid, state);
		    try {
		    	resp.sendRedirect(redURL);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(action.equalsIgnoreCase("run")) {
			this.runEngine(pid);
		    try {
		    	resp.sendRedirect(redURL);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(action.equalsIgnoreCase("execute")) {
			this.executePendingSteps(pid);
		    try {
		    	resp.sendRedirect(redURL);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(action.equalsIgnoreCase("info")) {
			String h = req.getParameter("history");
			if(h == null) {
				this.showInfo(pid, resp);
			} else {
				int history = Integer.parseInt(h);
				this.showInfo(pid, resp, history);
			}
		} else if(action.equalsIgnoreCase("sempathinfo")) {
			this.showEngineInfo(resp);
		} else if(action.equalsIgnoreCase("new")) {
			String namestr = req.getParameter("name");
			this.newPatient(pid, namestr);
		}
		
	}

	private void newPatient(int pid, String name) {
		String owlfile = this.getServletContext().getRealPath("sempath.owl");
		this.ontology = new PatientOntology(owlfile);
		this.ontology.setPatientName(name);
		this.ontology.setId(pid);
		map.put(pid, this.ontology);
	}
	
	private void printInfo(PatientInfo info, HttpServletResponse resp) {
		resp.setContentType("text/xml; charset=UTF-8");
		try {
			PrintWriter writer = resp.getWriter();
			info.marshal(writer);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private PatientInfo getPatientInfo(int pid) {
		PatientInfo info = new PatientInfo();
		info.setId(pid);
		States states = new States();
		RDFIndividual[] array;
		sempath.xsd.State[] stateArray = this.getOntology(pid).getXMLPatientStates();
		states.setState(stateArray);
		info.setStates(states);
		
		PendingSteps pendingSteps = new PendingSteps();
		array = this.getOntology(pid).getPendingSteps();
		for(int i = 0; i < array.length; i++) {
			Step step = new Step();
			step.setName(array[i].getName());
			pendingSteps.addStep(step);
		}
		info.setPendingSteps(pendingSteps);
		
		FinishedSteps finishedSteps = new FinishedSteps();
		array = this.getOntology(pid).getFinishedSteps();
		System.out.println("steps: " + array.length);
		for(int i = 0; i < array.length; i++) {
			String[] times = this.getOntology(pid).getEndTime(array[i]);
			System.out.println("times: " + times.length);
			for(int t = 0; t < times.length; t++) {
				Step step = new Step();
				step.setName(array[i].getName());
				step.setEndTime(times[t]);
				System.out.println(step.getName() + " - " + step.getEndTime());
				finishedSteps.addStep(step);
			}
		}
		info.setFinishedSteps(finishedSteps);
		
		DiagnosisList dlist = new DiagnosisList();
		String[] diag = this.getOntology(pid).getStringDiagnosis();
		System.out.println("dlist: " + diag.length);
		for(int d = 0; d < diag.length; d++) {
			Diagnosis di = new Diagnosis();
			di.setName(diag[d]);
			dlist.addDiagnosis(di);
			System.out.println(di.getName());
		}
		info.setDiagnosisList(dlist);
		return info;
	}
	
	private PatientInfo getPatientInfo(int pid, int history) {
		return this.getOntology(pid).getPatientInfo(history);
	}

	private void showInfo(int pid, HttpServletResponse resp, int history)
	{
		this.printInfo(this.getPatientInfo(pid, history), resp);
	}
	
	private void showInfo(int pid, HttpServletResponse resp)
	{
		this.printInfo(this.getPatientInfo(pid), resp);
	}
	
	private void showEngineInfo(HttpServletResponse resp) {
		resp.setContentType("text/xml; charset=UTF-8");
		try {
			PrintWriter writer = resp.getWriter();
			SemPathInfo info = new SemPathInfo();
			Iterator i = this.map.keySet().iterator();
			while(i.hasNext()) {
				String id = i.next().toString();
				String name = this.getOntology(Integer.parseInt(id)).getPatientName();
				Patient p = new Patient();
				p.setId(id);
				p.setName(name);
				info.addPatient(p);
			}
			
			sempath.xsd.State[] states = this.getOntology(0).getXMLAllPatientStates();
			info.setState(states);
			info.marshal(writer);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String exam = req.getParameter("exam");
		if(exam != null && exam.length() > 0) {
			System.out.println("exam received: " + exam);
			this.storeExam(exam);
		}
	}
	
	private void storeExam(String exam) {
		try {
			sempath.xsd.exam.ExamRequest examReq = (sempath.xsd.exam.ExamRequest)
			  sempath.xsd.exam.ExamRequest.unmarshal(new StringReader(exam));
			
			int pid = examReq.getId();
			String name = examReq.getExam();
			
			this.examMap.put(pid + ";" + name, examReq);
		} catch (MarshalException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}
	
	private ExamRequest getExamRequest (int pid, String exam) {
		sempath.xsd.exam.ExamRequest req =
			(sempath.xsd.exam.ExamRequest) this.examMap.get(pid + ";" + exam);
		return req;
	}

	private void info(int pid, HttpServletRequest req, HttpServletResponse resp)
	{
		try {
			ServletOutputStream out = resp.getOutputStream();
			out.println("<table><tr><td valign=top>");
			this.patientStates(pid, out);
			out.println("</td><td valign=top>");
			this.pendingSteps(pid, out);
			out.println("</td></tr></table>");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void controls(int pid, HttpServletRequest req, HttpServletResponse resp)
	{
		try {
			ServletOutputStream out = resp.getOutputStream();
			out.println("<form method=\"get\" action=\"sempath\">");
			out.println("<input type=\"hidden\" name=\"id\" value=\"" + pid + "\"/>");
			out.println("<input type=\"hidden\" name=\"action\" value=\"run\"/>");
			out.println("<input type=\"submit\" value=\"Run Engine\"/>");
			out.println("</form>");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void patientStates(int pid, ServletOutputStream out) {
		String[] states; 
		try {
			out.println("<h3>Patient states</h3>");
			out.println("<form method=\"get\" action=\"sempath\">");
			out.println("<input type=\"hidden\" name=\"id\" value=\"" + pid + "\"/>");
			out.println("<input type=\"hidden\" name=\"action\" value=\"removestate\"/>");
			out.println("<select size=\"10\" width=\"100\">");
			states = this.getOntology(pid).getStringPatientStates();
			for(int i = 0; i < states.length; i++) {
				out.println("<option value=\"" + states[i] + "\">" + states[i] + "</option>");
			}
			out.println("</select>");
			out.println("<input type=\"submit\" value=\"Remove State\"/>");
			out.println("</form>");
			out.println("<br>");
			out.println("<form method=\"get\" action=\"sempath\">");
			out.println("<input type=\"hidden\" name=\"id\" value=\"" + pid + "\"/>");
			out.println("<input type=\"hidden\" name=\"action\" value=\"addstate\"/>");
			out.println("<select name=\"state\" size=\"1\" width=\"100\">");
			states = this.getOntology(pid).getStringAllPatientStates();
			for(int i = 0; i < states.length; i++) {
				out.println("<option value=\"" + states[i] + "\">" + states[i] + "</option>");
			}
			out.println("</select>");
			out.println("<input type=\"submit\" value=\"Add State\"/>");
			out.println("</form>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void pendingSteps(int pid, ServletOutputStream out) {
		try {
			out.println("<h3>Pending steps</h3>");
			out.println("<form method=\"get\" action=\"sempath\">");
			out.println("<select size=\"10\" multiple>");
			String[] steps = this.getOntology(pid).getStringPendingSteps();
			for(int i = 0; i < steps.length; i++) {
				out.println("<option value=\"" + steps[i] + "\">" + steps[i] + "</option>");
			}
			out.println("</select>");
			out.println("<input type=\"hidden\" name=\"id\" value=\"" + pid + "\"/>");
			out.println("<input type=\"hidden\" name=\"action\" value=\"execute\"/>");
			out.println("<input type=\"submit\" value=\"Execute\"/>");
			out.println("</form>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String message="message one";
	public String getMessage() { return this.message; }
	public void setMessage(String value) { this.message = value; }
}
