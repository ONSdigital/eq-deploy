job('Deploy Survey Runner') {
  description 'Deploys Survey Runner to Elastic Beanstalk.'
  scm {
    github('ONSDigital/eq-survey-runner', 'master')
  }
  triggers {
       githubPush()
  }
  steps {
    shell('rm ./.ebextensions/git-revision.config')
    shell('cat << EOF >> ./.ebextensions/git-revision.config\n\noption_settings:\n  - option_name: EQ_GIT_REF\n    value: ${GIT_COMMIT}\nEOF')
    shell('npm install')
    shell('npm run compile')
    shell('mkdir -p keys')
    shell('cp /opt/keys/*.pem keys/')
  }
  configure { project ->
       project / builders << 'br.com.ingenieux.jenkins.plugins.awsebdeployment.AWSEBDeploymentBuilder' {
            credentialId('e50cc745-fe9a-4b08-94aa-eb65a4063cb9')
            awsRegion('eu-west-1')
            applicationName('prod-surveyrunner')
            environmentName('prod-prime')
            bucketName('')
            keyPrefix('')
            versionLabelFormat('${GIT_COMMIT}-${BUILD_TAG}')
            rootObject('.')
            includes('')
            excludes('')
            zeroDowntime('true')
       }
  }
}
