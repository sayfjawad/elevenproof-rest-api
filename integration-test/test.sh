#!/bin/bash
APP=""
BASEDIR=$(dirname $(realpath "$0"))
printUsageAndExit() {
  echo "Usage: test.sh"
  echo "       test.sh test"
  echo "       test.sh install"
  echo "       test.sh uninstall"
  echo "       test.sh port"
  exit 1
}
function uninstall() {
  helm uninstall --debug "integratie-test"
}
function install() {
  helm install --wait --wait-for-jobs --debug --values "$BASEDIR/openshift/values.yaml,$BASEDIR/openshift/values-local.yaml" "integratie-test" "$BASEDIR/openshift"
}
function portforwarding() {
  kubectl port-forward service/elevenproof-rest-api 8080:8080 &
}
function template() {
  helm template r1 --debug --values "$BASEDIR/openshift/values.yaml,$BASEDIR/openshift/values-local.yaml" "$BASEDIR/openshift"
}
case "${1:-}" in
"usage" | "--help")
  printUsageAndExit
  ;;
"dry-run"| "test")
  template
  exit 0
  ;;
"install")
  install
  exit 0
  ;;
"uninstall")
  uninstall
  exit 0
  ;;
"port")
  portforwarding
  exit 0
  ;;
*)
  printUsageAndExit
  ;;
esac
