/**
 * 
 */
package com.iiht.wicket.others;

import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;

import com.iiht.wicket.model.TelecomModel;
import com.iiht.wicket.model.TypeModel;

/**
 * @author Vignesh Durairaj
 *
 */
public class CustomSession extends WebSession {

	/** Generated Serial Version ID. */
	private static final long serialVersionUID = 5912555531003613557L;

	private TypeModel typeModel;
	
	private TelecomModel telecomModel;
	
	public CustomSession(Request request) {
		super(request);
	}
	
	public static CustomSession get() {
		return (CustomSession)Session.get();
	}

	/**
	 * @return the typeModel
	 */
	public TypeModel getTypeModel() {
		return typeModel;
	}

	/**
	 * @param typeModel the typeModel to set
	 */
	public void setTypeModel(TypeModel typeModel) {
		this.typeModel = typeModel;
	}

	/**
	 * @return the telecomModel
	 */
	public TelecomModel getTelecomModel() {
		return telecomModel;
	}

	/**
	 * @param telecomModel the telecomModel to set
	 */
	public void setTelecomModel(TelecomModel telecomModel) {
		this.telecomModel = telecomModel;
	}
	
}
