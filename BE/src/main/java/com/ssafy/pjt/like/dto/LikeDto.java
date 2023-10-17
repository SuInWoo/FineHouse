package com.ssafy.pjt.like.dto;

public class LikeDto {
	private int lno; // pk
	private String id; // fk
	private String name; // 집 이름
	private String sidoname;
	private String dong;
	private String jibun;
	private String type; // 월세 or 전세
	private int wal; // 월세
	private String jeon; // 전세
	private String floor; // 층
	private double area; // 전용면적
	private int img;
	private int construction; // 건축연도

	public LikeDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LikeDto(int lno, String id, String name, String sidoname, String dong, String jibun, String type, int wal,
			String jeon, String floor, double area, int img, int construction) {
		super();
		this.lno = lno;
		this.id = id;
		this.name = name;
		this.sidoname = sidoname;
		this.dong = dong;
		this.jibun = jibun;
		this.type = type;
		this.wal = wal;
		this.jeon = jeon;
		this.floor = floor;
		this.area = area;
		this.img = img;
		this.construction = construction;
	}

	public int getLno() {
		return lno;
	}

	public void setLno(int lno) {
		this.lno = lno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSidoname() {
		return sidoname;
	}

	public void setSidoname(String sidoname) {
		this.sidoname = sidoname;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getWal() {
		return wal;
	}

	public void setWal(int wal) {
		this.wal = wal;
	}

	public String getJeon() {
		return jeon;
	}

	public void setJeon(String jeon) {
		this.jeon = jeon;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public int getImg() {
		return img;
	}

	public void setImg(int img) {
		this.img = img;
	}

	public int getConstruction() {
		return construction;
	}

	public void setConstruction(int construction) {
		this.construction = construction;
	}

	@Override
	public String toString() {
		return "LikeDto [lno=" + lno + ", id=" + id + ", name=" + name + ", sidoname=" + sidoname + ", dong=" + dong
				+ ", jibun=" + jibun + ", type=" + type + ", wal=" + wal + ", jeon=" + jeon + ", floor=" + floor
				+ ", area=" + area + ", img=" + img + ", construction=" + construction + "]";
	}

}