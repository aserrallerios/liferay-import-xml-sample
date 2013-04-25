package com.cmt.importador.model.xml;

import java.util.ArrayList;
import java.util.List;

public class Root {

	private List<DynamicElement> dynamicElements;

	private String availableLocales;

	public Root(String availableLocales, String defaultLocale) {
		super();
		this.availableLocales = availableLocales;
		this.defaultLocale = defaultLocale;
		this.dynamicElements = new ArrayList<DynamicElement>();
	}

	public Root() {
	}

	private String defaultLocale;

	public List<DynamicElement> getDynamicElements() {
		return dynamicElements;
	}

	public void addDynamicElement(DynamicElement dynamicElement) {
		this.dynamicElements.add(dynamicElement);
	}

	public void setDynamicElements(List<DynamicElement> dynamicElements) {
		this.dynamicElements = dynamicElements;
	}

	public String getAvailableLocales() {
		return availableLocales;
	}

	public void setAvailableLocales(String availableLocales) {
		this.availableLocales = availableLocales;
	}

	public String getDefaultLocale() {
		return defaultLocale;
	}

	public void setDefaultLocale(String defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

}
