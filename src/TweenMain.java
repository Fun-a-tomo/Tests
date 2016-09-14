import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public class TweenMain {

	public static void main(String[] args) throws TwitterException {
		// TODO 自動生成されたメソッド・スタブ
		Twitter twi = new TwitterFactory().getInstance();
		User user = twi.verifyCredentials();
		
		Status stat = twi.updateStatus("リハビリしています。");
	}

}
