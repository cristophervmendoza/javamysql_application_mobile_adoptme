package com.example.javamysql_application_mobile_adoptme.model;

public class DetalleSolicitud {

    private int idDetalle;
    private int idSolicitud;

    // Paso 1 - Datos personales
    private String nombreCompleto;
    private String correoElectronico;
    private String telefono;
    private String direccion;

    // Paso 2 - Hogar
    private String tipoVivienda;
    private String composicionFamiliar;
    private String ninosEnHogar;
    private String alergiasAnimales;

    // Paso 3 - Experiencia
    private String otrasMascotas;
    private String experienciaPrev;
    private String tiempoCuidado;

    // Paso 4 - Motivación
    private String motivoAdopcion;
    private String planCuidado;

    // Paso 5 - Confirmación
    private boolean aceptaTerminos;
    private boolean confirmaVeracidad;

    public DetalleSolicitud() {}

    // Getters y setters (completos)
    public int getIdDetalle() { return idDetalle; }
    public void setIdDetalle(int idDetalle) { this.idDetalle = idDetalle; }

    public int getIdSolicitud() { return idSolicitud; }
    public void setIdSolicitud(int idSolicitud) { this.idSolicitud = idSolicitud; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTipoVivienda() { return tipoVivienda; }
    public void setTipoVivienda(String tipoVivienda) { this.tipoVivienda = tipoVivienda; }

    public String getComposicionFamiliar() { return composicionFamiliar; }
    public void setComposicionFamiliar(String composicionFamiliar) { this.composicionFamiliar = composicionFamiliar; }

    public String getNinosEnHogar() { return ninosEnHogar; }
    public void setNinosEnHogar(String ninosEnHogar) { this.ninosEnHogar = ninosEnHogar; }

    public String getAlergiasAnimales() { return alergiasAnimales; }
    public void setAlergiasAnimales(String alergiasAnimales) { this.alergiasAnimales = alergiasAnimales; }

    public String getOtrasMascotas() { return otrasMascotas; }
    public void setOtrasMascotas(String otrasMascotas) { this.otrasMascotas = otrasMascotas; }

    public String getExperienciaPrev() { return experienciaPrev; }
    public void setExperienciaPrev(String experienciaPrev) { this.experienciaPrev = experienciaPrev; }

    public String getTiempoCuidado() { return tiempoCuidado; }
    public void setTiempoCuidado(String tiempoCuidado) { this.tiempoCuidado = tiempoCuidado; }

    public String getMotivoAdopcion() { return motivoAdopcion; }
    public void setMotivoAdopcion(String motivoAdopcion) { this.motivoAdopcion = motivoAdopcion; }

    public String getPlanCuidado() { return planCuidado; }
    public void setPlanCuidado(String planCuidado) { this.planCuidado = planCuidado; }

    public boolean isAceptaTerminos() { return aceptaTerminos; }
    public void setAceptaTerminos(boolean aceptaTerminos) { this.aceptaTerminos = aceptaTerminos; }

    public boolean isConfirmaVeracidad() { return confirmaVeracidad; }
    public void setConfirmaVeracidad(boolean confirmaVeracidad) { this.confirmaVeracidad = confirmaVeracidad; }
}
