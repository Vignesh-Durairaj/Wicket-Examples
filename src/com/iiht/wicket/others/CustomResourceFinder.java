package com.iiht.wicket.others;

import java.util.Locale;

import org.apache.wicket.util.file.IResourceFinder;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.locator.ResourceStreamLocator;
import org.apache.wicket.util.string.Strings;

public class CustomResourceFinder extends ResourceStreamLocator {
	
	private IResourceFinder resourceFinder;
	
	public void addFinder (final IResourceFinder resourceFinder) {
		
		if (null != resourceFinder)
			this.resourceFinder = resourceFinder;
	}
	
	@Override
	public IResourceStream locate(Class<?> clazz, String path, String style,
			Locale locale, String extension) {
		
		String myFileName = Strings.lastPathComponent(clazz.getName(), '.') + 
			"." + extension;
		
		IResourceStream stream = locate(clazz, myFileName);
		
		if (null != stream)
			return stream;
		else
			return super.locate(clazz, path, style, locale, extension);
	}
	
	@Override
	protected IResourceStream locateByResourceFinder(Class<?> clazz, String path) {
		
		IResourceStream resourceStream = resourceFinder.find(clazz, path);
		
		if (null == resourceStream)
			resourceStream = locateByClassLoader(clazz, path);
		
		return resourceStream;
	}

}
