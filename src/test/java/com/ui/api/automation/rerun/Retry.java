
package com.ui.api.automation.rerun;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	private int minRetry;
	private int maxRetry;

	public Retry() { // TODO Auto-generated constructor stub this.minRetry = 0;
		this.maxRetry = 1;
	}

	@Override
	public boolean retry(ITestResult result) {
		boolean flag = false;
		if (!result.isSuccess() && this.minRetry < this.maxRetry) {
			this.minRetry++;
			flag = true;
		}
		return flag;
	}

}
