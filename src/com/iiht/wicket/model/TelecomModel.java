/**
 * 
 */
package com.iiht.wicket.model;

import java.io.Serializable;

/**
 * @author Vignesh Durairaj
 *
 */
public class TelecomModel implements Serializable {

	/** Generated Serial Version ID. */
	private static final long serialVersionUID = -8632980614927385072L;

	private Integer serviceId;
	
	private String serviceName;
	
	private String address;
	
	private String country;

	private TypeModel type;
	
	private Boolean activeStatus;

	/**
	 * @return the serviceId
	 */
	public Integer getServiceId() {
		return serviceId;
	}

	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the type
	 */
	public TypeModel getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(TypeModel type) {
		this.type = type;
	}

	/**
	 * @return the activeStatus
	 */
	public Boolean getActiveStatus() {
		return activeStatus;
	}

	/**
	 * @param activeStatus the activeStatus to set
	 */
	public void setActiveStatus(Boolean activeStatus) {
		this.activeStatus = activeStatus;
	}	
	
	@Override
	public String toString() {
		return (this.getServiceId() + "." + this.getServiceName() 
				+ "(" + this.getType().getTypeName() + "), " + this.getAddress() 
				+ " - " + this.getCountry());
	}
	
}
