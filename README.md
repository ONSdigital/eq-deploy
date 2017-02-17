This repo holds the Concourse build and deployment pipeline for the EQ products

The following command can be used to login to Concourse

`fly -t eq login -c CONCOURSE_URL`

To apply the `eq.yml` pipleine you will need to create a valid secrets file (`secrets.yml`) in the same format as the example file (`secrets.yml.example`)

Once logged in you are able to add/update the build plan with the command below.

`fly -t eq set-pipeline -p eq -c eq.yml  --load-vars-from secrets.yml`

When updating a build plan, a diff is shown for you to confirm the changes you are making.