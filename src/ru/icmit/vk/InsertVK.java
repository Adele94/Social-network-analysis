package ru.icmit.vk;

import java.sql.*;

public class InsertVK {
	public static void main(String[] args) {
		String url = "jdbc:postgresql://localhost:5432/finance";
		try (Connection conn = DriverManager.getConnection(url, "postgres", "post")) {
			// Statement statement = conn.createStatement();
			VkUsers h1 = new VkUsers(494345417,
					"8d1a830a90baf9e5be43886169f623a3f83cbc70b8034283341f133a7b8e9e58b019e0ac9dcd7d9bc3306");
			String userId = "10385607";
			String t = "≈никеева";
			String j = h1.getVkUser(userId).getFirstName();
			String sql = "insert into vk_test (id_vk, name) values ( ? , ? ) ";
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, userId);
			statement.setString(2, j);

			statement.executeUpdate();

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
