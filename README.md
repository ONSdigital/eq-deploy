
`fly -t eq login -c CONCOURSE_URL`

Add Survey Runner Pipeline

`fly -t eq set-pipeline -p eq-survey-runner -c eq-survey-runner.yml  --load-vars-from secrets.yml`

`fly -t eq unpause-pipeline --pipeline eq-survey-runner`

Add Author Pipeline

`fly -t eq set-pipeline -p eq-author -c eq-author.yml  --load-vars-from secrets.yml`

`fly -t eq unpause-pipeline --pipeline eq-author`