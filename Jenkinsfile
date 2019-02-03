pipeline {
    agent any

	tools {
	    maven 'maven3.6'
	    jdk 'java8-openjdk'
	}
    stages {
    	stage('Initialize') {
   			steps {
   			    sh '''
   			    	echo "PATH = ${PATH}"
   			    	echo "M2_HOME = ${M2_HOME}"
   			    '''   
   			}
    	}
        stage('Checkout') {
        	steps {
        	  checkout scm: [$class: 'GitSCM', source: 'git@github.com:dguymon/microservices.git', clean: true], poll: false    
        	}
        }
        stage('Build') {
            steps {
                sh 'mvn clean package -U -Dmaven.test.skip=true'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }
        }
        stage('Deliver') {
            steps {
               withMaven(
                 mavenSettingsConfig: 'maven-settings-for-nexus'
               ) {
                   sh 'mvn war:war deploy:deploy'    
                 }
            }
        }
    }
}