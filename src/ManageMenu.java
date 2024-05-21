import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManageMenu {
	String url = "jdbc:mysql://localhost:3306/teamproject";

	public boolean insertPassenger(int passengerID, String firstName, String lastName, String email, String passportNumber){
		Connection conn = null;
		PreparedStatement pstmt = null;

		try{
// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

// 2. 연결하기
			conn = DriverManager.getConnection(url, "root", "root");

// 3. SQL 쿼리 준비
			String sql = "INSERT INTO passenger (PassengerID, FirstName, LastName, Email, PassportNumber) VALUES (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);


// 4. 데이터 binding
			pstmt.setInt(1, passengerID);
			pstmt.setString(2, firstName);
			pstmt.setString(3, lastName);
			pstmt.setString(4, email);
			pstmt.setString(5, passportNumber);


// 5. 쿼리 실행 및 결과 처리
			int count = pstmt.executeUpdate();
			if( count == 0 ){
				System.out.println("데이터 입력에 실패했습니다.");
				return false;
			}
			else{
				System.out.println("입력이 완료되었습니다.");
				return true;
			}
		}
		catch( ClassNotFoundException e){
			System.out.println("드라이버 로딩 실패");
			return false;
		}
		catch( SQLException e){
			System.out.println("에러 " + e);
			return false;
		}
		finally{
			try{
				if( conn != null && !conn.isClosed()){
					conn.close();
				}
				if( pstmt != null && !pstmt.isClosed()){
					pstmt.close();
				}
			}
			catch( SQLException e){
				e.printStackTrace();
			}
		}
	}

	public boolean deletePassenger(String firstName, String lastName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql;
		try {
			conn = DriverManager.getConnection(url, "root", "root");
			sql = "DELETE FROM Passenger WHERE firstName = ? and lastName = ? ;";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);

			int count = pstmt.executeUpdate();
			if (count == 0) {
				System.out.println("Fail : No value");
				return false;
			}

		} catch (SQLException e) {
			System.out.println("Fail : SQL error");
			return true;
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;

	}

}
