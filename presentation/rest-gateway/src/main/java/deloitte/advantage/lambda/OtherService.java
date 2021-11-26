package deloitte.advantage.lambda;

/**
 * Illustrates a pattern when the rest gateway immediately delegates to a service handling the various verbs
 */
public class OtherService {

    public void onPost(Integer id) {
        System.out.println("p=" + id);
    }
}
