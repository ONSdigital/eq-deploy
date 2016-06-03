job('Terraform Destroy Author Environment') {
  description 'Terraform Destroy Author Environment.'
  scm {
    github ('ONSDigital/eq-terraform', 'master')
  }
  triggers {
       githubPush()
  }
  steps {
    shell('cd author')
    shell('terraform remote config -backend=S3 -backend-config="bucket=jenkins-ci-production-author-terraform-state" -backend-config="key=jenkins" -backend-config="region=eu-west-1"')
    shell('terraform remote pull')
    shell('terraform destroy -var "env=prod" --force')
    shell('terraform remote push')
  }
}
