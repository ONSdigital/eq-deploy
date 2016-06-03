job('Terraform Destroy Environment') {
  description 'Terraform Destroy Environment.'
  scm {
    github ('ONSDigital/eq-terraform', 'master')
  }
  triggers {
       githubPush()
  }
  steps {
    shell('cd survey-runner; terraform remote config -backend=S3 -backend-config="bucket=jenkins-ci-production-terraform-state" -backend-config="key=jenkins" -backend-config="region=eu-west-1"')
    shell('cd survey-runner; terraform remote pull')
    shell('cd survey-runner; terraform destroy --force -var "env=prod"')
    shell('cd survey-runner; terraform remote push')
  }
}
