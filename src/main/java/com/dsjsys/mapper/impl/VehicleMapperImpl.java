package com.dsjsys.mapper.impl;

import org.springframework.stereotype.Repository;

import com.dsjsys.mapper.VehicleMapper;
import com.dsjsys.pojo.Vehicle;
import com.dsjsys.tools.core.mapper.impl.BaseMapperImpl;

@Repository
public class VehicleMapperImpl extends BaseMapperImpl<Vehicle,Long> implements VehicleMapper {

}
