/**
 * 
 */
package com.iiht.wicket.components;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.model.util.ListModel;

import com.iiht.wicket.model.TelecomModel;
import com.iiht.wicket.utils.IConstants;

/**
 * @author Vignesh Durairaj
 *
 */
@SuppressWarnings("unchecked")
public class TelecomPalette extends Palette<TelecomModel> implements IConstants {

	private static final long serialVersionUID = -5444611644755839216L;

	public TelecomPalette(String id, ListModel<TelecomModel> inputListModel, ListModel<TelecomModel> choicesModel) {
		super(id, inputListModel, choicesModel, new TelecomChoiceRenderer(), 5, Boolean.FALSE);
	}

	public List<TelecomModel> getAllItems() {
		return (List<TelecomModel>) this.getChoices();
	}
	
	public List<TelecomModel> getSelectedItems() {
		return (List<TelecomModel>) this.getModelCollection();
	}
	
	private static class TelecomChoiceRenderer extends ChoiceRenderer<TelecomModel> {
		
		private static final long serialVersionUID = 3648694705487151033L;
		
		@Override
		public Object getDisplayValue(TelecomModel telecomModel) {
			String displayValue = (null != telecomModel ? telecomModel.getServiceId() + HYPHEN + telecomModel.getServiceName() : StringUtils.EMPTY);
			return displayValue;
		}
	}
}
