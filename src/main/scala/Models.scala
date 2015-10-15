package com.github.edgecaseberg


case class Box(id: Int, name: String, items: List[String]) 

object BoxXmlLoader {
	def apply(filePath: String) = {
		val loadedXml = LoadXmlWithSchema(filePath, "box.xsd")
		(loadedXml \\ "Box").map { boxNode =>
			Box(
				(boxNode \ "Id").text.toInt,
				(boxNode \ "Name").text,
				(boxNode \ "Contains" \\ "BoxedItem").map(_.text).toList
			)
		}
	}
}


object TreeState extends Enumeration {
	type TreeState = Value
	val ALIVE = Value("ALIVE")
	val DEAD = Value("DEAD")
}

case class Tree(state: TreeState.Value, leaves: List[String])

object TreeXmlLoader {
	def apply(filePath: String) = {
		val loadedXml = LoadXmlWithSchema(filePath, "tree.xsd")
		(loadedXml \\ "Tree").map { treeNode => 
			Tree(
				(treeNode \ "State").map { optionalStateNode =>
					optionalStateNode.text match {
						case ts:String => TreeState.withName(ts)
						case _ => TreeState.DEAD
					}
				}.headOption.getOrElse(TreeState.DEAD),
				(treeNode \ "Leaves" \\ "Leaf" \\ "@color").map(_.text).toList
			)
		}
	}
}