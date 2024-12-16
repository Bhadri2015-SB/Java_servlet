package com.manage.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;

import com.manage.bean.Admin;

public class AdminDao {
	
	public static int save(Admin bean) {
		int status = 0;
		Connection connection = DB.getConnection();
		try (PreparedStatement statement = connection.prepareStatement("INSERT INTO admins (username, password, mail, company, profile) VALUES (?, ?, ?, ?, ?)")) {
			InputStream is = new FileInputStream(new File("C:\\Users\\bhadr\\eclipse-workspace(new)\\webPage\\build\\classes\\Image\\defaultt.png"));
			statement.setString(1, bean.getName());
            statement.setString(2, bean.getPassword());
            statement.setString(3, bean.getEmail());
            statement.setString(4, bean.getCompany()); 
            statement.setBlob(5, is);
            status = statement.executeUpdate();
//            DB.close();
		} catch (SQLException | FileNotFoundException e) {
			e.printStackTrace();
//		}finally {
//			DB.close();
		}
		return status;
	}

	public static boolean athentication(String mail, String password) {
		boolean status = false;
		Connection connection = DB.getConnection();
		try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM admins WHERE mail = ? AND password = ?")) {
            statement.setString(1, mail);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();    
            if(resultSet.next()){
            	status = resultSet.getBoolean("is_verified");
			}
//            DB.close();
		} catch (SQLException e) {
			e.printStackTrace();
//		}finally {
//			DB.close();
		}
		return status;
	}

	public static Admin myDetail(String email) {
		Admin bean = new Admin();
		Connection connection = DB.getConnection();
		try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM admins WHERE mail = ?")) {
            statement.setString(1, email);
            ResultSet rs=statement.executeQuery();
			rs.next();
			bean.setId(rs.getInt("id"));
			bean.setName(rs.getString("username"));
			bean.setEmail(rs.getString("mail"));
			bean.setPassword(rs.getString("password"));
			bean.setCompany(rs.getString("company"));
			
		} catch (SQLException e) {
			e.printStackTrace();
//		}finally {
//			DB.close();
		}
		return bean;
	}

	public static int updatePass(String mail, String password) {
		int status = 0;
		Connection connection = DB.getConnection();
		try (PreparedStatement statement = connection.prepareStatement("UPDATE admins SET password = ? WHERE mail = ?")) {
            statement.setString(1, password);
            statement.setString(2, mail);
            status = statement.executeUpdate();
            
		} catch (SQLException e) {
			e.printStackTrace();
//		}finally {
//			DB.close();
		}
		return status;
	}

	public static int updatePic(String mail, InputStream is) {
		int status = 0;
		Connection connection = DB.getConnection();
		try (PreparedStatement statement = connection
				.prepareStatement("UPDATE admins SET profile = ? WHERE mail = ?")) {
			statement.setBlob(1, is);
			statement.setString(2, mail);
			status = statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
//		}finally {
//			DB.close();
		}
		return status;		
	}

	public static byte[] getImage(String mail) {
//		List<Byte> img = new ArrayList<Byte>();
		byte[] img = null;
		Connection connection = DB.getConnection();
		try (PreparedStatement statement = connection.prepareStatement("SELECT profile FROM admins WHERE mail = ?")) {
            statement.setString(1, mail);
            ResultSet rs=statement.executeQuery();
			rs.next();
			Blob image=rs.getBlob("profile");
			img=image.getBytes(1, (int)image.length());
		} catch (SQLException e) {
			e.printStackTrace();
//		}finally {
//			DB.close();
		}
		return img;
	}

}
