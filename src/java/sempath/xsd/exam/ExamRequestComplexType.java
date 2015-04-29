/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id$
 */

package sempath.xsd.exam;

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
 * Class ExamRequestComplexType.
 * 
 * @version $Revision$ $Date$
 */
public class ExamRequestComplexType implements java.io.Serializable {


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
     * Field _exam
     */
    private java.lang.String _exam;

    /**
     * Field _stateList
     */
    private java.util.Vector _stateList;


      //----------------/
     //- Constructors -/
    //----------------/

    public ExamRequestComplexType() 
     {
        super();
        _stateList = new Vector();
    } //-- sempath.xsd.exam.ExamRequestComplexType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addState
     * 
     * 
     * 
     * @param vState
     */
    public void addState(sempath.xsd.exam.State vState)
        throws java.lang.IndexOutOfBoundsException
    {
        _stateList.addElement(vState);
    } //-- void addState(sempath.xsd.exam.State) 

    /**
     * Method addState
     * 
     * 
     * 
     * @param index
     * @param vState
     */
    public void addState(int index, sempath.xsd.exam.State vState)
        throws java.lang.IndexOutOfBoundsException
    {
        _stateList.insertElementAt(vState, index);
    } //-- void addState(int, sempath.xsd.exam.State) 

    /**
     * Method deleteId
     * 
     */
    public void deleteId()
    {
        this._has_id= false;
    } //-- void deleteId() 

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
     * Returns the value of field 'exam'.
     * 
     * @return String
     * @return the value of field 'exam'.
     */
    public java.lang.String getExam()
    {
        return this._exam;
    } //-- java.lang.String getExam() 

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
     * Method getState
     * 
     * 
     * 
     * @param index
     * @return State
     */
    public sempath.xsd.exam.State getState(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _stateList.size())) {
            throw new IndexOutOfBoundsException("getState: Index value '"+index+"' not in range [0.."+(_stateList.size() - 1) + "]");
        }
        
        return (sempath.xsd.exam.State) _stateList.elementAt(index);
    } //-- sempath.xsd.exam.State getState(int) 

    /**
     * Method getState
     * 
     * 
     * 
     * @return State
     */
    public sempath.xsd.exam.State[] getState()
    {
        int size = _stateList.size();
        sempath.xsd.exam.State[] mArray = new sempath.xsd.exam.State[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (sempath.xsd.exam.State) _stateList.elementAt(index);
        }
        return mArray;
    } //-- sempath.xsd.exam.State[] getState() 

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
     * Method removeAllState
     * 
     */
    public void removeAllState()
    {
        _stateList.removeAllElements();
    } //-- void removeAllState() 

    /**
     * Method removeState
     * 
     * 
     * 
     * @param index
     * @return State
     */
    public sempath.xsd.exam.State removeState(int index)
    {
        java.lang.Object obj = _stateList.elementAt(index);
        _stateList.removeElementAt(index);
        return (sempath.xsd.exam.State) obj;
    } //-- sempath.xsd.exam.State removeState(int) 

    /**
     * Sets the value of field 'exam'.
     * 
     * @param exam the value of field 'exam'.
     */
    public void setExam(java.lang.String exam)
    {
        this._exam = exam;
    } //-- void setExam(java.lang.String) 

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
     * Method setState
     * 
     * 
     * 
     * @param index
     * @param vState
     */
    public void setState(int index, sempath.xsd.exam.State vState)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _stateList.size())) {
            throw new IndexOutOfBoundsException("setState: Index value '"+index+"' not in range [0.." + (_stateList.size() - 1) + "]");
        }
        _stateList.setElementAt(vState, index);
    } //-- void setState(int, sempath.xsd.exam.State) 

    /**
     * Method setState
     * 
     * 
     * 
     * @param stateArray
     */
    public void setState(sempath.xsd.exam.State[] stateArray)
    {
        //-- copy array
        _stateList.removeAllElements();
        for (int i = 0; i < stateArray.length; i++) {
            _stateList.addElement(stateArray[i]);
        }
    } //-- void setState(sempath.xsd.exam.State) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return ExamRequestComplexType
     */
    public static sempath.xsd.exam.ExamRequestComplexType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (sempath.xsd.exam.ExamRequestComplexType) Unmarshaller.unmarshal(sempath.xsd.exam.ExamRequestComplexType.class, reader);
    } //-- sempath.xsd.exam.ExamRequestComplexType unmarshal(java.io.Reader) 

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
