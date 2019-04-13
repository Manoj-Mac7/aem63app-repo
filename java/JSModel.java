package com.aem.community.core.components;

import java.util.ArrayList;
import java.util.List;

import com.adobe.cq.sightly.WCMUsePojo;
import com.adobe.granite.ui.clientlibs.ClientLibrary;
import com.adobe.granite.ui.clientlibs.HtmlLibraryManager;
import com.adobe.granite.ui.clientlibs.LibraryType;

public class JSModel extends WCMUsePojo {
	public List<String> jsFiles = new ArrayList<String>();;

	@Override
	public void activate() throws Exception {

		HtmlLibraryManager clientlibmanager = getSlingScriptHelper().getService(HtmlLibraryManager.class);
		if (clientlibmanager != null) {
			String[] categoryArray = { "AEM63Lab.page.async" };
			java.util.Collection<ClientLibrary> libs = clientlibmanager.getLibraries(categoryArray, LibraryType.JS,
					false, false);
			for (ClientLibrary lib : libs) {
				jsFiles.add(lib.getIncludePath(LibraryType.JS));
			}
		}
	}
	
	public List<String> getJsFiles() {
		return jsFiles;
	}

}
