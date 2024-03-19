pipeline{

      agent any

      stages{

          stage('Build JAR'){
              steps{
                    bat "mvn clean package -DskipTests"
              }

          }
           stage('Build Image'){
                steps{
                     bat "docker build -t=shaarv70/selenium:latest ."
              }

           }
            stage('Push Image'){
                environment{
                  DOCKER_HUB =credentials('dockerhub-creds')
                }
                steps{
                     bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
                     bat 'docker push shaarv70/selenium:latest'
                     bat "docker tag shaarv70/selenium:latest shaarv70/selenium:${env.BUILD_NUMBER}"
                      bat "docker push shaarv70/selenium:${env.BUILD_NUMBER}"
               }
           }

       }
         post{
             always {
                  bat "docker logout"
                  bat "docker image rm shaarv70/selenium:latest"
                  bat"docker image rm shaarv70/selenium:${env.BUILD_NUMBER}"
             }
         }

      }
