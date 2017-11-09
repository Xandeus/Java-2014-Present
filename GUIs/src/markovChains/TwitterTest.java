package markovChains;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterTest {
	public static void main(String[] args) throws TwitterException {
		Twitter twit = new TwitterFactory().getSingleton();
		Status status = twit.updateStatus("butt");
		System.out.println("Successfully updated the status to [" + status.getText() + "].");
	}
}
