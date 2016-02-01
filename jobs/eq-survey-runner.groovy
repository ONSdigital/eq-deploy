job('Deploy Survey Runner Test') {
  description 'Deploys Survey Runner to Elastic Beanstalk.'
  scm {
    github 'ONSDigital/eq-survey-runner'
  }
  triggers {
       githubPush()
  }
  steps {
    shell('npm install')
    shell('npm run compile')
    shell('mkdir -p keys')
    shell('cp /opt/keys/*.pem keys/')
  }
  configure { project ->
       project / builders << 'br.com.ingenieux.jenkins.plugins.awsebdeployment.AWSEBDeploymentBuilder' {
            credentialId('e50cc745-fe9a-4b08-94aa-eb65a4063cb9')
            awsRegion('eu-west-1')
            applicationName('eq-survey-runner')
            environmentName('survey-runner-pre-prod')
            bucketName('')
            keyPrefix('')
            versionLabelFormat('${GIT_COMMIT}-${BUILD_TAG}')
            rootObject('.')
            includes('')
            excludes('')
            zeroDowntime('false')
       }
  }
}