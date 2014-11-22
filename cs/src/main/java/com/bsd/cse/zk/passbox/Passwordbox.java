/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.passbox;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.sys.JsContentRenderer;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.api.Textbox;

/**
 *
 * @author bento
 */
public class Passwordbox extends Intbox {
    private static Log  LOG = LogFactory.getLog(Passwordbox.class);
    private static final long serialVersionUID = 2167520626382731542L;
    private AuxInfo _auxinf;


    public Passwordbox() {
        super();
        LOG.info(">>>>>>>>>>>>>>>.  Passwordbox  super");
        setType("password");
    }
    public Passwordbox(int value) throws WrongValueException {
            this();
            setValue(new Integer(value));
            LOG.info(">>>>>>>>>>>>>>>.  Passwordbox");
    }

    /** Returns the value (in Integer), might be null unless
     * a constraint stops it.
     * @exception WrongValueException if user entered a wrong value
     */
    @Override
    public Integer getValue() throws WrongValueException {
            LOG.info(">>>>>>>>>>>>>>>.  getValue");
            return (Integer)getTargetValue();
    }
    /** Returns the value in int. If null, zero is returned.
     */
    @Override
    public int intValue() throws WrongValueException {
            LOG.info(">>>>>>>>>>>>>>>.  intValue");
            final Object val = getTargetValue();
            return val != null ? ((Integer)val).intValue(): 0;
    }
    /** Sets the value (in Integer).
     * @exception WrongValueException if value is wrong
     */
    @Override
    public void setValue(Integer value) throws WrongValueException {
            LOG.info(">>>>>>>>>>>>>>>.  setValue");
            validate(value);
            setRawValue(value);
    }
    
    @Override
    protected Object coerceFromString(String value) throws WrongValueException {
        LOG.info(">>>>>>>>>>>>>>>>>>> coerceFromString");
        return super.coerceFromString(value);
    }

    @Override
    protected String coerceToString(Object value) {
        String val = super.coerceToString(value);
        LOG.info(">>>>>>>>>>>>>>>>>>>>> coerceToString = "+val);


        return val;
    }


    @Override
    protected String formatNumber(Object value, String defaultFormat) {
        String format = super.formatNumber(value, defaultFormat);
        LOG.info(">>>>>>>>>>>>>>>>>>>>> formatNumber = "+ format);
        LOG.info(">>>>>>>>>>>>>>>>>>>>> formatNumber = "+ format);
        return format;
    }
	
    @Override
    protected Object[] toNumberOnly(String val) {
        LOG.info(">>>>>>>>>>>>>>>>>>>>> toNumberOnly");
            return super.toNumberOnly(val);
    }

