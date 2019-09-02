package br.com.havensteinsolutions.agenda.Agenda.Infra.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.havensteinsolutions.agenda.Agenda.Infra.Dao.AlunoDAO;
import br.com.havensteinsolutions.agenda.Agenda.Infra.database.converter.ConversorCalendar;
import br.com.havensteinsolutions.agenda.Agenda.modelo.Aluno;

import static br.com.havensteinsolutions.agenda.Agenda.Infra.database.AgendaMigrations.TODAS_MIGRATIONS;

@Database(entities = {Aluno.class}, version = 4, exportSchema = false)
@TypeConverters({ConversorCalendar.class})
public abstract class AgendaDatabase extends RoomDatabase {

    public static final String NOME_DO_BANCO_DE_DADOS = "agenda.db";

    private static AgendaDatabase instance;

    public abstract AlunoDAO getRoomAlunoDAO();

    public static AgendaDatabase getInstance(Context contexto) {
        if (instance == null) {
            instance = Room.databaseBuilder(contexto, AgendaDatabase.class, NOME_DO_BANCO_DE_DADOS)
                    .allowMainThreadQueries()
                    .addMigrations(TODAS_MIGRATIONS)
                    .build();
        }
        return instance;
    }
}
