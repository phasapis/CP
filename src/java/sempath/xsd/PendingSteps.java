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
 * Class PendingSteps.
 * 
 * @version $Revision$ $Date$
 */
public class PendingSteps implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _stepList
     */
    private java.util.Vector _stepList;


      //----------------/
     //- Constructors -/
    //----------------/

    public PendingSteps() 
     {
        super();
        _stepList = new Vector();
    } //-- sempath.xsd.PendingSteps()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addStep
     * 
     * 
     * 
     * @param vStep
     */
    public void addStep(sempath.xsd.Step vStep)
        throws java.lang.IndexOutOfBoundsException
    {
        _stepList.addElement(vStep);
    } //-- void addStep(sempath.xsd.Step) 

    /**
     * Method addStep
     * 
     * 
     * 
     * @param index
     * @param vStep
     */
    public void addStep(int index, sempath.xsd.Step vStep)
        throws java.lang.IndexOutOfBoundsException
    {
        _stepList.insertElementAt(vStep, index);
    } //-- void addStep(int, sempath.xsd.Step) 

    /**
     * Method enumerateStep
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateStep()
    {
        return _stepList.elements();
    } //-- java.util.Enumeration enumerateStep() 

    /**
     * Method getStep
     * 
     * 
     * 
     * @param index
     * @return Step
     */
    public sempath.xsd.Step getStep(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _stepList.size())) {
            throw new IndexOutOfBoundsException("getStep: Index value '"+index+"' not in range [0.."+(_stepList.size() - 1) + "]");
        }
        
        return (sempath.xsd.Step) _stepList.elementAt(index);
    } //-- sempath.xsd.Step getStep(int) 

    /**
     * Method getStep
     * 
     * 
     * 
     * @return Step
     */
    public sempath.xsd.Step[] getStep()
    {
        int size = _stepList.size();
        sempath.xsd.Step[] mArray = new sempath.xsd.Step[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (sempath.xsd.Step) _stepList.elementAt(index);
        }
        return mArray;
    } //-- sempath.xsd.Step[] getStep() 

    /**
     * Method getStepCount
     * 
     * 
     * 
     * @return int
     */
    public int getStepCount()
    {
        return _stepList.size();
    } //-- int getStepCount() 

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
     * Method removeAllStep
     * 
     */
    public void removeAllStep()
    {
        _stepList.removeAllElements();
    } //-- void removeAllStep() 

    /**
     * Method removeStep
     * 
     * 
     * 
     * @param index
     * @return Step
     */
    public sempath.xsd.Step removeStep(int index)
    {
        java.lang.Object obj = _stepList.elementAt(index);
        _stepList.removeElementAt(index);
        return (sempath.xsd.Step) obj;
    } //-- sempath.xsd.Step removeStep(int) 

    /**
     * Method setStep
     * 
     * 
     * 
     * @param index
     * @param vStep
     */
    public void setStep(int index, sempath.xsd.Step vStep)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _stepList.size())) {
            throw new IndexOutOfBoundsException("setStep: Index value '"+index+"' not in range [0.." + (_stepList.size() - 1) + "]");
        }
        _stepList.setElementAt(vStep, index);
    } //-- void setStep(int, sempath.xsd.Step) 

    /**
     * Method setStep
     * 
     * 
     * 
     * @param stepArray
     */
    public void setStep(sempath.xsd.Step[] stepArray)
    {
        //-- copy array
        _stepList.removeAllElements();
        for (int i = 0; i < stepArray.length; i++) {
            _stepList.addElement(stepArray[i]);
        }
    } //-- void setStep(sempath.xsd.Step) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return PendingSteps
     */
    public static sempath.xsd.PendingSteps unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (sempath.xsd.PendingSteps) Unmarshaller.unmarshal(sempath.xsd.PendingSteps.class, reader);
    } //-- sempath.xsd.PendingSteps unmarshal(java.io.Reader) 

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
