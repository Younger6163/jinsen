package jinshen.bean;

public class monthtree {
	private double codeid;
	private double cutNum;//采伐证号
	private double checkNum;
	private String entrust;//委托人
	private String getPerson;//中标人
	private double salary;
	private double syear;
	private double smonth;
	private double sday;
	private String takeIn;//收货单位
	private double statement;
	private String treetype;
	private Double unitprice;
	private String forperson;//劳务承包人
	private String shipplace;//采伐地点
	
	public double getCodeid() {
		return codeid;
	}
	public void setCodeid(double codeid) {
		this.codeid = codeid;
	}
	
	public double getCutNum() {
		return cutNum;
	}
	public void setCutNum(double cutNum) {
		this.cutNum = cutNum;
	}
	public double getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(double checkNum) {
		this.checkNum = checkNum;
	}
	
	public String getEntrust() {
		return entrust;
	}
	public void setEntrust(String entrust) {
		this.entrust = entrust;
	}
	public String getGetPerson() {
		return getPerson;
	}
	public void setGetPerson(String getPerson) {
		this.getPerson = getPerson;
	}
	
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public double getSyear() {
		return syear;
	}
	public void setSyear(Double syear) {
		this.syear = syear;
	}
	public double getSmonth() {
		return smonth;
	}
	public void setSmonth(Double smonth) {
		this.smonth = smonth;
	}
	public double getSday() {
		return sday;
	}
	public void setSday(Double sday) {
		this.sday = sday;
	}
	public String getTakein() {
		return takeIn;
	}
	public void setTakein(String takeIn) {
		this.takeIn = takeIn;
	}
	public double getStatement() {
		return statement;
	}
	public void setStatement(Double statement) {
		this.statement = statement;
	}
	public String getTreetype() {
		return treetype;
	}
	public void setTreetype(String treetype) {
		this.treetype = treetype;
	}
	public double getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}
	public String getForperson() {
		return forperson;
	}
	public void setForperson(String forperson) {
		this.forperson = forperson;
	}
	public String getShipplace() {
		return shipplace;
	}
	public void setShipplace(String shipplace) {
		this.shipplace = shipplace;
	}


}
