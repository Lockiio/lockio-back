package com.miage.lockio.lockioback.entities;

import com.miage.lockio.lockioback.enums.BlockStatus;
import com.miage.lockio.lockioback.enums.Horaires;
import com.miage.lockio.lockioback.enums.Privacy;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.geo.Point;

import java.util.List;


@Getter
@Setter
public class ApiBlock {


    private Long id;

    private String name;

    private Point coordinate;

    private BlockStatus status;

    private Privacy privacy;

    private String url;

    private List<Lockio> lockio;


    private Horaires horaires;

    public ApiBlock(Block block) {
        this.id = block.getId();
        this.name = block.getName();
        this.coordinate = block.getCoordinate();
        this.status = block.getStatus();
        this.privacy = block.getPrivacy();
        this.lockio = block.getLockio();
        this.horaires=Horaires.EVERYDAYS;


    }
}
