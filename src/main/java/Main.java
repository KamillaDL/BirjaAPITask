import java.util.List;

public class Main {
    public static void main(String[] argv) {
        Kurs kurs = Kurs.BTC_USD;
        ExmoAPI exmoAPI = new ExmoAPI();
        ExmoModel exmoModel = exmoAPI.ExmoConnectAPI(kurs);
        if(exmoModel != null){
            DBManip.SaveToDB(exmoModel, kurs);
            List<ExmoModel> exmoModelList = DBManip.SelectFunc();
            for (ExmoModel em : exmoModelList){
                System.out.println(em);
            }
        }
    }
}
