#!/bin/bash

echo "login to quay.io"
oc login --token=sha256~WLHeSCAa4sZ1UDp-tbnoaDAojuQMpGSWHblH8S0dwgc --server=https://api.rm2.thpm.p1.openshiftapps.com:6443
oc get all

