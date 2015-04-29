/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id$
 */

package sempath.xsd;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class PatientInfoComplexType.
 * 
 * @version $Revision$ $Date$
 */
public class PatientInfoComplexType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id
     */
    private int _id;

    /**
     * keeps track of state for field: _id
     */
    private boolean _has_id;

    /**
     * Field _states
     */
    private sempath.xsd.States _states;

    /**
     * Field _pendingSteps
     */
    private sempath.xsd.PendingSteps _pendingSteps;

    /**
     * Field _finishedSteps
     */
    private sempath.xsd.FinishedSteps _finishedSteps;

    /**
     * Field _diagnosisList
     */
    private sempath.xsd.DiagnosisList _diagnosisList;


      //----------------/
     //- Constructors -/
    //----------------/

    public PatientInfoComplexType() 
     {
        super();
    } //-- sempath.xsd.PatientInfoComplexType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteId
     * 
     */
    public void deleteId()
    {
        this._has_id= false;
    } //-- void deleteId() 

    /**
     * Returns the value of field 'diagnosisList'.
     * 
     * @return DiagnosisList
     * @return the value of field 'diagnosisList'.
     */
    public sempath.xsd.DiagnosisList getDiagnosisList()
    {
        return this._diagnosisList;
    } //-- sempath.xsd.DiagnosisList getDiagnosisList() 

    /**
     * Returns the value of field 'finishedSteps'.
     * 
     * @return FinishedSteps
     * @return the value of field 'finishedSteps'.
     */
    public sempath.xsd.FinishedSteps getFinishedSteps()
    {
        return this._finishedSteps;
    } //-- sempath.xsd.FinishedSteps getFinishedSteps() 

    /**
     * Returns the value of field 'id'.
     * 
     * @return int
     * @return the value of field 'id'.
     */
    public int getId()
    {
        return this._id;
    } //-- int getId() 

    /**
     * Returns the value of field 'pendingSteps'.
     * 
     * @return PendingSteps
     * @return the value of field 'pendingSteps'.
     */
    public sempath.xsd.PendingSteps getPendingSteps()
    {
        return this._pendingSteps;
    } //-- sempath.xsd.PendingSteps getPendingSteps() 

    /**
     * Returns the value of field 'states'.
     * 
     * @return States
     * @return the value of field 'states'.
     */
    public sempath.xsd.States getStates()
    {
        return this._states;
    } //-- sempath.xsd.States getStates() 

    /**
     * Method hasId
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasId()
    {
        return this._has_id;
    } //-- boolean hasId() 

    /**
     * Method isValid
     * 
     * 
     * 
     * @return boolean
     */
    public boolean isValid()
    {
        try {
            validate();
        }
        catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } //-- boolean isValid() 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param out
     */
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param handler
     */
    public void marshal(org.xml.sax.ContentHandler handler)
        throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler) 

    /**
     * Sets the value of field 'diagnosisList'.
     * 
     * @param diagnosisList the value of field 'diagnosisList'.
     */
    public void setDiagnosisList(sempath.xsd.DiagnosisList diagnosisList)
    {
        this._diagnosisList = diagnosisList;
    } //-- void setDiagnosisList(sempath.xsd.DiagnosisList) 

    /**
     * Sets the value of field 'finishedSteps'.
     * 
     * @param finishedSteps the value of field 'finishedSteps'.
     */
    public void setFinishedSteps(sempath.xsd.FinishedSteps finishedSteps)
    {
        this._finishedSteps = finishedSteps;
    } //-- void setFinishedSteps(sempath.xsd.FinishedSteps) 

    /**
     * Sets the value of field 'id'.
     * 
     * @param id the value of field 'id'.
     */
    public void setId(int id)
    {
        this._id = id;
        this._has_id = true;
    } //-- void setId(int) 

    /**
     * Sets the value of field 'pendingSteps'.
     * 
     * @param pendingSteps the value of field 'pendingSteps'.
     */
    public void setPendingSteps(sempath.xsd.PendingSteps pendingSteps)
    {
        this._pendingSteps = pendingSteps;
    } //-- void setPendingSteps(sempath.xsd.PendingSteps) 

    /**
     * Sets the value of field 'states'.
     * 
     * @param states the value of field 'states'.
     */
    public void setStates(sempath.xsd.States states)
    {
        this._states = states;
    } //-- void setStates(sempath.xsd.States) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return PatientInfoComplexType
     */
    public static sempath.xsd.PatientInfoComplexType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (sempath.xsd.PatientInfoComplexType) Unmarshaller.unmarshal(sempath.xsd.PatientInfoComplexType.class, reader);
    } //-- sempath.xsd.PatientInfoComplexType unmarshal(java.io.Reader) 

    /**
     * Method validate
     * 
     */
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
