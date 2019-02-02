pipeline {
    agent any

    stages {
        stage('Checkout') {
        	checkout scm: [$class: 'GitSCM', source: 'git@github.com:dguymon/microservices.git', clean: true], poll: false
        }
        stage('Build') {
            steps {
                echo 'Building...'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}