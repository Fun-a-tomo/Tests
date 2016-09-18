import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class TweenMain {
	static String account = "";
	public static void main(String[] args) throws Throwable {
		// TODO 自動生成されたメソッド・スタブ
		boolean FOA = false;

		//テストコード試験
		if(FOA){
			MorphAnal ma = new MorphAnal("ハタケ、生きとったんかワレ");
			ma.MorphAnalResult();
			ma.EndMorphAnal();
		}
		else{
			ExtStreamListener esl = new ExtStreamListener(account);
			Thread thread = new Thread(esl);

			getConfigure();
			TwitterStream twi = new TwitterStreamFactory().getInstance();
			twi.addListener(esl);
			twi.user();
			thread.start();
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
