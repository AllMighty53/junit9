node {
    stage ('checkout') {
    git 'git@github.com:AllMighty53/junit9.git'

 }
    stage ('tests') {
        sh './gradlew clean test --tests test'
    }
    stage ('publish') {
        publishHTML([allowMissing: true, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'build/reports/tests/test/', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: ''])

    }
}