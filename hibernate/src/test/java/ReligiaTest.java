import com.mielewczykl.hibernate.model.Religia;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Administrator on 2015-12-15.
 */
public class ReligiaTest {

    Religia religia = new Religia();
    @Test
    public void sprawdzPolaczenie()
    {
        assertNotNull(religia.polaczenie());
    }
}
