#!/bin/bash
version=$1
REGISTRY_USERNAME=$2
REGISTRY_TOKEN=$3

echo "create a build container"
buildcon=$(buildah from eclipse-temurin:21-jre)
buildah config --workingdir /src $buildcon
buildah copy $buildcon target/*.war app.war
buildah config --port 80 $buildcon
buildah config --port 443 $buildcon

buildah config --entrypoint 'java -jar app.war' $buildcon

echo "commit an image"
buildah commit $buildcon spring-boot-primeface$version

echo "push to quay.io"
buildah login -u $REGISTRY_USERNAME --password $REGISTRY_TOKEN quay.io
buildah build -t quay.io/$REGISTRY_USERNAME/myrepo/spring-boot-primeface:$version
buildah push quay.io/$REGISTRY_USERNAME/myrepo/spring-boot-primeface:$version
buildah push quay.io/$REGISTRY_USERNAME/myrepo/spring-boot-primeface:latest

#echo "cleanup"
#buildah umount --all
#buildah rm --all