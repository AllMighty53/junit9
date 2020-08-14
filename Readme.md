
Testing/Automationfiles Junit/Gradle
-
~~~
tests at ./src/test/java/test.java
gradle config ./build.gradle
~~~

For installing the artifactory on minikube - used Helm chart
-
*  minikube start
* used minikube tunnel - for the nginx to get an IP


~~~
helm repo add center https://repo.chartcenter.io

helm install center-artifactory center/jfrog/artifactory
~~~

Jenkinsfile - at project root ./Jenkinsfile
-