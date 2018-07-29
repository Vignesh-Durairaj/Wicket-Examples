/**
 * 
 */
package com.iiht.wicket.panels;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.iiht.wicket.DAO.ServiceDAO;
import com.iiht.wicket.model.TelecomModel;

/**
 * @author Vignesh Durairaj
 *
 */
public class DataTablePanel extends Panel {

	/** Generated Serial Version ID. */
	private static final long serialVersionUID = -1241294949386156235L;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataTablePanel(final String id) {
		super(id);
		
		IColumn[] serviceColumns = {
				new PropertyColumn (new Model<String>("ID"), "id", "serviceId"), 
				new PropertyColumn (new Model<String>("Name"), "name", "serviceName"),
				new PropertyColumn (new Model<String>("Type"), "type", "type.typeName"), 
				new PropertyColumn (new Model<String>("Address"), "address"),
				new PropertyColumn (new Model<String>("Country"), "country", "country")
				
		};
		
		DefaultDataTable<TelecomModel> serviceDataTable = new DefaultDataTable<TelecomModel>("serviceDataTable", 
				serviceColumns, new ServiceDataProvider(ServiceDAO.getServiceProvidersList())  , 3);
		
		this.add(serviceDataTable);
	}
	
	private class ServiceDataProvider extends SortableDataProvider<TelecomModel> {

		/** Generated serial Version ID. */
		private static final long serialVersionUID = -6542741326085853492L;

		private List<TelecomModel> serviceList;
		
		public ServiceDataProvider(final List<TelecomModel> serviceList) {
			super();
			this.serviceList = serviceList;
		}

		public Iterator<? extends TelecomModel> iterator(int first, int count) {
			return getSortedIterator(first, count, getSort());
		}

		public int size() {
			return serviceList.size();
		}

		public IModel<TelecomModel> model(TelecomModel object) {
			return new Model<TelecomModel> (object);
		}
		
		private Iterator<TelecomModel> getSortedIterator (final int first, final int count, 
				final SortParam sortParam) {
			
			if (null != serviceList && !serviceList.isEmpty() && null != sortParam)
				if ("id".equalsIgnoreCase(sortParam.getProperty()))
					Collections.sort(serviceList, new Comparator<TelecomModel>() {

						public int compare(TelecomModel o1, TelecomModel o2) {
							int result = o1.getServiceId().compareTo(o2.getServiceId());
							return (sortParam.isAscending() ? result : -result);
						}
						
					});
				else if ("name".equalsIgnoreCase(sortParam.getProperty()))
					Collections.sort(serviceList, new Comparator<TelecomModel>() {
						
						public int compare(TelecomModel o1, TelecomModel o2) {
							int result = o1.getServiceName().compareTo(o2.getServiceName());
							return (sortParam.isAscending() ? result : -result);
						}
					});
				else if ("type".equalsIgnoreCase(sortParam.getProperty()))
					Collections.sort(serviceList, new Comparator<TelecomModel>() {
						
						public int compare(TelecomModel o1, TelecomModel o2) {
							int result = o1.getType().getTypeName().compareTo(o2.getType().getTypeName());
							return (sortParam.isAscending() ? result : -result);
						}
					});
				else if ("country".equalsIgnoreCase(sortParam.getProperty()))
					Collections.sort(serviceList, new Comparator<TelecomModel>() {
						
						public int compare(TelecomModel o1, TelecomModel o2) {
							int result = o1.getCountry().compareTo(o2.getCountry());
							return (sortParam.isAscending() ? result : -result);
						}
					});
				
			return serviceList.subList(first, first + count).iterator();
		}
	}
	
	
}
