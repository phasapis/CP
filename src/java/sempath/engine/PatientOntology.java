package sempath.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.util.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.io.File;
import java.util.Iterator;
import edu.stanford.smi.protege.model.Cls;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import edu.stanford.smi.protege.model.Instance;
import sempath.xsd.PatientInfo;
import sempath.xsd.State;

import edu.stanford.smi.protegex.owl.*;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import edu.stanford.smi.protegex.owl.model.*;
import edu.stanford.smi.protegex.owl.model.impl.XMLSchemaDatatypes;
import edu.stanford.smi.protegex.owl.swrl.bridge.exceptions.SWRLRuleEngineBridgeException;
import edu.stanford.smi.protegex.owl.swrl.bridge.jess.SWRLJessBridge;
import java.util.logging.Level;
import java.util.logging.Logger;
import jess.Rete;

public class PatientOntology {
	String filename;
	JenaOWLModel model;
	SWRLJessBridge bridge;
	Rete rete;
	
	RDFIndividual patient;
	RDFIndividual pathway;
	
	RDFProperty state;
	
	String propFile = "/home/panos/Downloads/sempath.properties";
	HashMap infoHistory = new HashMap();
	
	int pid = 0;
	
	public int getId() { return pid; }
	public void setId(int id) { pid = id; }
	
	public PatientOntology(String filename)
	{
            /*
            InputStream input = null;
		try {
                    Properties prop = new Properties();
                    try {
                        prop.load(new FileInputStream(propFile));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String protegeDir = prop.getProperty("protege.dir");
                    System.out.println("protege.dir = " + protegeDir);
                    System.setProperty("protege.dir", protegeDir);
                    this.filename = filename;
                    //input = new FileInputStream(this.filename);
                    //this.model = ProtegeOWL.createJenaOWLModelFromInputStream(input);
                
		this.initEngine();
		this.initData();
                
		} catch (Exception ex) {
                Logger.getLogger(PatientOntology.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(PatientOntology.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            */
                Properties prop = new Properties();
                try {
                        prop.load(new FileInputStream(propFile));
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                }

                String protegeDir = prop.getProperty("protege.dir");
                System.out.println("protege.dir = " + protegeDir);

                System.setProperty("protege.dir", protegeDir);
                this.filename = filename;
                this.initEngine();
                this.initData();            
	}
	
	public void printPatientStates()
	{
		RDFIndividual[] t = this.getPatientStates();
		System.out.println("Patient states:");
		for(int i = 0; i < t.length; i++) {
			System.out.println("  " + i + ": " + t[i]);
		}
		System.out.println();
	}
	
	public void printPendingSteps()
	{
		RDFIndividual[] t = this.getPendingSteps();
		System.out.println("Pending steps:");
		for(int i = 0; i < t.length; i++) {
			System.out.println("  " + i + ": " + t[i]);
		}
		System.out.println();
	}
	
	public void finishStep(String step)
	{
//		RDFSDatatype dateType = this.model.getRDFSDatatypeByName("xsd:date");
		RDFIndividual individual = this.model.getRDFIndividual(step);
//		individual.setPropertyValue(
//				this.model.getRDFProperty("hasEndTime"), new java.util.Date(System.currentTimeMillis()));
		this.pathway.removePropertyValue(
				this.model.getRDFProperty("hasPendingStep"), individual);
		Integer v = (Integer) individual.getPropertyValue(this.model.getRDFProperty("hasOrder"));
		System.out.println("******** hasOrder: " + v);
		individual.setPropertyValue(this.model.getRDFProperty("hasOrder"),
				new Integer(v.intValue() + 1));
		individual.addPropertyValue(this.model.getRDFProperty("hasEndTime"),
				XMLSchemaDatatypes.getDateTimeString(new Date(System.currentTimeMillis()))); 
		this.pathway.addPropertyValue(
				this.model.getRDFProperty("hasFinishedStep"), individual);
	}
	
