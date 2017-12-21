def prep(){
  def me =sh (script:'git diff HEAD^ HEAD --name-only',returnStdout:true).trim().split('/')
  def pckg= me.length>1?me[1]:'full'
  
  currentBuild.displayName = pckg+currentBuild.displayName
}
return this
