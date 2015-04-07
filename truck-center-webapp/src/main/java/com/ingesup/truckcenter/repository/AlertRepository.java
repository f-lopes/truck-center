package com.ingesup.truckcenter.repository;

import com.ingesup.truckcenter.model.Alert;
import com.ingesup.truckcenter.model.Driver;

/**
 * Created by lopes_f on 3/29/2015.
 * <florian.lopes@outlook.com>
 */
public interface AlertRepository extends BaseRepository<Alert, String> {

	Alert findFirstByDriver(Driver driver);
}
