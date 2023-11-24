pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test -f pom.xml'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    withSonarQubeEnv('sonarqube') {
                        sh 'mvn sonar:sonar -Pcoverage'
                    }
                }
            }
        }
        stage("Quality Gate") {
            steps {
              timeout(time: 15, unit: 'MINUTES') {
                waitForQualityGate abortPipeline: false
              }
            }
          }

          stage ('OWASP Dependency-Check Vulnerabilities') {
                              steps {
                                  dependencyCheck additionalArguments: '''
                                      --out "./"
                                      --scan "./"
                                      --nvdApiKey "9aaa12f1-05e4-4ea2-bdf5-2c202451b0c7"
                                      --noupdate
                                      --format "ALL"
                                      --prettyPrint''', odcInstallation: 'dependency-check'
                                  dependencyCheckPublisher pattern: 'dependency-check-report.xml'
                              }
                  }
        stage('Deploy') {
            steps {
            echo "DEPLOYMENT SUCCESSFUL!!  And the crowd goes wild!"
//                 cat './jenkins/scripts/deploy.sh'
            }
        }
    }
}