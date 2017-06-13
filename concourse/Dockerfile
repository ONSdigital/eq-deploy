FROM jonnyshaw89/dashvid-build
RUN apt-get update && \
    apt-get install -y awscli python-pip default-jre
RUN pip install --upgrade pip && \
    pip install awsebcli
RUN apt-key adv --keyserver pgp.mit.edu --recv 9D41F3C3 && \
    echo "deb http://dl.yarnpkg.com/debian/ stable main" | tee /etc/apt/sources.list.d/yarn.list && \
    apt-get update && \
    apt-get install yarn