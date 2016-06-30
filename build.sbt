import com.github.play2war.plugin._

name := "racom"

version := "1.0"

Play2WarPlugin.play2WarSettings

Play2WarKeys.servletVersion := "3.0"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "com.google.code.gson" % "gson" % "2.2.4",
  "org.json" % "json" % "20140107",
  "commons-codec" % "commons-codec" % "1.8",
  "commons-lang" % "commons-lang" % "2.4",
  "org.springframework" % "spring-context" % "3.2.6.RELEASE",
  "org.apache.velocity" % "velocity" % "1.7",
  "com.stormpath.sdk" % "stormpath-sdk-api" % "1.0.RC3",  
  "com.stormpath.sdk" % "stormpath-sdk-httpclient" % "1.0.RC3",
  "com.stormpath.sdk" % "stormpath-sdk-oauth" % "1.0.RC3",  
   "org.apache.httpcomponents" % "httpclient" % "4.3.5"
)

libraryDependencies += "net.sf.uadetector" % "uadetector-resources" % "2014.08"

libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.3.5"

libraryDependencies += "commons-io" % "commons-io" % "2.4"

play.Project.playJavaSettings
