call mvn package -Dmaven.test.skip
cd target
copy CourseApp-0.0.1-SNAPSHOT.war C:\Users\puqian\Programs\Tomcat\webapps\ /Y
cd C:\Users\puqian\Programs\Tomcat\webapps\
ren CourseApp-0.0.1-SNAPSHOT.war CourseApp.war
pause