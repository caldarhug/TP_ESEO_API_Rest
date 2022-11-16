package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.dto.Coordonnee;
import com.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO {
	private static final Logger LOGGER = LogManager.getLogger(VilleDAOImpl.class);

	private final DAOFactory daoFactory;

	public VilleDAOImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public List<Ville> findAllVilles() {
		final String query = "SELECT * FROM ville_france";
		List<Ville> listVille = new ArrayList<>();

		try (
				Connection connexion = this.daoFactory.getConnection();
				PreparedStatement statement = connexion.prepareStatement(query);
				) {

			try(ResultSet rs = statement.executeQuery()){
				while(rs.next()) {
					Coordonnee coordonnee = new Coordonnee(rs.getString(6), rs.getString(7));
					Ville ville = new Ville(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), coordonnee);
					listVille.add(ville);
				}
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return listVille;
	}  

	public List<Ville> findVilleByPostalCode(String codePostal) {
		final String query = "SELECT * FROM ville_france WHERE Code_postal = ?";
		List<Ville> listVille = new ArrayList<>();

		try (
				Connection connexion = this.daoFactory.getConnection();
				PreparedStatement statement = connexion.prepareStatement(query);
				) {
			statement.setString(1, codePostal);

			try(ResultSet rs = statement.executeQuery()){
				while(rs.next()) {
					Coordonnee coordonnee = new Coordonnee(rs.getString(6), rs.getString(7));
					Ville ville = new Ville(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), coordonnee);
					listVille.add(ville);
				}
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return listVille;
	}

	public Ville findVilleByCodeCommune(String codeCommune) {
		final String query = "SELECT * FROM ville_france WHERE Code_commune_INSEE = ?";
		Ville ville = new Ville();

		try (
				Connection connexion = this.daoFactory.getConnection();
				PreparedStatement statement = connexion.prepareStatement(query);
				) {
			statement.setString(1, codeCommune);

			try(ResultSet rs = statement.executeQuery()){
				while(rs.next()) {
					Coordonnee coordonnee = new Coordonnee(rs.getString(6), rs.getString(7));
					ville = new Ville(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), coordonnee);
				}
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return ville;
	}

	public String addVille(String codeCommune, String nomCommune, String codePostal,
			String libelleAcheminement, String ligne, String latitude, String longitude) {

		if (libelleAcheminement == null) {
			libelleAcheminement = nomCommune;
		}
		if (ligne == null) {
			ligne = "";
		}
		if (latitude == null) {
			latitude = "";
		}
		if (longitude == null) {
			longitude = "";
		}

		String attributes = "(Code_commune_INSEE, Nom_commune, Code_postal, Libelle_acheminement, Ligne_5, Latitude, Longitude)";
		String query = "INSERT INTO ville_france" + attributes + " VALUES(?,?,?,?,?,?,?)";

		try (
				Connection connexion = daoFactory.getConnection();
				PreparedStatement preparedStatement = connexion.prepareStatement(query);
				) {
			preparedStatement.setString(1, codeCommune);
			preparedStatement.setString(2, nomCommune);
			preparedStatement.setString(3, codePostal);
			preparedStatement.setString(4, libelleAcheminement);
			preparedStatement.setString(5, ligne);
			preparedStatement.setString(6, latitude);
			preparedStatement.setString(7, longitude);

			preparedStatement.executeUpdate();
			return "La ville de " + nomCommune + " a bien été ajoutée.";
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return "Une erreur est survenue, la ville de " + nomCommune + " n'a pas été ajoutée.";
	}

	public String deleteVille(String codeCommune) {
		String query = "DELETE FROM ville_france WHERE Code_commune_INSEE = ?";

		try (
				Connection connexion = daoFactory.getConnection();
				PreparedStatement preparedStatement = connexion.prepareStatement(query);
				) {
			preparedStatement.setString(1, codeCommune);

			preparedStatement.executeUpdate();
			return "La ville dont le code commune INSEE est " + codeCommune + " a bien été supprimée.";
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return "Une erreur est survenue, la ville n'a pas été suprimée.";
	}

	public String modifyVille(String codeCommune, String nomCommune, String codePostal, String libelleAcheminement,
			String ligne, String latitude, String longitude) {
		if (libelleAcheminement == null) {
			libelleAcheminement = nomCommune;
		}
		if (ligne == null) {
			ligne = "";
		}
		if (latitude == null) {
			latitude = "";
		}
		if (longitude == null) {
			longitude = "";
		}

		String query = "UPDATE ville_france "
				+ "SET Nom_commune = ?, Code_postal = ?, Libelle_acheminement = ?, Ligne_5 = ?, Latitude = ?, Longitude = ? "
				+ "WHERE Code_commune_INSEE = ?";

		try (
				Connection connexion = daoFactory.getConnection();
				PreparedStatement preparedStatement = connexion.prepareStatement(query);
				) {
			preparedStatement.setString(1, nomCommune);
			preparedStatement.setString(2, codePostal);
			preparedStatement.setString(3, libelleAcheminement);
			preparedStatement.setString(4, ligne);
			preparedStatement.setString(5, latitude);
			preparedStatement.setString(6, longitude);
			preparedStatement.setString(7, codeCommune);

			preparedStatement.executeUpdate();
			return "La ville de " + nomCommune + " a bien été modifiée.";
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return "Une erreur est survenue, la ville de " + nomCommune + " n'a pas été modifiée.";
	}
}
