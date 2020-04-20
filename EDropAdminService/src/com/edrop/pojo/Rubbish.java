/**
 * @Title: Rubbish.java
 * @Package com.edrop.pojo
 * @Description: 
 * @author 13071
 * @date 2019年11月28日
 * @version V1.0
 */
package com.edrop.pojo;

/**
 * 垃圾表
 * @ClassName: Rubbish
 * @Description: 
 * @author 13071
 * @date 2019年11月28日
 *
 */
public class Rubbish {
	private Integer id;
	private String name;
	private Integer typeId;
	private Type type;
	public Type getType() {
		return type;
	}
	public Rubbish(Integer id, String name, Integer typeId, Type type) {
		super();
		this.id = id;
		this.name = name;
		this.typeId = typeId;
		this.type = type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	@Override
	public String toString() {
		return "Rubbish [id=" + id + ", name=" + name + ", typeId=" + typeId + ", type=" + type + "]";
	}
	public Rubbish() {
		super();
		// TODO Auto-generated constructor stub
	}
}
