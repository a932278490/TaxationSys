package com.dsjsys.mapper.impl;

import org.springframework.stereotype.Repository;

import com.dsjsys.mapper.MessageMapper;
import com.dsjsys.pojo.Message;
import com.dsjsys.tools.core.mapper.impl.BaseMapperImpl;
@Repository
public class MessageMapperImpl extends BaseMapperImpl<Message,Long> implements MessageMapper {

}
