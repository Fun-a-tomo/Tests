import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class TweenMain {
	static String account = "";
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		boolean FOA = false;
			
			try{
	
			//テストコード試験
			if(FOA){
				getConfigure();
				TweetBase twi = new ExtStreamListener(account);
			}
			else{
				getConfigure();
				ExtStreamListener esl = new ExtStreamListener(account);
				Thread thread = new Thread(esl);
	
				
				TwitterStream twi = new TwitterStreamFactory().getInstance();
				twi.addListener(esl);
				twi.user();
				thread.start();
			}
		}catch(Exception e){
			
		}
	}



	private static void getConfigure(){
		Properties configuration = new Properties();
	    try {
	        InputStream inputStream = new FileInputStream(new File("src/Botty.properties"));
	        configuration.load(inputStream);

	        account = configuration.getProperty("account");
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
	    }


}
