package com.cmt.importador.utils;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.cmt.importador.model.xml.DynamicElement;
import com.cmt.importador.model.xml.Root;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.StaxWriter;

public class XMLReaderWriter {

	private static XStream xstream;

	/**
	 * @param args
	 */
	public static XStream getReaderWriter() {
		if (xstream == null) {
			XStream xstream = new XStream(new CustomizedStaxDriver());

			xstream.alias("root", Root.class);
			xstream.alias("dynamic-element", DynamicElement.class);

			xstream.useAttributeFor(Root.class, "defaultLocale");
			xstream.useAttributeFor(Root.class, "availableLocales");

			xstream.aliasField("dynamic-element", Root.class, "dynamicElements");
			xstream.aliasField("default-locale", Root.class, "defaultLocale");
			xstream.aliasField("available-locales", Root.class,
					"availableLocales");

			xstream.addImplicitCollection(Root.class, "dynamicElements");

			xstream.useAttributeFor(DynamicElement.class, "indexType");
			xstream.useAttributeFor(DynamicElement.class, "instanceId");
			xstream.useAttributeFor(DynamicElement.class, "name");
			xstream.useAttributeFor(DynamicElement.class, "repeatable");
			xstream.useAttributeFor(DynamicElement.class, "type");

			xstream.aliasField("dynamic-content", DynamicElement.class,
					"dynamicContent");
			xstream.aliasField("index-type", DynamicElement.class, "indexType");
			xstream.aliasField("instance-id", DynamicElement.class,
					"instanceId");
			xstream.aliasField("dynamic-element", DynamicElement.class,
					"dynamicElements");

			xstream.addImplicitCollection(DynamicElement.class,
					"dynamicElements");

			XMLReaderWriter.xstream = xstream;
		}
		return XMLReaderWriter.xstream;
	}

	public static class CustomizedStaxDriver extends StaxDriver {

		/**
		 * The list that contains the field names that would contain a CDATA in
		 * the xml.
		 */
		private static final List CDATA_FIELDS;

		static {
			CDATA_FIELDS = new ArrayList();

			// add cdata field names.eg:cdataField
			CDATA_FIELDS.add("dynamic-content");
		}

		public CustomizedStaxDriver() {
			super();

		}

		@Override
		public StaxWriter createStaxWriter(XMLStreamWriter out,
				boolean writeStartEndDocument) throws XMLStreamException {
			final XMLStreamWriter writer = out;
			return new StaxWriter(getQnameMap(), out, writeStartEndDocument,
					isRepairingNamespace()) {
				boolean cdata = false;

				@Override
				public void startNode(String name) {
					super.startNode(name);
					cdata = CDATA_FIELDS.contains(name);
				}

				@Override
				public void setValue(String value) {
					if (cdata) {
						try {
							writer.writeCData(value);
						} catch (XMLStreamException e) {
							e.printStackTrace();
						}
					} else {
						super.setValue(value);
					}

				}

			};
		}

	}

}
