package com.itsol.train.mock.dao.impl;

import com.itsol.train.mock.constants.AppConstants;
import com.itsol.train.mock.dao.AbstractBaseDAO;
import com.itsol.train.mock.dao.UserDAO;
import com.itsol.train.mock.dto.UserDto;
import com.itsol.train.mock.dto.UserSearchDto;
import com.itsol.train.mock.mapper.UserNotActiveMapper;
import com.itsol.train.mock.util.SqlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDAOImpl extends AbstractBaseDAO implements UserDAO {
    private Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    @Override
    public List<UserDto> findAllUserNotActive1() {
        logger.trace("DAO to get all user not active");
        String sql = getSqlQueryById(AppConstants.SQL_MODULE_USER, "user-not-active");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("p_status", "0");
        return getNamedParameterJdbcTemplate().query(sql,parameters, BeanPropertyRowMapper.newInstance(UserDto.class));
    }

    @Override
    public List<UserDto> findAllUserNotActive2(UserSearchDto userSearchDto) {
        logger.trace("DAO to get all user not active");
        String sql = getSqlQueryById(AppConstants.SQL_MODULE_USER, "user-not-active");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("p_status", "0");
        long totalRecords = countTotalRecords(sql, parameters);
        userSearchDto.setTotalRecords(totalRecords);
        return queryPaging(userSearchDto, sql, parameters, new UserNotActiveMapper());
    }
}
