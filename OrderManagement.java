import java.util.*;

public class OrderManagement {

    private static final int MAX_ORDERS_PER_SECOND = 3;
    private int currentSecondOrderCount = 0;
    private long currentSecond = System.currentTimeMillis() / 1000;

    private final int START_HOUR = 10;
    private final int END_HOUR = 13;

    public void onData(Request request) {
        if (!isWithinTimeWindow()) {
            System.out.println("Order rejected: Outside allowed time window.");
            return;
        }

        long now = System.currentTimeMillis() / 1000;
        if (now != currentSecond) {
            currentSecond = now;
            currentSecondOrderCount = 0;
        }

        if (currentSecondOrderCount < MAX_ORDERS_PER_SECOND && request.requestType == Request.RequestType.New) {
            send(request);
            currentSecondOrderCount++;
        } else {
            System.out.println("Order not sent: rate limit or unsupported type.");
        }
    }

    private void send(Request request) {
        System.out.println("Order sent to exchange: " + request.m_orderId);
    }

    private boolean isWithinTimeWindow() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Kolkata"));
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour >= START_HOUR && hour < END_HOUR;
    }

    public static void main(String[] args) {
        OrderManagement om = new OrderManagement();

        for (int i = 1; i <= 5; i++) {
            Request order = new Request(101, 100.0 + i, 10 + i, 'B', i, Request.RequestType.New);
            om.onData(order);
        }
    }
}
