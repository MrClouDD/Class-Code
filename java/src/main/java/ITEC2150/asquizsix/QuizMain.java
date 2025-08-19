package ITEC2150.asquizsix;


public class QuizMain {
    public static void main(String[] args) {
        CoinStack st = new CoinStack();
        st.push(new Coin("penny"));
        st.push(new Coin("nickel"));
        st.push(new Coin("dime"));
        st.push(new Coin("quarter"));
        while (!st.isEmpty()) {
            System.out.println(st.pop().getCoinName());
        }
    }
}
