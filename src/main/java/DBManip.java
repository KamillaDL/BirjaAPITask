import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManip {
    private static final Logger log = Logger.getLogger(ExmoAPI.class);
    public static void SaveToDB(ExmoModel model, Kurs kurs) {
        if(model != null) {
            Connection connection = null;
            try {
                connection = DBConnector.getConnection();
            } catch (IOException e) {
                log.error("IOException");
                e.printStackTrace();
            }
            String sql = "insert into exmo(valute, ask_amount, ask_top, bid_quantity, bid_amount, bid_top) values(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, kurs.toString());
                preparedStatement.setDouble(2, model.getAsk_amount());
                preparedStatement.setDouble(3, model.getAsk_top());
                preparedStatement.setDouble(4, model.getBid_quantity());
                preparedStatement.setDouble(5, model.getBid_amount());
                preparedStatement.setDouble(6, model.getBid_top());
                preparedStatement.executeUpdate();
                connection.close();
            } catch (SQLException e) {
                log.error("SQLException");
                e.printStackTrace();
            }
        }
    }

    public static List<ExmoModel> SelectFunc() {
        List<ExmoModel> exmoModelList = new ArrayList<ExmoModel>();
        Connection connection = null;
        try {
            connection = DBConnector.getConnection();
        } catch (IOException e) {
            log.error("IOException");
            e.printStackTrace();
        }
        try {
            Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY
                );
            ResultSet rs = null;
            rs = statement.executeQuery("select * from exmo");

            while (rs.next()) {
                ExmoModel exmoModel = new ExmoModel();
                exmoModel.setValute(rs.getString("valute"));
                exmoModel.setAsk_amount(rs.getDouble("ask_amount"));
                exmoModel.setAsk_top(rs.getDouble("ask_top"));
                exmoModel.setBid_quantity(rs.getDouble("bid_quantity"));
                exmoModel.setBid_amount(rs.getDouble("bid_amount"));
                exmoModel.setBid_top(rs.getDouble("bid_top"));
                exmoModelList.add(exmoModel);
            }
            connection.close();
        }
        catch (SQLException e) {
            log.error("SQLException");
            e.printStackTrace();
        }
        return exmoModelList;
    }
}
