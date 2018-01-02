def pckg=""
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
    def pipeline=load './pipelines/lambdas.groovy'
    pipeline.start()
  }
  else
  {
    print pckg
    def pipeline=load './pipelines/lambdas.groovy'
    pipeline.start()
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
