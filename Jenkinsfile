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
            }
        }

               stage('Compile Maven Project'){
          steps{
             sh 'mvn  compile '
          }                                                            
     }
 
       stage('Mock & JUnit') {
        steps {
            script {
              sh 'echo "********Mock & JUnit********"'
              sh 'mvn test'
            }
        }
       post {
         always {
            junit '**/target/surefire-reports/TEST-*.xml'
        }
      }
     }
        
        stage('Clean Maven Spring'){
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
                  sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=esprit'
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
        stage('Docker Compose') {
            steps {
                sh 'docker-compose up -d'
            }
        }
        
    }
    post {
            success {
                mail body: "success on job ${env.JOB_NAME}, Build Number: ${env.BUILD_NUMBER}, Build URL: ${env.BUILD_URL}",
                to: "ahlem.laajili@esprit.tn",
                subject: "Pipeline Success"  
            }
            failure {
                mail body: "Job has failed${env.JOB_NAME}, Build Number: ${env.BUILD_NUMBER}, Build URL: ${env.BUILD_URL}", 
                to: "ahlem.laajili@esprit.tn",
                subject: 'Pipeline fail'
            }
    }
}












 