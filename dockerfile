FROM centos:latest

ADD script.sh /bin/run.sh
RUN chmod +x /bin/run.sh

RUN yum update -y
RUN yum install java-1.8.0-openjdk -y

RUN yum install postgresql-server -y
RUN su postgres -c "initdb -D /var/lib/pgsql/data"

ENV POSTGRES_DB postgres
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD 12345

RUN mkdir -p /app/
ADD build/libs/ibe-0.0.1-SNAPSHOT.jar /app/ibe.jar


EXPOSE 8080

CMD ["/bin/run.sh"]