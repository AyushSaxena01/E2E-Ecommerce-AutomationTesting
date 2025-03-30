pipeline {
    agent any  // Runs on any available Jenkins agent

    tools {
        maven 'MAVEN'  // Ensure Maven is installed on Jenkins
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'master', url: 'https://github.com/AyushSaxena01/E2E-Ecommerce-AutomationTesting.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'  // Build the project using Maven
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'  // Run Selenium tests
            }
        }

        stage('Generate Allure Report') {
            steps {
                sh 'mvn allure:report'  // Generates a test report
            }
        }

        stage('Publish Allure Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            junit '**/target/surefire-reports/*.xml'  // Capture test results
        }
    }
}
