public class Response {
     public long m_orderId;               
    public ResponseType m_responseType; 

    public enum ResponseType {
        Unknown,Accept,Reject
    }  

    // Constructor 
    public Response(long orderId, ResponseType responseType) {
        this.m_orderId = orderId;
        this.m_responseType = responseType;
    }
    public Response(){};

}

