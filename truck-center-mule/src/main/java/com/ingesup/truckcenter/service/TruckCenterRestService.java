package com.ingesup.truckcenter.service;

import com.ingesup.truckcenter.domain.AlertDTO;
import com.ingesup.truckcenter.exception.TruckCenterRestException;

/**
 * Created by lopes_f on 4/2/2015.
 * <florian.lopes@outlook.com>
 */
public interface TruckCenterRestService {

	void addAlert(AlertDTO alert) throws TruckCenterRestException;
}
