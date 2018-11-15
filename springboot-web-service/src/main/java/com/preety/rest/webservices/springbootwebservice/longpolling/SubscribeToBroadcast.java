package com.preety.rest.webservices.springbootwebservice.longpolling;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Create a DeferredResult with a timeout value and a default result to use
 * in case of timeout.
 * @param timeout timeout value in milliseconds (ignored if {@code null})
 * @param timeoutResult the result to use
 */

@RestController
public class SubscribeToBroadcast {

	private static final Object TIMEOUT_RESULT = "Timedout";
	@Autowired
	private BroadcastCounter broadcastCounter;

	@GetMapping(value="/pollBroadcast")
	public DeferredResult<String> ajaxReply(final HttpServletRequest request, final HttpServletResponse response,
			ModelMap mm) throws Exception {
		final DeferredResult<String> dr = new DeferredResult<String>(TimeUnit.MINUTES.toMillis(1), TIMEOUT_RESULT);
		broadcastCounter.addSubscribed(dr);
		return dr;
	}
}
