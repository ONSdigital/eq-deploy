job('Deploy EQ Submitter') {
  description 'Deploy the EQ Submitter application'
  scm {
    github 'ONSDigital/eq-submitter'
  }
  triggers {
       githubPush()
  }
  steps {
      remoteShell('ubuntu@pre-prod-submitter-1.eq.ons.digital:22') {
        command('wget -O eq-submitter.zip https://github.com/ONSdigital/eq-submitter/archive/master.zip')
        command('unzip -o eq-submitter.zip')
        command('mv eq-submitter-master eq-submitter')
        command('cd eq-submitter')
        command('sudo pip install -r requirements.txt')
        command('sudo service supervisor restart')
      }
       remoteShell('ubuntu@pre-prod-submitter-2.eq.ons.digital:22') {
        command('wget -O eq-submitter.zip https://github.com/ONSdigital/eq-submitter/archive/master.zip')
        command('unzip -o eq-submitter.zip')
        command('mv eq-submitter-master eq-submitter')
        command('cd eq-submitter')
        command('sudo pip install -r requirements.txt')
        command('sudo service supervisor restart')
      }
  }
}
