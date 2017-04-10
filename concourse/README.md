
`fly -t eq login -n ONSdigital -c CONCOURSE_URL`

`fly -t eq set-pipeline -p eq-survey-runner -c concourse/eq-survey-runner.yml  --load-vars-from concourse/secrets.yml`

`fly -t eq unpause-pipeline --pipeline eq-survey-runner`