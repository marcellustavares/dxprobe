import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class Probe {

	protected static CloseableHttpClient getCloseableHttpClient() {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

		httpClientBuilder.useSystemProperties();

		return httpClientBuilder.build();
	}

	public static void main(String[] args) throws IOException {
//		HttpGet httpGet = new HttpGet(
//			"https://analytics-uat.liferay.com/endpoints/uatabe0ea7dbe704de6b30dbb5f22d540e3");

		HttpGet httpGet = new HttpGet(
			"https://analytics-uat.liferay.com/workspace/501108/endpoints/uatabe0ea7dbe704de6b30dbb5f22d540e3");

		try (CloseableHttpClient closeableHttpClient =
				 getCloseableHttpClient()) {

			CloseableHttpResponse closeableHttpResponse =
				closeableHttpClient.execute(httpGet);

			try {
				String response = EntityUtils.toString(
					closeableHttpResponse.getEntity(),
					Charset.defaultCharset());

				System.out.println("response: " + response);
			} catch (Exception exception) {
				System.out.println(
					"Unable to check Analytics Cloud endpoints" +  exception);

				return;
			}
		}
	}
}
