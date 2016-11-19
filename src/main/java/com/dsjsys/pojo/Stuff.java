package com.dsjsys.pojo;

public class Stuff {
    private Long id;

    private Long deptId;

    private String name;

    private String tele;

    private String phone;

    private String password;

    private Integer locked;

    private Long roleId;

	private Deptment deptment;
    
    private Role role;
    
    public Deptment getDeptment() {
  		return deptment;
  	}

  	public void setDeptment(Deptment deptment) {
  		this.deptment = deptment;
  	}

  	public Role getRole() {
  		return role;
  	}

  	public void setRole(Role role) {
  		this.role = role;
  	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele == null ? null : tele.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}