package com.miage.lockio.lockioback.enums;

import lombok.Getter;

@Getter

public enum Horaires {

    EVERYDAYS("24H/24",null,"everydays",null);


    String heuresOuverture;
    String heuresFermeture;

    String joursOuverture;

    String joursFermetures;

    Horaires(String heuresOuverture,String heuresFermeture,String joursOuverture,String joursFermetures) {
        this.heuresOuverture = heuresOuverture;
        this.heuresFermeture=heuresFermeture;
        this.joursOuverture=joursOuverture;
        this.joursFermetures=joursFermetures;
    }
}
