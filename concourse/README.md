
`fly -t eq login -c CONCOURSE_URL`

`fly -t eq set-pipeline -p eq-survey-runner -c eq-survey-runner.yml`

`fly -t eq unpause-pipeline --pipeline eq-survey-runner`