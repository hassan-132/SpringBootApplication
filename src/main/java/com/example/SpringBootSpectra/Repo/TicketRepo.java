package com.example.SpringBootSpectra.Repo;

import com.example.SpringBootSpectra.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TicketRepo extends JpaRepository<Ticket,Integer> {

}
