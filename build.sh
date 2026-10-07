#!/bin/bash
version=$1
REGISTRY_USERNAME=$2
REGISTRY_TOKEN=$3

echo "create a build container"
buildcon=$(buildah from eclipse-temurin:21-jre)
buildah config --workingdir /src $buildcon
buildah copy $buildcon target/*.jar app.jar
buildah config --port 80 $buildcon
buildah config --port 443 $buildcon

buildah config --entrypoint 'java -jar app.jar' $buildcon

echo "commit an image"
buildah commit $buildcon spring-boot-primeface$version

echo "cleanup"
#buildah umount --all
#buildah rm --all

echo "push to github"
buildah push --creds $REGISTRY_USERNAME:$REGISTRY_TOKEN quay.io/devcloud1user4/myrepo/spring-boot-primeface:$version quay.io/devcloud1user4/myrepo/spring-boot-primeface:$version
buildah push --creds $REGISTRY_USERNAME:$REGISTRY_TOKEN quay.io/devcloud1user4/myrepo/spring-boot-primeface:$version quay.io/devcloud1user4/myrepo/spring-boot-primeface:latest
