For installing the artifactory on minikube - used Helm

minikube start

** used minikube tunnel - for the nginx to get an IP



helm repo add center https://repo.chartcenter.io

helm install center-artifactory center/jfrog/artifactory
