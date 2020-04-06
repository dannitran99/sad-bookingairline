package io.github.tubean.eureka.gallery.entity;

import java.util.List;

public class Gallery {
    private List<Object> tickets;
 
    public Gallery( List<Object> tickets) {
        this.tickets = tickets;
    }

  
    public Gallery() {
    	
    }
    public List<Object> gettickets() {
        return tickets;
    }

    public void setTickets(List<Object> tickets) {
        this.tickets = tickets;
    }
 
}