	private void initEngine()
	{
		try {
			InputStream input = new FileInputStream(this.filename);
			this.model = ProtegeOWL.createJenaOWLModelFromInputStream(input);
			this.rete = new Rete();
			this.bridge = new SWRLJessBridge(model, rete);
			
			RDFIndividual[] steps = this.getAllClinicalPathSteps();
			for(int i = 0; i < steps.length; i++) {
				steps[i].setPropertyValue(this.model.getRDFProperty("hasOrder"), new Integer(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} catch (SWRLRuleEngineBridgeException se) {
			se.printStackTrace();
		}
	}
	
	private void initData()
	{
		RDFSNamedClass c;
		RDFProperty p;
		Collection collection;
		Iterator i;

		c = this.model.getRDFSNamedClass("Patient");
		collection = c.getInstances(false);
		i = collection.iterator();
		if(i.hasNext()) {
			this.patient = (RDFIndividual) i.next();
			System.out.println("Found patient: " + this.patient.getName());
		} else {
			System.err.println("No patient found in ontology");
		}

		p = this.model.getRDFProperty("hasClinicalPath");
		this.pathway = (RDFIndividual) this.patient.getPropertyValue(p);
		
		this.state = this.model.getRDFProperty("hasPatientState");
	}
	
	public void runEngine()
	{
		try {
			this.bridge.infer();
			if(this.getPendingSteps().length == 0) {
				this.setCuredState();
			}
		} catch (SWRLRuleEngineBridgeException e) {
			e.printStackTrace();
		}
	}
	
	private void setCuredState() {
		this.pathway.setPropertyValue(
				this.model.getRDFProperty("hasPendingStep"),
				this.model.getRDFIndividual("Cured"));
	}

	public RDFIndividual[] getPendingSteps()
	{
		RDFIndividual[] steps = null;
		RDFProperty p;
		Collection c;
		Iterator i;

		System.out.println("getPendingSteps");
		p = this.model.getRDFProperty("hasPendingStep");
		c = this.pathway.getPropertyValues(p);
		
		steps = new RDFIndividual[c.size()];
		i = c.iterator();
		int count = 0;
		while(i.hasNext()) {
			steps[count++] = (RDFIndividual) i.next();
		}

		System.out.println("getPendingSteps OK");
		return steps;
	}
	
	public RDFIndividual[] getAllPatientStates()
	{
		RDFIndividual[] states = null;
		RDFSNamedClass ps = null;
		Collection c;
		Iterator i;
		
		ps = this.model.getRDFSNamedClass("PatientState");
		c = ps.getInstances(true);
		i = c.iterator();
		states = new RDFIndividual[c.size()];
		int count = 0;
		while(i.hasNext()) {
			states[count++] = (RDFIndividual) i.next();
		}
		
		return states;
	}

	public String[] getStringAllPatientStates()
	{
		RDFIndividual[] rdf = this.getAllPatientStates();
		String[] states = new String[rdf.length];
		for(int i = 0; i < rdf.length; i++) {
			states[i] = rdf[i].getName();
		}
		return states;
	}
	
	public RDFIndividual[] getAllClinicalPathSteps()
	{
		RDFIndividual[] states = null;
		RDFSNamedClass ps = null;
		Collection c;
		Iterator i;
		
		ps = this.model.getRDFSNamedClass("ClinicalPathStep");
		c = ps.getInstances(false);
		i = c.iterator();
		states = new RDFIndividual[c.size()];
		int count = 0;
		while(i.hasNext()) {
			states[count++] = (RDFIndividual) i.next();
		}
		
		return states;
	}
	
	public void save(String file)
	{
		try {
			this.model.save(new URI(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public RDFIndividual[] getPatientStates()
	{
		Collection c = this.patient.getPropertyValues(this.state);
		RDFIndividual[] states = new RDFIndividual[c.size()];
		int count = 0;
		Iterator i = c.iterator();
		while(i.hasNext()) {
			RDFIndividual s = (RDFIndividual) i.next();
			states[count++] = s;
		}
		
		return states;
	}
	
	public String[] getStringPatientStates()
	{
		RDFIndividual[] rdf = this.getPatientStates();

		String[] states = new String[rdf.length];
		for(int i = 0; i < rdf.length; i++) {
			states[i] = rdf[i].getName();
		}
		return states;
	}
	
	public sempath.xsd.State[] getXMLPatientStates()
	{
		RDFIndividual[] rdf = this.getPatientStates();
		sempath.xsd.State[] states = new sempath.xsd.State[rdf.length];
		for(int i = 0; i < rdf.length; i++) {
			states[i] = new sempath.xsd.State();
			states[i].setName(rdf[i].getName());
			if(rdf[i].hasRDFType(this.model.getRDFSNamedClass("ValuePatientState"))) {
				states[i].setType(sempath.xsd.types.XSDSimpleType.VALUE);
				states[i].setValue(rdf[i].getPropertyValue(this.model.getRDFProperty("hasValue")).toString());
			} else {
				states[i].setType(sempath.xsd.types.XSDSimpleType.SIMPLE);				
			}
		}
		return states;
	}
	
	public String[] getStringPendingSteps()
	{
		RDFIndividual[] rdf = this.getPendingSteps();
		String[] states = new String[rdf.length];
		for(int i = 0; i < rdf.length; i++) {
			states[i] = rdf[i].getName();
		}
		return states;
	}
	
	public void addPatientState(String state)
	{
		RDFIndividual individual = this.model.getRDFIndividual(state);
		this.patient.addPropertyValue(this.state, individual);
	}
	
	public void addPatientState(String state, float value)
	{
		RDFIndividual individual = this.model.getRDFIndividual(state);
		individual.setPropertyValue(this.model.getRDFProperty("hasValue"), new Float(value));
		this.patient.addPropertyValue(this.state, individual);
	}
	
	public RDFIndividual[] getFinishedSteps() {
		RDFIndividual[] steps = null;
		RDFProperty p;
		Collection c;
		Iterator i;

		p = this.model.getRDFProperty("hasFinishedStep");
		c = this.pathway.getPropertyValues(p);
		
		steps = new RDFIndividual[c.size()];
		i = c.iterator();
		int count = 0;
		while(i.hasNext()) {
			steps[count++] = (RDFIndividual) i.next();
		}
		
		return steps;
		
	}

	public String[] getStringFinishedSteps() {
		RDFIndividual[] rdf = this.getFinishedSteps();
		String[] states = new String[rdf.length];
		for(int i = 0; i < rdf.length; i++) {
			states[i] = rdf[i].getName();
		}
		return states;
	}
	
	public String getStepProcessName(String step)
	{
		String process = "";
		RDFIndividual i = this.model.getRDFIndividual(step);
		System.out.println("*** look for " + step + ".hasJBPMProcess");
		Object o = i.getPropertyValue(this.model.getRDFProperty("hasJBPMProcess"));
		if(o != null) process = (String) o;
		else process = null;
		System.out.println("*** ... found: " + process);
		return process;
	}
	
	public String getStepWebServiceURL(String step)
	{
		String process = "";
		RDFIndividual i = this.model.getRDFIndividual(step);
		Object o = i.getPropertyValue(this.model.getRDFProperty("hasWebServiceURL"));
		if(o != null) process = (String) o;
		else process = null;
		return process;
	}

	public String getExamXML(String step) {
		RDFSNamedClass stateValue = this.model.getRDFSNamedClass("ValuePatientState");
		sempath.xsd.exam.ExamRequest exam = new sempath.xsd.exam.ExamRequest();
		exam.setId(this.getId());
		exam.setExam(step);
		
		RDFIndividual s = this.model.getRDFIndividual(step);
		Collection c = s.getPropertyValues(this.model.getRDFProperty("hasRelevantState"));
		Iterator i = c.iterator();
		while(i.hasNext()) {
			sempath.xsd.exam.State xmlstate = new sempath.xsd.exam.State();
			RDFIndividual state = (RDFIndividual) i.next();
			xmlstate.setName(state.getName());
			if(state.hasRDFType(stateValue)) {
				xmlstate.setType("value");
			} else {
				xmlstate.setType("simple");
			}
			exam.addState(xmlstate);
		}
	
		StringWriter writer = new StringWriter();
		try {
			exam.marshal(writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writer.getBuffer().toString();
	}

	public String[] getEndTime(RDFIndividual individual) {
		String[] result;
		Collection c = individual.getPropertyValues(this.model.getRDFProperty("hasEndTime"));
		Iterator i = c.iterator();
		result = new String[c.size()];
		int counter = 0;
		while(i.hasNext()) {
			String s = i.next().toString();
			result[counter] = s;
			counter++;
		}
		
		return result;
	}

	public void setPatientName(String name) {
		this.patient.setPropertyValue(this.model.getRDFProperty("hasName"), name);
	}

	public String getPatientName() {
		return this.patient.getPropertyValue(this.model.getRDFProperty("hasName")).toString();
	}

	public State[] getXMLAllPatientStates() {
		RDFSNamedClass stateValue = this.model.getRDFSNamedClass("ValuePatientState");
		RDFIndividual[] rdf = this.getAllPatientStates();
		State[] states = new State[rdf.length];
		for(int i = 0; i < rdf.length; i++) {
			states[i] = new State();
			states[i].setName(rdf[i].getName());
			if(rdf[i].hasRDFType(stateValue)) {
				states[i].setType(sempath.xsd.types.XSDSimpleType.VALUE);
			} else {
				states[i].setType(sempath.xsd.types.XSDSimpleType.SIMPLE);
			}
		}
		return states;
	}

	public PatientInfo getPatientInfo(int history) {
		return (PatientInfo) this.infoHistory.get("" + history);
	}
	
	public void addPatientInfo(PatientInfo info) {
		if(this.infoHistory.isEmpty()) {
			this.infoHistory.put("0", info);
		} else {
			int size = this.infoHistory.size();
			this.infoHistory.put("" + size, info);
		}
	}

	public String[] getStringDiagnosis() {
		String[] results;
		RDFIndividual[] rdf = this.getPossibleDiagnosis();
		results = new String[rdf.length];
		for(int i = 0; i < rdf.length; i++) {
			results[i] = rdf[i].getName();
		}
		return results;
	}

	private RDFIndividual[] getPossibleDiagnosis() {
		RDFIndividual[] steps = null;
		RDFProperty p;
		Collection c;
		Iterator i;

		p = this.model.getRDFProperty("hasPossibleDiagnosis");
		c = this.patient.getPropertyValues(p);
		
		steps = new RDFIndividual[c.size()];
		i = c.iterator();
		int count = 0;
		while(i.hasNext()) {
			steps[count++] = (RDFIndividual) i.next();
		}
		
		return steps;
	}        
        
        public void addPatientPersonalInformation(String patientID,
                                                  String name,
                                                  String lastName,
                                                  String fathersName,
                                                  String dateOfBirth,
                                                  String occupation,
                                                  String gender,
                                                  String husbandsFirstName,
                                                  String husbandsLastName,
                                                  String husbandOccupation,
                                                  String streetAddress,
                                                  String streetNumber,
                                                  String region,
                                                  String postalCode,
                                                  String homeNumber,
                                                  String workNumber,
                                                  String mobileNumber)
        throws Exception
        {
            RDFIndividual patientIndividual = this.model.getRDFIndividual(patientID);
            RDFProperty prop;
            Date date = new Date();
            
            prop = this.model.getOWLDatatypeProperty("hasName");
            patientIndividual.addPropertyValue(prop, name);

            prop = this.model.getOWLDatatypeProperty("hasLastName");
            patientIndividual.addPropertyValue(prop, lastName);
            
            prop = this.model.getOWLDatatypeProperty("hasFathersName");
            patientIndividual.addPropertyValue(prop, fathersName);

            //prop = this.model.getOWLDatatypeProperty("hasDateOfBirth");
            //patientIndividual.addPropertyValue(prop, date.);
            
            prop = this.model.getOWLDatatypeProperty("hasOccupation");
            patientIndividual.addPropertyValue(prop, occupation);            

            prop = this.model.getOWLDatatypeProperty("hasGender");
            patientIndividual.addPropertyValue(prop, gender);            

            prop = this.model.getOWLDatatypeProperty("hasHusbandFirstName");
            patientIndividual.addPropertyValue(prop, husbandsFirstName);            

            prop = this.model.getOWLDatatypeProperty("hasHusbandLastName");
            patientIndividual.addPropertyValue(prop, husbandsLastName);            

            prop = this.model.getOWLDatatypeProperty("hasHusbandOccupation");
            patientIndividual.addPropertyValue(prop, husbandOccupation);                        

            prop = this.model.getOWLDatatypeProperty("hasStreetAddress");
            patientIndividual.addPropertyValue(prop, streetAddress);              

            prop = this.model.getOWLDatatypeProperty("hasStreetNumber");
            patientIndividual.addPropertyValue(prop, new Integer(streetNumber));                
            
            this.model.save(new File(this.filename).toURI());
        }

	public void addStateToPatientByID(String patientID, String state, float value)
        throws Exception
	{
            RDFIndividual individual = this.model.getRDFIndividual(state);
            individual.setPropertyValue(this.model.getRDFProperty("hasValue"), new Float(value));
            
            RDFIndividual patientIndividual = this.model.getRDFIndividual(patientID);            
            patientIndividual.addPropertyValue(this.state, individual);        
        
            this.model.save(new File(this.filename).toURI());
        }        
        
        public static void main(String[] args)
        {
            InputStream input = null;
            try {
		
                PatientOntology p = new PatientOntology("/home/panos/Desktop/sempath.owl");
                p.addPatientPersonalInformation("JaneDoe", "Jane", "Doe", "Jim", "2001-01-01", null, null, null, null, null, null, "6", null, null, null, null, null);
                
                /*
                JenaOWLModel model;
                input = new FileInputStream("/home/panos/Desktop/test.owl");
                model = ProtegeOWL.createJenaOWLModelFromInputStream(input);
                
                //model.createRDFSNamedClass("TEST3");
                Collection error = new LinkedList();
                Instance instance = model.createInstance("TestInstance", null, true);

                OWLNamedClass theAlert = model.getOWLNamedClass("indivname1");//my class Alert
                RDFIndividual theAlert = model.getRDFIndividual("indivname1");
                Instance diagnosis = model.getInstance("HodgkinLemphoma");
                
                RDFProperty prop = model.getOWLObjectProperty("hasDiagnosis");
                
                System.out.println(theAlert + " " + diagnosis + " " + prop);
                theAlert.addPropertyValue(prop, diagnosis);
                

                RDFSNamedClass ps = model.getRDFSNamedClass("PatientState");
                Collection c = ps.getInstances(true);
                Iterator i = c.iterator();
                RDFIndividual[] states = new RDFIndividual[c.size()];
		int count = 0;
		while(i.hasNext())
                {
                    System.out.println(i.next());
		}                
                Collection errors = new LinkedList();
                model.save(new File("/home/panos/Desktop/test.owl").toURI());
                
                */
                
            } catch (Exception ex) {
                Logger.getLogger(PatientOntology.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
}
