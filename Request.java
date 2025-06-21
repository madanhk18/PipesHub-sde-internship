   public class Request {
    public int m_symbolId;
    public double m_price;
    public long m_qty;
    public char m_side; 
    public long m_orderId;

    public enum RequestType {
        Unknown,New,Modify,Cancel
    }

    public RequestType requestType;


    // Constructor with all the fields that I defined above
    public Request(int symbolId, double price, long qty, char side, long orderId, RequestType requestType) {
        this.m_symbolId = symbolId;
        this.m_price = price;
        this.m_qty = qty;
        this.m_side = side;
        this.m_orderId = orderId;
        this.requestType = requestType;
    }
    public Request() {}
   
}