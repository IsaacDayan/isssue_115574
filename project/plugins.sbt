logLevel := Level.Warn

addSbtPlugin("com.eed3si9n"   % "sbt-assembly"           % "2.1.0")
addSbtPlugin("io.spray"       % "sbt-revolver"           % "0.9.1")
addSbtPlugin("ch.epfl.scala"  % "sbt-scalafix"           % "0.9.34")
addSbtPlugin("org.scalameta"  % "sbt-scalafmt"           % "2.4.6")
addSbtPlugin("org.scoverage"  % "sbt-scoverage"          % "1.9.3")
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0")
addSbtPlugin("com.github.sbt" % "sbt-native-packager"    % "1.9.11")
addSbtPlugin("com.github.sbt" % "sbt-release"            % "1.1.0")
addSbtPlugin("org.latestbit"  % "sbt-gcs-plugin"         % "1.7.3")
addSbtPlugin("laughedelic"    % "sbt-publish-more"       % "0.1.0")

ThisBuild / libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
