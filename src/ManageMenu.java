import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class ManageMenu {
	static String url = "jdbc:mysql://localhost:3306/teamproject";
	static String user = "root";
    static String password = "root";
	
	public static String bookingSeats(int passengerID, String flightID, String seatNumber, String paymentStatus) {
		String res;
		try(Connection conn = DriverManager.getConnection(url, user, password)){
			if  (isValidFlightSeat(conn, flightID, seatNumber) && !isSeatBooked(conn, flightID, seatNumber)) {
				int bookingID = generateUniqueBookingID(conn);
				insertBooking(conn, bookingID, flightID, seatNumber, passengerID, paymentStatus);
				res = "Booking successfully! BookingID:"+ bookingID;
				return res;
			}
			else {
				res = "Invalid Flight ID, seatNumber or seat was already booked";
				return res;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "Fail";
		}
				
	}
	
	private static boolean isValidFlightSeat(Connection conn, String flightID, String seatNumber) throws SQLException {
		String query = "SELECT COUNT(*) FROM Seats WHERE FlightID = ? AND SeatNumber = ?;";
		try (PreparedStatement stmt = conn.prepareStatement(query)){
			stmt.setString(1, flightID);
			stmt.setString(2, seatNumber);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			return rs.getInt(1) > 0;
		}
	}
	
	private static boolean isSeatBooked(Connection conn, String flightID, String seatNumber) throws SQLException {
        String query = "SELECT COUNT(*) FROM Booking WHERE FlightID = ? AND SeatNumber = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, flightID);
            stmt.setString(2, seatNumber);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        }
    }
	
	private static int generateUniqueBookingID(Connection conn) throws SQLException {
		Random random = new Random();
		int bookingID;
		do {
			bookingID = random.nextInt(1000000);
		} while (isBookingIDExists(conn, bookingID));
		return bookingID;
	}
	
	private static boolean isBookingIDExists(Connection conn, int bookingID) throws SQLException {
        String query = "SELECT COUNT(*) FROM Booking WHERE BookingID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookingID);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        }
    }

    private static void insertBooking(Connection conn, int bookingID, String flightID, String seatNumber, int passengerID, String paymentStatus) throws SQLException {
        String query = "INSERT INTO Booking (BookingID, FlightID, SeatNumber, PassengerID, PaymentStatus) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookingID);
            stmt.setString(2, flightID);
            stmt.setString(3, seatNumber);
            stmt.setInt(4, passengerID);
            stmt.setString(5, paymentStatus);
            stmt.executeUpdate();
        }
    }
	
	public static String selectFlightAggregation(String originCode) {
		
	    
	    String query = "SELECT OriginAirportCode, COUNT(*) AS FlightCount FROM Flight WHERE OriginAirportCode = ? GROUP BY OriginAirportCode;";
	    String res;

	    try (Connection conn = DriverManager.getConnection(url, user, password);
	         PreparedStatement pstmt = conn.prepareStatement(query)) {

	        pstmt.setString(1, originCode);

	        try (ResultSet rs = pstmt.executeQuery()) { // 매개변수 없이 executeQuery() 호출
	            if (rs.next()) {
	                String origin = rs.getString("OriginAirportCode");
	                int flightCount = rs.getInt("FlightCount");
	                res = "origin: " + origin + ", Number of Flights: " + flightCount + "\n";
	                System.out.print(res);
	                return res;
	            } else {
	                res = "No flights found.";
	                return res;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "Fail";
	    }
    }

	
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
	
	public static String updatePaymentStatus(String firstName, String lastName) {
		//passenger 안에 paymentStatus가 없는데 왜 SELECT문에 썼는지 + 동명 이인 고려
		//select를 진행할 필요가 없는 듯.
		String selectQuery = "SELECT PaymentStatus FROM Passenger WHERE FirstName = ? AND LastName = ?;";
		String updateQuery = "UPDATE Passenger SET PaymentStatus = 'paid' WHERE FirstName = ? AND LastName = ?;";
		String res;
		try(Connection conn = DriverManager.getConnection(url, user, password);
			PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
			PreparedStatement updateStmt = conn.prepareStatement(updateQuery)){
			
			selectStmt.setString(1, firstName);
			selectStmt.setString(2, lastName);
			ResultSet rs = selectStmt.executeQuery();
			
			if(rs.next()) {
				String paymentStatus = rs.getString("PaymentStatus");
				
				if("unpaid".equalsIgnoreCase(paymentStatus)) {
					updateStmt.setString(1, firstName);
					updateStmt.setString(2, lastName);
					updateStmt.executeUpdate();
					res = "Payment status updated to paid.";
					return res;
				} else {
					res = "Payment status is already paid.";
					return res;
				}
			} else {
				res = "No passenger found.";
				return res;
			}
		}catch(SQLException e){
			e.printStackTrace();
			res = "fail to update";
			return res;
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
