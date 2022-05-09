#!/bin/bash

readonly files="./files.out"

readonly username="admin"
readonly password="password"
readonly nexusurl="http://localhost:8071/repository/3rd-party/"

if [ -f "${files}" ]; then
  rm -f "${files}"
fi

find . -name "*.*" -type f | cut -c 3- | grep "/" | grep -v maven-bulk-upload.sh > ${files}

while read i; do
  group=`echo ${i} | awk -F\/ '{print $1}' | tr "." "/"`
  artifact=`echo ${i} | awk -F\/ '{print $2}'`
  version=`echo ${i} | awk -F\/ '{print $3}'`
  file=`echo ${i} | awk -F\/ '{print $NF}'`
  echo "upload ${i} to ${nexusurl}${group}/${artifact}/${version}/${file}"
  curl -v -u ${username}:${password} --upload-file ${i} ${nexusurl}${group}/${artifact}/${version}/${file}
done <${files}