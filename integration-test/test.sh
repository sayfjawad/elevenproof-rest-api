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
# Function to kill processes binding to a specific port
kill_port() {
    port=$1
    echo "Checking for processes binding to port $port"

    # Use lsof to find processes using the specified port and extract their PIDs
    pids=$(lsof -nPi :$port | grep LISTEN | awk '{print $2}')

    # Check if we found any PIDs
    if [ -z "$pids" ]; then
        echo "No processes found binding to port $port."
    else
        # Kill each process found
        for pid in $pids; do
            echo "Killing process $pid binding to port $port"
            kill -9 $pid
        done
    fi
}
function uninstall() {
  helm uninstall --debug "integratie-test"
  # Ports to be checked and cleaned
  kubectl delete service elevenproof-rest-api
  kubectl delete service eleven-proof-api-service
  kill_port 8080
  kill_port 5005
#  echo "Uninstall tasks completed."
}
function install() {
  helm install --wait --wait-for-jobs --debug --values "$BASEDIR/k8s/values.yaml,$BASEDIR/k8s/values-local.yaml" "integratie-test" "$BASEDIR/k8s"
}
function portforwarding() {
  kubectl port-forward service/elevenproof-rest-api 8080:8080 &
  kubectl port-forward service/elevenproof-rest-api 5005:5005 &
}
function template() {
  helm template r1 --debug --values "$BASEDIR/k8s/values.yaml,$BASEDIR/k8s/values-local.yaml" "$BASEDIR/k8s"
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
