package com.ingesup.truckcenter.service.impl;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.security.crypto.codec.Base64;

import com.ingesup.truckcenter.domain.AlertDTO;
import com.ingesup.truckcenter.exception.TruckCenterRestException;
import com.ingesup.truckcenter.service.TruckCenterRestService;

/**
 * Created by lopes_f on 4/2/2015.
 * <florian.lopes@outlook.com>
 */
public class TruckCenterRestServiceImpl implements TruckCenterRestService {

	private HttpClient httpClient;

	private final String restApiURL;
	private final String username;
	private final String password;

	public TruckCenterRestServiceImpl(String restApiURL, String username, String password) {
		this.restApiURL = restApiURL;
		this.username = username;
		this.password = password;
	}

	public void addAlert(AlertDTO alert) throws TruckCenterRestException {
		HttpPost httpPost = new HttpPost(this.restApiURL);
		httpPost.addHeader("accept", "application/json");

		addAuthentication(httpPost);
		
		// TODO HttpEntity (JSON)

		try {
			final HttpResponse httpResponse = getHttpClient().execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() != 201) {
				throw new TruckCenterRestException("Failed to POST to TruckCenter API");
			}
		} catch (IOException e) {
			throw new TruckCenterRestException("Failed to POST to TruckCenter API", e);
		}
	}

	private void addAuthentication(HttpPost httpPost) {
		httpPost.addHeader(buildAuthorizationHeader());
	}

	private Header buildAuthorizationHeader() {
		return new BasicHeader("Authorization", buildAuthorisationHeaderValue());
	}

	private String buildAuthorisationHeaderValue() {
		return String.format("Basic %s", new String(Base64.encode(String.format("%s:%s", this.username, this.password).getBytes())));
	}

	private HttpClient getHttpClient() {
		if (this.httpClient == null) {
			this.httpClient = new DefaultHttpClient();
		}

		return this.httpClient;
	}
}
