node('linux') {
  stage ('Poll') {
    checkout([
      $class: 'GitSCM', branches: [[name: '*/main']], extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/rcloneport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [
      string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/rcloneport.git'),
      string(name: 'PORT_DESCRIPTION', value: 'rclone is a command-line tool for syncing files to and from cloud storage'),
      string(name: 'BUILD_LINE', value: 'STABLE')
    ]
  }
}
