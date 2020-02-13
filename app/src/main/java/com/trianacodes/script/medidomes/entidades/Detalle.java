package com.trianacodes.script.medidomes.entidades;

public class Detalle {

    private String Id_General;
    private Integer Tablas, Dia, Hora, SiNo;

    public Detalle(String id_General, Integer tablas, Integer dia, Integer hora, Integer siNo) {
        Id_General = id_General;
        Tablas = tablas;
        Dia = dia;
        Hora = hora;
        SiNo = siNo;
    }

    public Detalle() {
        Id_General = "";
        Tablas = 0;
        Dia = 0;
        Hora = 0;
        SiNo = 0;
    }

    public String getId_General() {
        return Id_General;
    }

    public void setId_General(String id_General) {
        Id_General = id_General;
    }

    public Integer getTablas() {
        return Tablas;
    }

    public void setTablas(Integer tablas) {
        Tablas = tablas;
    }

    public Integer getDia() {
        return Dia;
    }

    public void setDia(Integer dia) {
        Dia = dia;
    }

    public Integer getHora() {
        return Hora;
    }

    public void setHora(Integer hora) {
        Hora = hora;
    }

    public Integer getSiNo() {
        return SiNo;
    }

    public void setSiNo(Integer siNo) {
        SiNo = siNo;
    }

}
