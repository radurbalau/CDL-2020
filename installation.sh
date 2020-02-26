#!/bin/bash

apt-get update -y
apt-get upgrade -y
apt-get install openjdk-11-jdk -y
apt-get install snapd
snap install intellij-idea-community --classic


