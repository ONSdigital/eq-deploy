job('Terraform Author Environment') {
  description 'Terraform Author Environment.'
  scm {
    github ('ONSDigital/eq-terraform', 'master')
  }
  triggers {
       githubPush()
  }
  steps {
    shell('cd author; terraform remote config -backend=S3 -backend-config="bucket=jenkins-ci-production-author-terraform-state" -backend-config="key=jenkins" -backend-config="region=eu-west-1"')
    shell('cd author; terraform remote pull')
    shell('cd author; terraform apply -var "env=prod"')
    shell('cd author; terraform remote push')
  }
}
