package com.ingesup.truckcenter.esper.subscriber;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.ingesup.truckcenter.domain.AlertDTO;
import com.ingesup.truckcenter.domain.PositionNotification;
import com.ingesup.truckcenter.exception.TruckCenterRestException;
import com.ingesup.truckcenter.service.TruckCenterRestService;

import java.util.Date;
import java.util.Map;

public class PositionNotificationEventSubscriber {

	private final String eplStatementQuery;

	private final TruckCenterRestService truckCenterRestService;

	private EPServiceProvider epServiceProvider;
	private EPStatement alertsStatement;

	public PositionNotificationEventSubscriber(String eplStatementQuery, TruckCenterRestService truckCenterRestService) {
		this.eplStatementQuery = eplStatementQuery;
		this.truckCenterRestService = truckCenterRestService;
		initEsper();
	}

	private void initEsper() {
		final Configuration configuration = new Configuration();
		configuration.addEventType(PositionNotification.class);

		this.epServiceProvider = EPServiceProviderManager.getDefaultProvider(configuration);

		this.alertsStatement = this.epServiceProvider.getEPAdministrator().createEPL(this.eplStatementQuery);
		this.alertsStatement.setSubscriber(this);
	}
	
	public void update(Map<String, Object> events) {
		final PositionNotification positionNotification = ((PositionNotification) events.get("stream_0"));

		final AlertDTO alertDTO = new AlertDTO(new Date(), positionNotification.getDriverId());

		try {
			this.truckCenterRestService.addAlert(alertDTO);
		} catch (TruckCenterRestException e) {
			// TODO Handle exception
			e.printStackTrace();
		}
	}

}