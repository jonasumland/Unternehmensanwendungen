import sbt.Keys._
import sbt.Project
import com.sap.marmolata.sbt.Import._
import com.sap.marmolata.sbt.MarmolataRootProject

lazy val commonSettings = Seq(
  organization := "com.sap",
  name := "CallCenterApp", 
  version := "0.1-SNAPSHOT",
  resolvers := Seq(
    Resolver.url("local", new URL(Path.userHome.asFile.toURI.toURL + "/.ivy2/local"))(Resolver.ivyStylePatterns),
    DefaultMavenRepository,
    Resolver.sonatypeRepo("public"),
    Resolver.typesafeRepo("releases"),
    Resolver.sbtPluginRepo("releases"),
    "marmolata" at "http://vm-marmolata.eaalab.hpi.uni-potsdam.de/nexus/",
    "marmolata-alternative" at "https://jaheba.github.io/marmolata-mirror/nexus/"
  ),
  externalResolvers := resolvers.value
)

lazy val CallCenterAppRoot: Project = (project in file("."))
  .enablePlugins(MarmolataRootProject)
  .settings(commonSettings: _*)
  .aggregate(CallCenterAppJS, CallCenterAppJVM)

lazy val CallCenterApp = (crossProject in file("."))
  .marmolataCrossProject("CallCenterApp")
  .jsSettings(commonSettings: _*)
  .jvmSettings(commonSettings: _*)
  .jsSettings(
    fastOptJS in Compile := (fastOptJS in Compile dependsOn (unjarUI)).value,
    fullOptJS in Compile := (fullOptJS in Compile dependsOn (unjarUI)).value
  )
  .jvmSettings(
    libraryDependencies += Dependencies.ngdbc.value % "compile,test,it"
  )

lazy val CallCenterAppJS: Project = CallCenterApp.js
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies += "com.sap.marmolata" %%%! "app" % "hpi-5",
    libraryDependencies += "com.sap.marmolata" %%%! "erp-custom-hpb" % "hpi-6",
    libraryDependencies += "com.sap.marmolata" %%%! "data-sql" % "hpi-19"
  )

lazy val CallCenterAppJVM: Project = CallCenterApp.jvm
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies += "com.sap.marmolata" %% "app" % "hpi-5",
    libraryDependencies += "com.sap.marmolata" %% "erp-custom-hpb" % "hpi-6"
  )
