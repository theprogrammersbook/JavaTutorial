package com.muthyatechnology.rest.client;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

/**
 * Tests connecting to a HTTPS server that does not require a client
 * certificate.
 * 
 */
public class HttpsSSLClientGetAuthTokenTest {
	/* Constant(s): */
	/** URL which to send test-request to. */
	private final static String TEST_ENDPOINT_URL = "https://172.31.99.218:8443/MuthyaApp/api/v2/users/auth";
	/** Client truststore. */
	private static final String CLIENT_TRUSTSTORE = "D:/client_truststore.jks";
	/** Client truststore password. */
	private static final String CLIENT_TRUSTSTORE_PASSWORD = "secret";

	/* Instance variable(s): */
	private static DefaultHttpClient mHttpClient;

	public static void main(String[] args) throws Exception {
		mHttpClient = new DefaultHttpClient();
		FileInputStream instream = new FileInputStream(new File(CLIENT_TRUSTSTORE));
		/* Load client truststore. */
		final KeyStore theClientTruststore = KeyStore.getInstance("JKS");
		theClientTruststore.load(instream, CLIENT_TRUSTSTORE_PASSWORD.toCharArray());

		/* Create a trust manager factory using the client truststore. */
		final TrustManagerFactory theTrustManagerFactory = TrustManagerFactory
				.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		theTrustManagerFactory.init(theClientTruststore);

		/*
		 * Create a SSL context with a trust manager that uses the client
		 * truststore.
		 */
		final SSLContext theSslContext = SSLContext.getInstance("TLS");
		theSslContext.init(null, theTrustManagerFactory.getTrustManagers(), null);

		/*
		 * Create a SSL socket factory that uses the client truststore SSL
		 * context and that does not perform any kind of hostname verification.
		 * IMPORTANT: Hostname verification should be performed in a production
		 * environment! To turn on hostname verification, change the
		 * ALLOW_ALL_HOSTNAME_VERIFIER below to STRICT_HOSTNAME_VERIFIER.
		 */
		final SSLSocketFactory theSslSocketFactory = new SSLSocketFactory(theSslContext,
				SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		/*
		 * Register the SSL socket factory to be used with HTTPS connections
		 * with the HTTP client. A {@code Scheme} object is used to associate
		 * the protocol scheme, such as HTTPS in this case, and a socket
		 * factory.
		 */
		final Scheme theHttpsScheme = new Scheme("https", 443, theSslSocketFactory);
		mHttpClient.getConnectionManager().getSchemeRegistry().register(theHttpsScheme);
		HttpPost httppost = new HttpPost(TEST_ENDPOINT_URL);
		httppost.addHeader("test-header-name", "test-header-value");
		httppost.addHeader("authkey", "wrwfdgefetwrfwjrlrjkwljk242l4kjrjksklfkjgwel;rjkwt;l32rjjfslfksd");
		System.out.println("executing request" + httppost.getRequestLine());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("UserName", "admin");
		jsonObject.put("Password", "admin");
		StringEntity engity = new StringEntity(jsonObject.toJSONString());
		httppost.setEntity(engity);
		CloseableHttpResponse response = mHttpClient.execute(httppost);
		try {
			HttpEntity entity = response.getEntity();

			System.out.println("----------------------------------------");
			// System.out.println(response);
			if (entity != null) {
				System.out.println("Response content length: " + entity.getContentLength());
			}
			String responseString = EntityUtils.toString(entity, "UTF-8");
			System.out.println(responseString);
		} finally {
			response.close();
		}
	}

}