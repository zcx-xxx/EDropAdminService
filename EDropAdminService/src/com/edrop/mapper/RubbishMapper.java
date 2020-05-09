/**
 * @Title: RubbishMapper.java
 * @Package com.edrop.mapper
 * @Description: 
 * @author 13071
 * @date 2019年12月1日
 * @version V1.0
 */
package com.edrop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.edrop.pojo.Rubbish;

/**
 * @ClassName: RubbishMapper
 * @Description: 
 * @author 13071
 * @date 2019年12月1日
 *
 */
public interface RubbishMapper {
	//通过名字对垃圾进行模糊查询
	public List<Rubbish> selRubbishByName(String name);

	public List<Rubbish> selectAllRubbish(@Param("name")String name, @Param("typeId")Integer typeId);

	public void insertRubbish(@Param("name")String name, @Param("typeId")Integer typeId);

	public Rubbish selRubbishById(Integer id);
}
