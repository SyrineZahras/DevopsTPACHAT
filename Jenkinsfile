pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven 'M2_HOME'
        jdk 'JAVA_HOME'
    }

     environment { 
        registry = "muckydreamz/tpachatdevops" 
        registryCredential = 'dockerhub' 
        dockerImage = '' 
    }
    
    stages {
        stage('Checkout Git') {
            steps {
                echo 'Pulling ...';
                git branch : 'ahlem',
                // Get some code from a GitHub repository
                url: 'https://github.com/SyrineZahras/DevopsTPACHAT.git'

                // Get System Current Date
                script{
                    Date date = new Date()
                    String dateString = date.format("dd-MM-yyyy")
                    println "Date : " + dateString
                }
                mail body: 'Pipeline has been executed successfully', to: "ahlem.laajili@esprit.tn", subject: 'pipeline executed'
            }
        }

        stage('Build Maven Spring'){
            steps{
                 sh 'mvn  clean install '
           }
        }

        stage('NEXUS'){
            steps{
                  echo "nexus"
                  sh ' mvn deploy -DskipTests'
            }
        }

        stage('MVN SONARQUBE '){
            steps{
                  sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
            }
        }

        stage("Maven Build") {
            steps {
                script {
                    sh "mvn package -DskipTests=true"
                }
            }
        }

        stage('Building Docker image') { 
            steps { 
                script { 
                    dockerImage = docker.build registry + ":$BUILD_NUMBER" 
                }
            } 
        }
        stage('Push Docker image') { 
            steps { 
                script { 
                    docker.withRegistry( '', registryCredential ) { 
                        dockerImage.push() 
                    }
                } 
            }
        } 
    }
    post {
            failure {
                mail body: 'Pipeline has failed', to: "syrine.zahras@esprit.tn", subject: 'Pipeline fail'
            }
    }
}






