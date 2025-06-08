#!/usr/bin/env groovy
    node {

      stage('Initialise') {
        /* Checkout the scripts */
        checkout scm: [
                $class: 'GitSCM',
                userRemoteConfigs: [
                        [
                                url: "https://github.com/numairkaukab2/performance-test-repo.git",
                                credentialsId: "numair"
                        ]
                ],
                branches: [[name: "main"]]
        ], poll: false
      }

      stage('Complete any setup steps') {
        echo "Complete set-up steps"
      }

      stage('Execute Performance Tests') {
        dir("${WORKSPACE}/scripts") {
            bat 'c:/apache-jmeter/apache-jmeter/bin/jmeter.bat -n -t Shift-Left.jmx -l Shift-Left.jtl'
        }
      }

      stage('Analyse Results') {
        echo "Analyse results"
      }
    }