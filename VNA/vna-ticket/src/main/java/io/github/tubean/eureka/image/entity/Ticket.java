package io.github.tubean.eureka.image.entity;

public class Ticket {
    private Integer id;
    private Integer price;
    private String flightFrom;
    private String flightTo;
    private String type;
    private Integer date;
    private String classType;

    public Ticket(Integer id, Integer price, String flightFrom,String flightTo,String type,Integer date,String classType) {
        this.id = id;
        this.price = price;
        this.flightFrom = flightFrom;
        this.flightTo = flightTo;
        this.type = type;
        this.date = date;
        this.classType = classType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getFlightFrom() {
        return flightFrom;
    }

    public void setFlightFrom(String flightFrom) {
        this.flightFrom = flightFrom;
    }
    public String getFlightTo() {
        return flightTo;
    }

    public void setFlightTo(String flightTo) {
        this.flightTo = flightTo;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }
    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }
}
