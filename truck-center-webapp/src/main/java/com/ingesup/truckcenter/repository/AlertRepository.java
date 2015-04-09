package com.ingesup.truckcenter.repository;

import com.ingesup.truckcenter.model.Alert;
import com.ingesup.truckcenter.model.Driver;

import java.util.List;

/**
 * Created by lopes_f on 3/29/2015.
 * <florian.lopes@outlook.com>
 */
public interface AlertRepository extends BaseRepository<Alert, String> {

	Alert findFirstByDriver(Driver driver);

	List<Alert> findByDriver(Driver driver);

	List<Alert> findByResolved(boolean resolved);
}
