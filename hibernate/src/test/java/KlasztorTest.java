import com.mielewczykl.hibernate.model.domain.Klasztor;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Administrator on 2015-12-15.
 */
public class KlasztorTest {

    Klasztor klasztor = new Klasztor();
    @Test
    public void sprawdzPolaczenie()
    {
        assertNotNull(klasztor.polaczenie());
    }
}
