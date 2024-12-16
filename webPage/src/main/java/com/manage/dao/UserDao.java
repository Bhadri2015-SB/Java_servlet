package com.manage.dao;

import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.manage.bean.User;

public class UserDao {

	public static int save(User bean) {
		int status = 0;
		Connection connection = DB.getConnection();
		try (PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO users (username, password, mail, company, is_verified, token, profile) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
			InputStream is = new FileInputStream(new File("C:\\Users\\bhadr\\eclipse-workspace(new)\\webPage\\build\\classes\\Image\\defaultt.png"));
			statement.setString(1, bean.getName());
			statement.setString(2, bean.getPassword());
			statement.setString(3, bean.getEmail());
			statement.setString(4, bean.getCompany());
			statement.setBoolean(5, false);
			statement.setString(6, bean.getToken());
			statement.setBlob(7, is);
			status = statement.executeUpdate();
//            DB.close();
			sendVerificationEmail(bean.getEmail(), bean.getToken());
		} catch (SQLException | FileNotFoundException e) {
			e.printStackTrace();
//		}finally {
//			DB.close();
		}
		return status;
	}

	private static void sendVerificationEmail(String recipientEmail, String token) {
		String subject = "Email Verification";
		String body = "Please verify your account by clicking the link below:\n"
				+ "http://localhost:8080/webPage/verify?token=" + token;

		// Set up the email server properties
		String fromEmail = "";
		String emailPassword = "";
		String smtpHost = "smtp.gmail.com";
		String smtpPort = "587";

		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", smtpHost);
		properties.put("mail.smtp.port", smtpPort);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, emailPassword);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);
			System.out.println("Verification email sent to " + recipientEmail);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	public static boolean athentication(String mail, String password) {
		boolean status = false;
		Connection connection = DB.getConnection();
		try (PreparedStatement statement = connection
				.prepareStatement("SELECT * FROM users WHERE mail = ? AND password = ?")) {
			statement.setString(1, mail);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				status = resultSet.getBoolean("is_verified");
			}

		} catch (SQLException e) {
			e.printStackTrace();
//		}finally {
//			DB.close();
		}
		return status;
	}

	public static int verifyMail(String token) {
		int status = 0;
		Connection connection = DB.getConnection();
		try (PreparedStatement statement = connection
				.prepareStatement("UPDATE users SET is_verified = true WHERE token = ?")) {
			statement.setString(1, token);
			status = statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
//		}finally {
//			DB.close();
		}
		return status;
	}

	public static User myDetail(String email) {
		User bean = new User();
		Connection connection = DB.getConnection();
		try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE mail=?")) {
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			rs.next();
//			int id=rs.getInt("id");
			bean.setId(rs.getInt("id"));
			bean.setName(rs.getString("username"));
			bean.setEmail(rs.getString("mail"));
//			bean.setPassword(rs.getString("password"));
			bean.setCompany(rs.getString("company"));
//			System.out.println(id+" "+rs.getString("company"));
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
		try (PreparedStatement statement = connection
				.prepareStatement("UPDATE users SET password = ? WHERE mail = ?")) {
			statement.setString(1, password);
			statement.setString(2, mail);
			status = statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
//		}finally {
//			DB.close();uvflwjpidrvtuewp
		}
		return status;
	}

	public static List<User> viewAllUsers(String mail) {
		List<User> list = new ArrayList<User>();
		Connection connection = DB.getConnection();
		try  {
			PreparedStatement statement1 = connection.prepareStatement("SELECT company FROM admins WHERE mail=?");
			statement1.setString(1, mail);
			ResultSet rs1 = statement1.executeQuery();
			rs1.next();
			String com=rs1.getString("company");
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE company=?");
			statement.setString(1, com);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				User bean = new User();
				bean.setId(rs.getInt("id"));
				bean.setName(rs.getString("username"));
				bean.setEmail(rs.getString("mail"));
				bean.setPassword(rs.getString("password"));
				bean.setCompany(rs.getString("company"));
//				bean.setPic(rs.getBlob("profile"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static int delete(int id) {
		int status=0;
		try{
			Connection con=DB.getConnection();
			PreparedStatement ps=con.prepareStatement("DELETE FROM users WHERE id=?");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}

	public static int updatePic(String mail, InputStream is) {
		int status = 0;
		Connection connection = DB.getConnection();
		try (PreparedStatement statement = connection
				.prepareStatement("UPDATE users SET profile = ? WHERE mail = ?")) {
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
		byte[] img = null;
		Connection connection = DB.getConnection();
		try (PreparedStatement statement = connection.prepareStatement("SELECT profile FROM users WHERE mail = ?")) {
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

	public static byte[] getIteImage(int id) {
		byte[] img = null;
		Connection connection = DB.getConnection();
		try (PreparedStatement statement = connection.prepareStatement("SELECT profile FROM users WHERE id = ?")) {
            statement.setInt(1, id);
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
