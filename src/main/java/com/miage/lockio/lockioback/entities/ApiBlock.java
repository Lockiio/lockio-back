package com.miage.lockio.lockioback.entities;

import com.miage.lockio.lockioback.enums.BlockStatus;
import com.miage.lockio.lockioback.enums.Hours;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.geo.Point;


@Getter
@Setter
public class ApiBlock {

    private Long id;

    private String name;

    private Point coordinate;

    private BlockStatus status;

    private Hours hours;

    public ApiBlock(Block block) {
        this.id = block.getId();
        this.name = block.getName();
        this.coordinate = block.getCoordinate();
        this.status = block.getStatus();
        this.hours = Hours.EVERYTIME;

    }
}
