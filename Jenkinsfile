pipeline {
   agent any

  stages {
     stage('Compile') {
     steps {
        sh(script: 'mvn compile')
        echo 'Compile...'
     }
   }
    stage('Unit Test') {
     steps {
        sh(script: 'mvn test')
	//sh(script: 'mvn package')
	 junit 'target/surefire-reports/*.xml'

        echo 'Unit Test...'
     }
   } 
    stage('Code Quality') {
     steps {	    	    
           withSonarQubeEnv('sonarqube') {
		     sh """ 		        
                        mvn sonar:sonar \
                        -Dsonar.projectKey=sonar \
                        -Dsonar.host.url=http://65.2.108.33:9000 \
                         
                       """ 
		        } 
        
        echo 'Code Quality...'
	}
   }
    stage('Artifact Push') {
     steps {	          
	   //  sh(script: 'mvn  -version')
             sh(script: 'mvn   deploy')
	     
        echo 'Artifact Push...'
     }
   }
   stage('Deploy to dev') {
	      steps{
		      
		   script {
               sshagent (credentials:['deployserver']) { 
                sh 'ssh -o StrictHostKeyChecking=no ec2-user@172.31.9.220 "killall -9 java; rm -rf gs-spring-boot-1.0.1.jar; ls -ltr; ps -ef |grep java; wget http://65.1.231.149:8081/repository/spring-boot1/org/springframework/gs-spring-boot/1.0.1/gs-spring-boot-1.0.1.jar; "'		
                sh 'ssh -o StrictHostKeyChecking=no ec2-user@172.31.9.220 "pwd; ls -ltr; java -jar gs-spring-boot-1.0.1.jar 2>> /dev/null >> /dev/null &"; sleep 10; ps -ef |grep java'
		   }                 
	      } 
		       sh 'curl http://65.2.108.33:8080'	  
                       echo 'Deploy to dev...'
        }
   }
    stage('Deploy to QA') {
     steps {       			       	       
        echo 'Deploy to QA...'
		     }
   } 
    stage('Deploy to Production') {
     steps {	     
				    
        echo 'Deploy to Production...'
     }
   }  
  }
}
