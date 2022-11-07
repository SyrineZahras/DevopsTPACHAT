pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven 'M2_HOME'
        jdk 'JAVA_HOME'
    }

     environment { 
        registry = "muckydreamz/tpachatdevops" 
        registryCredential = 'dockerHub' 
        dockerImage = '' 
    }
    
    stages {
        stage('Checkout Git') {
            steps {
                echo 'Pulling ...';
                git branch : 'master',
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
    }
}


