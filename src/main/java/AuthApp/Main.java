//package AuthApp;
//
//import java.io.IOException;
//
//public class Main {
//    public static void main(String args[]) throws IOException {
//        testSystem();
//    }
//
//    private static void testSystem() throws IOException {
//        Client client = new Client();
//
//        testRegistration(client);
//        testLogin(client);
//        testUpdate(client);
//        testDelete(client);
//    }
//
//    private static void testRegistration(Client client) {
//        boolean success;
//
//        System.out.println(">>>>>>>>> Registration of new users");
//        try {
//            success = client.register("ranysaliman@gmail.com", "Rany Saliman", "1234As");
//            success = client.register("shilat@gmail.com", "Shilat Orakbi", "987Nl11");
//            success = client.register("dalia@gmail.com", "Dalia William", "El9622");
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    private static void testLogin(Client client) {
//        System.out.println(">>>>>>>>> Login of ranysaliman@gmail.com");
//        try {
//            client.login("ranysaliman@gmail.com", "1234As");
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    private static void testUpdate(Client client) {
//        boolean success;
//
//        System.out.println(">>>>>>>>> Updating user name: Rany Saliman -> Niss Saliman");
//        try {
//            success = client.updateUserName("ranysaliman@gmail.com", "Niss Saliman");
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        System.out.println(">>>>>>>>> Updating user email: \"ranysaliman@gmail.com\" -> \"nissimsaliman@gmail.com\"");
//        try {
//            success = client.updateUserEmail("ranysaliman@gmail.com", "nissimsaliman@gmail.com");
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        System.out.println(">>>>>>>>> Updating user's password: 1234As -> TrustNo1");
//        try {
//            success = client.updateUserPassword("nissimsaliman@gmail.com", "TrustNo1");
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    private static void testDelete(Client client) {
//        boolean success;
//
//        System.out.println(">>>>>>>>> Deleting user nissimsaliman@gmail.com");
//        try {
//            success = client.deleteUser("nissimsaliman@gmail.com");
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//}
