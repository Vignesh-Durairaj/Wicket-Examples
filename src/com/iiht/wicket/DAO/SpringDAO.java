/**
 * 
 */
package com.iiht.wicket.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.iiht.wicket.model.TelecomModel;
import com.iiht.wicket.model.TypeModel;

/**
 * @author Vignesh Durairaj
 *
 */

@Service
public class SpringDAO {

	public TypeModel getSampleType() {
		
		TypeModel sampleType = new TypeModel();
		
		sampleType.setTypeId(1);
		sampleType.setTypeName("Wireless");
		sampleType.setDescription("Provides wireless services.");
		
		return sampleType;
		
	}
	
	public TelecomModel getSampleService() {
		
		TelecomModel sampleService = new TelecomModel();
		
		sampleService.setServiceId(1);
		sampleService.setServiceName("Verizon");
		sampleService.setType(getSampleType());
		sampleService.setAddress("Phoenix, AZ");
		sampleService.setActiveStatus(Boolean.TRUE);
		sampleService.setCountry("USA");
		
		return sampleService;
	}
	
	public List<TypeModel> getTypeList() {
		
		List<TypeModel> typeList = new ArrayList<TypeModel>(0);
		TypeModel typeModel;
		
		typeList.add(getSampleType());
		
		typeModel = new TypeModel();
		typeModel.setTypeId(2);
		typeModel.setTypeName("Overhead");
		typeModel.setDescription("Connecting the users via old-school overhead line.");
		typeList.add(typeModel);
		
		typeModel = new TypeModel();
		typeModel.setTypeId(3);
		typeModel.setTypeName("Underground");
		typeModel.setDescription("Connecting the users through underground cables.");
		typeList.add(typeModel);
		
		typeModel = new TypeModel();
		typeModel.setTypeId(4);
		typeModel.setTypeName("Hybrid");
		typeModel.setDescription("Combines all the available ways to connect to the customers.");
		typeList.add(typeModel);
		
		return typeList;
	}
	
	public List<TelecomModel> getServiceProvidersList () {
		
		List<TelecomModel> serviceProvidersList = new ArrayList<TelecomModel>(0);
		TelecomModel serviceModel;
		
		serviceProvidersList.add(getSampleService());
		
		serviceModel = new TelecomModel();
		serviceModel.setServiceId(2);
		serviceModel.setServiceName("British Telecom");
		serviceModel.setType(getTypeFromList(4));
		serviceModel.setActiveStatus(Boolean.TRUE);
		serviceModel.setAddress("Hertford, London");
		serviceModel.setCountry("UK");
		serviceProvidersList.add(serviceModel);
		
		serviceModel = new TelecomModel();
		serviceModel.setServiceId(3);
		serviceModel.setServiceName("AT & T");
		serviceModel.setType(getTypeFromList(2));
		serviceModel.setActiveStatus(Boolean.TRUE);
		serviceModel.setAddress("San Jose, California");
		serviceModel.setCountry("USA");
		serviceProvidersList.add(serviceModel);
		
		serviceModel = new TelecomModel();
		serviceModel.setServiceId(4);
		serviceModel.setServiceName("Hutch");
		serviceModel.setType(getTypeFromList(3));
		serviceModel.setActiveStatus(Boolean.FALSE);
		serviceModel.setAddress("Marylbore, Brisbane");
		serviceModel.setCountry("Australia");
		serviceProvidersList.add(serviceModel);
		
		serviceModel = new TelecomModel();
		serviceModel.setServiceId(5);
		serviceModel.setServiceName("BSNL India");
		serviceModel.setType(getTypeFromList(3));
		serviceModel.setActiveStatus(Boolean.TRUE);
		serviceModel.setAddress("Cannought Circus, New Delhi");
		serviceModel.setCountry("India");
		serviceProvidersList.add(serviceModel);
		
		serviceModel = new TelecomModel();
		serviceModel.setServiceId(6);
		serviceModel.setServiceName("BSNL Cellone");
		serviceModel.setType(getTypeFromList(1));
		serviceModel.setActiveStatus(Boolean.TRUE);
		serviceModel.setAddress("Andheri West, Mumbai");
		serviceModel.setCountry("India");
		serviceProvidersList.add(serviceModel);
		
		return serviceProvidersList;
	}
	
	public TypeModel getTypeFromList (final Integer typeId) {
		
		TypeModel typeModel = null;
		
		List<TypeModel> typeModelList = getTypeList();
		
		if (null != typeModelList && !typeModelList.isEmpty())
			for (TypeModel type : typeModelList)
				if (null != typeId && typeId.equals(type.getTypeId()))
					typeModel = type;
					
		return typeModel;
	}
}
