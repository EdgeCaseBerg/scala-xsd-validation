package com.github.edgecaseberg

import org.scalatest._

import scala.util.{Try,Success,Failure}

class BoxXmlLoaderTest extends FlatSpec with Matchers {

	val expectedData = List(
		Box(
			0,
			"First Box",
			List(
				"Thing 1", 
				"Thing 2"
			)
		), 
		Box(
			2,
			"Second Box",
			List(
				"Second Thing 1", 
				"Second Thing 2"
			)
		)
	)
	
	"The BoxXMLLoader" should "be able to load a valid xml file and transform it to a List of Boxes" in {		
		Try(BoxXmlLoader("src/test/resources/box-sample.xml")) match {
			case Success(xml) => 
				assertResult(expectedData)(xml)
			case Failure(e) => fail(e)
		}
	}

}