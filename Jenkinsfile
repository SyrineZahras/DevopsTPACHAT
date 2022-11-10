pipeline {
    agent any

    // tools {
    //     // Install the Maven version configured as "M3" and add it to the path.
    //     maven 'M2_HOME'
    //     jdk 'JAVA_HOME'
    // }

    //  environment { 
    //     registry = "muckydreamz/tpachatdevops" 
    //     registryCredential = 'dockerhub' 
    //        NEXUS_VERSION = 'nexus3' 
    //        NEXUS_PROTOCOL = 'http'
    //        NEXUS_URL = 'http://172.10.0.140:8081'
    //        NEXUS_REPOSITORY = 'nexus-repo-devops'
    //        NEXUS_CREDENDIAL_ID = 'nexus-user-credentials'
    //     dockerImage = '' 
    // }
    
    stages {
        stage('Checkout Git') {
            steps {
                echo 'Pulling ...';
                git branch : 'hichem',
                // Get some code from a GitHub repository
                url: 'https://github.com/SyrineZahras/DevopsTPACHAT.git'

                // Get System Current Date
                script{
                    Date date = new Date()
                    String dateString = date.format("dd-MM-yyyy")
                    println "Date : " + dateString
                }
                mail body: 'Pipeline has been executed successfully', to: "hichem.benzammel@esprit.tn", subject: 'pipeline executed'
            }
        }


        stage("Maven Build") {
            steps {
                script {
                    sh "mvn clean install -DskipTests"
                }
            }
        }

        stage('MVN COMPILE') { 
            steps { 
               sh' mvn compile' 

            }
         }

         stage('MVN SONARQUBE') {
                     steps {
                         sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
                     }
                 }

                 stage ('NEXUS DEPLOY') {
                     steps {
                         sh 'mvn clean package deploy:deploy-file -DgroupId=com.esprit.examen -DartifactId=tpAchatProject -Dversion=1.0 -DgeneratePom=true -Dpackaging=jar -DrepositoryId=deploymentRepo -Durl=http://192.168.43.231:8081/repository/maven-releases/ -Dfile=target/tpAchatProject-1.0.jar -DskipTests'
                     }
                 }

        // stage('Building Docker image') { 
        //     steps { 
        //         script { 
        //             dockerImage = docker.build registry + ":$BUILD_NUMBER" 
        //         }
        //     } 
        // }
        // stage('Push Docker image') { 
        //     steps { 
        //         script { 
        //             docker.withRegistry( '', registryCredential ) { 
        //                 dockerImage.push() 
        //             }
        //         } 
        //     }
        // } 
    }
    // post {
    //         failure {
    //             mail body: 'Pipeline has failed', to: "syrine.zahras@esprit.tn", subject: 'Pipeline fail'
    //         }
    // }
}






