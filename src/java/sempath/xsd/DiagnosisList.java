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
 * Class DiagnosisList.
 * 
 * @version $Revision$ $Date$
 */
public class DiagnosisList implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _diagnosisList
     */
    private java.util.Vector _diagnosisList;


      //----------------/
     //- Constructors -/
    //----------------/

    public DiagnosisList() 
     {
        super();
        _diagnosisList = new Vector();
    } //-- sempath.xsd.DiagnosisList()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addDiagnosis
     * 
     * 
     * 
     * @param vDiagnosis
     */
    public void addDiagnosis(sempath.xsd.Diagnosis vDiagnosis)
        throws java.lang.IndexOutOfBoundsException
    {
        _diagnosisList.addElement(vDiagnosis);
    } //-- void addDiagnosis(sempath.xsd.Diagnosis) 

    /**
     * Method addDiagnosis
     * 
     * 
     * 
     * @param index
     * @param vDiagnosis
     */
    public void addDiagnosis(int index, sempath.xsd.Diagnosis vDiagnosis)
        throws java.lang.IndexOutOfBoundsException
    {
        _diagnosisList.insertElementAt(vDiagnosis, index);
    } //-- void addDiagnosis(int, sempath.xsd.Diagnosis) 

    /**
     * Method enumerateDiagnosis
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateDiagnosis()
    {
        return _diagnosisList.elements();
    } //-- java.util.Enumeration enumerateDiagnosis() 

    /**
     * Method getDiagnosis
     * 
     * 
     * 
     * @param index
     * @return Diagnosis
     */
    public sempath.xsd.Diagnosis getDiagnosis(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _diagnosisList.size())) {
            throw new IndexOutOfBoundsException("getDiagnosis: Index value '"+index+"' not in range [0.."+(_diagnosisList.size() - 1) + "]");
        }
        
        return (sempath.xsd.Diagnosis) _diagnosisList.elementAt(index);
    } //-- sempath.xsd.Diagnosis getDiagnosis(int) 

    /**
     * Method getDiagnosis
     * 
     * 
     * 
     * @return Diagnosis
     */
    public sempath.xsd.Diagnosis[] getDiagnosis()
    {
        int size = _diagnosisList.size();
        sempath.xsd.Diagnosis[] mArray = new sempath.xsd.Diagnosis[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (sempath.xsd.Diagnosis) _diagnosisList.elementAt(index);
        }
        return mArray;
    } //-- sempath.xsd.Diagnosis[] getDiagnosis() 

    /**
     * Method getDiagnosisCount
     * 
     * 
     * 
     * @return int
     */
    public int getDiagnosisCount()
    {
        return _diagnosisList.size();
    } //-- int getDiagnosisCount() 

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
     * Method removeAllDiagnosis
     * 
     */
    public void removeAllDiagnosis()
    {
        _diagnosisList.removeAllElements();
    } //-- void removeAllDiagnosis() 

    /**
     * Method removeDiagnosis
     * 
     * 
     * 
     * @param index
     * @return Diagnosis
     */
    public sempath.xsd.Diagnosis removeDiagnosis(int index)
    {
        java.lang.Object obj = _diagnosisList.elementAt(index);
        _diagnosisList.removeElementAt(index);
        return (sempath.xsd.Diagnosis) obj;
    } //-- sempath.xsd.Diagnosis removeDiagnosis(int) 

    /**
     * Method setDiagnosis
     * 
     * 
     * 
     * @param index
     * @param vDiagnosis
     */
    public void setDiagnosis(int index, sempath.xsd.Diagnosis vDiagnosis)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _diagnosisList.size())) {
            throw new IndexOutOfBoundsException("setDiagnosis: Index value '"+index+"' not in range [0.." + (_diagnosisList.size() - 1) + "]");
        }
        _diagnosisList.setElementAt(vDiagnosis, index);
    } //-- void setDiagnosis(int, sempath.xsd.Diagnosis) 

    /**
     * Method setDiagnosis
     * 
     * 
     * 
     * @param diagnosisArray
     */
    public void setDiagnosis(sempath.xsd.Diagnosis[] diagnosisArray)
    {
        //-- copy array
        _diagnosisList.removeAllElements();
        for (int i = 0; i < diagnosisArray.length; i++) {
            _diagnosisList.addElement(diagnosisArray[i]);
        }
    } //-- void setDiagnosis(sempath.xsd.Diagnosis) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return DiagnosisList
     */
    public static sempath.xsd.DiagnosisList unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (sempath.xsd.DiagnosisList) Unmarshaller.unmarshal(sempath.xsd.DiagnosisList.class, reader);
    } //-- sempath.xsd.DiagnosisList unmarshal(java.io.Reader) 

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
