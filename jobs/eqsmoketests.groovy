job('Survey Runner Smoke Tests') {
  description 'Runs the Survey Runner smoke tests.'
  scm {
    github('ONSDigital/eq-smoke-test', 'master')
  }
  triggers {
         upstream('Deploy Survey Runner', 'SUCCESS')
  }
  steps {
    shell('export EQ_SURVEYRUNNER=preprod-surveys.eq.ons.digital; cd eq-tests; bundle install; bundle exec rspec')
  }
}
