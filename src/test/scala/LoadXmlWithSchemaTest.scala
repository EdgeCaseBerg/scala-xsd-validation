package com.github.edgecaseberg

import org.scalatest._

import scala.util.{Try,Success,Failure}

class LoadXmlWithSchemaTest extends FlatSpec with Matchers {
	
	"The loader" should "be able to load a valid xml file" in {		
		Try(LoadXmlWithSchema("src/test/resources/sample.xml","sample.xsd")) match {
			case Success(xml) => 
			case Failure(e) => fail(e)
		}
	}

	it should "throw an exception on xml validation errors" in {
		Try(LoadXmlWithSchema("src/test/resources/invalid-sample.xml","sample.xsd")) match {
			case Success(xml) => fail("Should not have validated xml")
			case Failure(e : org.xml.sax.SAXParseException) => assert(true)
			case Failure(_) => fail("should have failed with a org.xml.sax.SAXParseException")
		}		
	}
}