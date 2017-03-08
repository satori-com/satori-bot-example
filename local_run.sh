#!/bin/bash

function help {
	echo "$0 framework_jar bot_jar bot_config_file"
}

if [ $# -ne 3 -o "$1" == "--help" -o "$1" == "-h" ]; then
	help
	exit 1
fi

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
for i in $1 $2 $3; do
 if [ ! -f $i ]; then
  echo "$i not found"
  exit 1
 fi
done

framework_jar=$1
bot_jar=$2
config=$3

java -Djava.security.policy=$DIR/security.policy -cp $framework_jar:$bot_jar com.satori.bots.framework.Framework -botConfigPath $config
