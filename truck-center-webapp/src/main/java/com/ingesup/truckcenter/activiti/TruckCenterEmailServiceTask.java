package com.ingesup.truckcenter.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by lopes_f on 4/7/2015.
 * <florian.lopes@outlook.com>
 */
public class TruckCenterEmailServiceTask implements JavaDelegate {

	private static final Logger logger = LogManager.getLogger(TruckCenterEmailServiceTask.class);

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		final String driverId = (String) execution.getVariable(ActivitiConstants.DRIVER_ID);

		if (logger.isInfoEnabled()) {
			this.logger.info(String.format("Sending mail to ... for driver %s", driverId));
		}
	}
}
