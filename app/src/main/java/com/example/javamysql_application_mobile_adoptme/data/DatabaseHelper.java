package com.example.javamysql_application_mobile_adoptme.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "adoptme_local.db";
    private static final int DATABASE_VERSION = 2; // 🔹 nuevo número de versión

    // Tabla de usuarios
    private static final String TABLE_USERS = "usuarios";
    private static final String COL_ID = "id";
    private static final String COL_NOMBRE = "nombre";
    private static final String COL_APELLIDO = "apellido";
    private static final String COL_DNI = "dni";
    private static final String COL_CORREO = "correo";
    private static final String COL_TELEFONO = "telefono";
    private static final String COL_CONTRASENA = "contrasena";

    // Tabla de mascotas
    private static final String TABLE_MASCOTAS = "mascotas";
    private static final String M_ID = "id_mascota";
    private static final String M_NOMBRE = "nombre";
    private static final String M_ESPECIE = "especie";
    private static final String M_RAZA = "raza";
    private static final String M_EDAD = "edad";
    private static final String M_TAMANO = "tamano";
    private static final String M_SEXO = "sexo";
    private static final String M_DESCRIPCION = "descripcion";
    private static final String M_FOTO = "foto_url";
    private static final String M_FAVORITO = "favorito"; // 0 = no, 1 = sí

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tabla usuarios
        String createUsers = "CREATE TABLE " + TABLE_USERS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NOMBRE + " TEXT, " +
                COL_APELLIDO + " TEXT, " +
                COL_DNI + " TEXT UNIQUE, " +
                COL_CORREO + " TEXT UNIQUE, " +
                COL_TELEFONO + " TEXT, " +
                COL_CONTRASENA + " TEXT)";
        db.execSQL(createUsers);

        // Tabla mascotas
        String createPets = "CREATE TABLE " + TABLE_MASCOTAS + " (" +
                M_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                M_NOMBRE + " TEXT, " +
                M_ESPECIE + " TEXT, " +
                M_RAZA + " TEXT, " +
                M_EDAD + " TEXT, " +
                M_TAMANO + " TEXT, " +
                M_SEXO + " TEXT, " +
                M_DESCRIPCION + " TEXT, " +
                M_FOTO + " TEXT, " +
                M_FAVORITO + " INTEGER DEFAULT 0)";
        db.execSQL(createPets);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MASCOTAS);
        onCreate(db);
    }


    // USUARIOS


    public boolean insertUser(String nombre, String apellido, String dni, String correo,
                              String telefono, String contrasena) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NOMBRE, nombre);
        values.put(COL_APELLIDO, apellido);
        values.put(COL_DNI, dni);
        values.put(COL_CORREO, correo);
        values.put(COL_TELEFONO, telefono);
        values.put(COL_CONTRASENA, contrasena);

        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result != -1;
    }

    public boolean userExists(String correo) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{COL_ID},
                COL_CORREO + "=?",
                new String[]{correo},
                null, null, null);
        boolean exists = cursor.moveToFirst();
        cursor.close();
        db.close();
        return exists;
    }


    // Mascotas


    public boolean insertPet(String nombre, String especie, String raza, String edad,
                             String tamano, String sexo, String descripcion, String fotoUrl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(M_NOMBRE, nombre);
        values.put(M_ESPECIE, especie);
        values.put(M_RAZA, raza);
        values.put(M_EDAD, edad);
        values.put(M_TAMANO, tamano);
        values.put(M_SEXO, sexo);
        values.put(M_DESCRIPCION, descripcion);
        values.put(M_FOTO, fotoUrl);

        long result = db.insert(TABLE_MASCOTAS, null, values);
        db.close();
        return result != -1;
    }

    // Obtener todas las mascotas
    public List<String> getAllPets() {
        List<String> pets = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nombre FROM " + TABLE_MASCOTAS, null);

        if (cursor.moveToFirst()) {
            do {
                pets.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return pets;
    }

    // Marcar mascota como favorita
    public boolean setFavorite(int idMascota, boolean isFavorite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(M_FAVORITO, isFavorite ? 1 : 0);
        int result = db.update(TABLE_MASCOTAS, values, M_ID + "=?", new String[]{String.valueOf(idMascota)});
        db.close();
        return result > 0;
    }

    //  Obtener solo las mascotas favoritas
    public List<String> getFavoritePets() {
        List<String> favorites = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nombre FROM " + TABLE_MASCOTAS + " WHERE favorito=1", null);

        if (cursor.moveToFirst()) {
            do {
                favorites.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return favorites;
    }

    // Borrar todas las mascotas
    public void clearPets() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_MASCOTAS);
        db.close();
    }
}
