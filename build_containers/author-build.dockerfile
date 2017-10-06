FROM ubuntu:16.04

RUN apt-get update \
    && apt-get install -y curl wget git make build-essential python apt-transport-https xvfb libappindicator1 fonts-liberation openjdk-8-jre-headless

RUN curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | apt-key add - \
    && echo "deb https://dl.yarnpkg.com/debian/ stable main" | tee /etc/apt/sources.list.d/yarn.list \
    && curl -sL https://deb.nodesource.com/setup_7.x | bash -

RUN apt-get update \
    && apt-get install -y nodejs yarn
