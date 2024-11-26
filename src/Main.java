public class Main {
    public static void main(String[] args) throws Exception {
        Lexer lexer = new Lexer();
        lexer.tokenizeCode("+++[>+++[>++<-]");

        Parser parser = new Parser();
        parser.parseTokenQueue(lexer.getTokenQueue());
    }
}