import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class ManageMenu {
	// db 연결 시 필요한 url, user, password 선언
	static String url = "jdbc:mysql://localhost:3306/teamproject";
	static String user = "root";
    static String password = "root";
	
    // 좌석을 예약할 때 사용
	public static String bookingSeats(int passengerID, String flightID, String seatNumber, String paymentStatus) {
		String res; // 결과값을 저장할 변수
		// DB와 연결
		try(Connection conn = DriverManager.getConnection(url, user, password)){
			// 항공편에 존재하는 좌석이고,다른 사람이 예약하지 않은 경우에
			if  (isValidFlightSeat(conn, flightID, seatNumber) && !isSeatBooked(conn, flightID, seatNumber)) {
				// 기존 DB에 있는 bookingID와 중복되지 않는 bookingID 생성
				int bookingID = generateUniqueBookingID(conn);
				// 생성된 booking ID, 사용자로부터 입력받은 데이터를 가지고 booking 진행
				insertBooking(conn, bookingID, flightID, seatNumber, passengerID, paymentStatus);
				// booking이 잘 진행되었을 때, 성공메시지 저장
				res = "Booking successfully! BookingID:"+ bookingID;
				// 저장된 메시지 리턴
				return res;
			}
			// 예약을 진행할 수 없는 경우에
			else {
				// 에러 메시지를 저장하고 리턴함
				res = "Invalid Flight ID, seatNumber or seat was already booked";
				return res;
			}
			//예외 발생 시, Fail을 리턴
		} catch (SQLException e) {
			e.printStackTrace();
			return "Fail";
		}
				
	}
	//항공편에 존재하는 좌석인지 확인
	private static boolean isValidFlightSeat(Connection conn, String flightID, String seatNumber) throws SQLException {
		//해당 항공편과 좌석번호를 입력 받아 항공편에 좌석번호가 존재하는 개수를 알아냄
		//쿼리 작성
		String query = "SELECT COUNT(*) FROM Seats WHERE FlightID = ? AND SeatNumber = ?;";
		//쿼리 실행
		try (PreparedStatement stmt = conn.prepareStatement(query)){
			//쿼리에서 입력받은 값 대입
			stmt.setString(1, flightID);
			stmt.setString(2, seatNumber);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			// 존재하지 않으면 값이 0이므로 그 이상의 경우에는 true(항공편에 해당 좌석이 존재하는 경우, true)
			return rs.getInt(1) > 0;
		}
	}
	// 좌석을 예약하는 과정에서 이미 예약된 좌석인지 파악하는 함수
	private static boolean isSeatBooked(Connection conn, String flightID, String seatNumber) throws SQLException {
		//해당되는 항공편의 좌석이 예약되어 있는 횟수를 보여주는 쿼리 작성
        String query = "SELECT COUNT(*) FROM Booking WHERE FlightID = ? AND SeatNumber = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
        	// 쿼리에 항공편 아이디와 좌석 번호 대입
            stmt.setString(1, flightID);
            stmt.setString(2, seatNumber);
            //쿼리 실행
            ResultSet rs = stmt.executeQuery();
            rs.next();
            // 이미 좌석이 예약되어 있으면(count가 1이상) true 리턴
            return rs.getInt(1) > 0;
        }
    }
	//좌석을 예약하는 과정에서 기존 db 안에 부킹 아이디와 중복되지 않는 임의의 아이디 만드는 함수
	private static int generateUniqueBookingID(Connection conn) throws SQLException {
		Random random = new Random();
		int bookingID;
		do {
			//랜덤한 수 저장
			bookingID = random.nextInt(1000000);
		} while (isBookingIDExists(conn, bookingID)); // 저장한 아이디가 DB의 부킹 아이디와 겹치치 않을 때까지 반복
		//만든 부킹 아이디 리턴
		return bookingID;
	}
	// 매개변수로 받은 부킹 아이디와 DB의 부킹 아이디가 겹치는지 파악하는 함수 
	private static boolean isBookingIDExists(Connection conn, int bookingID) throws SQLException {
		//부킹 아이디가 booking table에 존재하는 개수를 알아내는 쿼리 작성
        String query = "SELECT COUNT(*) FROM Booking WHERE BookingID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
        	//쿼리에 입력받은 아이디 대입
            stmt.setInt(1, bookingID);
            //쿼리 실행
            ResultSet rs = stmt.executeQuery();
            rs.next();
            // 한 개 이상 존재(중복된 부킹 아이디)인 경우 true 리턴
            return rs.getInt(1) > 0;
        }
    }
	//booking table에 tuple 추가하는 함수
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
	//서연
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

	
	//연우
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
	//연우
	 public static String getPassengerBookingInfo(int passengerID){
	   	 Connection conn = null;
	   	 PreparedStatement pstmt = null;
	   	 ResultSet rs = null;
	   	 String res= null;
	   	 String sql = "SELECT Passenger.PassengerId, FirstName, LastName, Email, PassportNumber, Flight.FlightID, SeatNumber, PaymentStatus, DepartureDateTime, ArrivalDateTime "
	   			 + "FROM Passenger "
	   			 + "JOIN Booking ON Passenger.PassengerID = Booking.PassengerID "
	   			 + "JOIN Flight ON Booking.FlightID = Flight.FlightID "
	   			 + "WHERE Passenger.PassengerID = ?";

	   	 try{
	   		 Class.forName("com.mysql.jdbc.Driver");

	   		 conn = DriverManager.getConnection(url, user, password);
	   		 
	   		 pstmt = conn.prepareStatement(sql);

	   		 pstmt.setInt(1, passengerID);
	   		 rs = pstmt.executeQuery();

	   		if (rs.next()) {
	            res = "PassengerID: " + rs.getInt("PassengerID") + ",\n "
	                   + "FirstName: " + rs.getString("FirstName") + ", \n"
	                   + "LastName: " + rs.getString("LastName") + ", \n"
	                   + "Email: " + rs.getString("Email") + ", "
	                   + "PassportNumber: " + rs.getString("PassportNumber") + ", \n"
	                   + "FlightID: " + rs.getString("FlightID") + ", \n"
	                   + "SeatNumber: " + rs.getString("SeatNumber") + ", \n"
	                   + "PaymentStatus: " + rs.getString("PaymentStatus") + ", \n"
	                   + "DepartureDateTime: " + rs.getTimestamp("DepartureDateTime") + ", \n"
	                   + "ArrivalDateTime: " + rs.getTimestamp("ArrivalDateTime")+"\n";
	            return res;
	        } else {
	            res = "No data found for PassengerID: " + passengerID;
	            return res;
	        }}
	   	 catch( ClassNotFoundException e){
	   		res = "드라이버 로딩 실패";
	   		return res;
	   		 }
	   	 catch( SQLException e){
	   		 res = "에러 " + e;
	   		return res;
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
	    
	    //연우
	    public static void createFlightInfoView() {
	   	 String createView = "CREATE OR REPLACE VIEW FlightInfoView AS "
	   			 + "SELECT FlightID, DepartureDateTime, ArrivalDateTime, OriginAirportCode, DestinationAirportCode, AvailableSeats "
	   			 + "FROM Flight";
	   	 try (Connection conn = DriverManager.getConnection(url, user, password);
	   		 Statement stmt = conn.createStatement()){
	   		 stmt.executeUpdate(createView);
	   		 System.out.println("FlightInfoView 생성 완료");
	   	 } catch (SQLException e) {
	   		 e.printStackTrace();
	   	 }
	    }
	    //연우
	    public static String getFlightInfoFromView() {
	   	 String sql = "SELECT * FROM FlightInfoView";
	   	 String res = "";
	   	 try (Connection conn = DriverManager.getConnection(url, user, password);
	   		 Statement stmt = conn.createStatement();
	   			 ResultSet rs = stmt.executeQuery(sql)){
	   		 while (rs.next()) {
	   			 res += "Flight ID: " + rs.getString("FlightID")+"\n";
	   			 res += "Departure: " + rs.getTimestamp("DepartureDateTime")+"\n";
	   			 res +=  "Arrival: " + rs.getTimestamp("ArrivalDateTime")+"\n";
	   			 res += "Origin Airport Code: " + rs.getString("OriginAirportCode")+"\n";
	   			 res += "Destination Airport Code: " + rs.getString("DestinationAirportCode")+"\n";
	   			 res += "Available Seats: " + rs.getInt("AvailableSeats")+"\n";
	   			 
	   		 }
	   		 return res;
	   	 } catch (SQLException e) {
	   		 e.printStackTrace();
	   		 return "Fail";
	   	 }
	    }

	//서연
	public static String updatePaymentStatus(int passengerID) {
		String selectQuery = "SELECT PaymentStatus FROM Booking WHERE PassengerID = ?";
		String updateQuery = "UPDATE Booking SET PaymentStatus = 'paid' WHERE PassengerID = ?";
		String res;
		try(Connection conn = DriverManager.getConnection(url, user, password);
			PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
			PreparedStatement updateStmt = conn.prepareStatement(updateQuery)){
			
			selectStmt.setInt(1, passengerID);
			ResultSet rs = selectStmt.executeQuery();
			
			if(rs.next()) {
				String paymentStatus = rs.getString("PaymentStatus");
				
				if("unpaid".equalsIgnoreCase(paymentStatus)) {
					updateStmt.setInt(1, passengerID);
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
			return "Fail";
		}
				
	}

	//승객을 삭제하는 함수
	public boolean deletePassenger(int passengerID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql;
		try {
			//DB 연결
			conn = DriverManager.getConnection(url, "root", "root");
			//passenger에서 입력받은 passengerID를 통해 승객 삭제하는 sql 작성
			sql = "DELETE FROM Passenger WHERE passengerID = ? ;";
			
			pstmt = conn.prepareStatement(sql);
			// 입력받은 ID 대입
			pstmt.setInt(1, passengerID);
			
			//SQL 실행
			int count = pstmt.executeUpdate();
			//입력 받은 ID가 없을 경우에는 False 리턴
			if (count == 0) {
				System.out.println("Fail : No value");
				return false;
			}
			//SQL 에러면 catch
		} catch (SQLException e) {
			System.out.println("Fail : SQL error");
			return false;
		} finally {
			try {
				//연결 종료
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
		//정상적으로 진행되면 true 리턴
		return true;

	}

}
