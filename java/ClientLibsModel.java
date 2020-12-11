package com.acc.aem64.core.models;
import java.util.ArrayList;
import java.util.List;

import com.adobe.cq.sightly.WCMUsePojo;
import com.adobe.granite.ui.clientlibs.ClientLibrary;
import com.adobe.granite.ui.clientlibs.HtmlLibraryManager;
import com.adobe.granite.ui.clientlibs.LibraryType;

/*
 * Sightly Code
 * 
<!-- CSS -->
<sly data-sly-use.jsObj="com.aem.community.core.components.ClientLibsModel" data-sly-list="${jsObj.cssFiles}">
    <link rel="stylesheet" href="${item}" type="text/css" async>
</sly>
<!-- JS -->
<sly data-sly-use.jsObj="com.aem.community.core.components.ClientLibsModel" data-sly-list="${jsObj.jsFiles}">
 	<script async type="text/javascript" src="${item}"></script>
</sly>
 * 
 */


public class ClientLibsModel extends WCMUsePojo {
	public List<String> jsFiles = new ArrayList<String>();
	public List<String> cssFiles = new ArrayList<String>();;

	@Override
	public void activate() throws Exception {

		HtmlLibraryManager clientlibmanager = getSlingScriptHelper().getService(HtmlLibraryManager.class);
		if (clientlibmanager != null) {
			String[] categoryArray = { "AEM64App.base" };
			java.util.Collection<ClientLibrary> libs = clientlibmanager.getLibraries(categoryArray, LibraryType.JS,
					false, false);
			for (ClientLibrary lib : libs) {
				String libPath = getClientLibPath(lib,LibraryType.JS);
				jsFiles.add(libPath);
			}
			
			libs = clientlibmanager.getLibraries(categoryArray, LibraryType.CSS,
					false, false);
			for (ClientLibrary lib : libs) {
				String libPath = getClientLibPath(lib,LibraryType.CSS);
				cssFiles.add(libPath);
			}
		}
	}

	private String getClientLibPath(ClientLibrary lib, LibraryType type) {
		String libPath = lib.getIncludePath(type);
		if(lib.allowProxy()) {
			libPath = libPath.replaceFirst("^/apps/", "/etc.clientlibs/");
		}
		return libPath;
	}
	
	public List<String> getJsFiles() {
		return jsFiles;
	}
	
	public List<String> getCSSFiles() {
		return cssFiles;
	}

}
