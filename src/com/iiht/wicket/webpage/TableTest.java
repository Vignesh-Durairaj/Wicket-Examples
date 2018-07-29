/**
 * 
 */
package com.iiht.wicket.webpage;

import org.apache.wicket.markup.html.WebPage;

import com.iiht.wicket.panels.DataTablePanel;
import com.iiht.wicket.panels.DataViewPanel;
import com.iiht.wicket.panels.ListViewPanel;
import com.iiht.wicket.panels.RepeaterViewPanel;

/**
 * @author Vignesh Durairaj
 *
 */
public class TableTest extends WebPage {

	public TableTest() {
		
		this.add(new ListViewPanel("listViewPanel"));
		this.add(new RepeaterViewPanel("repeaterViewPanel"));
		this.add(new DataTablePanel("dataTablePanel"));
		this.add(new DataViewPanel("dataViewPanel"));
	}
}
