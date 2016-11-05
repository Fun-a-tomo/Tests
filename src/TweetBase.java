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

import twitter4j.IDs;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public class TweetBase implements Runnable {
	static String path = "src/SelfyTweet.txt";
	static int prob = 100;
	static Twitter twi;
	User user;
	Status stat;
	Calendar now;
	Random ran;
	int i;
	List<String> selfy = new ArrayList<String>();
	long sleeptime = 100000L;


	public TweetBase()throws TwitterException, IOException {
		twi = new TwitterFactory().getInstance();
		user = twi.verifyCredentials();
		now = Calendar.getInstance();
		ran = new Random(now.getTimeInMillis());
		InputSelfy();
		Following();
		}

	//定期イベントごとを記載
	@Override
	public void run() {

		int follow_count = 0;

		// TODO 自動生成されたメソッド・スタブ
		while(true){
			//SelfTweet
			if(RandomResult(1)){
				SelfyTweet();
			}

			System.out.println("Random Result = " + i);
			try {
				//Re-Follow
				if(follow_count>11){
					Following();
					follow_count = 0;
				}

				Thread.sleep(sleeptime);
				follow_count++;
			} catch (InterruptedException | TwitterException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	};

	public void Tweet(String twit){
		try {
			System.out.println("Tweet ------::"+twit);
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
		BufferedReader buf = new BufferedReader(new InputStreamReader(io,"UTF-8"));
		while((line = buf.readLine()) != null){
			this.selfy.add(line);
		}
		buf.close();
		io.close();
	}

	protected boolean RandomResult(int k){
		i = Math.abs(ran.nextInt());
		if(i%(prob/k)==0)return true;
		else return false;
	}

	private void Following() throws TwitterException{
		List<Long> frends = getFrendIds();
		List<Long> follower = getFollowerIds();
		List<Long> notFollowIdList = calcNotFollow(follower, frends);
		//System.out.println("DoFollowTarget : "+notFollowIdList.size());
		if(!notFollowIdList.isEmpty())doFollow(notFollowIdList);
	}



	protected static void doFollow(List<Long> notFollowIdList) throws TwitterException {
		for (Long userId : notFollowIdList) {
			User user = twi.createFriendship(userId);
			if(user == null) {
			//	throw new TwitterException("フォローに失敗しました。対象ID：" + userId);
			}
			//System.out.println("フォローしました。対象：" + user.getName());
		}
	}

	protected static List<Long> calcNotFollow(List<Long> follower,List<Long> frends) {
		List<Long> returnValue = new ArrayList<Long>();
		for(long id : follower) {
			//System.out.println("Followers : "+id);
			if(!contains(frends, id)) {
				returnValue.add(id);
			}
		}
		return returnValue;
	}

	private static boolean contains(List<Long> frends, long id) {

		for(long frendId : frends) {
			//System.out.println("Friend : "+frendId);
			if(frendId == id) {
				//System.out.println("---------TRUE---------");
				return true;
			}
		}
		//System.out.println("---------False---------");
		return false;
	}

	protected static List<Long> getFollowerIds() throws TwitterException {
		List<Long> result = new ArrayList<Long>();
		long cursor = -1L;
		while(true) {
		  IDs followers = twi.getFollowersIDs(cursor);
		  long[] ids = followers.getIDs();
		  if(0 == ids.length) break;
		  for(int i = 0;i < ids.length; i++) {
		    result.add(ids[i]);
		  }
		  cursor = followers.getNextCursor();
		}
		return result;
	}

	protected static List<Long> getFrendIds() throws TwitterException {
		List<Long> result = new ArrayList<Long>();
		long cursor = -1L;
		while(true) {
		  IDs followers = twi.getFriendsIDs(cursor);
		  long[] ids = followers.getIDs();
		  if(0 == ids.length) break;
		  for(int i = 0;i < ids.length; i++) {
		    result.add(ids[i]);
		  }
		  cursor = followers.getNextCursor();
		}
		return result;
	}
}

