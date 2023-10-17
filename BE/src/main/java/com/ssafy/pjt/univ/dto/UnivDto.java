package com.ssafy.pjt.univ.dto;

public class UnivDto {
	private String type;
	private int no; // pk
	private String name;
	private String univtype;
	private String sidoname;
	private String addr;
	private int zip; // 우편번호
	private String homepage;
	private double lat;
	private double lng;

	public UnivDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UnivDto(String type, int no, String name, String univtype, String sidoname, String addr, int zip,
			String homepage, double lat, double lng) {
		super();
		this.type = type;
		this.no = no;
		this.name = name;
		this.univtype = univtype;
		this.sidoname = sidoname;
		this.addr = addr;
		this.zip = zip;
		this.homepage = homepage;
		this.lat = lat;
		this.lng = lng;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnivtype() {
		return univtype;
	}

	public void setUnivtype(String univtype) {
		this.univtype = univtype;
	}

	public String getSidoname() {
		return sidoname;
	}

	public void setSidoname(String sidoname) {
		this.sidoname = sidoname;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "UnivDto [type=" + type + ", no=" + no + ", name=" + name + ", univtype=" + univtype + ", sidoname="
				+ sidoname + ", addr=" + addr + ", zip=" + zip + ", homepage=" + homepage + ", lat=" + lat + ", lng="
				+ lng + "]";
	}

}
