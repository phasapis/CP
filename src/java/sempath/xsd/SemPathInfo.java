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
import java.util.Enumeration;
import java.util.Vector;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class SemPathInfo.
 * 
 * @version $Revision$ $Date$
 */
public class SemPathInfo implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _patientList
     */
    private java.util.Vector _patientList;

    /**
     * Field _stateList
     */
    private java.util.Vector _stateList;


      //----------------/
     //- Constructors -/
    //----------------/

    public SemPathInfo() 
     {
        super();
        _patientList = new Vector();
        _stateList = new Vector();
    } //-- sempath.xsd.SemPathInfo()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addPatient
     * 
     * 
     * 
     * @param vPatient
     */
    public void addPatient(sempath.xsd.Patient vPatient)
        throws java.lang.IndexOutOfBoundsException
    {
        _patientList.addElement(vPatient);
    } //-- void addPatient(sempath.xsd.Patient) 

    /**
     * Method addPatient
     * 
     * 
     * 
     * @param index
     * @param vPatient
     */
    public void addPatient(int index, sempath.xsd.Patient vPatient)
        throws java.lang.IndexOutOfBoundsException
    {
        _patientList.insertElementAt(vPatient, index);
    } //-- void addPatient(int, sempath.xsd.Patient) 

    /**
     * Method addState
     * 
     * 
     * 
     * @param vState
     */
    public void addState(sempath.xsd.State vState)
        throws java.lang.IndexOutOfBoundsException
    {
        _stateList.addElement(vState);
    } //-- void addState(sempath.xsd.State) 

    /**
     * Method addState
     * 
     * 
     * 
     * @param index
     * @param vState
     */
    public void addState(int index, sempath.xsd.State vState)
        throws java.lang.IndexOutOfBoundsException
    {
        _stateList.insertElementAt(vState, index);
    } //-- void addState(int, sempath.xsd.State) 

    /**
     * Method enumeratePatient
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumeratePatient()
    {
        return _patientList.elements();
    } //-- java.util.Enumeration enumeratePatient() 

    /**
     * Method enumerateState
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateState()
    {
        return _stateList.elements();
    } //-- java.util.Enumeration enumerateState() 

    /**
     * Method getPatient
     * 
     * 
     * 
     * @param index
     * @return Patient
     */
    public sempath.xsd.Patient getPatient(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _patientList.size())) {
            throw new IndexOutOfBoundsException("getPatient: Index value '"+index+"' not in range [0.."+(_patientList.size() - 1) + "]");
        }
        
        return (sempath.xsd.Patient) _patientList.elementAt(index);
    } //-- sempath.xsd.Patient getPatient(int) 

    /**
     * Method getPatient
     * 
     * 
     * 
     * @return Patient
     */
    public sempath.xsd.Patient[] getPatient()
    {
        int size = _patientList.size();
        sempath.xsd.Patient[] mArray = new sempath.xsd.Patient[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (sempath.xsd.Patient) _patientList.elementAt(index);
        }
        return mArray;
    } //-- sempath.xsd.Patient[] getPatient() 

    /**
     * Method getPatientCount
     * 
     * 
     * 
     * @return int
     */
    public int getPatientCount()
    {
        return _patientList.size();
    } //-- int getPatientCount() 

    /**
     * Method getState
     * 
     * 
     * 
     * @param index
     * @return State
     */
    public sempath.xsd.State getState(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _stateList.size())) {
            throw new IndexOutOfBoundsException("getState: Index value '"+index+"' not in range [0.."+(_stateList.size() - 1) + "]");
        }
        
        return (sempath.xsd.State) _stateList.elementAt(index);
    } //-- sempath.xsd.State getState(int) 

    /**
     * Method getState
     * 
     * 
     * 
     * @return State
     */
    public sempath.xsd.State[] getState()
    {
        int size = _stateList.size();
        sempath.xsd.State[] mArray = new sempath.xsd.State[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (sempath.xsd.State) _stateList.elementAt(index);
        }
        return mArray;
    } //-- sempath.xsd.State[] getState() 

    /**
     * Method getStateCount
     * 
     * 
     * 
     * @return int
     */
    public int getStateCount()
    {
        return _stateList.size();
    } //-- int getStateCount() 

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
     * Method removeAllPatient
     * 
     */
    public void removeAllPatient()
    {
        _patientList.removeAllElements();
    } //-- void removeAllPatient() 

    /**
     * Method removeAllState
     * 
     */
    public void removeAllState()
    {
        _stateList.removeAllElements();
    } //-- void removeAllState() 

    /**
     * Method removePatient
     * 
     * 
     * 
     * @param index
     * @return Patient
     */
    public sempath.xsd.Patient removePatient(int index)
    {
        java.lang.Object obj = _patientList.elementAt(index);
        _patientList.removeElementAt(index);
        return (sempath.xsd.Patient) obj;
    } //-- sempath.xsd.Patient removePatient(int) 

    /**
     * Method removeState
     * 
     * 
     * 
     * @param index
     * @return State
     */
    public sempath.xsd.State removeState(int index)
    {
        java.lang.Object obj = _stateList.elementAt(index);
        _stateList.removeElementAt(index);
        return (sempath.xsd.State) obj;
    } //-- sempath.xsd.State removeState(int) 

    /**
     * Method setPatient
     * 
     * 
     * 
     * @param index
     * @param vPatient
     */
    public void setPatient(int index, sempath.xsd.Patient vPatient)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _patientList.size())) {
            throw new IndexOutOfBoundsException("setPatient: Index value '"+index+"' not in range [0.." + (_patientList.size() - 1) + "]");
        }
        _patientList.setElementAt(vPatient, index);
    } //-- void setPatient(int, sempath.xsd.Patient) 

    /**
     * Method setPatient
     * 
     * 
     * 
     * @param patientArray
     */
    public void setPatient(sempath.xsd.Patient[] patientArray)
    {
        //-- copy array
        _patientList.removeAllElements();
        for (int i = 0; i < patientArray.length; i++) {
            _patientList.addElement(patientArray[i]);
        }
    } //-- void setPatient(sempath.xsd.Patient) 

    /**
     * Method setState
     * 
     * 
     * 
     * @param index
     * @param vState
     */
    public void setState(int index, sempath.xsd.State vState)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _stateList.size())) {
            throw new IndexOutOfBoundsException("setState: Index value '"+index+"' not in range [0.." + (_stateList.size() - 1) + "]");
        }
        _stateList.setElementAt(vState, index);
    } //-- void setState(int, sempath.xsd.State) 

    /**
     * Method setState
     * 
     * 
     * 
     * @param stateArray
     */
    public void setState(sempath.xsd.State[] stateArray)
    {
        //-- copy array
        _stateList.removeAllElements();
        for (int i = 0; i < stateArray.length; i++) {
            _stateList.addElement(stateArray[i]);
        }
    } //-- void setState(sempath.xsd.State) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return SemPathInfo
     */
    public static sempath.xsd.SemPathInfo unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (sempath.xsd.SemPathInfo) Unmarshaller.unmarshal(sempath.xsd.SemPathInfo.class, reader);
    } //-- sempath.xsd.SemPathInfo unmarshal(java.io.Reader) 

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
