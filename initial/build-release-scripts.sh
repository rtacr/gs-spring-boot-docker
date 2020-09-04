#!/bin/bash
set -ex

pushd /release-scripts
	./mvnw clean install
popd
cp /release-scripts/target/gs-spring-boot-docker-0.1.0.jar ./app.jar