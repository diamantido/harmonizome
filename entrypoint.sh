#!/bin/bash

if [ -z "${ORIGIN}" ]; then
  export ORIGIN="https://amp.pharm.mssm.edu"
fi

if [ -z "${HARMONIZOME_PREFIX}" ]; then
  export HARMONIZOME_PREFIX="Harmonizome"
fi

if [ -z "${STATIC_URL}" ]; then
  export STATIC_URL="${ORIGIN}/static"
fi

if [ -z "${DB_URL}" ]; then
  echo "Missing DB_URL"
  exit 1
fi

# Pre-init
echo "Moving war to prefix"
mv "Harmonizome.war" "webapps/${HARMONIZOME_PREFIX}.war"

echo "Pre initialization overrides..."
find overrides/pre -type f -print | while IFS= read -r INPATH; do
  OUTPATH=$(sh -c "echo \"$(realpath --relative-to=overrides/pre ${INPATH})\"")
  mkdir -p $(dirname ${OUTPATH})
  envsubst < ${INPATH} > ${OUTPATH}
done

chmod +x "${CATALINA_HOME}/bin/setenv.sh"
catalina.sh run &
PID=$1

echo "Waiting for it to start..."
while true; do
  URL="http://localhost:8080/${HARMONIZOME_PREFIX}/about"
  PROBE="$(curl --silent -m 1 --write-out '%{response_code}' -o /dev/null ${URL})"
  echo "GET ${URL} returned ${PROBE}"
  if [ "${PROBE}" -eq "200" ]; then
    echo "Post initialization overrides..."
    find overrides/post -type f -print | while IFS= read -r INPATH; do
      OUTPATH=$(sh -c "echo \"$(realpath --relative-to=overrides/post ${INPATH})\"")
      mkdir -p $(dirname ${OUTPATH})
      envsubst < ${INPATH} > ${OUTPATH}
    done
    break
  elif [ "${PROBE}" -eq "500" ]; then
    catalina.sh stop
    break
  fi
  sleep 5
done

wait ${PID}
