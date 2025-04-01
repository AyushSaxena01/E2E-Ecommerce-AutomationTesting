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
                bat 'mvn clean install'  // Build the project using Maven
            }
        }

        stage('Build and Run Tests') {
            steps {
                bat 'mvn verify'  //Runs tests and generate Allure results and report
            }
        }

        stage('Publish Extent Report') {
            steps {
                publishHTML([
                    reportDir: 'test-output\\extentreports', 
                    reportFiles: 'Amazon.html', 
                    reportName: 'Extent Report',
                    keepAll: true,
                    allowMissing: true, 
                    alwaysLinkToLastBuild: false
                ])
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
            junit '**/test-output/junitreports/*.xml'  // Capture test results
        }
    }
}
