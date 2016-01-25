name := "authorization"

version := "1.0-SNAPSHOT"

playJavaSettings

ebeanEnabled := false

libraryDependencies ++= Seq(
    javaCore,
    javaJpa,
    javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
	"org.springframework" % "spring-context" % "4.1.7.RELEASE",
    "javax.inject" % "javax.inject" % "1",
    "org.springframework.data" % "spring-data-jpa" % "1.9.0.RELEASE",
    "org.springframework.ws" % "spring-ws-core" % "2.1.4.RELEASE",
    "org.springframework.boot" % "spring-boot-starter" % "1.3.0.RELEASE",
    "org.springframework" % "spring-expression" % "4.1.7.RELEASE",
    "org.hibernate" % "hibernate-entitymanager" % "5.0.2.Final",
    "org.mockito" % "mockito-core" % "1.9.5" % "test",
    "org.postgresql" % "postgresql" % "9.4-1200-jdbc41"
)