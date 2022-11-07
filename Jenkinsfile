pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven 'M2_HOME'
        jdk 'JAVA_HOME'
    }

    
    stages {
        stage('Checkout Git') {
            steps {
                echo 'Pulling ...';
                git branch : 'syrine',
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

        stage("Maven Build") {
            steps {
                script {
                    sh "mvn package -DskipTests=true"
                }
            }
        }

        stage('Docker login') {

                                         steps {
                                          sh 'echo "login Docker ...."'
                   	sh 'docker login -u syrine123456789 -p Sysysyrine1'
                               }  }

		 stage('Docker push') {

                 steps {
                      sh 'echo "Docker is pushing ...."'
                     	sh 'docker push syrine123456789/tpdevops'
                        }  }
        

        
        
    }
    post {
            failure {
                mail body: 'Pipeline has failed', to: "syrine.zahras@esprit.tn", subject: 'Pipeline fail'
            }
    }
}






