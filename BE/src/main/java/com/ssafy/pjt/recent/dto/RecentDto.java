package com.ssafy.pjt.recent.dto;

public class RecentDto {
	private int no; // pk
	private String id; // fk
	private String sidoname;
	private String name; // 집 이름
	private String dong;
	private String jibun;
	private String type; // 월세 or 전세
	private int wal; // 월세
	private String jeon; // 전세
	private String floor; // 층
	private double area; // 전용면적
	private int img; // 사진 번호
	private int construction; // 건축연도

	public RecentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecentDto(int no, String id, String sidoname, String name, String dong, String jibun, String type, int wal,
			String jeon, String floor, double area, int img, int construction) {
		super();
		this.no = no;
		this.id = id;
		this.sidoname = sidoname;
		this.name = name;
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

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getsidoname() {
		return sidoname;
	}

	public void setsidoname(String sidoname) {
		this.sidoname = sidoname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return "RecentDto [no=" + no + ", id=" + id + ", sidoname=" + sidoname + ", name=" + name + ", dong=" + dong
				+ ", jibun=" + jibun + ", type=" + type + ", wal=" + wal + ", jeon=" + jeon + ", floor=" + floor
				+ ", area=" + area + ", img=" + img + ", construction=" + construction + "]";
	}

}
