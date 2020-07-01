FROM httpd:2.4

# Common
RUN apt-get update \
    && apt-get  install -y \
                wget \
                git \
                zip \
                unzip 


# Installing JAVA
RUN wget https://download.java.net/java/GA/jdk13.0.2/d4173c853231432d94f001e99d882ca7/8/GPL/openjdk-13.0.2_linux-x64_bin.tar.gz \
    && mkdir -p /opt/java/jdk/ \
    && tar -xzf openjdk-13.0.2_linux-x64_bin.tar.gz -C /opt/java/jdk/ \
    && rm -rfv openjdk-13.0.2_linux-x64_bin.tar.gz

# Downloading maven
RUN wget http://mirror.nbtelecom.com.br/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz

# Installing maven
RUN mkdir -p /opt/java/maven/ \
    && tar -xzvf apache-maven-3.6.3-bin.tar.gz -C /opt/java/maven/ \
    && rm -rfv apache-maven-3.6.3-bin.tar.gz

# Installing maven / Creating environments variables
RUN echo 'PATH=/opt/java/maven/apache-maven-3.6.3/bin:$PATH' >> /root/.bashrc \
    && echo "export JAVA_HOME=/opt/java/jdk/jdk-13.0.2" >> /root/.bashrc 

# Clean DIR of apache
RUN rm -rf /usr/local/apache2/htdocs/index.html

# Linking de APP dir to apache to view on web the files
RUN mkdir /home/app
RUN ln -s /home/app /usr/local/apache2/htdocs/app

# Coping entrypoint file
COPY ./java/entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]