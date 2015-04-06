package com.ingesup.truckcenter.http;

import org.apache.http.HttpHost;
import org.apache.http.client.AuthCache;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;

import java.net.URI;

/**
 *
 * Created by lopes_f on 3/25/2015.
 * <florian.lopes@outlook.com>
 */
public class HttpComponentsClientHttpRequestFactoryBasicAuth extends HttpComponentsAsyncClientHttpRequestFactory {

	private final HttpHost host;

	public HttpComponentsClientHttpRequestFactoryBasicAuth(HttpHost httpHost) {
		super();
		this.host = httpHost;
	}

	@Override
	protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {
		AuthCache authCache = new BasicAuthCache();
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(this.host, basicAuth);

		BasicHttpContext localContext = new BasicHttpContext();
		localContext.setAttribute(HttpClientContext.AUTH_CACHE, authCache);

		return localContext;
	}
}
