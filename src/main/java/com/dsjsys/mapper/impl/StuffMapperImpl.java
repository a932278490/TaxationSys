package com.dsjsys.mapper.impl;

import org.springframework.stereotype.Repository;

import com.dsjsys.mapper.StuffMapper;
import com.dsjsys.pojo.Stuff;
import com.dsjsys.tools.core.mapper.impl.BaseMapperImpl;
@Repository
public class StuffMapperImpl extends BaseMapperImpl<Stuff,Long> implements StuffMapper {

}