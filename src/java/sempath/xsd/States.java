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
 * Class States.
 * 
 * @version $Revision$ $Date$
 */
public class States implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _stateList
     */
    private java.util.Vector _stateList;


      //----------------/
     //- Constructors -/
    //----------------/

    public States() 
     {
        super();
        _stateList = new Vector();
    } //-- sempath.xsd.States()


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
    public sempath.xsd.State removeState(int index)
    {
        java.lang.Object obj = _stateList.elementAt(index);
        _stateList.removeElementAt(index);
        return (sempath.xsd.State) obj;
    } //-- sempath.xsd.State removeState(int) 

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
     * @return States
     */
    public static sempath.xsd.States unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (sempath.xsd.States) Unmarshaller.unmarshal(sempath.xsd.States.class, reader);
    } //-- sempath.xsd.States unmarshal(java.io.Reader) 

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
