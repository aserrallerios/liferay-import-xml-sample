package com.cmt.importador.model.xml;

import java.util.ArrayList;
import java.util.List;

public class DynamicElement {

	private String dynamicContent;

	private String repeatable;

	private List<DynamicElement> dynamicElements;

	private String instanceId;

	private String name;

	private String type;

	private String indexType;

	public DynamicElement(DynamicElement element, String instanceId,
			String dynamicContent) {
		this.dynamicContent = element.dynamicContent;
		this.name = element.name;
		this.type = element.type;
		this.indexType = element.indexType;
		this.instanceId = instanceId;
		this.dynamicContent = dynamicContent;
		this.dynamicElements = new ArrayList<DynamicElement>();
	}

	public DynamicElement() {
	}

	public String getDynamicContent() {
		return dynamicContent;
	}

	public String getRepeatable() {
		return repeatable;
	}

	public void setRepeatable(String repeatable) {
		this.repeatable = repeatable;
	}

	public void setDynamicContent(String dynamicContent) {
		this.dynamicContent = dynamicContent;
	}

	public List<DynamicElement> getDynamicElements() {
		return dynamicElements;
	}

	public void setDynamicElements(List<DynamicElement> dynamicElements) {
		this.dynamicElements = dynamicElements;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public void addDynamicElement(DynamicElement dynamicElement) {
		this.dynamicElements.add(dynamicElement);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIndexType() {
		return indexType;
	}

	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}

}
