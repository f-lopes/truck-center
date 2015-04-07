package com.ingesup.truckcenter.service.impl;

import com.ingesup.truckcenter.model.Truck;
import com.ingesup.truckcenter.repository.BaseRepository;
import com.ingesup.truckcenter.repository.TruckRepository;
import com.ingesup.truckcenter.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lopes_f on 3/23/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class TruckServiceImpl extends BaseServiceImpl<Truck, String> implements TruckService {

	private final TruckRepository truckRepository;

	@Autowired
	public TruckServiceImpl(TruckRepository truckRepository) {
		this.truckRepository = truckRepository;
	}

	@Override
	public BaseRepository<Truck, String> getRepository() {
		return this.truckRepository;
	}
}
