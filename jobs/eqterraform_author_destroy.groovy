job('Terraform Destroy Author Environment') {
  description 'Terraform Destroy Author Environment.'
  scm {
    github ('ONSDigital/eq-terraform', 'master')
  }
  triggers {
       githubPush()
  }
  steps {
    shell('cd author; terraform remote config -backend=S3 -backend-config="bucket=jenkins-ci-production-author-terraform-state" -backend-config="key=jenkins" -backend-config="region=eu-west-1"')
    shell('cd author; terraform remote pull')
    shell('cd author; terraform destroy -var "env=prod" --force')
    shell('cd author; terraform remote push')
  }
}
