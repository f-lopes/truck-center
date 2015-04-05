package com.ingesup.truck.service.impl;

import com.ingesup.truck.repository.BaseRepository;
import com.ingesup.truck.repository.TruckRepository;
import com.ingesup.truck.service.TruckService;
import com.ingesup.truck_center.model.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lopes_f on 3/23/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class TruckServiceImpl extends BaseServiceImpl<Truck, Integer> implements TruckService {

	private final TruckRepository truckRepository;

	@Autowired
	public TruckServiceImpl(TruckRepository truckRepository) {
		this.truckRepository = truckRepository;
	}

	@Override
	public BaseRepository<Truck, Integer> getRepository() {
		return this.truckRepository;
	}
}
