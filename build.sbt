name := "xsd-example"

version := "0.0.0-SNAPSHOT"

scalaVersion := "2.11.7"

libraryDependencies <+= (scalaVersion)("org.scala-lang" % "scala-compiler" % _)

libraryDependencies ++= { 
	Seq(
		"org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
	)
}