    @Override
    public void setFormat(String format) throws WrongValueException {
        LOG.info(">>>>>>>>>>>>>>>>>>>>> setFormat");
        super.setFormat(format);
    }
    @Override
    protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)
    throws java.io.IOException {
        LOG.info(">>>>>>>>>>>>>>>>>>>>> renderProperties = "+renderer);
        LOG.info(">>>>>>>>>>>>>>>>>>>>> ddsdsdf = "+((JsContentRenderer)renderer).getBuffer().toString());
        super.renderProperties(renderer);
        LOG.info(">>>>>>>>>>>>>>>>>>>>> ddsdsdf = "+((JsContentRenderer)renderer).getBuffer().toString());
    }

    @Override
    protected void validate(Object value) throws WrongValueException {
        LOG.info(">>>>>>>>>>>>>>>>>>>>> validate");
        super.validate(value);
    }

    @Override
    protected WrongValueException showCustomError(WrongValueException ex) {
        LOG.info(">>>>>>>>>>>>>>>>>>>>> WrongValueException");
        return super.showCustomError(ex);
    }

    @Override
    public void setMaxlength(int maxlength) {
        LOG.info(">>>>>>>>>>>>>>>>>>>>> setMaxlength");
        super.setMaxlength(maxlength);
    }

    @Override
    public void setCols(int cols) throws WrongValueException {
        LOG.info(">>>>>>>>>>>>>>>>>>>>> setCols");
           super.setCols(cols);
    }

    public int getTabindex() {
        int ind = super.getTabindex();
        LOG.info(">>>>>>>>>>>>>>>>>>>>> getTabindex = "+ind);
        return ind;
    }
    /** Sets the tab order of this component.
     */
    public void setTabindex(int tabindex) throws WrongValueException {
        LOG.info(">>>>>>>>>>>>>>>>>>>>> setTabindex");
        super.setTabindex(tabindex);
    }
 

    public void select() {
        LOG.info(">>>>>>>>>>>>>>>>>>>>> select");
            super.select();
    }

    public void setConstraint(String constr) {
        LOG.info(">>>>>>>>>>>>>>>>>>>. setConstraint");
		super.setConstraint(constr);
	}
	public void setConstraint(Constraint constr) {
		LOG.info(">>>>>>>>>>>>>>>>>>>. setConstraint");
                super.setConstraint(constr);
	}
	public Constraint getConstraint() {
            LOG.info(">>>>>>>>>>>>>>>>>>>. getConstraint");
		return super.getConstraint();
	}

	/** Returns the value in the targeting type.
	 * It is used by the deriving class to implement the getValue method.
	 * For example, {@link org.zkoss.zul.Intbox#getValue} is the same
	 * as this method except with a different signature.
	 *
	 * <p>It invokes {@link #checkUserError} to ensure no user error.
	 * @exception WrongValueException if the user entered a wrong value
	 * @see #getText
	 */
	protected Object getTargetValue() throws WrongValueException {
            LOG.info(">>>>>>>>>>>>>>>>>>>. getTargetValue");
		checkUserError();
		return _value;
	}

	/** Returns the raw value directly with checking whether any
	 * error message not yet fixed. In other words, it does NOT invoke
	 * {@link #checkUserError}.
	 *
	 * <p>Note: if the user entered an incorrect value (i.e., caused
	 * {@link WrongValueException}), the incorrect value doesn't
	 * be stored so this method returned the last correct value.
	 *
	 * @see #getRawText
	 * @see #getText
	 * @see #setRawValue
	 */
	public Object getRawValue() {
            LOG.info(">>>>>>>>>>>>>>>>>>>. getRawValue");
		return _value;
	}
	/** Returns the text directly without checking whether any error
	 * message not yet fixed. In other words, it does NOT invoke
	 * {@link #checkUserError}.
	 *
	 * <p>Note: if the user entered an incorrect value (i.e., caused
	 * {@link WrongValueException}), the incorrect value doesn't
	 * be stored so this method returned the last correct value.
	 *
	 * @see #getRawValue
	 * @see #getText
	 */
	public String getRawText() {
            LOG.info(">>>>>>>>>>>>>>>>>>>. getRawText");
		return coerceToString(_value);
	}
	/** Sets the raw value directly. The caller must make sure the value
	 * is correct (or intend to be incorrect), because this method
	 * doesn't do any validation.
	 *
	 * <p>If you feel confusing with setValue, such as {@link org.zkoss.zul.Textbox#setValue},
	 * it is usually better to use setValue instead. This method
	 * is reserved for developer that really want to set an 'illegal'
	 * value (such as an empty string to a textbox with no-empty contraint).
	 *
	 * <p>Note: since 3.0.1, the value will be re-validate again if
	 * {@link #getText} or others (such as {@link org.zkoss.zul.Intbox#getValue})
	 * is called. In other words, it is assumed that the specified value
	 * is not validated yet -- the same state when this component is
	 * created. If you want to avoid the re-valiation, you have to invoke
	 * {@link #clearErrorMessage()}.
	 *
	 * <p>Like setValue, the result is returned back to the server
	 * by calling {@link #getText}.
	 *
	 * @see #getRawValue
	 */
	public void setRawValue(Object value) {
            LOG.info(">>>>>>>>>>>>>>>>>>>. setRawValue");
		super.setRawValue(value);
	}
	/** Sets the value directly.
	 * Note: Unlike {@link #setRawValue} (nor setValue), this method
	 * assigns the value directly without clearing error message or
	 * synchronizing with the client.
	 *
	 * <p>It is usually used only the constructor.
	 * Though it is also OK to use {@link #setRawValue} in the constructor,
	 * this method has better performance.
	 * @since 3.0.3
	 */
	protected void setValueDirectly(Object value) {
		_value = value;
	}

	/** Returns the current content of this input is correct.
	 * If the content is not correct, next call to the getvalue method will
	 * throws WrongValueException.
	 */
    @Override
	public boolean isValid() {
                LOG.info(">>>>>>>>>>>>>>>>>>>. isValid");
		return super.isValid();
	}

	/**
	 * Sets the text of this InputElement to the specified text which is
	 * begining with the new start point and ending with the new end point.
	 *
	 * @param start the start position of the text (included)
	 * @param end the end position of the text (excluded)
	 * @param newtxt the new text to be set.
	 * @param isHighLight
	 *            Sets whether it will represent highlihgt style or cursor
	 *            style.If the start point same with the end point always
	 *            represent cursor style.
	 */
    @Override
	public void setSelectedText(int start, int end, String newtxt,
			boolean isHighLight) {
            LOG.info(">>>>>>>>>>>>>>>>>>>. setSelectedText");
		super.setSelectedText(start, end, newtxt, isHighLight);
	}

	/**
	 * Sets the selection end to the specified position and the selection start
	 * to the specified position. The new end point is constrained to be at or
	 * after the current selection start. If the new start point is different
	 * with the new end point, then will represent the result of highlight in
	 * this text.
	 *
	 * <p>Set both arguments to the same value to move the cursor to
	 * the corresponding position without selecting text.
	 *
	 * @param start the start position of the text (included)
	 * @param end the end position of the text (excluded)
	 */
	public void setSelectionRange(int start, int end) {
            LOG.info(">>>>>>>>>>>>>>>>>>>. setSelectionRange");
		super.setSelectionRange(start, end);
                
	}

	/** Checks whether user entered a wrong value (and not correct it yet).
	 * Since user might enter a wrong value and moves on to other components,
	 * this methid is called when {@link #getText} or {@link #getTargetValue} is
	 * called.
	 *
	 * <p>Derives rarely need to access this method if they use only
	 * {@link #getText} and {@link #getTargetValue}.
	 */
    @Override
	protected void checkUserError() throws WrongValueException {
            LOG.info(">>>>>>>>>>>>>>>>>>>. checkUserError");
		super.checkUserError();
	}

	//-- Component --//
	/** Not childable. */
    @Override
	protected boolean isChildable() {
        LOG.info(">>>>>>>>>>>>>>>>>>>. isChildable");
		return false;
	}

	//-- ComponentCtrl --//
    @Override
	public WrongValueException onWrongValue(WrongValueException ex) {
            LOG.info(">>>>>>>>>>>>>>>>>>>. onWrongValue");
		return super.onWrongValue(ex);
	}
	/** Marshall value to be sent to the client if needed.
	 *
	 * <p>Overrides it if the value to be sent to the client is not JSON Compatible.
	 * @param value the value to be sent to the client
	 * @return the marshalled value
	 * @since 5.0.5
	 */
    @Override
	protected Object marshall(Object value) {
            LOG.info(">>>>>>>>>>>>>>>>>>>. marshall = "+value);
		return value;
	}
	/** Unmarshall value returned from client if needed.
	 *
	 * <p>Overrides it if the value returned is not JSON Compatible.
	 * @param value the value returned from client
	 * @return the unmarshalled value
	 * @since 5.0.5
	 */
    @Override
	protected Object unmarshall(Object value) {
            LOG.info(">>>>>>>>>>>>>>>>>>>. unmarshall");
            return super.unmarshall(value);
	}	
	/** Processes an AU request.
	 *
	 * <p>Default: in addition to what are handled by {@link XulElement#service},
	 * it also handles onChange, onChanging and onError.
	 * @since 5.0.0
	 */
    @Override
	public void service(org.zkoss.zk.au.AuRequest request, boolean everError) {
            LOG.info(">>>>>>>>>>>>>>>>>>>. service");
		super.service(request, everError);
	}

    

	//Cloneable//
    @Override
    public Object clone() {
        LOG.info(">>>>>>>>>>>>>>>>>>>. clone");
            return super.clone();
    }

    public void setType(String type) throws WrongValueException {
        LOG.info(">>>>>>>>>>>>>>>>>>>. type = "+type);
		if (!TEXT.equals(type) && !"password".equals(type))
			throw new WrongValueException("Illegal type: "+type);

		if (!type.equals(_auxinf != null ? _auxinf.type: TEXT)) {
			initAuxInfo().type = type;
			smartUpdate("type", getType());
		}
	}

    @Override
    public String getType() {
            return _auxinf != null ? _auxinf.type: TEXT;
    }

    private static final String TEXT = "text";
	private AuxInfo initAuxInfo() {
		if (_auxinf == null)
			_auxinf = new AuxInfo();
		return _auxinf;
	}
	private static class AuxInfo implements java.io.Serializable, Cloneable  {
		private String type = TEXT;
		private int rows = 1;
		private boolean multiline;
		private boolean tabbable;

		public Object clone() {
			try {
				return super.clone();
			} catch (CloneNotSupportedException e) {
				throw new InternalError();
			}
		}
	}
}
