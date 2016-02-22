job('Terraform Environment') {
  description 'Terraform Environment.'
  scm {
    github ('ONSDigital/eq-terraform', 'master')
  }
  triggers {
       githubPush()
  }
  steps {
    shell('/opt/go/bin/terraform remote config -backend=S3 -backend-config="bucket=jenkins-ci-production-terraform-state" -backend-config="key=jenkins" -backend-config="region=eu-west-1"')
    shell('/opt/go/bin/terraform remote pull')
    shell('/opt/go/bin/terraform apply -var "env=prod"')
    shell('/opt/go/bin/terraform remote push')
  }
}
