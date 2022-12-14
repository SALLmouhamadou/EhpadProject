package fr.ehpad.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;

import fr.ehpad.entity.Medicament;
import fr.ehpad.entity.Prescription;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class MedicamentDao {
	public static Medicament getByID(Integer idMedic) throws SQLException {
		// ouvrir un canal de requettage.
		Connection con = Database.getConnection();
		String sql = "SELECT * FROM medicament WHERE id_medicament =?;";
		PreparedStatement state = con.prepareCall(sql);
		state.setInt(1, idMedic);
		ResultSet res = state.executeQuery();
		res.next();
		Integer idMedicament = res.getInt("id_medicament");
		String nom = res.getString("nom");
		String fonction = res.getString("fonction");
		Integer stock = res.getInt("stock");

		Medicament medic = new Medicament(idMedicament, nom, fonction, stock);

		return medic;
	}
	
	public static Medicament getByNom(String nom) throws SQLException {
		// ouvrir un canal de requettage.
		Connection con = Database.getConnection();
		String sql = "SELECT * FROM medicament WHERE nom=?;";
		PreparedStatement state = con.prepareCall(sql);
		state.setString(1, nom);
		ResultSet res = state.executeQuery();
		res.next();
		Integer idMedicament = res.getInt("id_medicament");
		String nomMedicament = res.getString("nom");
		String fonction = res.getString("fonction");
		Integer stock = res.getInt("stock");

		Medicament medic = new Medicament(idMedicament, nomMedicament, fonction, stock);

		return medic;
	}
	
	@Test
    public void testConnection() {
		try {
			assertNotNull(Database.getConnection());
			Medicament medic = this.getByID(1);
			assertNotNull(medic);
			assertTrue(medic.getId_medicament() == 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
