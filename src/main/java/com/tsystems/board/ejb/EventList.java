package com.tsystems.board.ejb;

import com.tsystems.board.ejb.dto.Event;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Singleton
public class EventList {

   public List<Event> events = new ArrayList<>();
}
