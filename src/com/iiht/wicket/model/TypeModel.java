package com.iiht.wicket.model;

import java.io.Serializable;

public class TypeModel implements Serializable {

	/** Generated Serial Version ID. */
	private static final long serialVersionUID = 4030663554356626323L;

	private Integer typeId;
	
	private String typeName;
	
	private String description;

	public TypeModel() {
		// Can be implemented, if needed in future.
	}
	
	public TypeModel(Integer typeId, String typeName, String description) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
		this.description = description;
	}

	/**
	 * @return the typeId
	 */
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}
