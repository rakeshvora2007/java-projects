package search;

import twitter4j.IDs;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class TwitterSearch {
	private static Connection con = null;
	private static Statement statement = null;
	private static Twitter twitter = null;
	private static PreparedStatement preparedStatementForStoringUserTweets;

	public static void getAuthentication() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey("***")
				.setOAuthConsumerSecret("***")
				.setOAuthAccessToken("***")
				.setOAuthAccessTokenSecret("***");
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
	}

	public static void searchTweets() throws TwitterException {
		Query query = new Query("trump");
		query.count(100);
		query.lang("en");
		QueryResult result = twitter.search(query);
		// int count=0;
		FileWriter file = null;
		try {
			file = new FileWriter("C:/Mehul/Projects/Twitter/tweets.json");
			file.write("");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		for (Status status : result.getTweets()) {
			// System.out.println("@" + status.getUser().getScreenName() + ":" +
			// status.getText());
			// count+=1;
			JsonObject model = Json.createObjectBuilder().add("username", status.getUser().getScreenName())
					.add("Tweet", status.getText()).build();
			try {
				file.append(model.toString());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		try {
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(count);
	}

	public static void timelineTweets() throws TwitterException {
		// First param of Paging() is the page number, second is the number per
		// page (this is capped around 200 I think.
		Paging paging = new Paging(1, 200);
		System.out.println(paging.getMaxId());
		System.out.println(paging.getSinceId());
		List<Status> statuses = twitter.getUserTimeline("McLeanHealth", paging);
		System.out.println(paging.getMaxId());
		System.out.println(paging.getSinceId());
		long oldMaxID = paging.getMaxId();
		FileWriter file = null;
		try {
			file = new FileWriter("C:/Mehul/Projects/Twitter/usertweets.json");
			file.write("");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println("Size" + statuses.size());
		IDs ids = twitter.getFollowersIDs("McLeanHealth", -1);
		long[] idlist = ids.getIDs();
		System.out.println(idlist.length);
		for (Status status : statuses) {
			JsonObject model = Json.createObjectBuilder().add("username", status.getUser().getScreenName())
					.add("Tweet", status.getText()).add("isRetweet", status.isRetweet()).add("statusID", status.getId())
					.add("userId", status.getUser().getId()).add("followers", status.getUser().getFollowersCount())
					.add("timestamp", status.getCreatedAt().getTime()).build();
			try {
				file.append(model.toString());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void retrieveAllTweets() throws TwitterException {
		int count = 0;
		FileWriter file = null;
		Paging paging = new Paging();
		paging.setCount(200);
		try {
			file = new FileWriter("C:/Mehul/Projects/Twitter/usertweets.json");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		while (count <= 5000) {
			List<Status> statuses = twitter.getUserTimeline("BarackObama", paging);
			long earliestStatusId = 0L;
			for (Status status : statuses) {
				earliestStatusId = status.getId();
				count++;
				String textOfTweet = status.getText();
				String userScreenName = status.getUser().getScreenName();
				String isRetweet = status.isRetweet() + "";
				String statusId = status.getId() + "";
				String userId = status.getUser().getId() + "";
				String followers = status.getUser().getFollowersCount() + "";
				int retweetCount = status.getRetweetCount();
				java.sql.Timestamp timestamp = new java.sql.Timestamp(status.getCreatedAt().getTime());
				JsonObject model = Json.createObjectBuilder().add("ID", count).add("TEXT_OF_TWEET", textOfTweet)
						.add("USER_SCREEN_NAME", userScreenName).add("FOLLOWERS", followers)
						.add("IS_RETWEET", isRetweet).add("STATUS_ID", statusId).add("USER_ID", userId)
						.add("TIMESTAMP", timestamp.toString()).build();
				try {
					file.append(model.toString());
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			if (earliestStatusId - 1 != -1) {
				paging.setMaxId(earliestStatusId - 1);
			} else {
				break;
			}
		}
		System.out.println("Count = " + count);
		try {
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void retrieveAllTweetsToTable(String twitterAccountName) throws TwitterException {
		int count = 0;
		Paging paging = new Paging();
		paging.setCount(200);
		connect();
		while (count <= 5000) {
			List<Status> statuses = twitter.getUserTimeline(twitterAccountName, paging);
			long earliestStatusId = 0L;
			for (Status status : statuses) {
				earliestStatusId = status.getId();
				count++;
				insertToDatabase(status, count);
			}
			if (earliestStatusId - 1 != -1) {
				paging.setMaxId(earliestStatusId - 1);
			} else {
				break;
			}
		}
		System.out.println(count);
		disconnect();
	}

	public static void connect() {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "ids515";
		String userName = "root";
		String password = "****";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
			con.setAutoCommit(false);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void disconnect() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void insertToDatabase(Status status, int count) {
		String queryForStoringUserTweets = "insert into ids515.tweetsuser_10_21_2016 " + "(" + "id, " + "textOfTweet, "
				+ "userScreenName, " + "isRetweet, " + "followers, " + "statusId, " + "userId, " + "timestamp, "
				+ "retweetCount" + ") " + "values" + "(?,?,?,?,?,?,?,?,?)";
		try {
			preparedStatementForStoringUserTweets = con.prepareStatement(queryForStoringUserTweets);
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String textOfTweet = status.getText();
		String userScreenName = status.getUser().getScreenName();
		String isRetweet = status.isRetweet() + "";
		String statusId = status.getId() + "";
		String userId = status.getUser().getId() + "";
		String followers = status.getUser().getFollowersCount() + "";
		int retweetCount = status.getRetweetCount();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(status.getCreatedAt().getTime());
		String textOfTweetStrippedOfAllNonAlphaNumericCharacters = null;
		textOfTweetStrippedOfAllNonAlphaNumericCharacters = textOfTweet.replaceAll("[^\\x00-\\x7F]", " ");
		try {
			preparedStatementForStoringUserTweets.setInt(1, count);
			preparedStatementForStoringUserTweets.setString(2, textOfTweetStrippedOfAllNonAlphaNumericCharacters);
			preparedStatementForStoringUserTweets.setString(3, userScreenName);
			preparedStatementForStoringUserTweets.setString(4, isRetweet);
			preparedStatementForStoringUserTweets.setString(5, followers);
			preparedStatementForStoringUserTweets.setString(6, statusId);
			preparedStatementForStoringUserTweets.setString(7, userId);
			preparedStatementForStoringUserTweets.setTimestamp(8, timestamp);
			preparedStatementForStoringUserTweets.setInt(9, retweetCount);
			preparedStatementForStoringUserTweets.executeUpdate();
			con.commit();
			preparedStatementForStoringUserTweets.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getFollowerCount(String twitterAccountName) throws TwitterException {
		Paging paging = new Paging();
		paging.setCount(200);
		IDs followerIDs = twitter.getFollowersIDs(twitterAccountName, -1);
		long[] ids = followerIDs.getIDs();
		int followerCount = ids.length;
		java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
		connect();
		String queryForStoringFollowerCount = "INSERT INTO ids515.tab_follower_count(timestamp, user_screen_name, follower_count) VALUES (?,?,?)";
		try {
			PreparedStatement preparedStatementForStoringFollowerCount = con.prepareStatement(queryForStoringFollowerCount);
			con.commit();
			preparedStatementForStoringFollowerCount.setTimestamp(1, timestamp);
			preparedStatementForStoringFollowerCount.setString(2, twitterAccountName);
			preparedStatementForStoringFollowerCount.setInt(3, followerCount);
			preparedStatementForStoringFollowerCount.executeUpdate();
			con.commit();
			preparedStatementForStoringFollowerCount.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();
	}
}