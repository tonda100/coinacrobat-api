#!/usr/bin/env bash
if [ "$TRAVIS_BRANCH" = 'dev' ] && [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
    mvn deploy -P sign,build-extras --settings cd/mvnsettings.xml
fi