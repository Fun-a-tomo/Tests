import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public class TweetBase implements Runnable {
	static String path = "src/SelfyTweet.txt";
	static int prob = 100;
	Twitter twi;
	User user;
	Status stat;
	Calendar now;
	Random ran;
	int i;
	List<String> selfy = new ArrayList<String>();

	public TweetBase()throws TwitterException, IOException {
		twi = new TwitterFactory().getInstance();
		user = twi.verifyCredentials();
		now = Calendar.getInstance();
		ran = new Random(now.getTimeInMillis());
		InputSelfy();
		}

	@Override
	public void run() {
		// TODO 自動生成されたメソッド・スタブ
		while(true){
			i = Math.abs(ran.nextInt());
			if((i%prob)==1){
				SelfyTweet();
			}
			System.out.println("Random Result = " + i);
			try {
				Thread.sleep(1000000);
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	};

	public void Tweet(String twit){
		try {
			stat = twi.updateStatus(twit);
		} catch (TwitterException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


	public void SelfyTweet(){
		int size = selfy.size();
		Tweet(selfy.get(Math.abs(ran.nextInt()%size)));
	}

	private void InputSelfy() throws IOException{
		String line;
		InputStream io = new FileInputStream(new File(path));
		BufferedReader buf = new BufferedReader(new InputStreamReader(io));
		while((line = buf.readLine()) != null){
			this.selfy.add(line);
		}
		buf.close();
		io.close();
	}

}
