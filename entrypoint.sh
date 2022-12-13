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
  if [ "${DATABASE_URL}" != "" ]; then
    DB_CONFIG=$(sed 's/^\(mysql\|mariadb\):\/\/\([^:]\+\):\([^@]\+\)@\([^:]\+\):\([0-9]\+\)\/\(.\+\)/\1 \2 \3 \4 \5 \6/g' <<< "${DATABASE_URL}")
    read -r DB_PROTO DB_USER DB_PASS DB_HOST DB_PORT DB_DATABASE <<< "${DB_CONFIG}"
    export DB_URL="jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_DATABASE}"
    export DB_USER="${DB_USER}"
    export DB_PASS="${DB_PASS}"
    echo "${DATABASE_URL} => DB_URL=${DB_URL}, DB_USER=${DB_USER}, DB_PASS=${DB_PASS}"
  else
    echo "Missing DB_URL"
    exit 1
  fi
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
