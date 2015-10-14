package com.github.edgecaseberg

import java.io._

import javax.xml.XMLConstants
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory

import org.xml.sax.InputSource

import scala.xml.XML

object LoadXmlWithSchema {
	def apply(filePath: String, schemaResource: String) = {
		val sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
		val s = sf.newSchema(new StreamSource(getClass().getClassLoader().getResourceAsStream(schemaResource)))
		val in = new FileInputStream(new File(filePath));
		val is = new InputSource(new InputStreamReader(in));
		new SchemaAwareFactoryAdapter(s).loadXML(is)
	}
}