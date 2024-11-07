def BUILD_CONFIGURATION = 'UNKNOWN'
def BUILD_CONFIGURATION_DEMO = 'UNKNOWN'

pipeline {
	agent any
	environment {
        BUILD_VERSION = "X"
    }

	stages {
        stage('Build Image'){
            steps{
                script{
                    filtered_branch_name = BRANCH_NAME.replaceAll("[^A-Za-z-0-1]","")
                    if (BRANCH_NAME == 'master') {
                        sh script:'''docker build -t git.hyrana.info/PageUp/bolComWebhookReceiver:latest -t git.hyrana.info/PageUp/bolComWebhookReceiver:''' + BUILD_NUMBER + ''' . '''
                    } else {
                        sh script:'''docker build -t git.hyrana.info/PageUp/bolComWebhookReceiver/'''  + filtered_branch_name +  ''':''' + BUILD_NUMBER + ''' . '''
                    }
                    
                }
                
            }
        }
        stage('Push Image'){
            when {
                branch 'master'
            }
            steps{
                script{
                    sh 'docker login git.hyrana.info'
                    sh 'docker push git.hyrana.info/PageUp/bolComWebhookReceiver --all-tags'
                }
            }
        }
}
}

