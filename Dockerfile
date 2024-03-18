FROM bellsoft/liberica-openjdk-alpine:17.0.8
#Install curl jq (curl is used to check whether selenium grid hub and nodes are ready)
#jq-curl will give the output in json object we feed that json object in to jq(jq will take the input in json)
#We are doin all this as our suite container is very fast and it will not wait for selenium grid to get ready
RUN apk add curl jq
#workspace

WORKDIR /home/selenium-docker
#Add the required files to run the test into the currnt workdir(./) which we have mentioned above
#we can add multiple files
ADD target/docker-resources   ./
#down line means runner .sh present in root directory add it in the working directory of container with same name
ADD runner.sh runner.sh
RUN dos2unix runner.sh
#Run the tests in container here we canot hardcode ip, \ is used to refer to the next line
ENTRYPOINT sh runner.sh