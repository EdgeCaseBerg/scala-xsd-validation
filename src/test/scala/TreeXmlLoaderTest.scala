package com.github.edgecaseberg

import org.scalatest._

import scala.util.{Try,Success,Failure}

class TreeXmlLoaderTest extends FlatSpec with Matchers {

	val expectedData = List(
		Tree(
			TreeState.ALIVE,
			List("Green","Yellow")
		), 
		Tree(
			TreeState.DEAD,
			List(
				"Brown",
				"Grey",
				"Gray"
			)
		)
	)
	
	"The TreeXMLLoader" should "be able to load a valid xml file and transform it to a List of Trees" in {		
		Try(TreeXmlLoader("src/test/resources/tree-sample.xml")) match {
			case Success(xml) => 
				assertResult(expectedData)(xml)
			case Failure(e) => fail(e)
		}
	}

}