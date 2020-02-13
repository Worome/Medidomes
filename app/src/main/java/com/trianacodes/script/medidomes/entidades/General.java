package com.trianacodes.script.medidomes.entidades;

public class General {

    private String Id, Medicamento, Uso, FechaInicio;
    private Integer Duracion, Posologia, Aplicacicones;

    public General(String id, String medicamento, String uso, String fechaInicio,
                   Integer duracion, Integer posologia, Integer aplicacicones) {

        Id = id;
        Medicamento = medicamento;
        Uso = uso;
        FechaInicio = fechaInicio;
        Duracion = duracion;
        Posologia = posologia;
        Aplicacicones = aplicacicones;

    }

    public General() {

        Id = "";
        Medicamento = "";
        Uso = "";
        FechaInicio = "";
        Duracion = 0;
        Posologia = 0;
        Aplicacicones = 0;

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getMedicamento() {
        return Medicamento;
    }

    public void setMedicamento(String medicamento) {
        Medicamento = medicamento;
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

    public Integer getAplicacicones() {
        return Aplicacicones;
    }

    public void setAplicacicones(Integer aplicacicones) {
        Aplicacicones = aplicacicones;
    }
}
