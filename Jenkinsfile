pipeline {
     agent {
               docker {
                    image 'maven:3-amazoncorretto-17'
                    args '-p 33333:8090'
               }
          }
          environment {
               HOME = '.'
          }
     stages {
          stage('Source') {
               steps {
                    git branch: 'main',
                        url: 'https://github.com/TanapatButsai/YakiWebsite.git'
               }
          }
          stage('Build') {
               steps {
                    sh 'mvn package -DskipTests'
               }
          }
          stage('Test') {
               steps {
                    echo 'testing...'
                    //sh 'mvn test'
               }
          }
          stage('Deploy') {
               steps {
                    sh 'java -jar ./target/YakinikuWebsite-0.0.1-SNAPSHOT.jar'
               }
          }
     }
}