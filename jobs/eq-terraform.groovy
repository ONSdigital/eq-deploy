job('Terraform Pre Production Environment') {
  description 'Terraform Pre Production Environment.'
  scm {
    github 'ONSDigital/eq-terraform'
  }
  triggers {
       githubPush()
  }
  steps {
    shell('/usr/local/terraform/bin/terraform remote config -backend=S3 -backend-config="bucket=jenkins-ci-terraform-state" -backend-config="key=jenkins" -backend-config="region=eu-west-1"')
    shell('/usr/local/terraform/bin/terraform remote pull')
    shell('/usr/local/terraform/bin/terraform plan -var "env=test"')
    shell('/usr/local/terraform/bin/terraform remote push')
  }
}
