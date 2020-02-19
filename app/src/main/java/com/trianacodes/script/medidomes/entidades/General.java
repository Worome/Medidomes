package com.trianacodes.script.medidomes.entidades;

public class General {

    private String Id, Medicina, Uso, FechaInicio;
    private Integer Duracion, Posologia, Aplicaciones;

    public General(String id, String medicina, String uso, String fechaInicio, Integer duracion,
                   Integer posologia, Integer aplicaciones) {

        Id = id;
        Medicina = medicina;
        Uso = uso;
        FechaInicio = fechaInicio;
        Duracion = duracion;
        Posologia = posologia;
        Aplicaciones = aplicaciones;

    }

    public General() {

        Id = "";
        Medicina = "";
        Uso = "";
        FechaInicio = "";
        Duracion = 0;
        Posologia = 0;
        Aplicaciones = 0;

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getMedicina() {
        return Medicina;
    }

    public void setMedicina(String medicina) {
        Medicina = medicina;
    }

    public String getUso() {
        return Uso;
    }

    public void setUso(String uso) {
        Uso = uso;
    }

    public String getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public Integer getDuracion() {
        return Duracion;
    }

    public void setDuracion(Integer duracion) {
        Duracion = duracion;
    }

    public Integer getPosologia() {
        return Posologia;
    }

    public void setPosologia(Integer posologia) {
        Posologia = posologia;
    }

    public Integer getAplicaciones() {
        return Aplicaciones;
    }

    public void setAplicaciones(Integer aplicaciones) {
        Aplicaciones = aplicaciones;
    }
}
