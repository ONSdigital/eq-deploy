job('Terraform Environment') {
  description 'Terraform Destroy Environment.'
  scm {
    github ('ONSDigital/eq-terraform', 'master')
  }
  triggers {
       githubPush()
  }
  steps {
    shell('cd survey-runner')
    shell('terraform remote config -backend=S3 -backend-config="bucket=jenkins-ci-production-terraform-state" -backend-config="key=jenkins" -backend-config="region=eu-west-1"')
    shell('terraform remote pull')
    shell('terraform destroy --force -var "env=prod"')
    shell('terraform remote push')
  }
}
