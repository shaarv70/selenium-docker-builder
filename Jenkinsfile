pipeline{

      agent any

      stages{

          stage('Build JAR'){
              steps{
                    bat "mvn clean package -DskipTests"
              }

          }
           stage('Build Imange'){
                steps{
                     bat "docker build -t=shaarv70/selenium ."
              }

           }
            stage('Push Image'){
                steps{
                     bat "docker push shaarv70/selenium"
               }

            }

      }


}