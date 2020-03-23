addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.0")
addSbtPlugin("org.foundweekends.giter8" % "sbt-giter8-scaffold" % "0.11.0")
// sbtの依存性解決でダウンロードするのを並列でやってくれるプラグイン
addSbtPlugin("io.get-coursier" % "sbt-coursier" % "1.1.0-M13-4")
