job('Deploy Author') {
  description 'Deploys Author to Elastic Beanstalk.'
  scm {
    github('ONSDigital/eq-author', 'master')
  }
  triggers {
       githubPush()
  }
  steps {
    shell('rm ./.ebextensions/git-revision.config')
    shell('cat << EOF >> ./.ebextensions/git-revision.config\n\noption_settings:\n  - option_name: EQ_GIT_REF\n    value: ${GIT_COMMIT}\nEOF')
    shell('rm -rf node_modules')
    shell('npm cache clean')
    shell('npm install')
    shell('npm run compile')
  }
  configure { project ->
       project / builders << 'br.com.ingenieux.jenkins.plugins.awsebdeployment.AWSEBDeploymentBuilder' {
            credentialId('e50cc745-fe9a-4b08-94aa-eb65a4063cb9')
            awsRegion('eu-west-1')
            applicationName('prod-author')
            environmentName('prod-author-prime')
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
