job('Terraform Author Environment') {
  description 'Terraform Author Environment.'
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
    shell('terraform apply -var "env=prod"')
    shell('terraform remote push')
  }
}
