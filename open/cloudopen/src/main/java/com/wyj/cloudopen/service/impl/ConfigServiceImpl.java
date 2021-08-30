package com.wyj.cloudopen.service.impl;

import com.wyj.cloudopen.entity.Config;
import com.wyj.cloudopen.mapper.ConfigMapper;
import com.wyj.cloudopen.service.IConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WYJ
 * @since 2021-08-30
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

}
