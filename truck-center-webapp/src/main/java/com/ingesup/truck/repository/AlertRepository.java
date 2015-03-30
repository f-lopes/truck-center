package com.ingesup.truck.repository;

import com.ingesup.truck_center.model.Alert;
import com.ingesup.truck_center.model.Driver;

/**
 * Created by lopes_f on 3/29/2015.
 * <florian.lopes@outlook.com>
 */
public interface AlertRepository extends BaseRepository<Alert, Integer> {

	Alert findFirstByDriver(Driver driver);
}
