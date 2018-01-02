def pckg=""
def lambda_pipeline=load './pipelines/lambdas.groovy'
def uiweb_pipeline=load './pipelines/ui-web.groovy'
def setUp(){
  stage('setUp'){
  def me =sh (script:'git diff HEAD^ HEAD --name-only',returnStdout:true).trim().split('/')
  pckg= me.length>1?me[1]:'full'
  
  currentBuild.displayName = pckg+currentBuild.displayName
  }
}
def start(){
  if(pckg=='full'){
    print 'full'
    parallel(
      lambda_pipeline.start(),
      uiweb_pipeline.start()
      )
  }
  else
  {
    print pckg
    lambda_pipeline.start()
  }
}
def tearDown(){
  stage('tearDown'){
  def me =sh (script:'git diff HEAD^ HEAD --name-only',returnStdout:true).trim().split('/')
  def pckg= me.length>1?me[1]:'full'
  
  currentBuild.displayName = pckg+currentBuild.displayName
  }
}
return this
