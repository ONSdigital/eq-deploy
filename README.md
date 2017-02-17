
`fly -t eq login -c CONCOURSE_URL`

Add Survey Runner Pipeline

`fly -t eq set-pipeline -p eq-survey-runner -c concourse/eq-survey-runner.yml  --load-vars-from concourse/secrets.yml`

`fly -t eq unpause-pipeline --pipeline eq-survey-runner`

Add Author Pipeline

`fly -t eq set-pipeline -p eq-author -c concourse/eq-author.yml  --load-vars-from concourse/secrets.yml`

`fly -t eq unpause-pipeline --pipeline eq-author`