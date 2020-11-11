This repo holds the Concourse build and deployment pipeline for the EQ products

## Prerequesits

* [Fly](https://concourse-ci.org/)


## Installation and setup

1. If you haven't done so already, install [Fly](https://concourse-ci.org/). The easiest way to do this is through [Homebrew](https://brew.sh/):

`➜ brew cask install fly`

2. Sync your Fly version with Concourse, which is currently version 3.3.3:

`➜ fly sync -c CONCOURSE_URL`

3. Login to Concourse:

`➜ fly -t eq login -c CONCOURSE_URL`

This command will supply a URL and ask you to visit it in order to login. The login uses your GitHub account.

## Notes

To apply the `eq.yml` pipleine you will need to create a valid secrets file (`secrets.yml`) in the same format as the example file (`secrets.yml.example`)

Once logged in you are able to add/update the build plans with the commands below.

`fly -t eq set-pipeline -p eq -c eq.yml  --load-vars-from secrets.yml`

`fly -t eq set-pipeline -p eq-deploy -c deploy.yml  --load-vars-from secrets.yml`

When updating a build plan, a diff is shown for you to confirm the changes you are making.
